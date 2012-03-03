package org.glvnsjc.action.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.converter.Convert;
import org.glvnsjc.model.SchoolDay;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.view.SchoolCalendarForm;
import org.glvnsjc.view.SchoolDayView;
import org.hibernate.Session;

/**
 * <tt>GenerateCalendar</tt> generates a list of SchoolCalendar based on a date range<br<br>
 *    and merge with those in DB
 *
 * <br>
 * @author Dan Tran
 */

public class GenerateCalendar
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( GenerateCalendar.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;
        Date startDate = Convert.StringToDate( theForm.get( "startDate" ).toString() );
        Date endDate = Convert.StringToDate( theForm.get( "endDate" ).toString() );

        if ( startDate.getTime() > endDate.getTime() )
        {
            Date tmp = startDate;
            startDate = endDate;
            endDate = tmp;
            theForm.set( "startDate", Convert.DateToString( startDate ) );
            theForm.set( "endDate", Convert.DateToString( endDate ) );
        }

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime( startDate );
        GregorianCalendar endCalendar = new GregorianCalendar();
        endCalendar.setTime( endDate );

        try
        {
            Session session = SessionUtil.begin();

            //build a list of saturdays between start and end dates
            //ArrayList schoolDays = new ArrayList();
            SchoolCalendarForm schoolCalendarForm = new SchoolCalendarForm();
            List schoolDays = schoolCalendarForm.getSchoolDays();

            while ( true )
            {
                if ( calendar.get( Calendar.DAY_OF_WEEK ) == Calendar.SATURDAY )
                {
                    Date day = calendar.getTime();
                    SchoolDayView schoolDayView = new SchoolDayView( Convert.DateToString( day ) );
                    //merge with the one in DB

                    String hsql = "from org.glvnsjc.model.SchoolDay schoolDay where schoolDay.day = :day";
                    List list = session.createQuery( hsql ).setParameter( "day", day ).list();
                    if ( list.size() != 0 )
                    {
                        SchoolDay schoolDay = (SchoolDay) list.get( 0 );
                        BeanUtils.copyProperties( schoolDayView, schoolDay );
                    }
                    schoolDays.add( schoolDayView );

                    //schoolDays.add(new SchoolDay(calendar.getTime()));
                }
                calendar.add( Calendar.DATE, 1 );
                if ( calendar.getTime().getTime() > endCalendar.getTime().getTime() )
                {
                    break;
                }
            }

            request.setAttribute( "schoolCalendarForm", schoolCalendarForm );

            SessionUtil.end();

        }
        catch ( Exception e )
        {
            log.error( "Error loading SChoolCalendars", e );
            SessionUtil.rollback( e );
        }

        //merge with the ones in db

        return ( mapping.findForward( "success" ) );
    }

}