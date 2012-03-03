package org.glvnsjc.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.LoginProfile;

/**
 * <tt>LoadMyStudentsAction</tt> load a list of student base on classType and current teacher class assignments
 *
 * It is assummed that CMA allows only user with "teacher" privilege to access this action
 * <br>
 * @author Dan Tran
 */

public class LoadMyStudentsAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( LoadMyStudentsAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        //reuse the main search form
        DynaActionForm theForm = (DynaActionForm) request.getSession().getAttribute( "searchForm" );

        String classTypeStr = request.getParameter( "classType" );

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
        ClassType classType = ClassType.fromString( classTypeStr );

        theForm.initialize( mapping );
        theForm.set( "schoolYear", GlobalConfig.getInstance().getCurrentYear().toString() );
        theForm.set( "schoolId", loginProfile.getSchool().getId().toString() );
        if ( classType == ClassType.GIAOLY )
        {
            theForm.set( "giaolyClassName", loginProfile.getGiaolyClass().toString() );
            theForm.set( "giaolyClassSubName", loginProfile.getGiaolySubClass().toString() );
        }
        else
        {
            theForm.set( "vietnguClassName", loginProfile.getVietnguClass().toString() );
            theForm.set( "vietnguClassSubName", loginProfile.getVietnguSubClass().toString() );

        }

        return ( mapping.findForward( "success" ) );
    }

}