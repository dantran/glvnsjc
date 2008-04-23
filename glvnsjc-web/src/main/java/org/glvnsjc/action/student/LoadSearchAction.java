package org.glvnsjc.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.GlobalConfig;

/**
 * LoadSearchAction prepares student search form.
 * For those with Principal privilege or below, School and SchooYear criteria are not allowed
 *
 * @struts.action
 *      name="searchForm"
 *      path="/loadSearch"
 *      scope="session"
 *      validate="false"
 *
 * @struts.action-forward
 *      name="success"
 *      path="/search.jsp"
 */

public class LoadSearchAction
    extends org.apache.struts.action.Action
{

    //------------------------------------------------------------ Action Methods

    //Load a student based on studentId

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {
        DynaActionForm theForm = (DynaActionForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        //wipe out the old data in form saved by http session
        theForm.initialize( mapping );

        // default current year to the current year for previlege above master
        String currentYear = GlobalConfig.getInstance().getGlobalConfig().getCurrentSchoolYear().toString();
        theForm.set( "schoolYear", currentYear );
        if ( loginProfile.getSchool() != null )
        {
            theForm.set( "schoolId", loginProfile.getSchool().getId().toString() );
        }

        return mapping.findForward( "success" );

    }

}