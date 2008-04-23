/*
 * This Source code was written by theKM* (the KeyboardMonkey) and is held under
 * The popular ISYM.NSMYP technology agreement (I've Shown You Mine, Now Show Me
 * Yours Please). In which case, writing source that uses this source in part or
 * in whole means that it would be nice of you to show me at least what it does
 * and how my source played a part.
 * All rights reserved... arron@keyboardmonkey.com
 */
package org.glvnsjc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Standard action class. Always throws back to the monkeyForward page.
 * nothing more.
 *
 * @author Arron Bates <arron@keyboardmonkey.com>
 */
public class UpdateMenuAction
    extends Action
{

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        /* return back to the page we came */
        return ( mapping.findForward( "input" ) );
    }
}
