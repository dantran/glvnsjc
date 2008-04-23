package org.glvnsjc.view.option;

/**
 * <p>Title: </p>
 * <p>Description: Singleton to init torque</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.struts.util.LabelValueBean;
import org.glvnsjc.model.School;

public class SchoolOptions
{
    private static SchoolOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList options = new ArrayList();

    private ArrayList nonEmptyOptions = new ArrayList();

    public ArrayList getOptions()
    {
        return this.options;
    }

    public ArrayList getNonEmptyOptions()
    {
        return this.nonEmptyOptions;
    }

    private void load()
        throws Exception
    {

        //add an empty schoolName
        this.options.add( new LabelValueBean( "", "" ) );

        //add the rest from DB
        Map allSchools = org.glvnsjc.model.SchoolList.getInstance().schoolMapWithIdKey();
        Set keys = allSchools.keySet();

        for ( Iterator i = keys.iterator(); i.hasNext(); )
        {
            School school = (School) allSchools.get( i.next() );
            LabelValueBean labelValueBean = new LabelValueBean( school.getName(), school.getId().toString() );
            this.options.add( labelValueBean );
            this.nonEmptyOptions.add( labelValueBean );
        }

    }

    private SchoolOptions()
        throws Exception
    {
        load();
    }

    public static SchoolOptions getInstance()
        throws Exception
    {

        if ( !s_instanceFlag )
        {
            s_instance = new SchoolOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

    synchronized public void reload()
        throws Exception
    {
        this.options.clear();
        load();
    }
}
