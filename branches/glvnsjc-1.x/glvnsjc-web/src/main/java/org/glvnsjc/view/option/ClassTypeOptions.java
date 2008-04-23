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
import org.glvnsjc.model.ClassType;

public class ClassTypeOptions
{
    private static ClassTypeOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _options = new ArrayList();

    public ArrayList getOptions()
    {
        return _options;
    }

    private ClassTypeOptions()
    {
        _options.add( new LabelValueBean( "Gia\u0301o Ly\u0301", ClassType.GIAOLY.toString() ) );
        _options.add( new LabelValueBean( "Viê\u0323t Ng\u01B0\u0303", ClassType.VIETNGU.toString() ) );
    }

    public static ClassTypeOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new ClassTypeOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
