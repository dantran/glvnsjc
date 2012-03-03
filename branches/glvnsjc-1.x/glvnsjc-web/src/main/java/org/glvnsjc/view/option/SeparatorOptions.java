package org.glvnsjc.view.option;

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

import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;

public class SeparatorOptions
{
    private static SeparatorOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _separatorOptions = new ArrayList();

    private static String[] _optionValues = { ",", "\t", ":", "|" };

    private static String[] _optionLabels = { ",", "Tab", ":", "|" };

    public ArrayList getOptions()
    {
        return _separatorOptions;
    }

    private SeparatorOptions()
    {
        for ( int i = 0; i < _optionLabels.length; ++i )
        {
            _separatorOptions.add( new LabelValueBean( _optionLabels[i], _optionValues[i] ) );
        }

    }

    public static SeparatorOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new SeparatorOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
