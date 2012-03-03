package org.glvnsjc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.securityfilter.AppPrincipal;
import org.hibernate.Session;

public class UpdateUserPassword
    extends org.apache.struts.action.Action
{

    //------------------------------------------------------------ Action Methods

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        AppPrincipal principal = (AppPrincipal) request.getUserPrincipal();

        DynaActionForm theForm = (DynaActionForm) form;
        String newPassword = (String) theForm.get( "newPassword" );

        try
        {
            Session session = SessionUtil.begin();

            LoginProfile loginProfile = principal.getLoginProfile();
            loginProfile.setPassword( newPassword );
            session.update( loginProfile );

            SessionUtil.end();

        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "success" ) );
    }

}