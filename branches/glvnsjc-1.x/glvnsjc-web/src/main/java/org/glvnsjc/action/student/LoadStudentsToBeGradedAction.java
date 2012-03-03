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
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassSubName;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.view.ClassGradeListForm;
import org.glvnsjc.view.StudentGradeView;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

/**
 * <tt>LoadStudentsToBeGradedAction</tt> prepares a a list of students belong to the current login user
 * so that he/she can grade them in one form
 *
 * It is assummed that CMA allows only user with "teacher" privilege to access this action
 * <br>
 * @author Dan Tran
 */

public class LoadStudentsToBeGradedAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( LoadStudentsToBeGradedAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        ClassGradeListForm theForm = (ClassGradeListForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        String classTypeStr = theForm.getClassType();
        ClassType classType = ClassType.fromString( classTypeStr );
        //FIXme catch error here

        if ( classType == ClassType.GIAOLY )
        {
            theForm.setFullClassName( loginProfile.getGiaolyClass().toString() + loginProfile.getGiaolySubClass() );
            theForm.setTitle( "Assign Gia\u0301o Ly\u0301 " + theForm.getFullClassName() + " Grades" );
        }
        else
        {
            theForm.setFullClassName( loginProfile.getVietnguClass().toString() + loginProfile.getVietnguSubClass() );
            theForm.setTitle( "Assign Viê\u0323t Ng\u01B0\u0303 " + theForm.getFullClassName() + " Grades" );

        }

        //find all giaoly students in the current school year belong to user login

        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? and schoolYear.school = ? and" );
        if ( classType == ClassType.GIAOLY )
        {
            queryBuff.append( " schoolYear.giaolyClass.name = ? and schoolYear.giaolyClass.subName = ?" );
        }
        else
        {
            queryBuff.append( " schoolYear.vietnguClass.name = ? and schoolYear.vietnguClass.subName = ?" );
        }

        queryBuff.append( " order by schoolYear.student.name.lastNameRaw" );

        List paramList = new ArrayList();
        paramList.add( GlobalConfig.getInstance().getCurrentYear() );
        paramList.add( loginProfile.getSchool().getId() );
        if ( classType == ClassType.GIAOLY )
        {
            paramList.add( loginProfile.getGiaolyClass() );
            paramList.add( loginProfile.getGiaolySubClass() );
        }
        else
        {
            paramList.add( loginProfile.getVietnguClass() );
            paramList.add( loginProfile.getVietnguSubClass() );
        }

        List typeList = new ArrayList();
        typeList.add( Hibernate.INTEGER );
        typeList.add( Hibernate.INTEGER );
        typeList.add( Hibernate.custom( ClassName.class ) );
        typeList.add( Hibernate.custom( ClassSubName.class ) );
        Type[] types = new Type[typeList.size()];
        typeList.toArray( types );

        try
        {
            Session session = SessionUtil.begin();

            Query query = session.createQuery( queryBuff.toString() );

            query.setParameters( paramList.toArray(), types );

            List schoolYears = query.list();

            theForm.removeAllGradeView();

            for ( int i = 0; i < schoolYears.size(); ++i )
            {
                SchoolYear schoolYear = (SchoolYear) schoolYears.get( i );
                StudentGradeView gradeView = theForm.getGradeView( i );
                if ( classType == ClassType.GIAOLY )
                {
                    gradeView.setGrade( schoolYear.getGiaolyClass().getGrade().toString() );
                }
                else
                {
                    gradeView.setGrade( schoolYear.getVietnguClass().getGrade().toString() );
                }

                gradeView.setSchoolYearId( schoolYear.getId().toString() );
                gradeView.setStudentName( schoolYear.getStudent().getName().getFullName() );
            }

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            log.error( "Error found during loading student grade list", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "success" ) );
    }

}