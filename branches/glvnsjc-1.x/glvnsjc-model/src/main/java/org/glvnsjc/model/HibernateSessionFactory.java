package org.glvnsjc.model;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: Singleton to init JDO Persistent Manager Factory
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hsqldb.DatabaseManager;

public class HibernateSessionFactory
{
    private static Log log = LogFactory.getLog( HibernateSessionFactory.class );

    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
            DBSetup.initDB();
        }
        catch ( Throwable ex )
        {
            // Make sure you log the exception, as it might be swallowed
            System.err.println( "Initial SessionFactory creation failed." + ex );
            throw new ExceptionInInitializerError( ex );
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    private static Session openSession()
    {
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public static void flush()
        throws HibernateException
    {
        log.info( "Flush database" );
        Session session = HibernateSessionFactory.openSession();

        try
        {
            session.connection().createStatement().execute( "CHECKPOINT" );
        }
        catch ( java.sql.SQLException e )
        {
            throw new HibernateException( e );
        }
        finally
        {
            if ( session != null )
            {
                session.close();
            }
        }
    }

    public static void shutdown()
    {
        hsqldbCleanup( sessionFactory );
        sessionFactory.close();
    }

    private static void hsqldbCleanup( SessionFactory f )
    {
        Session session = f.openSession();
        try
        {
            log.info( "Shutdown hsqldb" );
            session.connection().createStatement().execute( "SHUTDOWN" );
            DatabaseManager.getTimer().shutDown();
        }
        catch ( java.sql.SQLException e )
        {
            throw new HibernateException( e );
        }
        finally
        {
            if ( session != null )
            {
                session.close();
            }
        }
    }

}
