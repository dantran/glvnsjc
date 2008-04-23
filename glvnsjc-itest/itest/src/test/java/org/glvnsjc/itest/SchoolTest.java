/**
 * 
 */
package org.glvnsjc.itest;

import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author dtran
 *
 */
public class SchoolTest
    extends TestCase
{

    public static String SN1 = "SN1";

    public static String SN2 = "SN2";

    public static String SN3 = "SN3";

    public static String LN1 = "Long Name 1";

    public static String LN2 = "Long Name 2";

    public static String LN3 = "Long Name 3";

    /**
     * @param arg0
     */
    public SchoolTest( String arg0 )
    {
        super( arg0 );
    }

    public void testBadInputsAddSchools()
        throws Exception
    {
        WebClient webClient = login();

        HtmlPage page;
        HtmlForm form;
        HtmlSubmitInput button;

        //bad input
        page = getAddSchoolPage( webClient );
        form = page.getFormByName( "schoolForm" );
        button = (HtmlSubmitInput) form.getInputByName( "action" );

        page = (HtmlPage) button.click();
        assertEquals( "School", page.getTitleText() );

        //bad input again, only one field enterred
        form = page.getFormByName( "schoolForm" );
        setInputField( form, "shortName", "SN" );
        button = (HtmlSubmitInput) form.getInputByName( "action" );

        page = (HtmlPage) button.click();
        assertEquals( "School", page.getTitleText() );

    }

    public void testAddSchools()
        throws Exception
    {
        WebClient webClient = login();

        addSchool( webClient, SN1, LN1 );
        addSchool( webClient, SN2, LN2 );
        addSchool( webClient, SN3, LN3 );
        
        //testDuplicate
        HtmlPage page = addSchool( webClient, SN3, LN3, false );
        assertEquals( "Operation error", page.getTitleText() );        
        
    }

    private HtmlPage addSchool( WebClient webClient, String shortName, String longName )
        throws Exception
    {
        return addSchool( webClient, shortName, longName, true );
    }

    private HtmlPage addSchool( WebClient webClient, String shortName, String longName, boolean checkError )
        throws Exception
    {
        HtmlPage page = getAddSchoolPage( webClient );
        HtmlForm form = page.getFormByName( "schoolForm" );
        setInputField( form, "shortName", shortName );
        setInputField( form, "name", longName );
        HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName( "action" );

        page = (HtmlPage) button.click();

        if ( checkError )
        {
            assertEquals( "School", page.getTitleText() );
        }
        
        return page;
    }

    public void testSchoolList()
        throws Exception
    {
        WebClient webClient = login();

        HtmlPage page = getSchoolListPage( webClient );

        List anchors = page.getAnchors();

        assertEquals( 6, anchors.size() );

        //more validation here?
    }

    public void testRemoveSchools()
        throws Exception
    {
        WebClient webClient = login();

        HtmlPage page = getSchoolListPage( webClient );

        HtmlTable table = (HtmlTable) page.getHtmlElementById( "schools" );

        List anchors = page.getAnchors();

        for ( int i = 0; i < anchors.size(); ++i )
        {
            HtmlAnchor anchor = (HtmlAnchor) anchors.get( i );

            if ( anchor.getHrefAttribute().contains( "delete" ) )
            {
                HtmlPage deletePage = (HtmlPage) anchor.click();

                assertEquals( "School", deletePage.getTitleText() );

                HtmlForm form = deletePage.getFormByName( "schoolForm" );
                HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName( "action" );

                page = (HtmlPage) button.click();
                assertEquals( "School List", page.getTitleText() );

            }
        }
    }

    private HtmlPage getSchoolListPage( WebClient webClient )
        throws Exception
    {
        URL url = getURL( "admin/loadSchools.do" );

        HtmlPage page = (HtmlPage) webClient.getPage( url );

        assertEquals( "School List", page.getTitleText() );

        return page;
    }

    private HtmlPage getAddSchoolPage( WebClient webClient )
        throws Exception
    {
        URL url = getURL( "admin/loadSchool.do?command=add" );

        HtmlPage page = (HtmlPage) webClient.getPage( url );

        assertEquals( "School", page.getTitleText() );

        return page;
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTest( new SchoolTest( "testBadInputsAddSchools" ) );
        suite.addTest( new SchoolTest( "testAddSchools" ) );
        suite.addTest( new SchoolTest( "testSchoolList" ) );
        return suite;
    }
}
