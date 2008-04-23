package org.glvnsjc.action.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.view.StudentClassView;
import org.glvnsjc.view.StudentsToBeSubClassedForm;

/**
 * <tt>LoadStudentsToBeSubClassedAction</tt> prepares a a list of students
 * belong to a requested class so that he/she can assign subclass in one form
 *
 * It is assummed that CMA allows only user with "principal" privilege to access this action
 * <br>
 * @author Dan Tran
 */

public class LoadStudentsToBeSubClassedAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( LoadStudentsToBeSubClassedAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        StudentsToBeSubClassedForm theForm = (StudentsToBeSubClassedForm) form;
        ClassName className = ClassName.fromString( theForm.getClassName() );

        //return blank form, ie first time hit this action
        if ( className == ClassName.UNASSIGNED )
        {
            return ( mapping.findForward( "success" ) );
        }

        ClassType classType = ClassType.fromString( theForm.getClassType() );
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        //find all giaoly students in the current school year belong to user login

        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? and schoolYear.school = ? and" );
        if ( classType == ClassType.GIAOLY )
        {
            queryBuff.append( " schoolYear.giaolyClass.name = ?" );
        }
        else
        {
            queryBuff.append( " schoolYear.vietnguClass.name = ?" );
        }
        queryBuff.append( " order by schoolYear.student.birthDate" );

        List paramList = new ArrayList();
        paramList.add( GlobalConfig.getInstance().getCurrentYear() );
        paramList.add( loginProfile.getSchool().getId() );
        paramList.add( className );

        List typeList = new ArrayList();
        typeList.add( Hibernate.INTEGER );
        typeList.add( Hibernate.INTEGER );
        typeList.add( Hibernate.custom( ClassName.class ) );
        Type[] types = new Type[typeList.size()];
        typeList.toArray( types );

        try
        {
            Session session = SessionUtil.begin();

            Query query = session.createQuery( queryBuff.toString() );

            query.setParameters( paramList.toArray(), types );

            List schoolYears = query.list();

            ArrayList studentClassViews = new ArrayList();
            for ( int i = 0; i < schoolYears.size(); ++i )
            {
                SchoolYear schoolYear = (SchoolYear) schoolYears.get( i );
                StudentClassView classView = new StudentClassView();
                classView.setSchoolYearId( schoolYear.getId().toString() );
                classView.setStudentName( schoolYear.getStudent().getName().getFullName() );
                BeanUtils.copyProperty( classView, "birthDate", schoolYear.getStudent().getBirthDate() );

                if ( classType == ClassType.GIAOLY )
                {
                    classView.setClassName( schoolYear.getGiaolyClass().getName().toString() );
                    classView.setClassSubName( schoolYear.getGiaolyClass().getSubName().toString() );
                }
                else
                {
                    classView.setClassName( schoolYear.getVietnguClass().getName().toString() );
                    classView.setClassSubName( schoolYear.getVietnguClass().getSubName().toString() );
                }
                studentClassViews.add( classView );
            }
            
            SessionUtil.end();

            theForm.setStudentClassViews( studentClassViews );
        }
        catch ( Exception e )
        {
            log.error( "Error found during loading students to be subclassed", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "success" ) );
    }

}