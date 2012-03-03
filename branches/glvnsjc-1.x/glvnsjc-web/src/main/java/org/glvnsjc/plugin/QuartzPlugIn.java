/**
 * $Header: /cvs/glvndb/nglvnsjc/glvnsjc-web/src/main/java/org/glvnsjc/plugin/QuartzPlugIn.java,v
 * 1.1 2006/07/06 21:31:33 danttran Exp $ $Revision $Date: 2006/07/06 21:31:33 $
 * 
 * ===========================================================================
 * 
 * JGSullivan Quartz Plugin
 * 
 * Quartz plugin allowing for XML-configured jobs to be run from within the application
 * server/Jakarta Struts environment.
 * 
 * ===========================================================================
 */
package org.glvnsjc.plugin;

/**
 * Class QuartzPlugin
 *
 * This plugin extends the parent plugin to add the destroy method sothat
 * quartz can shutdown properly
 *
 * @see com.jgsullivan.struts.plugins.QuartzPlugIn
 */
public class QuartzPlugIn
    extends com.jgsullivan.struts.plugins.QuartzPlugIn
{

    public void destroy()
    {
        super.destroy();
        try
        {
            this.scheduler.shutdown( true );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

}
