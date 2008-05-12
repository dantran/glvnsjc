package org.glvnsjc.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.hibernate.Session;

import org.glvnsjc.model.hibernate.SessionUtil;

import com.glvnsjc.test.TestCase;

/**
 * @author dtran
 *
 */
public class StudentTest
    extends TestCase
{
    // need to be static since each testCase instantiate a new StudentTest object
    private static Integer studentId1;

    private static Integer studentId2;

    private static Integer studentId3;

    private static Integer schoolId;

    /**
     * This test how minimal requirements to create a student
     * @throws Exception
     */
    public void testAddMinimalStudent()
        throws Exception
    {
        Student student1 = new Student();
        student1.setGender( Gender.MALE );
        studentId1 = StudentIdGen.getInstance().nextStudentId();
        student1.setId( studentId1 );

        try
        {
            Session session = SessionUtil.begin();
            session.save( student1 );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public void testAddStudentWithSchoolYearInOneSession()
        throws Exception
    {
        Student student2 = new Student();
        student2.setGender( Gender.FEMALE );
        studentId2 = StudentIdGen.getInstance().nextStudentId();
        student2.setId( studentId2 );
        School school = new School( "Long", "S", new Address() );
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setSchool( school );
        schoolYear.setStudent( student2 );
        schoolYear.setYear( 2005 );
        schoolYear.setGiaolyClass( new SchoolClass() );
        schoolYear.setVietnguClass( new SchoolClass() );
        Set set = new HashSet();
        set.add( schoolYear );
        student2.setSchoolYears( set );

        try
        {
            Session session = SessionUtil.begin();
            session.save( school );
            schoolId = school.getId();
            session.save( student2 );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public void testAddStudentWithSchoolYearInTwoSessions()
        throws Exception
    {
        Student student3 = new Student();
        student3.setGender( Gender.FEMALE );
        studentId3 = StudentIdGen.getInstance().nextStudentId();
        student3.setId( studentId3);
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setStudent( student3 );
        schoolYear.setYear( 2005 );
        schoolYear.setGiaolyClass( new SchoolClass() );
        schoolYear.setVietnguClass( new SchoolClass() );

        try
        {
            Session session = SessionUtil.begin();
            session.save( student3 );
            SessionUtil.end();
            
            session = SessionUtil.begin();
            School school = (School) session.load( School.class, schoolId );
            schoolYear.setSchool( school );
            session.save( schoolYear );            
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public void testModifyStudent()
        throws Exception
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        assertNotNull( studentId1 );
        Student student1 = (Student) session.load( Student.class, studentId1 );
        assertEquals( Gender.MALE, student1.getGender() );
        Name name1 = new Name();
        name1.setFirstName( "firstName1" );
        name1.setLastName( "lastName1" );
        student1.setName( name1 );
        assertNotNull( student1.getSchoolYears() );
        assertEquals( 0, student1.getSchoolYears().size() );
        session.save( student1 );

        Name name2 = new Name();
        name2.setFirstName( "firstName2" );
        name2.setLastName( "lastName2" );
        Student student2 = (Student) session.load( Student.class, studentId2 );
        assertEquals( Gender.FEMALE, student2.getGender() );
        assertEquals( 1, student2.getSchoolYears().size() );
        assertEquals( "S", ( (SchoolYear) student2.getSchoolYears().toArray()[0] ).getSchool().getShortName() );
        student2.setName( name2 );
        session.save( student2 );
        session.getTransaction().commit();

        session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        student1 = (Student) session.load( Student.class, studentId1 );
        student2 = (Student) session.load( Student.class, studentId2 );

        assertEquals( "firstName1", student1.getName().getFirstName() );
        assertEquals( "lastName1", student1.getName().getLastName() );

        assertEquals( "firstName2", student2.getName().getFirstName() );
        assertEquals( "lastName2", student2.getName().getLastName() );

        
        Student student3 = (Student) session.load( Student.class, studentId3 );
        assertEquals( Gender.FEMALE, student3.getGender() );
        assertEquals( 1, student3.getSchoolYears().size() );
        assertEquals( "S", ( (SchoolYear) student3.getSchoolYears().toArray()[0] ).getSchool().getShortName() );
         
        //must be last to get lazy load working
        session.getTransaction().commit();

    }

    public void testQueryStudent()
        throws Exception
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        List students = session.createQuery( "from org.glvnsjc.model.Student student" ).list();
        session.getTransaction().commit();
        assertEquals( 3, students.size() );
    }

    public void testDeleteStudent()
    {
        
        // testout StudentUtil.deleteStudent
        StudentUtil.deleteStudent( studentId2 );
        StudentUtil.deleteStudent( studentId3 );   
        
        
        try 
        {
            Session session = SessionUtil.begin();
            Student s1 = (Student) session.load( Student.class, studentId1 );
            session.delete( s1 );
            School school = (School) session.load( School.class, schoolId );
            session.delete( school );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        //ensure the delete acually work
        try 
        {
            Session session = SessionUtil.begin();
            List list = session.createQuery( "from org.glvnsjc.model.School ").list();
            assertEquals( 0, list.size() );
            list = session.createQuery( "from org.glvnsjc.model.SchoolYear ").list();
            assertEquals( 0, list.size() );
            list = session.createQuery( "from org.glvnsjc.model.Student ").list();
            assertEquals( 0, list.size() );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }        
    }

    public StudentTest( String name )
    {
        super( name );
    }

    public static Test suite()
        throws Exception
    {
        TestSuite st = new TestSuite();
        st.addTest( new StudentTest( "testAddMinimalStudent" ) );
        st.addTest( new StudentTest( "testAddStudentWithSchoolYearInOneSession" ) );
        st.addTest( new StudentTest( "testAddStudentWithSchoolYearInTwoSessions" ) );
        st.addTest( new StudentTest( "testModifyStudent" ) );
        st.addTest( new StudentTest( "testQueryStudent" ) );
        st.addTest( new StudentTest( "testDeleteStudent" ) );
        return st;
    }
}