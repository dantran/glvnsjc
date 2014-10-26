package org.glvnsjc.model;

import java.util.Date;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.Session;

import com.glvnsjc.test.TestCase;

public class LoginProfileTest
    extends TestCase
{

    private static Integer userId;

    /**
     * This test how minimal requirements to create a student
     *
     * @throws Exception
     */
    public void testAddMinimalLoginProfile()
        throws Exception
    {
        LoginProfile user = new LoginProfile();
        Name name = new Name( "last", "middle", "first" );
        user.setName( name );
        user.setUserId( "userId" );
        user.setPrivilege( Privilege.ADMIN );
        user.setTeacherType( TeacherType.MASTER );
        user.setPassword( "password" );
        user.setGiaolyClass( ClassName.UNASSIGNED );
        user.setVietnguClass( ClassName.UNASSIGNED );
        user.setGiaolySubClass( ClassSubName.UNASSIGNED );
        user.setVietnguSubClass( ClassSubName.UNASSIGNED );
        user.setGender( Gender.UNASSIGNED );
        try
        {
            Session session = SessionUtil.begin();
            userId = (Integer) session.save( user );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    /**
     * This tests how to add certificate into LoginProfile
     *
     * @throws Exception
     */
    public void testAddCertificates()
        throws Exception
    {
        LoginProfile user = null;
        try
        {
            Session session = SessionUtil.begin();
            user = (LoginProfile) session.load( LoginProfile.class, userId );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        Assert.assertEquals( 0, user.getCertificates().size() );

        try
        {
            Session session = SessionUtil.begin();

            Certificate cert1 = new Certificate();
            cert1.setType( CertificateType.FINGER_PRINT_PROCESS );
            cert1.setTeacher( user );
            cert1.setDate( new Date(0) );
            session.save( cert1 );

            Certificate cert2 = new Certificate();
            cert2.setType( CertificateType.CATACHIST_CERTIFICATION );
            cert2.setTeacher( user );
            cert2.setDate( new Date(0) );
            session.save( cert2 );

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        try
        {
            Session session = SessionUtil.begin();
            user = (LoginProfile) session.load( LoginProfile.class, userId );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        Assert.assertEquals( 2, user.getCertificates().size() );
        Assert.assertEquals( -57600000, ((Certificate)user.getCertificates().iterator().next()).getDate().getTime() );
    }

    public void testUpdateCertificate()
        throws Exception
    {
        LoginProfile user = null;

        try
        {
            Session session = SessionUtil.begin();
            user = (LoginProfile) session.load( LoginProfile.class, userId );
            Certificate cert = (Certificate) user.getCertificates().iterator().next();
            cert.setDate( new Date() );
            session.save( user );
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        try
        {
            Session session = SessionUtil.begin();
            user = (LoginProfile) session.load( LoginProfile.class, userId );
            session.save( user );
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        Certificate cert = (Certificate) user.getCertificates().iterator().next();
        Assert.assertNotSame(-57600000, cert.getDate().getTime() );
    }

    public void testDelete()
        throws Exception
    {
        try
        {
            Session session = SessionUtil.begin();
            LoginProfile lf = (LoginProfile) session.load( LoginProfile.class, userId );
            session.delete( lf );

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

    }

    public LoginProfileTest( String name )
    {
        super( name );
    }

    public static Test suite()
        throws Exception
    {
        TestSuite st = new TestSuite();
        st.addTest( new LoginProfileTest( "testAddMinimalLoginProfile" ) );
        st.addTest( new LoginProfileTest( "testAddCertificates" ) );
        st.addTest( new LoginProfileTest( "testUpdateCertificate" ) );

        st.addTest( new LoginProfileTest( "testDelete" ) );
        return st;
    }
}
