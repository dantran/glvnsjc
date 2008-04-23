package org.glvnsjc.securityfilter;

import java.io.Serializable;
import java.security.Principal;

import org.glvnsjc.model.LoginProfile;

/**
 * AppPrincipal - a simple, serializable Principal.
 *
 * @author Dan Tran (danttran@hotmail.com)

 */
public class AppPrincipal
    implements Principal, Serializable
{

    private LoginProfile loginProfile;

    /**
     * Default constructor is private. Use AppPrincipal(User user) constructor.
     */
    private AppPrincipal()
    {
    }

    /**
     * Constructor
     */
    public AppPrincipal( LoginProfile loginProfile )
    {
        this.loginProfile = loginProfile;
    }

    public boolean isUserInRole( String roleName )
    {
        return loginProfile.getPrivilege().isInRole( roleName );
    }

    /**
     * Returns the name of this principal.
     *
     * @return the name of this principal.
     */
    public String getName()
    {
        return this.loginProfile.getUserId();
    }

    public LoginProfile getLoginProfile()
    {
        return this.loginProfile;
    }

    public void setLoginProfile( LoginProfile newValue )
    {
        this.loginProfile = newValue;
    }

    /**
     * Compares this principal to the specified object.
     *
     * @param obj object to compare with.
     *
     * @return true if the object passed in is a AppPrincipal with the same name.
     */
    public boolean equals( Object obj )
    {
        if ( obj instanceof AppPrincipal )
        {
            return this.getName().equals( ( (AppPrincipal) obj ).getName() ); //fixme
        }
        return false;
    }

    /**
     * Returns a string representation of this principal.
     *
     * @return a string representation of this principal.
     */
    public String toString()
    {
        return "AppPrincipal[name = \'" + this.getName() + "\']";
    }

    /**
     * Returns a hashcode for this principal.
     *
     * @return a hashcode for this principal.
     */
    public int hashCode()
    {
        return this.getName().hashCode();
    }
}

// ----------------------------------------------------------------------------
// EOF
