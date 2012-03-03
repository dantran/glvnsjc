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
import org.glvnsjc.model.Grade;

public class GradeOptions
{
    private static GradeOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _options = new ArrayList();

    public ArrayList getOptions()
    {
        return _options;
    }

    private GradeOptions()
    {
        for ( int i = 0; i < Grade.gradeList.length; ++i )
        {
            _options.add( new LabelValueBean( Grade.gradeList[i].toString(), Grade.gradeList[i].toString() ) );
        }

    }

    public static GradeOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new GradeOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
