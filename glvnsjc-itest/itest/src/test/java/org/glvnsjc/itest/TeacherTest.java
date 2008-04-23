/**
 * 
 */
package org.glvnsjc.itest;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author dtran
 *
 */
public class TeacherTest
    extends TestCase
{
    public static String CLASS_USER_ID = "classId";

    public static String SCHOOL_USER_ID = "schoolId";

    public static String COMMUNITY_USER_ID = "communityId";

    /**
     * @param arg0
     */
    public TeacherTest( String arg0 )
    {
        super( arg0 );
    }

    public void testBadInputsAddTeacher()
        throws Exception
    {
        WebClient webClient = login();

        HtmlPage page;
        HtmlForm form;
        HtmlSubmitInput button;

        //bad input

    }

    public void testAddTeachers()
        throws Exception
    {
        WebClient webClient = login();

    }

    public void testRemoveTeachers()
        throws Exception
    {
        WebClient webClient = login();

    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTest( new SchoolTest( "testBadInputsAddTeacher" ) );
        return suite;
    }
}
