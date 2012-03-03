package org.glvnsjc.model;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: Singleton to init torque
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

import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class GlobalConfig
{
    private static GlobalConfig s_instance = null;

    private static boolean s_instanceFlag = false;

    private SiteConfig globalConfig = new SiteConfig();

    public SiteConfig getGlobalConfig()
    {
        return this.globalConfig;
    }

    public Integer getCurrentYear()
    {
        return globalConfig.getCurrentSchoolYear();
    }

    public String getCurrentYearStr()
    {
        return getCurrentYear().toString();
    }

    private void load()
        throws HibernateException
    {

        //add the rest from DB
        try
        {
            Session session = SessionUtil.begin();
            globalConfig = (SiteConfig) session.load( SiteConfig.class, new Integer( 1 ) );
            //TOTO force a full readd so that web app can see it
            //globalConfig.isSiteActive();
            SessionUtil.end();
        }
        catch ( HibernateException e )
        {
            e.printStackTrace();

            SessionUtil.rollback( e );
            /*
            Calendar ca = Calendar.getInstance();
            
            Integer currentYear = new Integer( ca.get ( Calendar.YEAR ) );
            
            globalConfig.setCurrentSchoolYear( currentYear );
            
            session.save( globalConfig );
            */
        }
    }

    private GlobalConfig()
        throws HibernateException
    {
        load();
    }

    public static GlobalConfig getInstance()
        throws HibernateException
    {

        if ( !s_instanceFlag )
        {
            s_instance = new GlobalConfig();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

    public void reload()
        throws HibernateException
    {
        load();
    }
}
