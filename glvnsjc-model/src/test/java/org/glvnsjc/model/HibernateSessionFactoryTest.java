package org.glvnsjc.model;

import org.hibernate.Session;

import junit.framework.TestCase;

public class HibernateSessionFactoryTest
    extends TestCase
{

    public void testGetSession()
    {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.close();
    }

    public void testShutdown()
    {
        HibernateSessionFactory.shutdown();
    }
    
}
