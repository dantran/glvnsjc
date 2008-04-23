package org.glvnsjc.action.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.Query;

/**
 * <tt>ListLostStudents</tt> prepares dynamic search criteria
 * disable School and SchooYear search for those with privilege principal and below
 *
 * It is assummed that CMA allows only user with "teacher" privilege to access this action
 * <br>
 * @author Dan Tran
 */

public class ListLostStudents
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( ListLostStudents.class );

    //------------------------------------------------------------ Action Methods

    //Load a student based on studentId

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
        
        //search student that the current login user created
        String searchStr = "from org.glvnsjc.model.LostStudent student where student.reportedBy = "
            + loginProfile.getId();
        
        if ( request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            // get everything for master or better account
            searchStr = "from org.glvnsjc.model.LostStudent student";
        }
        else if ( request.isUserInRole( Privilege.PRINCIPAL.toString() ) )
        {
            searchStr = "from org.glvnsjc.model.LostStudent student where student.reportedBy.school= "
                + loginProfile.getSchool().getId();
        }


        try
        {
            Session session = SessionUtil.begin();
                        
            Query query = session.createQuery( searchStr ) ;
            
            List schoolYears = query.list();
            
            request.getSession().setAttribute( "list", schoolYears );

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            log.error( "Error loading lost students", e );
            SessionUtil.rollback( e );
        }
        
        return mapping.findForward( "success" );

    }

}