package org.glvnsjc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.securityfilter.AppPrincipal;

public class StartAction
    extends org.apache.struts.action.Action
{

    //------------------------------------------------------------ Action Methods

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        AppPrincipal principal = (AppPrincipal) request.getUserPrincipal();

        //show user responsibility
        Privilege previlege = principal.getLoginProfile().getPrivilege();
        String introductionLink = "./doc/blank.html";
        if ( previlege.equals( Privilege.SCHOOL ) )
        {
            introductionLink = "./doc/teachertasks.htm";
        }
        if ( previlege.equals( Privilege.PRINCIPAL ) )
        {
            introductionLink = "./doc/principaltasks.htm";
        }

        request.setAttribute( "introductionLink", introductionLink );
        return ( mapping.findForward( "success" ) );
    }

}