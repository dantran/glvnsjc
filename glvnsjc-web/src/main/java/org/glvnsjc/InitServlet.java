package org.glvnsjc;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.plexus.util.Os;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class InitServlet
    implements ServletContextListener
{

    private static Log log = LogFactory.getLog( InitServlet.class );

    public void contextInitialized( ServletContextEvent servletContextEvent )
    {
        log.info( "contextInitialized..." );

        //work around for jar locking problem during undeploy
        //http://permalink.gmane.org/gmane.comp.jakarta.hivemind.user/2014
        try
        {
            if ( Os.isFamily( "windows" ) )
            {
                new URL( "http://This-is-just-to-get-the-URLConnection" ).openConnection().setDefaultUseCaches( false );
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    //Clean up resources
    public void contextDestroyed( ServletContextEvent servletContextEvent )
    {
        log.info( "contextDestroyed..." );
    }
}