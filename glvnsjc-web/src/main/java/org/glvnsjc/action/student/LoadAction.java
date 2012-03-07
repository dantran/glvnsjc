package org.glvnsjc.action.student;

import java.util.Collections;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.converter.Convert;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.Student;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.view.SchoolYearView;
import org.glvnsjc.view.StudentForm;
import org.hibernate.Session;

/**
 * <tt>LoadAction</tt> populates StudentForm based on a student id, it also
 * setup the display attributes that control the display of SchoolYear records.
 * Here are the rules:
 *   - All fields are disable if the form is setup for delete
 *   - All passed schoolYear are disable
 *   - Only those with Master privilege can modify School field.
 *
 * <br>
 * @author Dan Tran
 */

public class LoadAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( LoadAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {
        StudentForm theForm = (StudentForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
        DispatchType command = DispatchType.fromStr( request.getParameter( "command" ) );

        //setup the return title page
        MessageResources messages = this.getResources( request );
        theForm.setTitle( messages.getMessage( "title.student." + command ) );

        //setup action button to show user what action they allow to invoke
        theForm.setSubmitKey( messages.getMessage( "button." + command ) );

        //goto db and load the record, except the add case
        if ( command == DispatchType.DELETE || command == DispatchType.UPDATE )
        {
            try
            {
                Integer id = new Integer( request.getParameter( "id" ) );

                Session session = SessionUtil.begin();

                Student student = (Student) session.load( Student.class, id );

                BeanUtils.copyProperties( theForm.getStudent(), student );
                
                theForm.getStudent().setBaptismDate( Convert.DateToString( student.getBaptism().getDate() ) );
                theForm.getStudent().setBaptismLocation( student.getBaptism().getLocation() );
                theForm.getStudent().setEucharistDate( Convert.DateToString( student.getEucharist().getDate() ) );
                theForm.getStudent().setEucharistLocation( student.getEucharist().getLocation() );
                

                theForm.removeAllSchoolYear();
                int index = 0;
                boolean currentSchoolYearFound = false;

                Iterator iterator = student.getSchoolYears().iterator();

                int currentSchoolYear = GlobalConfig.getInstance().getCurrentYear().intValue();

                while ( iterator.hasNext() )
                {
                    SchoolYear schoolYear = (SchoolYear) iterator.next();
                    SchoolYearView schoolYearView = theForm.getSchoolYear( index );
                    BeanUtils.copyProperties( schoolYearView, schoolYear );
                    //copy others
                    schoolYearView.setSchoolId( schoolYear.getSchool().getId().toString() );
                    schoolYearView.setSchoolName( schoolYear.getSchool().getName() );
                    //tell the view to allow current schoolYear editable
                    if ( schoolYear.getYear() == currentSchoolYear )
                    {
                        currentSchoolYearFound = true;
                        schoolYearView.setEditAllow( true );

                        theForm.setHasAward( true );
                    }
                    index++;

                }

                //there is no current schoolYear record found in the database, allow the view to and a new one. Only Master can do this
                if ( command == DispatchType.UPDATE && request.isUserInRole( Privilege.COMMUNITY.toString() ) )
                {
                    if ( !currentSchoolYearFound )
                    {
                        theForm.setOptionToAddSchoolYear( true );
                        theForm.setOptionToRemoveCurrentYear( false );
                    }
                    else
                    {
                        theForm.setOptionToAddSchoolYear( false );
                        theForm.setOptionToRemoveCurrentYear( true );
                    }
                }

                theForm.setCancelAllow( true );

                SessionUtil.end();

            }
            catch ( Exception e )
            {
                log.error( "Error loading student", e );

                SessionUtil.rollback( e );
            }
        }
        else
        {
            //add case, create blank form of student and one current schoolYear
            //delay privilege check until the real action happens
            theForm.removeAllSchoolYear();
            SchoolYearView schoolYearView = theForm.getSchoolYear( 0 );
            schoolYearView.setYear( GlobalConfig.getInstance().getCurrentYear().intValue() );
            schoolYearView.setEditAllow( true );
        }

        //disable page base on command

        boolean readonlyPage = false;
        if ( command == DispatchType.DELETE )
        {
            readonlyPage = true;;
        }

        //disable some field base on privilege
        boolean readonlySchool = false;
        boolean readonlySchoolYear = false;
        //any thing below MASTER can not change the school
        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            readonlySchool = true;
            readonlySchoolYear = true;
        }

        //readonly page take precident
        readonlySchool = readonlyPage || readonlySchool;
        readonlySchoolYear = readonlyPage || readonlySchoolYear;

        theForm.setReadonlyPage( readonlyPage );
        theForm.setReadonlySchool( readonlySchool );
        theForm.setReadonlySchoolYear( readonlySchoolYear );
        theForm.setReadonlyCertificates( ! request.isUserInRole( Privilege.COMMUNITY.toString() ) );

        // Set a transactional control token to prevent double posting
        saveToken( request );

        if ( log.isDebugEnabled() )
        {
            log.debug( loginProfile.getUserId() + " loaded " + theForm.getStudent().getName().getFullName() );
        }

        Collections.sort( theForm.getSchoolYears() );
        
        return ( mapping.findForward( "success" ) );
    }

}