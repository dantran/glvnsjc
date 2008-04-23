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

import org.apache.struts.util.LabelValueBean;

public class StateOptions
{
    private static StateOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _gradeOptions = new ArrayList();

    private static String[] _optionValues = { "", "CA" };

    public ArrayList getOptions()
    {
        return _gradeOptions;
    }

    private StateOptions()
    {
        for ( int i = 0; i < _optionValues.length; ++i )
        {
            _gradeOptions.add( new LabelValueBean( _optionValues[i], _optionValues[i] ) );
        }

    }

    public static StateOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new StateOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
