package org.glvnsjc.action.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.securityfilter.AppPrincipal;

//Load a student based on studentId

public class UpdateCurrentLoginUserProfileAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( UpdateCurrentLoginUserProfileAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {
        DynaActionForm theForm = (DynaActionForm) form;

        AppPrincipal appPrincipal = (AppPrincipal) request.getUserPrincipal();
        LoginProfile loginProfile = appPrincipal.getLoginProfile();


        try
        {
            //need to work on a copy
            Session session = SessionUtil.begin();
            LoginProfile dbLoginProfile = (LoginProfile) session.load( LoginProfile.class, loginProfile.getId() );
            BeanUtils.copyProperties( dbLoginProfile, theForm );
            SessionUtil.end();
            appPrincipal.setLoginProfile( dbLoginProfile );
        }
        catch ( Exception e )
        {
            log.error( "Error during updating current login user profile", e );
            SessionUtil.rollback( e );
        }

        log.info( loginProfile.getUserId() + " update profile\n" + form );
        this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );

        return ( mapping.findForward( "success" ) );
    }

}