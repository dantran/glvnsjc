package org.glvnsjc.itest;

import java.net.URL;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public abstract class TestCase
    extends junit.framework.TestCase
{
    //protected WebClient webClient;
    
    protected static String version = System.getProperty( "version" );

    protected static String port = System.getProperty( "cargo.servlet.port" );

    public TestCase( String testName )
    {
        super( testName );
    }    
    
    protected void setUp()
        throws Exception
    {
        //this.webClient = this.login();
    }
    
    protected void tearDown()
        throws Exception
    {
        this.logout();
    }
    
    private void logout()
       throws Exception
    {
        
    }
    
    protected URL getURL( String relativePath )
        throws Exception
    {
        String root = "http://localhost:" + port + "/glvnsjc-web-" + version + "/";

        return new URL( root + relativePath );
    }

    protected WebClient login()
        throws Exception
    {
        
        WebClient webClient = new WebClient();

        URL url = getURL( "login.jsp" );

        HtmlPage page1 = (HtmlPage) webClient.getPage( url );

        assertEquals( "Login", page1.getTitleText() );

        HtmlForm form = page1.getFormByName( "fakeForm" );
        HtmlTextInput userField = (HtmlTextInput) form.getInputByName( "username" );

        setInputField( form, "username", "admin");
        setInputField( form, "password", "admin");
        
        // Now submit the form by clicking the button and get back the 
        // second page.

        final HtmlButtonInput button = (HtmlButtonInput) form.getInputByName( "Login" );
        final HtmlPage page2 = (HtmlPage) button.click();

        assertEquals( "Giaoly Vietngu Menu", page2.getTitleText() );

        return webClient;
    }
    
    protected void setInputField( HtmlForm form, String name, String value )
    {
        form.getInputByName( name ).setValueAttribute( value );
        
    }
    
    protected String getInputField( HtmlForm form, String name )
    {
        return form.getInputByName( name ).getValueAttribute() ;
    }    

}
