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

public class GenderOptions
{
    private static GenderOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _genderTypeOptions = new ArrayList();

    private static String[] _optionLabels = { "", "M", "F" };

    private static String[] _optionValues = { "", "M", "F" };

    public ArrayList getOptions()
    {
        return _genderTypeOptions;
    }

    private GenderOptions()
    {
        for ( int i = 0; i < _optionValues.length; ++i )
        {
            _genderTypeOptions.add( new LabelValueBean( _optionLabels[i], _optionValues[i] ) );
        }

    }

    public static GenderOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new GenderOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
