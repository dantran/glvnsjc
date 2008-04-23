package org.glvnsjc.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.SiteConfig;
import org.glvnsjc.model.hibernate.SessionUtil;

/**
 * <tt>LoadGlobalConfig</tt> load the only one SiteConfig from database <br<br>
 *
 * It is assummed that CMA protects this action from unorthorized user
 * <br>
 * @author Dan Tran
 */

public class LoadGlobalConfig
    extends org.apache.struts.action.Action
{

    //there is only one and only one SiteConfig
    public static final Integer GLOBAL_CONFIG_ID = new Integer( 1 );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response )
        throws Exception
    {
        
        try
        {
            Session session = SessionUtil.begin();
            SiteConfig config = (SiteConfig) session.load( SiteConfig.class, GLOBAL_CONFIG_ID );
            BeanUtils.copyProperties( form, config );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "success" ) );
    }

}