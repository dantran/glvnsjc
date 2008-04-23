package org.glvnsjc.util;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SiteConfig;
import org.glvnsjc.model.GlobalConfig;

public class SiteMaintainantFilter
    implements Filter
{

    protected String maintainantPage = null;

    protected FilterConfig filterConfig = null;

    public void destroy()
    {
        this.maintainantPage = null;
        this.filterConfig = null;
    }

    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
        throws IOException, ServletException
    {

        try
        {
            SiteConfig siteConfig = GlobalConfig.getInstance().getGlobalConfig();
            if ( !siteConfig.isSiteActive() )
            {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                //will not reject any request which does not require authentication
                if ( httpRequest.getUserPrincipal() != null )
                {
                    if ( !httpRequest.isUserInRole( Privilege.ADMIN.toString() ) )
                    {
                        //ask user to comback later
                        HttpServletResponse httpResponse = (HttpServletResponse) response;
                        showMaintainantPage( httpRequest, httpResponse );
                        return;
                    }
                }
            }
        }
        catch ( HibernateException e )
        {
            throw new ServletException( e );
        }

        // Pass control on to the next filter
        chain.doFilter( request, response );

    }

    public void init( FilterConfig filterConfig )
        throws ServletException
    {
        this.filterConfig = filterConfig;
        this.maintainantPage = filterConfig.getInitParameter( "maintainantPage" );
    }

    protected void showMaintainantPage( HttpServletRequest request, HttpServletResponse response )
        throws IOException, ServletException
    {

        // redirect to login page
        response.sendRedirect( response.encodeRedirectURL( request.getContextPath() + this.maintainantPage ) );
        request.getSession().invalidate();
    }

}
