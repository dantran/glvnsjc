package org.glvnsjc.itest;

import java.net.URL;
import java.util.Calendar;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BannerTest
    extends TestCase
{

    public BannerTest( String arg0 )
    {
        super( arg0 );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite( BannerTest.class );
        return suite;
    }

    public void testBannerPage()
        throws Exception
    {
        WebClient webClient = login();

        URL url = getURL( "banner.do" );

        HtmlPage page1 = (HtmlPage) webClient.getPage( url );

        assertEquals( "Banner", page1.getTitleText() );
        
        assertTrue( page1.asText().contains( "GLVNSJC: ") );
        assertTrue( page1.asText().contains( "Logout") );
        assertTrue( page1.asText().contains( "Welcome") );

        assertTrue( page1.asText().contains( "Privilege: Administrator" ) );

    }

}
