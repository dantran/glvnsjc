package org.glvnsjc.action.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.MessageResources;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.School;
import org.glvnsjc.model.SchoolList;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.Session;

// note: the form must have property named as "action" since the dispatch action
// already use this. Is this a bug in DispatchLookupAction?

public class DispatchSchool
    extends org.apache.struts.actions.LookupDispatchAction
{

    private static Log log = LogFactory.getLog( DispatchSchool.class );

    protected Map getKeyMethodMap()
    {
        Map map = new HashMap();
        map.put( "button.add", "add" );
        map.put( "button.update", "update" );
        map.put( "button.delete", "delete" );
        return map;
    }

    public ActionForward add( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;
        School school = new School();
        BeanUtils.copyProperties( school, theForm );

        try
        {
            Session session = SessionUtil.begin();
            Object id = session.save( school );
            SessionUtil.end();

            //after add, allow update
            MessageResources messages = this.getResources( request );
            theForm.set( "submitButton", messages.getMessage( "button.update" ) );
            theForm.set( "title", messages.getMessage( "title.school.update" ) );
            theForm.set( "command", "update" );

            //send the object id back to the form
            theForm.set( "id", id.toString() );
            log.info( "Add School: " + form );
            this.saveMessages( request, ActionUtil.createActionMessages( "message.add.success" ) );

            SchoolList.getInstance().reload();

        }
        catch ( Exception e )
        {
            log.error( "Error during adding new LoginProfile", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "update" ) );

    }

    public ActionForward update( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;

        try
        {
            Session session = SessionUtil.begin();
            School school = (School) session.load( School.class, new Integer( theForm.get( "id" ).toString() ) );
            BeanUtils.copyProperties( school, theForm );
            SessionUtil.end();

            SchoolList.getInstance().reload();
            log.info( "update School: " + form );
            this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );

        }
        catch ( Exception e )
        {
            log.error( "Error during updating a school record", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "update" ) );

    }

    public ActionForward delete( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;

        try
        {

            Session session = SessionUtil.begin();
            School school = (School) session.load( School.class, new Integer( theForm.get( "id" ).toString() ) );
            session.delete( school );
            SessionUtil.end();

            SchoolList.getInstance().reload();
            log.info( "delete School: " + form );
            this.saveMessages( request, ActionUtil.createActionMessages( "message.delete.success" ) );

        }
        catch ( Exception e )
        {
            log.error( "Error during updating a school record", e );
            SessionUtil.rollback( e );
        }

        //the record is gone, send the user back to search page
        return ( mapping.findForward( "list" ) );
    }

}