package org.glvnsjc.action.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.converter.Convert;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SchoolDay;
import org.glvnsjc.util.StringUtil;

/**
 * <tt>LoadStudentsCalendar</tt> prepares a report so that it can be used as attendant sheet.
 * <br>
 * @author Dan Tran
 */

public class LoadStudentsCalendar
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( LoadStudentsCalendar.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        List headers = new ArrayList();
        List secondHeaders = new ArrayList();
        List schoolYears = null;

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        String classTypeStr = request.getParameter( "classType" );
        ClassType classType = ClassType.fromString( classTypeStr );
        //FIXme catch error here

        schoolYears = StudentUtils.getCurrentLoginUserSchoolYears( request, classType );

        MessageResources messages = this.getResources( request );

        //student header info
        headers.add( messages.getMessage( "label.name" ) );
        headers.add( messages.getMessage( "label.phone1" ) );
        headers.add( messages.getMessage( "label.DOB" ) );
        headers.add( messages.getMessage( "label.parentName" ) );
        headers.add( messages.getMessage( "label.class" ) );
        for ( int i = 0; i < headers.size(); ++i )
        {
            secondHeaders.add( "-" );
        }

        //school calendar

        List schoolDays = org.glvnsjc.action.admin.LoadSchoolCalendar.getSchoolCalendar();
        for ( int i = 0; i < schoolDays.size(); ++i )
        {
            SchoolDay schoolDay = (SchoolDay) schoolDays.get( i );
            headers.add( Convert.DateToString( schoolDay.getDay() ) );
            if ( StringUtil.isBlank( schoolDay.getSchoolNote() ) )
            {
                secondHeaders.add( "-" );
            }
            else
            {
                secondHeaders.add( schoolDay.getSchoolNote() );
            }
        }

        //put them in the request so that the view can display
        request.setAttribute( "firstHeaders", headers );
        request.setAttribute( "secondHeaders", secondHeaders );
        request.setAttribute( "schoolYears", schoolYears );
        request.setAttribute( "schoolDaysSize", new Integer( schoolDays.size() ) );
        String className = null;
        if ( classType == ClassType.GIAOLY )
        {
            className = "GL" + loginProfile.getGiaolyClassFullName();
        }
        else
        {
            className = "VN" + loginProfile.getVietnguClassFullName();
        }
        request.setAttribute( "className", className );

        return ( mapping.findForward( "success" ) );
    }

}