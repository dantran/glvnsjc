package org.glvnsjc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Logout
    extends org.apache.struts.action.Action
{

    /**
     * Logging output for this plug in instance.
     */
    private static Log log = LogFactory.getLog( Logout.class );

    //------------------------------------------------------------ Action Methods

    //Load a student based on studentId

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        log.info( request.getUserPrincipal().getName() + " logged out." );
        request.getSession().invalidate();

        return ( mapping.findForward( "success" ) );
    }

}