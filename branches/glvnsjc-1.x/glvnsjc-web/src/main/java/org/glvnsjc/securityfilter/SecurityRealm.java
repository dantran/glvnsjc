package org.glvnsjc.securityfilter;

import java.security.MessageDigest;
import java.security.Principal;
import java.util.Collection;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glvnsjc.model.HibernateSessionFactory;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.securityfilter.realm.SecurityRealmInterface;

public class SecurityRealm
    implements SecurityRealmInterface
{

    /**
     * Logging output for this plug in instance.
     */
    private static Log log = LogFactory.getLog( SecurityRealm.class );

    /**
     * Authenticate a user.
     *
     * Implement this method in a subclass to avoid dealing with Principal objects.
     *
     * @param username a username
     * @param password a plain text password, as entered by the user
     *
     * @return null if the user cannot be authenticated, otherwise a Pricipal object is returned
     */
    public boolean booleanAuthenticate( String userName, String password )
    {
        return ( authenticate( userName, password ) != null );
    }

    /**
     * Authenticate a user.
     *
     * @param username a username
     * @param password a plain text password, as entered by the user
     *
     * @return a Principal object representing the user if successful, false otherwise
     */

    public Principal authenticate( String userName, String password )
    {

        Principal principal = null;
        LoginProfile loginProfile = null;

        //preventing user mistake putting spaces at the end and begin
        userName = userName.trim();
        password = password.trim();
        String key = password.substring( 32 );
        String userPasswordWithKeyInHex = password.substring( 0, 32 );

        try
        {

            Session session = SessionUtil.begin();

            String hsql = "from org.glvnsjc.model.LoginProfile loginProfile where loginProfile.userId = ? and loginProfile.loginable = ?";
            Query query = session.createQuery( hsql );

            Object[] params = new Object[] { userName, new Boolean( true ) };
            Type[] types = new Type[] { Hibernate.STRING, Hibernate.BOOLEAN };

            query.setParameters( params, types );

            Collection c = query.list();

            if ( !c.isEmpty() )
            {
                loginProfile = (LoginProfile) c.iterator().next();
                MessageDigest md = MessageDigest.getInstance( "MD5" );
                md.update( ( loginProfile.getPassword() + key ).getBytes() );
                String hexDigestPassword = HexString.bufferToHex( md.digest() ).toLowerCase();
                if ( hexDigestPassword.equals( userPasswordWithKeyInHex ) )
                {
                    principal = new AppPrincipal( loginProfile );
                    String school = "";
                    if ( loginProfile.getSchool() != null )
                    {
                        school = "@" + loginProfile.getSchool().getShortName();
                    }
                    log.info( userName + school + " logged in." );
                }
            }

            if ( principal == null )
            {
                log.info( "Login failed for " + userName );
            }

            SessionUtil.end();

        }
        catch ( Exception e )
        {
            log.fatal( "Fatal error dectected in AppPrincipal.authenticate", e );
            principal = null;
            SessionUtil.rollback( e );
        }

        return principal;
    }

    /**
     * Test for role studentship.
     *
     * Use Principal.getName() to get the username from the principal object.
     *
     * @param principal Principal object representing a user
     * @param rolename name of a role to test for studentship
     *
     * @return true if the user is in the role, false otherwise
     */
    public boolean isUserInRole( Principal principal, String roleName )
    {

        if ( principal != null )
        {
            AppPrincipal appPrincipal = (AppPrincipal) principal;
            return appPrincipal.isUserInRole( roleName );
        }

        return false;

    }

}

// ----------------------------------------------------------------------------
// EOF
