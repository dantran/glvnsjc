package org.glvnsjc.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import org.glvnsjc.model.SchoolList;
import org.glvnsjc.model.School;

public class LoadSchool
    extends org.apache.struts.action.Action
{


    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;

        String id = (String) theForm.get( "id" );
        String action = (String) theForm.get( "command" );

        //setup action button to show user what action they allow to invoke
        MessageResources messages = this.getResources( request );
        theForm.set( "submitButton", messages.getMessage( "button." + action ) );
        theForm.set( "title", messages.getMessage( "title.school." + action ) );

        if ( action.equals( "delete" ) || action.equals( "update" ) )
        {
            Integer objId = new Integer( id );
            School school = SchoolList.getInstance().getSchool( objId );
            BeanUtils.copyProperties( theForm, school );
        }
        else
        {
        }

        return ( mapping.findForward( "success" ) );
    }

}