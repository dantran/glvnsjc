package org.glvnsjc.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SiteConfig;
import org.glvnsjc.model.hibernate.SessionUtil;

/**
 * <tt>SaveGlobalConfig</tt> update the only one SiteConfig from database <br<br>
 *
 * It is assummed that CMA protects this action from unorthorized user
 * <br>
 * @author Dan Tran
 */

public class SaveGlobalConfig
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( SaveGlobalConfig.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {

        if ( !request.isUserInRole( Privilege.ADMIN.toString() ) )
        {
            ActionMessages errors = new ActionMessages();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.permissionDeny" ) );
            saveErrors( request, errors );
            return ( mapping.getInputForward() );
        }        

        try
        {
            Session session = SessionUtil.begin(); 
            SiteConfig config = (SiteConfig) session.load( SiteConfig.class, LoadGlobalConfig.GLOBAL_CONFIG_ID );
            BeanUtils.copyProperties( config, form );
            SessionUtil.end();
            org.glvnsjc.model.GlobalConfig.getInstance().reload();
            org.glvnsjc.view.option.SchoolYearOptions.getInstance().reload();
            
            this.saveMessages( request, ActionUtil.createActionMessages( "message.update.success" ) );
        }
        catch ( Exception e )
        {
            log.error( "Error updating SiteConfig", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "success" ) );
    }

}