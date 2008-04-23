package org.glvnsjc.action.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.Grade;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.securityfilter.AppPrincipal;
import org.glvnsjc.view.ClassGradeListForm;
import org.glvnsjc.view.StudentGradeView;

public class UpdateStudentGradesAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( UpdateStudentGradesAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        ClassGradeListForm theForm = (ClassGradeListForm) form;
        LoginProfile loginProfile = ( (AppPrincipal) request.getUserPrincipal() ).getLoginProfile();

        ClassType classType = ClassType.fromString( theForm.getClassType() );

        try
        {
            List gradeViews = theForm.getGradeViews();
            
            Session session = SessionUtil.begin();
            
            for ( int i = 0; i < gradeViews.size(); ++i )
            {
                StudentGradeView gradeView = (StudentGradeView) gradeViews.get( i );
                SchoolYear schoolYear = (SchoolYear) session.load( SchoolYear.class, Integer.valueOf( gradeView
                    .getSchoolYearId() ) );
                if ( classType == ClassType.GIAOLY )
                {
                    schoolYear.getGiaolyClass().setGrade( Grade.fromString( gradeView.getGrade() ) );
                }
                else
                {
                    schoolYear.getVietnguClass().setGrade( Grade.fromString( gradeView.getGrade() ) );

                }
            }
            SessionUtil.end();

        }
        catch ( Exception e )
        {
            log.error( "Error found during loading student grade list", e );
            SessionUtil.rollback(  e );
        }

        this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );

        return ( mapping.findForward( "success" ) );
    }

}