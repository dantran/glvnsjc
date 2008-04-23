package org.glvnsjc.itest;

import java.net.URL;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ConfigurationTest
    extends TestCase
{

    public ConfigurationTest( String arg0 )
    {
        super( arg0 );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite( ConfigurationTest.class );
        return suite;
    }

    public void testGlobalConfigurationPage()
        throws Exception
    {
        WebClient webClient = login();
        
        URL url = getURL( "admin/loadGlobalConfig.do" );

        HtmlPage page1 = (HtmlPage) webClient.getPage( url );

        assertEquals( "Global Configuration", page1.getTitleText() );

        HtmlForm form = page1.getFormByName( "globalConfigForm" );
        
        setInputField( form, "smtpServer", "bogus.com" ) ;

        setInputField( form, "smtpUserId", "smtpUserId" );
        
        setInputField( form, "smtpPassword", "smtpPassword" );
        
        setInputField( form, "feedbackEmail", "feedbackEmail" );
        
        final HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName( "update" );
        final HtmlPage page2 = (HtmlPage) button.click();

        assertEquals( "Global Configuration", page2.getTitleText() );
        
        form = page2.getFormByName( "globalConfigForm" );
        
        assertEquals( "on", getInputField( form, "siteActive" ) );

        assertEquals( "bogus.com", getInputField( form, "smtpServer" ) ) ;

        assertEquals( "smtpUserId",  getInputField( form, "smtpUserId"  ) );
        
        assertEquals( "smtpPassword", getInputField( form, "smtpPassword" ) );
        
        assertEquals( "feedbackEmail", getInputField( form, "feedbackEmail" ) );        
    }

}
