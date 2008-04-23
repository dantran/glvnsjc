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
import org.glvnsjc.view.TreeMenuBean;

/**
 * Standard action class. Always throws back to the monkeyForward page.
 * nothing more.
 *
 * @author Arron Bates <arron@keyboardmonkey.com>
 */
public class LoadMenuAction
    extends Action
{

    /* It's a lazy, but reliable way to get our stuff saved to disk */
    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        TreeMenuBean treeForm = (TreeMenuBean) form;

        treeForm.load( request );

        // have the view to display treeForm
        return ( mapping.findForward( "success" ) );
    }
}
