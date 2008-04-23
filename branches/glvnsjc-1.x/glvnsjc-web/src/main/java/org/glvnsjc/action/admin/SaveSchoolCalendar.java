package org.glvnsjc.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.view.SchoolCalendarForm;
import org.glvnsjc.view.SchoolDayView;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SchoolDay;
import org.glvnsjc.model.hibernate.SessionUtil;

/**
 * <tt>SaveSchoolCalendar</tt> update school calendar
 * <br>
 * @author Dan Tran
 */

public class SaveSchoolCalendar
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( SaveSchoolCalendar.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {
        
        if ( !request.isUserInRole( Privilege.ADMIN.toString() ) )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permissionDeny" ) );
            saveErrors( request, errors );
            return ( mapping.getInputForward() );
        }              

        SchoolCalendarForm theForm = (SchoolCalendarForm) form;
        

        try
        {
            Session session = SessionUtil.begin();
            //remove the db first
            session.createQuery( "delete from org.glvnsjc.model.SchoolDay" ).executeUpdate();
            //add the form to DB
            List list = theForm.getSchoolDays();
            for ( int i = 0; i < list.size(); i++ )
            {
                SchoolDayView schoolDayView = (SchoolDayView) list.get( i );
                SchoolDay schoolDay = new SchoolDay();
                BeanUtils.copyProperties( schoolDay, schoolDayView );
                session.save( schoolDay );
            }
            SessionUtil.end();
            this.saveMessages( request, ActionUtil.createActionMessages( "message.save.success" ) );
        }
        catch ( Exception e )
        {
            log.error( "Error updating School Calendar", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "success" ) );
    }

}