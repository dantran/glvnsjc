package org.glvnsjc.action.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.School;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.action.ActionUtil;

public class LoadSearchAction
    extends org.apache.struts.action.Action
{

    //Load a student based on studentId

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        School school = loginProfile.getSchool();
        if ( school != null )
        {
            theForm.set( "schoolId", school.getId().toString() );
        }

        //less previledge than master can not search for other school

        theForm.set( "schoolPriviledgeOnly", new Boolean( false ) );
        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            theForm.set( "schoolPriviledgeOnly", new Boolean( true ) );
        }

        return ( mapping.findForward( "success" ) );
    }

}