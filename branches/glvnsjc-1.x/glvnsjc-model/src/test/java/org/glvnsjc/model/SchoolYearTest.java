package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.Session;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SchoolYearTest
    extends TestCase
{

    private static Integer schoolYearId;

    public SchoolYearTest( String arg0 )
    {
        super( arg0 );
    }

    public void testAddSchoolYear()
    {
        SchoolYear schoolYear = null;
        
        try
        {
            Session session = SessionUtil.begin();
            School school = new School( "schoolTest1", "ST1", null );
            session.save( school );

            Student student = new Student();
            student.setId( StudentIdGen.getInstance().nextStudentId() );
            student.setGender( Gender.FEMALE );
            session.save( student );

            SchoolClass glClass = new SchoolClass();
            glClass.setGrade( Grade.FIRST );
            glClass.setName( ClassName.C1 );
            glClass.setSubName( ClassSubName.A );

            SchoolClass vnClass = new SchoolClass();
            vnClass.setGrade( Grade.UNASSIGNED );
            vnClass.setName( ClassName.C1 );
            vnClass.setSubName( ClassSubName.A );

            schoolYear = new SchoolYear();
            schoolYear.setSchool( school );
            schoolYear.setStudent( student );
            schoolYear.setYear( 2006 );

            //@TODO fix model to allow null in gl and vn classes
            schoolYear.setGiaolyClass( glClass );
            schoolYear.setVietnguClass( vnClass );

            session.save( schoolYear );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback(  e );
        }        


        //used by subsequence test
        schoolYearId = schoolYear.getId();

    }

    public void testAddSchoolYearWithoutSchool()
    {
        
        SchoolClass glClass = new SchoolClass();
        glClass.setGrade( Grade.FIRST );
        glClass.setName( ClassName.C1 );
        glClass.setSubName( ClassSubName.A );

        SchoolClass vnClass = new SchoolClass();
        vnClass.setGrade( Grade.UNASSIGNED );
        vnClass.setName( ClassName.C1 );
        vnClass.setSubName( ClassSubName.A );

        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setYear( 2006 );

        //@TODO fix model to allow null in gl and vn classes
        schoolYear.setGiaolyClass( glClass );
        schoolYear.setVietnguClass( vnClass );

        try 
        {
            Session session = SessionUtil.begin();
            session.save( schoolYear );
            fail( "Able to create SchoolYear without student and school" );
        }
        catch ( Exception e )
        {
            SessionUtil.rollback();
        }

    }
    
    public void testVerifyAddSchoolYear()
    {
        SchoolYear sy = null;
        try
        {
            Session session = SessionUtil.begin();
            sy = (SchoolYear) session.load( SchoolYear.class, schoolYearId );
            assertEquals( 2006, sy.getYear() );

            assertEquals( "schoolTest1", sy.getSchool().getName() );
            assertEquals( "ST1", sy.getSchool().getShortName() );
            assertEquals( Gender.FEMALE, sy.getStudent().getGender() );
            assertEquals( Grade.FIRST, sy.getGiaolyClass().getGrade() );
            assertEquals( Grade.UNASSIGNED, sy.getVietnguClass().getGrade() );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback(  e );
        }
    }

    public void testDeleteSchoolYear()
    {
        SchoolYear sy = null;
        try
        {
            Session session = SessionUtil.begin();
            sy = (SchoolYear) session.load( SchoolYear.class, schoolYearId );            
            //must delete in this order
            session.delete( sy );
            session.delete( sy.getSchool() );
            session.delete( sy.getStudent() );

            session.getTransaction().commit();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback(  e );
        }        
    }

    public void testVerifyDeleteSchoolYear()
    {
        try 
        {
            Session session = SessionUtil.begin();            
            SchoolYear sy = (SchoolYear) session.load( SchoolYear.class, schoolYearId );
            fail( "not able to delete school year: " + sy.toString() );
            SessionUtil.end();
        }
        catch ( org.hibernate.ObjectNotFoundException e )
        {
            //not there
            SessionUtil.rollback();
        }
    }

    public static Test suite()
        throws Exception
    {
        TestSuite st = new TestSuite();
        st.addTest( new SchoolYearTest( "testAddSchoolYear" ) );
        st.addTest( new SchoolYearTest( "testAddSchoolYearWithoutSchool" ) );
        
        st.addTest( new SchoolYearTest( "testVerifyAddSchoolYear" ) );
        st.addTest( new SchoolYearTest( "testDeleteSchoolYear" ) );
        st.addTest( new SchoolYearTest( "testVerifyDeleteSchoolYear" ) );

        return st;
    }
}
