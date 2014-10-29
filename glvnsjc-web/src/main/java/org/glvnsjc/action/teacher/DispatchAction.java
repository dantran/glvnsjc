package org.glvnsjc.action.teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.CertificateType;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SchoolList;
import org.glvnsjc.model.SiteConfig;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.util.ProcessRunnables;
import org.glvnsjc.util.SendMail;
import org.glvnsjc.util.StringUtil;
import org.glvnsjc.view.CertificateView;
import org.glvnsjc.view.LoginProfileForm;
import org.hibernate.Query;
import org.hibernate.Session;

// note: the form must have property named as "action" since the dispatch action
// already use this. Is this a bug in DispatchLookupAction?

public class DispatchAction
    extends org.apache.struts.actions.LookupDispatchAction
{

    private static Log log = LogFactory.getLog( DispatchAction.class );

    protected Map getKeyMethodMap()
    {
        Map map = new HashMap();
        map.put( "button.add", "add" );
        map.put( "button.teacher.addCertificate", "addCertificate" );
        map.put( "button.teacher.delCertificate", "delCertificate" );
        map.put( "button.update", "update" );
        map.put( "button.delete", "delete" );
        map.put( "button.teacher.resetPassword", "resetPassword" );
        return map;
    }

    private boolean isUserIdExist( Session session, String userId )
        throws Exception
    {
        String hql = "from org.glvnsjc.model.LoginProfile profile where profile.userId = :userId";

        Query query = session.createQuery( hql ).setParameter( "userId", userId );

        List list = query.list();

        return ( list.size() != 0 );
    }

    private void overrideSchool( HttpServletRequest request, LoginProfileForm form )
        throws Exception
    {
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            form.setSchoolId( loginProfile.getSchool().getId().toString() );
        }
    }

    public ActionForward add( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response )
        throws Exception
    {

        LoginProfileForm theForm = (LoginProfileForm) form;

        if ( hasEditPrivilege( request ) )
        {
            return ( mapping.getInputForward() );
        }

        theForm.setUserId( theForm.getUserId().trim() );
        overrideSchool( request, theForm );

        try
        {
            Session session = SessionUtil.begin();

            LoginProfile loginProfile = new LoginProfile();
            BeanUtils.copyProperties( loginProfile, theForm );
            if ( this.isUserIdExist( session, theForm.getUserId() ) )
            {
                ActionMessages errors = new ActionMessages();
                errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.loginIdAlreadyTaken" ) );
                saveErrors( request, errors );
                return ( mapping.getInputForward() );
            }

            if ( !StringUtil.isBlank( (String) theForm.getSchoolId() ) )
            {
                loginProfile.setSchool( SchoolList.getInstance().getSchool( Integer.valueOf( theForm.getSchoolId() ) ) );
            }

            session.save( loginProfile );

            SessionUtil.end();

            // send the object id back to the form
            theForm.setId( loginProfile.getId().toString() );
            log.info( "Add Teacher: " + form );
            this.saveMessages( request, ActionUtil.createActionMessages( "message.add.success" ) );

        }
        catch ( Exception e )
        {
            log.error( "Error during adding new LoginProfile", e );
            SessionUtil.rollback( e );
        }

        theForm.setCommand( "update" );

        return ( mapping.findForward( "update" ) );

    }

    public ActionForward resetPassword( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response )
        throws Exception
    {

        LoginProfileForm theForm = (LoginProfileForm) form;

        // assign a new random password
        Random random = new Random( System.currentTimeMillis() );
        String newPassword = Integer.toString( Math.abs( random.nextInt() ) );

        // update the form to new password
        theForm.setPassword( newPassword );
        theForm.setConfirmPassword( newPassword );

        ActionForward actionForward = update( mapping, form, request, response );

        if ( theForm.getLoginable() )
        {
            // notify the user via email regarding the changes
            // email notification to the db administrator

            LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
            String to = theForm.getAddress().getEmail();
            String from = loginProfile.getAddress().getEmail();
            SendMail sendMail =
                new SendMail( from, to, "GLVNSJC Account Notification",
                              generateAccountResetEmailBody( loginProfile.getName().getFullName(), theForm ) );

            SiteConfig siteConfig = GlobalConfig.getInstance().getGlobalConfig();

            sendMail.setSmtpServerHost( siteConfig.getSmtpServer() );
            sendMail.setSmtpserverUserId( siteConfig.getSmtpUserId() );
            sendMail.setSmtpserverPassword( siteConfig.getSmtpPassword() );

            ProcessRunnables.getInstance().submit( sendMail );

            this.saveMessages( request, ActionUtil.createActionMessages( "message.resetPassword.notification" ) );

        }

        return actionForward;
    }

    private String generateAccountResetEmailBody( String from, LoginProfileForm theForm )
    {
        StringBuffer buff = new StringBuffer();

        buff.append( "\n" );
        buff.append( "Dear " ).append( theForm.getName().getFullName() ).append( ", \n\n" );
        buff.append( "Your account with GLVNSJC " );
        buff.append( "has been changed or newly created.\n\n" );
        buff.append( "Your user id is " ).append( theForm.getUserId() ).append( "\n" );
        buff.append( "Your password is " ).append( theForm.getPassword() ).append( "\n\n" );
        buff.append( "Please login and change your password as soon as possible." ).append( "\n\n" );
        buff.append( "Many Blessings\n\n" );
        buff.append( from );

        return buff.toString();
    }

    public ActionForward update( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        LoginProfileForm theForm = (LoginProfileForm) form;

        theForm.setUserId( theForm.getUserId().trim() );
        overrideSchool( request, theForm );

        try
        {
            Session session = SessionUtil.begin();

            LoginProfile loginProfile =
                (LoginProfile) session.load( LoginProfile.class, new Integer( theForm.getId() ) );
            if ( isObjectEditAllowed( request, loginProfile ) )
            {
                SessionUtil.rollback();
                return mapping.getInputForward();
            }
            // makesure hacker
            if ( !loginProfile.getUserId().equals( theForm.getUserId() ) )
            {
                if ( this.isUserIdExist( session, theForm.getUserId() ) )
                {
                    ActionMessages errors = new ActionMessages();
                    errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.loginIdAlreadyTaken" ) );
                    saveErrors( request, errors );
                    SessionUtil.rollback();
                    return ( mapping.getInputForward() );
                }
            }

            copyProperties( theForm, loginProfile );

            if ( !StringUtil.isBlank( theForm.getSchoolId() ) )
            {
                loginProfile.setSchool( SchoolList.getInstance().getSchool( Integer.valueOf( theForm.getSchoolId() ) ) );
            }
            else
            {
                loginProfile.setSchool( null );
            }
            SessionUtil.end();

            log.info( loginProfile.getUserId() + " update Teacher: " + form );
            this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );

        }
        catch ( Exception e )
        {
            log.error( "Error during update LoginProfile.", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "update" ) );

    }

    public ActionForward addCertificate( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        LoginProfileForm theForm = (LoginProfileForm) form;

        CertificateView certificate = new CertificateView();
        certificate.setDate( "10/10/2000" );
        certificate.setDescription( CertificateType.UNKNOWN.getDisplay() );

        certificate.setSertificateTypeId( CertificateType.UNKNOWN.getEnumCode().toString() );
        theForm.getCertificateViews().add( certificate );

        // must return to the same CRUD state( ie add or update )
        return ( mapping.findForward( theForm.getCommand() ) );
    }

    public ActionForward delCertificate( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        LoginProfileForm theForm = (LoginProfileForm) form;

        CertificateView certificate = new CertificateView();
        certificate.setDate( "10/10/2000" );
        certificate.setDescription( CertificateType.UNKNOWN.getDisplay() );

        certificate.setSertificateTypeId( CertificateType.UNKNOWN.getEnumCode().toString() );
        theForm.getCertificateViews().add( certificate );

        // must return to the same CRUD state( ie add or update )
        return ( mapping.findForward( theForm.getCommand() ) );
    }

    public ActionForward delete( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        LoginProfileForm theForm = (LoginProfileForm) form;

        try
        {
            Session session = SessionUtil.begin();
            LoginProfile loginProfile =
                (LoginProfile) session.load( LoginProfile.class, new Integer( theForm.getId() ) );
            if ( isObjectEditAllowed( request, loginProfile ) )
            {
                SessionUtil.rollback();
                return mapping.getInputForward();
            }
            session.delete( loginProfile );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            log.error( "Error during deleting LoginProfile" );
            SessionUtil.rollback( e );
        }

        log.info( "Remove Teacher: " + form );

        // the record is gone, send the user back to search page
        return ( mapping.findForward( "list" ) );
    }

    private boolean hasEditPrivilege( HttpServletRequest request )
    {

        boolean errorFound = false;
        if ( !request.isUserInRole( Privilege.PRINCIPAL.toString() ) )
        {
            // user has teacher privilege or less, can't update/add/delete teacher object
            errorFound = true;
        }
        if ( errorFound )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permisstionDeny" ) );
            saveErrors( request, errors );
            LoginProfile currentUser = ActionUtil.getCurrentUserLoginProfile( request );
            log.warn( currentUser.getId() + " has no privilege to edit teacher object" );
        }

        return errorFound;
    }

    private boolean isObjectEditAllowed( HttpServletRequest request, LoginProfile dbLoginProfile )
    {
        boolean errorFound = false;
        if ( !request.isUserInRole( Privilege.PRINCIPAL.toString() ) )
        {
            // user has teacher privilege or less, can't update/add/delete teacher object
            errorFound = true;
        }
        else
        {
            if ( request.isUserInRole( Privilege.PRINCIPAL.toString() )
                && !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
            {
                // user is principal
                LoginProfile currentUser = ActionUtil.getCurrentUserLoginProfile( request );
                if ( !currentUser.getSchool().getId().equals( dbLoginProfile.getSchool().getId() ) )
                {
                    // user is a principal but tries to edit teacher object of other school, hack?
                    log.warn( currentUser.getId() + " has no privilege to edit teacher object" + dbLoginProfile );
                    errorFound = true;
                }
            }
            // else user is Master, he/she can edit any teacher object

        }
        if ( errorFound )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permissionDeny" ) );
            saveErrors( request, errors );
        }

        return errorFound;
    }

    private void copyProperties( LoginProfileForm from, LoginProfile to )
        throws IllegalAccessException, InvocationTargetException
    {
        BeanUtils.copyProperties( to, from );
    }

}