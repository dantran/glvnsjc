package org.glvnsjc.plugin;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.glvnsjc.model.HibernateSessionFactory;

/**
 * Mainly used to allow use shutdown database properly
 * <p><strong>OptionsPlugIn</strong> Load all option values from database</p>
 *
 * @author Dan T. Tran
 */

public final class DBAdminPlugIn
    implements org.apache.struts.action.PlugIn
{

    /**
     * Logging output for this plug in instance.
     */
    private static Log log = LogFactory.getLog( DBAdminPlugIn.class );

    /**DBAdminPlugIn
     * Gracefully shut down this database, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy()
    {
        HibernateSessionFactory.shutdown();
        log.info( "DBAdminPlugIn:detroyed" );
    }

    /**
     * do and load our initial database from persistent storage.
     *
     * @param servlet The ActionServlet for this web application
     * @param config The ApplicationConfig for our owning module
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init( ActionServlet servlet, ModuleConfig config )
        throws ServletException
    {
        log.info( "DBAdminPlugIn:initialized" );
    }
}
