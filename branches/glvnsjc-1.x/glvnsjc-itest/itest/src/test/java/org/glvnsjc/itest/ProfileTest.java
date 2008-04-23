package org.glvnsjc.itest;


import java.net.URL;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author dtran
 *
 */
public class ProfileTest
    extends TestCase
{
    public static String CLASS_USER_ID = "classId";

    public static String SCHOOL_USER_ID = "schoolId";

    public static String COMMUNITY_USER_ID = "communityId";

    /**
     * @param arg0
     */
    public ProfileTest( String arg0 )
    {
        super( arg0 );
    }

    public void testConfigureCurrentProfile()
        throws Exception
    {
        WebClient webClient = login();

        HtmlPage page = loadCurrentProfilePage( webClient );

        assertEquals( "Teacher Profile", page.getTitleText() );
        
        HtmlForm form = page.getFormByName( "currentLoginUserProfile" );
        
        HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByValue( "Update" );
                
        setInputField( form, "password", "admin" ) ;
        setInputField( form, "confirmPassword", "admin" ) ;
        setInputField( form, "name.lastName", "admin" ) ;
        setInputField( form, "name.firstName", "admin" ) ;
        setInputField( form, "address.email", "admin@localhost.com" ) ;
        setInputField( form, "address.phone1", "123-456-789" ) ;
        
        HtmlSelect select = null;
        
        select = form.getSelectByName( "giaolyClass" );
        select.setDefaultValue( "1" );
        
        select = form.getSelectByName( "giaolySubClass" );
        select.setDefaultValue( "A" );

        select = form.getSelectByName( "vietnguClass" );
        select.setDefaultValue( "1" );

        select = form.getSelectByName( "vietnguSubClass" );
        select.setDefaultValue( "B" );
        
        button = (HtmlSubmitInput) form.getInputByValue( "Update" );
                        
        page = (HtmlPage) button.click();
               
        assertTrue( "Profile update is not successful.", page.asText().contains( "Successful update.") );
        
        //FIXME more assertion to validate the returned page

    }

    private HtmlPage loadCurrentProfilePage( WebClient webClient )
        throws Exception
    {
        URL url = getURL( "teacher/currenLoginUserProfile.do" );

        HtmlPage page = (HtmlPage) webClient.getPage( url );

        assertEquals( "Teacher Profile", page.getTitleText() );

        return page;
    }
    

    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTest( new ProfileTest( "testConfigureCurrentProfile" ) );
        return suite;
    }
}
