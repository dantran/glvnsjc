package org.glvnsjc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.LoginProfile;

public class Banner
    extends org.apache.struts.action.Action
{


    //------------------------------------------------------------ Action Methods

    //Load a student based on studentId

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        LoginProfile loginUser = ActionUtil.getCurrentUserLoginProfile( request );

        String bannerMsg = "GLVNSJC";
        if ( loginUser.getSchool() != null )
        {
            bannerMsg = loginUser.getSchool().getName();
        }
        int thisYear = GlobalConfig.getInstance().getGlobalConfig().getCurrentSchoolYear().intValue();
        int nextYear = GlobalConfig.getInstance().getGlobalConfig().getCurrentSchoolYear().intValue() + 1;
        bannerMsg = bannerMsg + ": " + thisYear + "-" + nextYear;

        request.setAttribute( "bannerMessage", bannerMsg );
        request.setAttribute( "loginProfile", loginUser );
        return ( mapping.findForward( "success" ) );
    }

}
