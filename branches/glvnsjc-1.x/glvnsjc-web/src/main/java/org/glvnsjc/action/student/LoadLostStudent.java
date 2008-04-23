package org.glvnsjc.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.LostStudent;
import org.glvnsjc.model.hibernate.SessionUtil;

/**
 * <tt>LoadLostStudent</tt> populates StudentForm based on a student id, it also
 * setup the display attributes that control the display of SchoolYear records.
 * Here are the rules:
 *   - All fields are disable if the form is setup for delete
 *   - All passed schoolYear are disable
 *   - Only those with Master privilege can modify School field.
 *
 * <br>
 * @author Dan Tran
 */

public class LoadLostStudent
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( LoadLostStudent.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
        DispatchType command = DispatchType.fromStr( request.getParameter( "command" ) );

        //setup the return title page
        MessageResources messages = this.getResources( request );

        //setup action button to show user what action they allow to invoke
        theForm.set( "submitButtonName", messages.getMessage( "button." + command ) );

        //goto db and load the record, except the add case
        if ( command == DispatchType.DELETE || command == DispatchType.UPDATE )
        {
            try
            {
                Integer id = new Integer( request.getParameter( "id" ) );
                
                Session session = SessionUtil.begin();
                
                LostStudent student = (LostStudent) session.load( LostStudent.class, id );
                BeanUtils.copyProperties( theForm, student );
                BeanUtils.copyProperties( theForm, student.getName() );
                BeanUtils.copyProperty( theForm, "giaolyClassName", student.getGiaolyClass().getName() );
                BeanUtils.copyProperty( theForm, "giaolyClassSubName", student.getGiaolyClass().getSubName() );
                BeanUtils.copyProperty( theForm, "vietnguClassName", student.getVietnguClass().getName() );
                BeanUtils.copyProperty( theForm, "vietnguClassSubName", student.getVietnguClass().getSubName() );

                SessionUtil.end();
                
            }
            catch ( Exception e )
            {
                log.error( "Error load lost student", e );
                SessionUtil.rollback( e );
            }
        }
        else
        {
            //add case
        }

        // Set a transactional control token to prevent double posting
        saveToken( request );

        if ( log.isDebugEnabled() )
        {
            log.debug( loginProfile.getUserId() + " loaded " + theForm );
        }

        return ( mapping.findForward( "success" ) );
    }

}