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

public class EncodingOptions
{
    private static EncodingOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _encodingOptions = new ArrayList();

    private static String[] _optionValues = { "", "UTF-8", "UTF-16" };

    private static String[] _optionLabels = { "Ascii", "Unicode UTF-8", "Unicode UTF-16" };

    public ArrayList getOptions()
    {
        return _encodingOptions;
    }

    private EncodingOptions()
    {
        for ( int i = 0; i < _optionLabels.length; ++i )
        {
            _encodingOptions.add( new LabelValueBean( _optionLabels[i], _optionValues[i] ) );
        }
    }

    public static EncodingOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new EncodingOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
