package org.glvnsjc.model;

import java.util.ArrayList;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;

import org.hibernate.Session;

public class StudentSearchTestSetup
    extends TestSetup
{

    public static int DB_SCHOOL_COUNT = 10;

    public static int DB_STUDENT_COUNT = 10;

    public StudentSearchTestSetup( Test test )
    {
        super( test );
    }

    protected void setUp()
        throws Exception
    {
        generateDB();
    }

    protected void tearDown()
        throws Exception
    {
        clearDB();
    }

    private void clearDB()
    {

        Session session = HibernateSessionFactory.getCurrentSession();

        session.beginTransaction();

        List students = session.createQuery( "from org.glvnsjc.model.Student student " ).list();

        session.getTransaction().commit();

        for ( int i = 0; i < students.size(); ++i )
        {
            Student student = (Student) students.get( i );
            StudentUtil.deleteStudent( student.getId() );
        }

    }

    private void generateDB()
    {
        Session session = HibernateSessionFactory.getCurrentSession();

        session.beginTransaction();

        ArrayList schools = new ArrayList();
        for ( int i = 1; i <= DB_SCHOOL_COUNT; ++i )
        {
            School school = createSchool( i );
            session.save( school );
            schools.add( school );

        }

        ArrayList students = new ArrayList();
        for ( int i = 1; i <= DB_STUDENT_COUNT; ++i )
        {
            Student student;

            if ( i % 2 == 0 )
            {
                student = createStudent( i, Gender.FEMALE );
            }
            else
            {
                student = createStudent( i, Gender.MALE );
            }

            session.save( student );

            students.add( student );
        }

        for ( int i = 1; i <= DB_STUDENT_COUNT; ++i )
        {
            int years = i;
            School school = (School) schools.get( i - 1 );
            Student student = (Student) students.get( i - 1 );

            for ( int y = 1; y <= years; ++y )
            {
                SchoolYear sy = new SchoolYear();
                sy.setYear( y );
                sy.setSchool( school );
                sy.setStudent( student );
                sy.setGiaolyClass( createClass() );
                sy.setVietnguClass( createClass() );
                session.save( sy );
            }
        }

    }

    private Student createStudent( int number, Gender gender )
    {
        Student student = new Student();
        student.setId( StudentIdGen.getInstance().nextStudentId() );
        student.setName( createName( number ) );
        student.setAddress( createAddress( number ) );
        student.setGender( gender );
        return student;
    }

    private School createSchool( int number )
    {
        return new School( "School" + number, "S" + number, null );
    }

    private Name createName( int number )
    {
        return new Name( "lastName" + number, "middleName" + number, "firstName" + number );
    }

    private Address createAddress( int number )
    {
        return new Address( "street1" + number, "street2" + number, "city" + number, "S" + number, "9512" + number,
                            number + "@glvn.org", "408-223-645" + number, "408-224-645" + number );
    }

    private SchoolClass createClass()
    {
        SchoolClass sc = new SchoolClass();

        return sc;

    }

}
