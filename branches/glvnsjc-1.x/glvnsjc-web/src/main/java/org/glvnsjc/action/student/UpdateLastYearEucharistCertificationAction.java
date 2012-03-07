package org.glvnsjc.action.student;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.Student;
import org.glvnsjc.model.StudentSearchForm;
import org.glvnsjc.model.StudentUtil;

public class UpdateLastYearEucharistCertificationAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( NextYearReportAction.class );

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

        int currentSchoolYear = GlobalConfig.getInstance().getCurrentYear();
        int lastSchoolYear = currentSchoolYear--;

        log.info( "Updating Eucharist Info for school year " + lastSchoolYear + " by "
            + request.getUserPrincipal().getName() );

        StudentSearchForm criteria = new StudentSearchForm();

        criteria.setSchoolYear( Integer.toString( lastSchoolYear ) );

        List<SchoolYear> schoolYears = (List<SchoolYear>) StudentUtil.search( criteria );

        for ( SchoolYear schoolYear : schoolYears )
        {
            Student student = schoolYear.getStudent();
            if ( student.getEucharist().getDate() == null )
            {
                Set<SchoolYear> studentSchoolYears = (Set<SchoolYear>) student.getSchoolYears();
                for ( SchoolYear studentSchoolYear : studentSchoolYears )
                {
                    if ( studentSchoolYear.getGiaolyClass().getName().equals( ClassName.C3 ) )
                    {
                        if ( studentSchoolYear.getGiaolyClass().getGrade().completed() )
                        {
                            updateEucharistInfo( studentSchoolYear );
                            break;
                        }
                    }
                }
            }
        }

        return ( mapping.findForward( "success" ) );
    }

    private void updateEucharistInfo( SchoolYear schoolYear )
    {

        Calendar ca = Calendar.getInstance();
        ca.set( schoolYear.getYear(), Calendar.JUNE, 1 );

        Student student = schoolYear.getStudent();

        student.getEucharist().setDate( ca.getTime() );
        student.getEucharist().setLocation( "GLVNSJC" );

        log.info( "Updating Eucharist Info for " + student.getName().getFullName() + " from "
            + schoolYear.getSchool().getName() );
        StudentUtil.updateStudent( student );

    }
}