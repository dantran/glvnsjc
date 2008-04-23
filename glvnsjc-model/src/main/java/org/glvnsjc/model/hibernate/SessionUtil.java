package org.glvnsjc.model.hibernate;

import org.glvnsjc.model.HibernateSessionFactory;
import org.hibernate.Session;

/**
 * Session Transaction Utility
 * @author dtran
 *
 */
public class SessionUtil
{
    public static Session begin()
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        return session;
    }
    
    public static void end()
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.getTransaction().commit();
    }    
    
    public static void rollback( Exception e )
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.getTransaction().rollback();
        throw new RuntimeException( e );
    }    
    
    public static void rollback()
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.getTransaction().rollback();
    }        
}
