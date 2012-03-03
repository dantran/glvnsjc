package org.glvnsjc.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.HibernateSessionFactory;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.view.SchoolCalendarForm;
import org.glvnsjc.view.SchoolDayView;
import org.hibernate.Session;

/**
 * <tt>LoadSchoolCalendar</tt> loads SchoolCalendar from database<br<br>
 *
 * <br>
 * @author Dan Tran
 */

public class LoadSchoolCalendar
    extends org.apache.struts.action.Action
{

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        //load calendar from DB
        List schoolDays = getSchoolCalendar();

        //copy db calendar to view calendar
        SchoolCalendarForm theForm = (SchoolCalendarForm) form;
        List schoolDayViews = theForm.getSchoolDays();
        for ( int i = 0; i < schoolDays.size(); ++i )
        {
            SchoolDayView schoolDayView = new SchoolDayView();
            BeanUtils.copyProperties( schoolDayView, schoolDays.get( i ) );
            schoolDayViews.add( schoolDayView );
        }

        return ( mapping.findForward( "success" ) );
    }

    static public List getSchoolCalendar()
        throws Exception
    {
        Session session = HibernateSessionFactory.getCurrentSession();

        List schoolDays = null;
        try
        {
            SessionUtil.begin();
            session.beginTransaction();
            schoolDays = session.createQuery( "from org.glvnsjc.model.SchoolDay" ).list();
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
        return schoolDays;
    }

}