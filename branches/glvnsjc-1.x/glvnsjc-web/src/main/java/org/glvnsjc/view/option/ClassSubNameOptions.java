package org.glvnsjc.view.option;

/**
 * <p>Title: </p>
 * <p>Description: A fixed list of avaiable ClassSubName object to init torque</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;
import org.glvnsjc.model.ClassSubName;

public class ClassSubNameOptions
{
    private static ClassSubNameOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList options = new ArrayList();

    public ArrayList getOptions()
    {
        return this.options;
    }

    private ClassSubNameOptions()
    {
        for ( int i = 0; i < ClassSubName.subNameList.length; ++i )
        {
            this.options.add( new LabelValueBean( ClassSubName.subNameList[i].toString(), ClassSubName.subNameList[i]
                .toString() ) );
        }
    }

    public static ClassSubNameOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new ClassSubNameOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
