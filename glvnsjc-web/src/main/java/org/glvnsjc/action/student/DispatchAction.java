package org.glvnsjc.action.student;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.converter.Convert;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.Student;
import org.glvnsjc.model.StudentIdGen;
import org.glvnsjc.model.StudentUtil;
import org.glvnsjc.view.SchoolYearView;
import org.glvnsjc.view.StudentForm;
import org.glvnsjc.view.StudentView;

/**
 * <tt>DispatchAction</tt> allow add/delete/update a school year record which
 * consists of a Student and a SchoolYear objects <br<br>
 * 
 * It is assummed that CMA allows only user with "teacher" privilege to access
 * this action <br>
 * 
 * @author Dan Tran
 */

// note: the form must have property named as "action" since the dispatch action
// already use this. Is this a bug in DispatchLookupAction?
public class DispatchAction
    extends org.apache.struts.actions.LookupDispatchAction
{

    private static Log log = LogFactory.getLog( DispatchAction.class );

    // ------------------------------------------------------------ Action
    // Methods

    protected Map getKeyMethodMap()
    {
        Map map = new HashMap();
        map.put( "button.add", "add" );
        map.put( "button.update", "update" );
        map.put( "button.delete", "delete" );
        map.put( "button.addSchoolYear", "addSchoolYear" );
        map.put( "button.removeCurrentYear", "removeCurrentYear" );
        map.put( "button.cancel", "cancel" );

        return map;
    }

    public ActionForward cancel( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {
        StudentForm theForm = (StudentForm) form;

        String startAt = theForm.getStartAt();

        if ( StringUtils.isBlank( startAt ) )
        {
            startAt = "list";
        }

        return ( mapping.findForward( startAt ) );
    }

    public ActionForward add( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response )
        throws Exception
    {

        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permissionDeny" ) );
            saveErrors( request, errors );
            return ( mapping.getInputForward() );
        }

        if ( isBadTransactionToken( request ) )
        {
            return ( mapping.getInputForward() );
        }

        StudentForm theForm = (StudentForm) form;
        Student student = new Student();
        copyProperties( student, theForm.getStudent() );

        // get the next available student Id, and save
        boolean idGenerateAutomatically = true;
        if ( student.getId().intValue() == 0 )
        {
            student.setId( StudentIdGen.getInstance().nextStudentId() );
        }
        else
        {
            idGenerateAutomatically = false;
        }

        List schoolYears = theForm.getSchoolYears();

        if ( schoolYears.size() == 1 )
        {
            SchoolYearView schoolYearView = (SchoolYearView) schoolYears.get( 0 );

            SchoolYear schoolYear = new SchoolYear();

            BeanUtils.copyProperties( schoolYear, schoolYearView );

            StudentUtil.addStudent( student, schoolYear, schoolYearView.getSchoolId() );

            schoolYearView.setId( schoolYear.getId().toString() );
        }

        theForm.setOptionToAddSchoolYear( false );
        theForm.setOptionToRemoveCurrentYear( true );
        log.info( request.getUserPrincipal().getName() + " added student:\n" + form );
        this.saveMessages( request, ActionUtil.createActionMessages( "message.add.success" ) );

        if ( !idGenerateAutomatically )
        {
            // add just the id autogeneration, dont know what would happy if
            // exception is thrown here, bad bad
            StudentIdGen.getInstance().reload();
        }

        // after add, allow update
        theForm.setCommand( "update" );
        MessageResources messages = this.getResources( request );
        theForm.setTitle( messages.getMessage( "title.student." + DispatchType.UPDATE ) );
        theForm.setSubmitKey( messages.getMessage( "button." + DispatchType.UPDATE ) );
        // Set a transactional control token to prevent double posting
        saveToken( request );

        return ( mapping.findForward( "display" ) );
    }

    // add a blank current SchoolYear
    public ActionForward addSchoolYear( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response )
        throws Exception
    {

        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permisstionDeny" ) );
            saveErrors( request, errors );
            return ( mapping.getInputForward() );
        }

        StudentForm theForm = (StudentForm) form;
        SchoolYearView newSchoolYear = new SchoolYearView();
        newSchoolYear.setEditAllow( true );
        newSchoolYear.setYear( GlobalConfig.getInstance().getCurrentYear().intValue() );
        theForm.getSchoolYears().add( newSchoolYear );
        theForm.setOptionToAddSchoolYear( false );
        theForm.setOptionToRemoveCurrentYear( true );
        return ( mapping.findForward( "display" ) );
    }

    // add a blank current SchoolYear
    public ActionForward removeCurrentYear( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response )
        throws Exception
    {

        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permisstionDeny" ) );
            saveErrors( request, errors );
            return ( mapping.getInputForward() );
        }

        StudentForm theForm = (StudentForm) form;

        // todo ensure a user from one school does not update the other
        // school's student

        int currentSchoolYear = GlobalConfig.getInstance().getCurrentYear().intValue();

        for ( int i = 0; i < theForm.getSchoolYears().size(); ++i )
        {
            SchoolYearView schoolYearView = (SchoolYearView) theForm.getSchoolYears().get( i );
            SchoolYear schoolYear = new SchoolYear();
            BeanUtils.copyProperties( schoolYear, schoolYearView );

            if ( schoolYear.getYear() == currentSchoolYear )
            {
                StudentUtil.deleteSchoolYear( schoolYear.getId() );
                log.info( request.getUserPrincipal().getName() + " updated student:\n" + form );
                theForm.getSchoolYears().remove( i );
                theForm.setOptionToRemoveCurrentYear( false );
                theForm.setOptionToAddSchoolYear( true );
                break;
            }

        }

        return ( mapping.findForward( "display" ) );
    }

    public ActionForward update( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        // Report any errors we have discovered back to the original form
        if ( isBadTransactionToken( request ) )
        {
            return ( mapping.getInputForward() );
        }

        StudentForm theForm = (StudentForm) form;

        // todo ensure a user from one school does not update the other
        // school's student

        Student student = new Student();
        copyProperties( student, theForm.getStudent() );

        // update the schoolYears
        List schoolYears = theForm.getSchoolYears();

        SchoolYear schoolYear = null;
        String schoolId = null;
        for ( int i = 0; i < schoolYears.size(); ++i )
        {
            SchoolYearView schoolYearView = (SchoolYearView) schoolYears.get( i );
            if ( !schoolYearView.getEditAllow() )
            {
                continue;
            }

            schoolYear = new SchoolYear();
            BeanUtils.copyProperties( schoolYear, schoolYearView );
            schoolId = schoolYearView.getSchoolId();

            //expect only one modifiable schoolYear
            break;
        }

        StudentUtil.updateStudent( student, schoolYear, schoolId );

        log.info( request.getUserPrincipal().getName() + " updated student:\n" + form );

        this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );

        if ( theForm.getStartAt().equals( "list" ) )
        {
            return ( mapping.findForward( "list" ) );
        }

        // setup the title for a new update screen
        saveToken( request );
        MessageResources resources = this.getResources( request );
        theForm.setTitle( resources.getMessage( "title.student." + DispatchType.UPDATE ) );
        theForm.setSubmitKey( resources.getMessage( "button." + DispatchType.UPDATE ) );
        return ( mapping.findForward( "display" ) );

    }

    public ActionForward delete( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permisstionDeny" ) );
            saveErrors( request, errors );
            return ( mapping.getInputForward() );
        }

        if ( isBadTransactionToken( request ) )
        {
            return ( mapping.getInputForward() );
        }

        StudentForm theForm = (StudentForm) form;

        StudentUtil.deleteStudent( new Integer( theForm.getStudent().getId() ) );

        log.info( request.getUserPrincipal().getName() + " removed student:\n" + form );

        // the record is gone, send the user back to search page
        return ( mapping.findForward( "list" ) );
    }

    // helpers
    // ///////////////////////////////////////////////////////////////////
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

    private void copyProperties( Student student, StudentView studentView )
        throws IllegalAccessException, InvocationTargetException
    {

        BeanUtils.copyProperties( student, studentView );
        student.getBaptism().setDate( Convert.StringToDate( studentView.getBaptismDate() ) );
        student.getBaptism().setLocation( studentView.getBaptismLocation() );

        student.getEucharist().setDate( Convert.StringToDate( studentView.getEucharistDate() ) );
        student.getEucharist().setLocation( studentView.getEucharistLocation() );
    }

}