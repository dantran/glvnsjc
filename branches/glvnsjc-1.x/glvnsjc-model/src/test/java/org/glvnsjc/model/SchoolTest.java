package org.glvnsjc.model;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.hibernate.Session;

import com.glvnsjc.test.TestCase;

public class SchoolTest
    extends TestCase
{
    public void testAddSchool()
        throws Exception
    {
        Session session = HibernateSessionFactory.getCurrentSession();

        session.beginTransaction();
        session.save( new School( "schoolTest1", "ST1", null ) );
        session.save( new School( "schoolTest2", "ST2", null ) );
        session.getTransaction().commit();

        SchoolList schools = SchoolList.getInstance();
        schools.reload();
        School school = schools.getSchoolByShortName( "ST1" );
        assertEquals( "schoolTest1", school.getName() );
        school = schools.getSchoolByShortName( "ST2" );
        assertEquals( "schoolTest2", school.getName() );

    }

    public void testAddSchoolWithRollback()
        throws Exception
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        School school = new School( "schoolRB", "SRB", null );

        session.save( school );
        session.getTransaction().rollback();

        SchoolList schools = SchoolList.getInstance();
        schools.reload();
        school = schools.getSchoolByShortName( "SRB" );
        assertNull( "rollback school found", school );
    }

    public void testModifySchool()
        throws Exception
    {
        SchoolList schools = SchoolList.getInstance();
        schools.reload();
        School school = schools.getSchoolByShortName( "ST1" );
        assertNotNull( school );

        Session session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        school = (School) session.load( School.class, school.getId() );
        school.setName( "testSchoolModify" );
        session.save( school );
        session.getTransaction().commit();

        schools.reload();
        school = schools.getSchoolByShortName( "ST1" );
        assertEquals( "testSchoolModify", school.getName() );
    }

    public void testDeleteSchool()
        throws Exception
    {
        SchoolList schools = SchoolList.getInstance();
        schools.reload();
        School school1 = schools.getSchoolByShortName( "ST1" );
        School school2 = schools.getSchoolByShortName( "ST2" );
        assertNotNull( school1 );
        assertNotNull( school2 );

        Session session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();

        School school = (School) session.load( School.class, school1.getId() );
        session.delete( school );
        school = (School) session.load( School.class, school2.getId() );
        session.delete( school );
        session.getTransaction().commit();

        schools.reload();
        school = schools.getSchoolByShortName( "ST1" );
        assertNull( "found deleted school", school );
        school = schools.getSchoolByShortName( "ST2" );
        assertNull( "found deleted school", school );
    }

    public SchoolTest( String name )
    {
        super( name );
    }

    public static Test suite()
        throws Exception
    {
        TestSuite st = new TestSuite();
        st.addTest( new SchoolTest( "testAddSchool" ) );
        st.addTest( new SchoolTest( "testAddSchoolWithRollback" ) );
        st.addTest( new SchoolTest( "testModifySchool" ) );
        st.addTest( new SchoolTest( "testDeleteSchool" ) );
        return st;
    }
}
