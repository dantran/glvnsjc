package org.glvnsjc.action.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.ClassSubName;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.view.StudentClassView;
import org.glvnsjc.view.StudentsToBeSubClassedForm;
import org.glvnsjc.action.ActionUtil;

public class UpdateSubClassedStudentsAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( UpdateSubClassedStudentsAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        StudentsToBeSubClassedForm theForm = (StudentsToBeSubClassedForm) form;

        ClassType classType = ClassType.fromString( theForm.getClassType() );

        try
        {
            Session session = SessionUtil.begin();

            ArrayList studentClassViews = (ArrayList) theForm.getStudentClassViews();

            for ( int i = 0; i < studentClassViews.size(); ++i )
            {
                StudentClassView classView = (StudentClassView) studentClassViews.get( i );
                SchoolYear schoolYear = (SchoolYear) session.load( SchoolYear.class, Integer.valueOf( classView
                    .getSchoolYearId() ) );
                if ( classType == ClassType.GIAOLY )
                {
                    schoolYear.getGiaolyClass().setSubName( ClassSubName.fromString( classView.getClassSubName() ) );
                }
                else
                {
                    schoolYear.getVietnguClass().setSubName( ClassSubName.fromString( classView.getClassSubName() ) );

                }
            }
            SessionUtil.end();

        }
        catch ( Exception e )
        {
            log.error( "Error found during loading students to be subclassed", e );
            SessionUtil.rollback( e );
        }
        
        this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );

        return ( mapping.findForward( "success" ) );
    }

}