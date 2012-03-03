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
import org.glvnsjc.model.Privilege;

public class PrivilegeOptions
{
    private static PrivilegeOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList roleOptions = new ArrayList();

    private ArrayList schoolRoleOptions = new ArrayList();

    public ArrayList getOptions()
    {
        return roleOptions;
    }

    public ArrayList getSchoolOptions()
    {
        return schoolRoleOptions;
    }

    private PrivilegeOptions()
    {
        this.roleOptions.add( new LabelValueBean( Privilege.CLASS.toString(), Privilege.CLASS.toString() ) );
        this.roleOptions.add( new LabelValueBean( Privilege.SCHOOL.toString(), Privilege.SCHOOL.toString() ) );
        this.roleOptions.add( new LabelValueBean( Privilege.PRINCIPAL.toString(), Privilege.PRINCIPAL.toString() ) );
        this.roleOptions.add( new LabelValueBean( Privilege.COMMUNITY.toString(), Privilege.COMMUNITY.toString() ) );
        this.roleOptions.add( new LabelValueBean( Privilege.ADMIN.toString(), Privilege.ADMIN.toString() ) );

        this.schoolRoleOptions.add( new LabelValueBean( Privilege.CLASS.toString(), Privilege.CLASS.toString() ) );
        this.schoolRoleOptions.add( new LabelValueBean( Privilege.SCHOOL.toString(), Privilege.SCHOOL.toString() ) );
        this.schoolRoleOptions
            .add( new LabelValueBean( Privilege.PRINCIPAL.toString(), Privilege.PRINCIPAL.toString() ) );

    }

    public static PrivilegeOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new PrivilegeOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
