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

public class MemberTypeOptions
{
    private static MemberTypeOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _memberTypeOptions = new ArrayList();

    private static String[] _optionLabels = { "", "Student", "Teacher", "TeacherAid" }; //these must match with torque schema file

    private static String[] _optionValues = { "", "S", "T", "TA" }; //these must match with torque schema file

    public ArrayList getOptions()
    {
        return _memberTypeOptions;
    }

    private MemberTypeOptions()
    {
        for ( int i = 0; i < _optionValues.length; ++i )
        {
            _memberTypeOptions.add( new LabelValueBean( _optionLabels[i], _optionValues[i] ) );
        }

    }

    public static MemberTypeOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new MemberTypeOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
