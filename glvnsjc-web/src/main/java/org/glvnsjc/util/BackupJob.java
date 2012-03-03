/**
 * $Header: /cvs/glvndb/nglvnsjc/glvnsjc-web/src/main/java/org/glvnsjc/util/BackupJob.java,v 1.1
 * 2006/07/06 21:31:34 danttran Exp $ $Revision $Date: 2006/07/06 21:31:34 $
 * 
 * ===========================================================================
 * 
 * JGSullivan Quartz Package
 * 
 * 
 * Classes designed to aid in working with the Quartz Enterprise Scheduler package Initially
 * designed to provide XML-based configuration
 * 
 * ===========================================================================
 */
package org.glvnsjc.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.HibernateSessionFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * Class BackupJob
 *
 * This class backup glvnsjc hsqldb data file using the current timestamp
 *
 * @author Dan Tran
 */
public class BackupJob
    implements Job
{
    /**
     * Field: log
     */
    private Log log = LogFactory.getLog( getClass().getName() );

    /**
     * Method execute
     *
     * @param parm1
     *
     * @param ctx
     *
     * @throws org.quartz.JobExecutionException
     *
     */
    public void execute( JobExecutionContext ctx )
        throws org.quartz.JobExecutionException
    {
        boolean isSiteActive = true;

        try
        {
            log.info( "BackupJob Executing..." );

            isSiteActive = GlobalConfig.getInstance().getGlobalConfig().isSiteActive();

            GlobalConfig.getInstance().getGlobalConfig().setSiteActive( false );

            //needed by hsqldb to sync database files
            HibernateSessionFactory.flush();

            File dbFile = new File( Constants.DB_FULL_PATH );

            File backupFile = new File( Constants.DB_BAK_FULL_PATH );

            DBBackup.run( dbFile, backupFile );
        }
        catch ( Exception e )
        {
            log.error( e.getMessage(), e );

            throw new org.quartz.JobExecutionException( e, false );
        }
        finally
        {
            try
            {
                GlobalConfig.getInstance().getGlobalConfig().setSiteActive( isSiteActive );
            }
            catch ( org.hibernate.HibernateException e )
            {
                log.error( e.getMessage(), e );
                throw new org.quartz.JobExecutionException( e, false );
            }
        }
    }

}
