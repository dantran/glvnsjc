package org.glvnsjc.model;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassSubName;
import org.glvnsjc.model.Gender;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.SiteConfig;
import org.glvnsjc.model.TeacherType;
import org.glvnsjc.model.hibernate.SessionUtil;

/**
 * One time call before access database to make sure we have directory and 
 * initial hsqldb setup correctly
 * @author dtran
 *
 */
public class DBSetup
{

    public static void initDB()
    {
        try
        {
            Session session  = SessionUtil.begin();
            List siteConfigs = session.createQuery( "from org.glvnsjc.model.SiteConfig siteConfig" ).list();
            if ( siteConfigs.size() == 0 )
            {
                Calendar ca = Calendar.getInstance();
                Integer currentYear = new Integer( ca.get( Calendar.YEAR ) );
                SiteConfig siteConfig = new SiteConfig( currentYear, true );
                session.save( siteConfig );

                //create dummy admin account
                LoginProfile loginProfile = new LoginProfile();
                loginProfile.setUserId( "admin" );
                loginProfile.setPassword( "admin" );
                loginProfile.setLoginable( true );
                loginProfile.setRemovable( false );
                loginProfile.setPrivilege( Privilege.ADMIN );
                loginProfile.setTeacherType( TeacherType.MASTER );
                loginProfile.setGender( Gender.UNASSIGNED );
                loginProfile.setGiaolyClass( ClassName.UNASSIGNED );
                loginProfile.setGiaolySubClass( ClassSubName.UNASSIGNED );
                loginProfile.setVietnguClass( ClassName.UNASSIGNED );
                loginProfile.setVietnguSubClass( ClassSubName.UNASSIGNED );
                session.save( loginProfile );
            }
            
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

    }
}
