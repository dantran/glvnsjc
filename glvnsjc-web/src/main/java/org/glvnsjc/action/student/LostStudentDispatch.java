package org.glvnsjc.action.student;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SchoolClass;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassSubName;
import org.glvnsjc.model.Name;
import org.glvnsjc.model.LostStudent;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.SiteConfig;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.util.SendMail;
import org.glvnsjc.converter.Convert;
import org.glvnsjc.util.ProcessRunnables;
import org.glvnsjc.util.StringUtil;

/**
 * <tt>LostStudentDispatch</tt> allow add/delete/update a school year record which consists of
 * a Student and a SchoolYear objects <br<br>
 *
 * It is assummed that CMA allows only user with "teacher" privilege to access this action
 * <br>
 * @author Dan Tran
 */

//note: the form must have property named as "action" since the dispatch action
//  already use this.  Is this a bug in DispatchLookupAction?

public class LostStudentDispatch
    extends org.apache.struts.actions.LookupDispatchAction
{

    private static Log log = LogFactory.getLog( LostStudentDispatch.class );

    //------------------------------------------------------------ Action Methods

    protected Map getKeyMethodMap()
    {
        Map map = new HashMap();
        map.put( "button.add", "add" );
        map.put( "button.update", "update" );
        map.put( "button.delete", "delete" );
        map.put( "button.cancel", "cancel" );

        return map;
    }

    public ActionForward cancel( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response )
        throws Exception
    {

        return ( mapping.findForward( "list" ) );
    }

    public ActionForward add( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                             HttpServletResponse response )
        throws Exception
    {

        if ( isBadTransactionToken( request ) )
        {
            return ( mapping.getInputForward() );
        }

        DynaActionForm theForm = (DynaActionForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        try
        {

            Session session = SessionUtil.begin();

            //copy data from view to model

            LostStudent student = Form2LostStudent( theForm );
            student.setReportedBy( loginProfile );
            student.setCreatedDate( new Date( System.currentTimeMillis() ) );

            session.save( student );
            //the view does not have the id yet, so set it
            theForm.set( "id", student.getId().toString() );
            
            SessionUtil.end();
            
            log.info( request.getUserPrincipal().getName() + " added a missing student:\n" + form );
            this.saveMessages( request, ActionUtil.createActionMessages( "message.add.success" ) );

            //email notification to the db administrator
            String feedBackEmail = GlobalConfig.getInstance().getGlobalConfig().getFeedbackEmail();
            SendMail sendMail = new SendMail( loginProfile.getAddress().getEmail(), feedBackEmail,
                                              "Request to lookup missing student record",
                                              generateMissingStudentInfoForEmail( student ) );
            
            SiteConfig siteConfig = GlobalConfig.getInstance().getGlobalConfig();
            
            sendMail.setSmtpServerHost( siteConfig.getSmtpServer() );
            sendMail.setSmtpserverUserId( siteConfig.getSmtpUserId() );
            sendMail.setSmtpserverPassword( siteConfig.getSmtpPassword() );
            
            ProcessRunnables.getInstance().submit( sendMail );

        }
        catch ( Exception e )
        {
            log.error( "Error during adding new lost student.", e );
            
            SessionUtil.rollback( e );
        }

        //after add, allow update
        MessageResources messages = this.getResources( request );
        theForm.set( "submitButtonName", messages.getMessage( "button." + DispatchType.UPDATE ) );
        // Set a transactional control token to prevent double posting
        saveToken( request );

        return ( mapping.findForward( "display" ) );
    }

    public ActionForward update( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response )
        throws Exception
    {

        // Report any errors we have discovered back to the original for
        if ( isBadTransactionToken( request ) )
        {
            return ( mapping.getInputForward() );
        }

        DynaActionForm theForm = (DynaActionForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        try
        {
            Session session = SessionUtil.begin();
            LostStudent dbStudent = (LostStudent) session.load( LostStudent.class, new Integer( theForm.get( "id" )
                .toString() ) );
            //copy data from form to LostStudent except the reportedBy field
            Form2LostStudent( theForm, dbStudent );
            
            SessionUtil.end();

            log.info( request.getUserPrincipal().getName() + " updated a missing student:\n" + form );
            this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );

        }
        catch ( Exception e )
        {
            log.error( "Error during update an existing lost student.", e );
            SessionUtil.rollback( e );
        }

        if ( theForm.get( "startAt" ).equals( "list" ) )
        {
            return ( mapping.findForward( "list" ) );
        }

        //setup the title for a new update screen
        saveToken( request );
        MessageResources resources = this.getResources( request );
        theForm.set( "submitButtonName", resources.getMessage( "button." + DispatchType.UPDATE ) );
        return ( mapping.findForward( "display" ) );
    }

    public ActionForward delete( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response )
        throws Exception
    {

        if ( isBadTransactionToken( request ) )
        {
            return ( mapping.getInputForward() );
        }

        DynaActionForm theForm = (DynaActionForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        try
        {
            Session session = SessionUtil.begin();

            LostStudent dbStudent = (LostStudent) session.load( LostStudent.class, new Integer( theForm.get( "id" ).toString() ) );
            session.delete( dbStudent );
            
            SessionUtil.end();
            
            log.info( request.getUserPrincipal().getName() + " removed missing student:\n" + form );

            //email notification to the owner of the record
            String fromEmail = loginProfile.getAddress().getEmail();
            String toEmail = dbStudent.getReportedBy().getAddress().getEmail();
            String message = "Reason: " + theForm.get( "reasonToDelete" ) + "\r\n"
                + generateMissingStudentInfoForEmail( dbStudent );
            SendMail sendMail = new SendMail( fromEmail, toEmail, "Missing student resolved", message );
            ProcessRunnables.getInstance().submit( sendMail );

        }
        catch ( Exception e )
        {
            log.error( "Error during deleting an existing student.", e );
            SessionUtil.rollback( e );
        }
        //the record is gone, send the user back to search page
        return ( mapping.findForward( "list" ) );
    }

    //helpers ///////////////////////////////////////////////////////////////////
    private boolean isBadTransactionToken( HttpServletRequest request )
    {

        boolean bad = false;
        // Validate the transactional control token
        ActionMessages errors = new ActionMessages();
        if ( !isTokenValid( request ) )
        {
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.transaction.token" ) );
            saveErrors( request, errors );
            saveToken( request );
            bad = true;
        }
        resetToken( request );
        return bad;
    }

    private LostStudent Form2LostStudent( DynaActionForm theForm )
        throws Exception
    {
        LostStudent student = new LostStudent();
        Form2LostStudent( theForm, student );
        return student;

    }

    private void Form2LostStudent( DynaActionForm theForm, LostStudent student )
        throws Exception
    {
        BeanUtils.copyProperties( student, theForm );
        Name name = new Name();
        student.setName( name );
        BeanUtils.copyProperties( student.getName(), theForm );
        SchoolClass giaoly = new SchoolClass();
        giaoly.setName( ClassName.fromString( theForm.get( "giaolyClassName" ) ) );
        giaoly.setSubName( ClassSubName.fromString( (String) theForm.get( "giaolyClassSubName" ) ) );
        student.setGiaolyClass( giaoly );
        SchoolClass vietngu = new SchoolClass();
        vietngu.setName( ClassName.fromString( theForm.get( "vietnguClassName" ) ) );
        vietngu.setSubName( ClassSubName.fromString( (String) theForm.get( "vietnguClassSubName" ) ) );
        student.setVietnguClass( vietngu );

    }

    private String generateMissingStudentInfoForEmail( LostStudent student )
    {
        StringBuffer buffer = new StringBuffer( "\r\n" );
        buffer.append( "Student: " ).append( student.getName().getFullName() ).append( "\r\n" );
        buffer.append( "School: " ).append( student.getReportedBy().getSchool().getName() ).append( "\r\n" );
        if ( !StringUtil.isBlank( student.getGiaolyClass().getFullClassName() ) )
        {
            buffer.append( "GL: " ).append( student.getGiaolyClass().getFullClassName() ).append( "\r\n" );
        }
        if ( !StringUtil.isBlank( student.getVietnguClass().getFullClassName() ) )
        {
            buffer.append( "VN: " ).append( student.getVietnguClass().getFullClassName() ).append( "\r\n" );
        }
        if ( student.getBirthDate() != null )
        {
            buffer.append( "DOB: " ).append( Convert.DateToString( student.getBirthDate() ) ).append( "\r\n" );
        }
        if ( !StringUtil.isBlank( student.getPhone() ) )
        {
            buffer.append( "Phone: " ).append( student.getPhone() ).append( "\r\n" );
        }

        buffer.append( "Reported by: " ).append( student.getReportedBy().getName().getFullName() );

        return buffer.toString();
    }

}