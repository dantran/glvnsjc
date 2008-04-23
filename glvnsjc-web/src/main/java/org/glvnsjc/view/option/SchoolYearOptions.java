package org.glvnsjc.view.option;

/**
 * Singleton to cache a list of school years available in database
 * It is load/reload at startup and when GlobalConfig gets changed
 */

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.apache.struts.util.LabelValueBean;
import org.glvnsjc.model.hibernate.SessionUtil;

public class SchoolYearOptions
{
    private static SchoolYearOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _options = new ArrayList();

    public ArrayList getOptions()
    {
        return _options;
    }

    private SchoolYearOptions()
        throws HibernateException
    {
        load();
    }

    private void load()
        throws HibernateException
    {

        try
        {
            Integer currentYear = org.glvnsjc.model.GlobalConfig.getInstance().getCurrentYear();
            
            Session session = SessionUtil.begin();
            String queryStr = "Select distinct schoolYear.year from org.glvnsjc.model.SchoolYear schoolYear";
            List list = session.createQuery( queryStr ).list();;
            _options.add( new LabelValueBean( "", "" ) );
            
            boolean foundCurrentYear = false;
            for ( int i = 0; i < list.size(); ++i )
            {
                int nextYear = ( (Integer) list.get( i ) ).intValue() + 1;
                _options.add( new LabelValueBean( list.get( i ).toString() + "-" + nextYear, list.get( i ).toString() ) );
                
                if ( nextYear == currentYear.intValue() + 1 )
                {
                    foundCurrentYear = true;
                }
            }
            
            //handle case where there no curent school year record in DB yet
            if ( ! foundCurrentYear )
            {
                int nextYear = currentYear.intValue() + 1;
                _options.add( new LabelValueBean( currentYear.toString() + "-" + nextYear, currentYear.toString() ) );                
            }
            
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    synchronized public void reload()
        throws HibernateException
    {
        _options.clear();
        load();
    }

    public static SchoolYearOptions getInstance()
        throws HibernateException
    {
        if ( !s_instanceFlag )
        {
            s_instance = new SchoolYearOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
