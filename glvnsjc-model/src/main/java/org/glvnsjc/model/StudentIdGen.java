package org.glvnsjc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.Session;

public class StudentIdGen
{

    private static StudentIdGen s_instance = null;

    private static boolean s_instanceFlag = false;

    private Integer studentId;

    private StudentIdGen()
    {
        reload();
    }

    public static StudentIdGen getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new StudentIdGen();

            s_instanceFlag = true;

            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

    public synchronized Integer nextStudentId()
    {
        this.studentId = new Integer( this.studentId.intValue() + 1 );

        return this.studentId;
    }

    public synchronized void reload()
    {
        String queryString = "select MAX(id) from Student";

        Connection conn = null;
        ResultSet rs = null;
        Statement stm = null;

        try
        {
            Session session = SessionUtil.begin();

            conn = session.connection();
            stm = conn.createStatement();
            rs = stm.executeQuery( queryString );
            while ( rs.next() )
            {
                studentId = new Integer( rs.getInt( 1 ) );
            }

            SessionUtil.end();

        }
        catch ( SQLException e )
        {
            SessionUtil.rollback( e );
        }
    }

}