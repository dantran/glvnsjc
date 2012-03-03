package org.glvnsjc.model;

import junit.framework.TestCase;

import org.hibernate.Session;

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
