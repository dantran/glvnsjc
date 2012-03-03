package org.glvnsjc.action.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.Grade;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.Student;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.securityfilter.AppPrincipal;
import org.glvnsjc.view.NextYearClassSummary;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class NextYearReportAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( NextYearReportAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        LoginProfile loginProfile = ( (AppPrincipal) request.getUserPrincipal() ).getLoginProfile();

        //find all giaoly students in the current school year belong to user login

        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? " );
        if ( loginProfile.getSchool() != null )
        {
            queryBuff.append( "and schoolYear.school = ? " );
        }
        //queryBuff.append(" order by schoolYear.school, schoolYear.giaolyClass.name, schoolYear.student.name.lastNameRaw");

        List paramList = new ArrayList();
        List typeList = new ArrayList();
        paramList.add( GlobalConfig.getInstance().getCurrentYear() );
        typeList.add( Hibernate.INTEGER );
        if ( loginProfile.getSchool() != null )
        {
            paramList.add( loginProfile.getSchool().getId() );
            typeList.add( Hibernate.INTEGER );
        }
        Type[] types = new Type[typeList.size()];
        typeList.toArray( types );

        try
        {
            Session session = SessionUtil.begin();

            Query query = session.createQuery( queryBuff.toString() );

            query.setParameters( paramList.toArray(), types );

            List schoolYears = query.list();

            ArrayList list = new ArrayList();

            for ( int i = 0; i < schoolYears.size(); ++i )
            {
                SchoolYear schoolYear = (SchoolYear) schoolYears.get( i );
                NextYearClassSummary summary = new NextYearClassSummary();
                Student student = schoolYear.getStudent();
                summary.setId( student.getId().toString() );
                summary.setSchoolName( schoolYear.getSchool().getShortName() );
                summary.setStudentName( student.getName().getFullName() );
                BeanUtils.copyProperty( summary, "birthDate", student.getBirthDate() );

                Grade vietnguGrade = schoolYear.getVietnguClass().getGrade();
                Grade giaolyGrade = schoolYear.getGiaolyClass().getGrade();

                //set the this year and change it based on grade
                summary.setVietnguClass( schoolYear.getVietnguClass().getName().toString()
                    + schoolYear.getVietnguClass().getSubName().toString() );
                summary.setGiaolyClass( schoolYear.getGiaolyClass().getName().toString()
                    + schoolYear.getGiaolyClass().getSubName().toString() );

                summary.setNextVietnguClass( schoolYear.getVietnguClass().getName().toString() );
                summary.setNextGiaolyClass( schoolYear.getGiaolyClass().getName().toString() );

                if ( giaolyGrade != Grade.UNASSIGNED && giaolyGrade != Grade.FAIL )
                {
                    summary.setNextGiaolyClass( schoolYear.getGiaolyClass().getName().nextClass().toString() );
                }
                if ( vietnguGrade != Grade.UNASSIGNED && vietnguGrade != Grade.FAIL )
                {
                    summary.setNextVietnguClass( schoolYear.getVietnguClass().getName().nextClass().toString() );
                }

                list.add( summary );

            }

            SessionUtil.end();

            request.getSession().setAttribute( "nextYearReport", list );
        }
        catch ( Exception e )
        {
            log.error( "Error found during loading next year report", e );
            SessionUtil.rollback( e );
        }
        return ( mapping.findForward( "success" ) );
    }

}