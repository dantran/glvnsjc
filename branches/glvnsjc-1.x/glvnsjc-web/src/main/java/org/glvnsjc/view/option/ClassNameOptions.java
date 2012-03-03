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
import org.glvnsjc.model.ClassName;

public class ClassNameOptions
{
    private static ClassNameOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _options = new ArrayList();

    public ArrayList getOptions()
    {
        return _options;
    }

    private ClassNameOptions()
    {
        for ( int i = 0; i < ClassName.classNameList.length; ++i )
        {
            _options.add( new LabelValueBean( ClassName.classNameList[i].toString(), ClassName.classNameList[i]
                .toString() ) );
        }

    }

    public static ClassNameOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new ClassNameOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
