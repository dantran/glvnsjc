package org.glvnsjc.model;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.glvnsjc.test.TestCase;

public class StudentUtilTest
    extends TestCase
{

    public StudentUtilTest( String arg0 )
    {
        super( arg0 );
    }

    public void testSearchSchoolYear()
    {
        //find all students in a school year
        StudentSearchForm form = new StudentSearchForm();
        form.setSchoolYear( "0" );
        assertEquals( 0, StudentUtil.search( form ).size() );

        form.setSchoolYear( "1" );
        assertEquals( 10, StudentUtil.search( form ).size() );

        form.setSchoolYear( "9" );
        assertEquals( 2, StudentUtil.search( form ).size() );

        form.setSchoolYear( "10" );
        assertEquals( 1, StudentUtil.search( form ).size() );
    }

    public void testSearchSchoolYearWithLastName()
    {
        StudentSearchForm form = new StudentSearchForm();

        form.setSchoolYear( "1" );
        form.setLastName( "lastName%" );
        assertEquals( 10, StudentUtil.search( form ).size() );

        form.setLastName( "l%Name%" );
        assertEquals( 10, StudentUtil.search( form ).size() );

        form.setLastName( "%1" );
        assertEquals( 1, StudentUtil.search( form ).size() );

    }

    public void testSearchFirstName()
    {
        StudentSearchForm form = new StudentSearchForm();

        form.setFirstName( "firstName1" );

        List students = StudentUtil.search( form );

        assertEquals( 1, students.size() );

        form.setFirstName( "firstName2" );

        assertEquals( 1, StudentUtil.search( form ).size() );

        form.setFirstName( "firstName10" );

        assertEquals( 1, StudentUtil.search( form ).size() );

        form.setFirstName( "firstName11" );

        assertEquals( 0, StudentUtil.search( form ).size() );

    }

    public void testSearchLastName()
    {
        StudentSearchForm form = new StudentSearchForm();

        form.setLastName( "lastName1" );

        List students = StudentUtil.search( form );

        assertEquals( 1, students.size() );

        form.setLastName( "lastName2" );

        assertEquals( 1, StudentUtil.search( form ).size() );

        form.setLastName( "lastName10" );

        assertEquals( 1, StudentUtil.search( form ).size() );
    }

    public void testSearchLastFirst()
    {
        StudentSearchForm form = new StudentSearchForm();

        form.setLastName( "lastName1" );
        form.setFirstName( "firstName%" );
        List students = StudentUtil.search( form );
        assertEquals( 1, students.size() );

        form.setFirstName( "firstName1" );
        students = StudentUtil.search( form );
        assertEquals( 1, students.size() );

        form.setFirstName( "firstName0" );
        students = StudentUtil.search( form );
        assertEquals( 0, students.size() );

    }

    /**
     * @return the suite of tests being tested
     */

    public static Test suite()
    {
        TestSuite suite = new TestSuite( StudentUtilTest.class );
        return new StudentSearchTestSetup( suite );
    }

}
