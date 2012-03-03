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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.Session;

public class SchoolList
{
    private static SchoolList s_instance = null;

    private static boolean s_instanceFlag = false;

    private Map schoolMapWithIdKey = new HashMap();

    private Map schoolMapWithShortNameKey = new HashMap();

    private List allSchools = null;

    public Map schoolMapWithIdKey()
    {
        return this.schoolMapWithIdKey;
    }

    public Map getSchoolMapWithShortNameKey()
    {
        return this.schoolMapWithShortNameKey;
    }

    private void load()
    {

        try
        {
            Session session = SessionUtil.begin();

            allSchools = session.createQuery( "from org.glvnsjc.model.School school" ).list();
            for ( int i = 0; i < allSchools.size(); i++ )
            {
                School school = (School) allSchools.get( i );
                this.schoolMapWithIdKey.put( school.getId(), school );
                this.schoolMapWithShortNameKey.put( school.getShortName(), school );
            }

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    private SchoolList()
    {
        load();
    }

    public static SchoolList getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new SchoolList();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

    public void reload()
    {
        this.schoolMapWithIdKey.clear();
        this.schoolMapWithShortNameKey.clear();
        this.allSchools = null;
        load();
    }

    public School getSchool( Integer id )
    {
        return (School) schoolMapWithIdKey.get( id );
    }

    public School getSchoolByShortName( String shortName )
    {
        return (School) schoolMapWithShortNameKey.get( shortName );
    }

    public List getSchoolList()
    {
        return this.allSchools;
    }

}
