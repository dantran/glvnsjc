package org.glvnsjc.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * Implementation of <strong>ExceptionHandler</strong> that
 * handles any Exceptions that are bundles up to the Action
 * layer.  This allows us to remove generic try/catch statements
 * from our Action Classes.
 *
 * <p>
 * <a href="ActionExceptionHandler.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Revision: 1.2 $ $Date: 2006/07/10 00:25:57 $
 */
public final class ActionExceptionHandler
    extends ExceptionHandler
{

    private static Log log = LogFactory.getLog( ActionExceptionHandler.class );

    //~ Methods ================================================================

    /**
     * This method handles any java.lang.Exceptions that are not
     * caught in previous classes.  It will loop through and get
     * all the causes (exception chain), create ActionMessages,
     * add them to the request and then forward to the input.
     *
     * @see org.apache.struts.action.ExceptionHandler#execute
     *      (
     *          java.lang.Exception,
     *          org.apache.struts.config.ExceptionConfig,
     *          org.apache.struts.action.ActionMapping,
     *          org.apache.struts.action.ActionForm,
     *          javax.servlet.http.HttpServletRequest,
     *          javax.servlet.http.HttpServletResponse
     *      )
     */
    public ActionForward execute( Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance,
                                 HttpServletRequest request, HttpServletResponse response )
        throws ServletException
    {

        ActionForward forward = super.execute( ex, ae, mapping, formInstance, request, response );

        ActionMessage error = null;
        String property = null;

        // Get the chained exceptions (causes) and add them to the
        // list of errors as well
        while ( ex != null )
        {
            String msg = ex.getMessage();
            log.error( msg );
            error = new ActionMessage( "errors.detail", msg );
            property = error.getKey();
            ex = (Exception) ex.getCause();

            if ( ( ex != null ) && ( ex.getMessage() != null ) )
            {
                // check to see if the child message is the same
                // if so, don't store it
                if ( msg.indexOf( ex.getMessage() ) == -1 )
                {
                    storeException( request, property, error, forward );
                }
            }
            else
            {
                storeException( request, property, error, forward );
            }
        }

        return forward;
    }

    /**
     * This method overrides the the ExceptionHandler's storeException
     * method in order to create more than one error message.
     *
     * @param request - The request we are handling
     * @param property  - The property name to use for this error
     * @param error - The error generated from the exception mapping
     * @param forward - The forward generated from the input path (from the form or exception mapping)
     * @param scope - The scope of the exception mapping.
     */
    protected void storeException( HttpServletRequest request, String property, ActionMessage error,
                                  ActionForward forward )
    {

        ActionMessages errors = (ActionMessages) request.getAttribute( Globals.ERROR_KEY );
        if ( errors == null )
        {
            errors = new ActionMessages();
        }
        errors.add( property, error );
        request.setAttribute( Globals.ERROR_KEY, errors );
    }
}
