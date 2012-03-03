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
import org.glvnsjc.model.TeacherType;

public class TeacherTypeOptions
{
    private static TeacherTypeOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList options = new ArrayList();

    private ArrayList principalOptions = new ArrayList();

    public ArrayList getOptions()
    {
        return options;
    }

    public ArrayList getPrincipalOptions()
    {
        return principalOptions;
    }

    private TeacherTypeOptions()
    {
        for ( int i = 0; i < TeacherType.teacherTypeList.length; ++i )
        {
            options.add( new LabelValueBean( TeacherType.teacherTypeList[i].toString(), TeacherType.teacherTypeList[i]
                .toString() ) );
        }

        principalOptions.add( new LabelValueBean( "", "" ) );
        principalOptions.add( new LabelValueBean( TeacherType.PRINCIPAL.toString(), TeacherType.PRINCIPAL.toString() ) );
        principalOptions.add( new LabelValueBean( TeacherType.TEACHER.toString(), TeacherType.TEACHER.toString() ) );
        principalOptions
            .add( new LabelValueBean( TeacherType.TEACHERAID.toString(), TeacherType.TEACHERAID.toString() ) );
    }

    public static TeacherTypeOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new TeacherTypeOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
