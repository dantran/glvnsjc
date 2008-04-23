package org.glvnsjc.itest;

import java.net.URL;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SchoolCalendarTest
    extends TestCase
{

    public SchoolCalendarTest( String arg0 )
    {
        super( arg0 );
    }

    public void testBadCalendarInputs()
        throws Exception
    {
        WebClient webClient = login();

        URL url = getURL( "admin/promptCalendarRange.do" );

        HtmlPage page;
        HtmlForm form;
        HtmlSubmitInput button;

        page = (HtmlPage) webClient.getPage( url );
        assertEquals( "Calendar Range", page.getTitleText() );

        //both end date and startdate are empty, expect same form return
        form = page.getFormByName( "calendarRange" );
        button = (HtmlSubmitInput) form.getInputByName( "generate" );
        page = (HtmlPage) button.click();
        assertEquals( "Calendar Range", page.getTitleText() );

        //only one valid entry
        form = page.getFormByName( "calendarRange" );
        button = (HtmlSubmitInput) form.getInputByName( "generate" );
        setInputField( form, "startDate", "07/07/2006" );
        page = (HtmlPage) button.click();
        assertEquals( "Calendar Range", page.getTitleText() );
    }

    public void testGenerateSchoolCalendar()
        throws Exception
    {
        WebClient webClient = login();
        
        URL url = getURL( "admin/promptCalendarRange.do" );

        HtmlPage page;
        HtmlForm form ;
        HtmlSubmitInput button;       
          
        page = (HtmlPage) webClient.getPage( url );
        form = page.getFormByName( "calendarRange" );
        setInputField( form, "startDate", "07/07/2006" ) ;
        setInputField( form, "endDate", "07/31/2006" );
        button = (HtmlSubmitInput) form.getInputByName( "generate" );
        
        page = (HtmlPage) button.click();
        assertEquals( "School Calendar", page.getTitleText() );     
        form = page.getFormByName( "schoolCalendarForm" );
        setInputField( form, "schoolDay[0].schoolNote", "schoolNote0" ) ;
        setInputField( form, "schoolDay[1].schoolNote", "schoolNote1" ) ;
        setInputField( form, "schoolDay[2].schoolNote", "schoolNote2" ) ;
        setInputField( form, "schoolDay[3].schoolNote", "schoolNote3" ) ;
        setInputField( form, "schoolDay[0].teacherNote", "teacherNote0" ) ;
        setInputField( form, "schoolDay[1].teacherNote", "teacherNote1" ) ;
        setInputField( form, "schoolDay[2].teacherNote", "teacherNote2" ) ;
        setInputField( form, "schoolDay[3].teacherNote", "teacherNote3" ) ;
        button = (HtmlSubmitInput) form.getInputByName( "save" );     
        
        page = (HtmlPage) button.click();  
        
        validateSavedCalendarPage( page );
        
    }

    private void validateSavedCalendarPage( HtmlPage page )
        throws Exception
    {
        assertEquals( "School Calendar", page.getTitleText() );   
        HtmlForm form = page.getFormByName( "schoolCalendarForm" );   
        
        assertEquals( "schoolNote0", getInputField( form, "schoolDay[0].schoolNote" ) );
        assertEquals( "schoolNote1", getInputField( form, "schoolDay[1].schoolNote" ) );
        assertEquals( "schoolNote2", getInputField( form, "schoolDay[2].schoolNote" ) );
        assertEquals( "schoolNote3", getInputField( form, "schoolDay[3].schoolNote" ) );
        assertEquals( "teacherNote0", getInputField( form, "schoolDay[0].teacherNote" ) );
        assertEquals( "teacherNote1", getInputField( form, "schoolDay[1].teacherNote" ) );
        assertEquals( "teacherNote2", getInputField( form, "schoolDay[2].teacherNote" ) );
        assertEquals( "teacherNote3", getInputField( form, "schoolDay[3].teacherNote" ) );
    }

    public void testGeneratedSchoolCalendar()
        throws Exception
    {
        WebClient webClient = login();
        
        URL url = getURL( "admin/loadSchoolCalendar.do" );

        HtmlPage page = (HtmlPage) webClient.getPage( url );  
        
        validateSavedCalendarPage( page );
        
    }
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTest( new SchoolCalendarTest( "testBadCalendarInputs" ) );
        suite.addTest( new SchoolCalendarTest( "testGenerateSchoolCalendar" ) );
        suite.addTest( new SchoolCalendarTest( "testGeneratedSchoolCalendar" ) );
        return suite;
    }
}
