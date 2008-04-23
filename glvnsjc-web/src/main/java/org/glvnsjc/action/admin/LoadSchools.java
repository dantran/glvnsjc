package org.glvnsjc.action.admin;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.glvnsjc.model.SchoolList;

/**
 * <tt>LoadSchools</tt> load the only one SiteConfig from database <br<br>
 *
 * It is assummed that CMA protects this action from unorthorized user
 * <br>
 * @author Dan Tran
 */

public class LoadSchools
    extends org.apache.struts.action.Action
{

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        Collection schools = SchoolList.getInstance().schoolMapWithIdKey().values();
        request.setAttribute( "schools", schools );
        return ( mapping.findForward( "success" ) );
    }

}