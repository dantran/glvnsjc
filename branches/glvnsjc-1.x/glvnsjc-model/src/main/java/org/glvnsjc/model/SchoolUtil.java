package org.glvnsjc.model;

import java.util.List;

import org.glvnsjc.model.hibernate.SessionUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class SchoolUtil
{
    public static String getPrincipalName( School school )
    {
        String principalName = "";
        
        Object [] paramList = new Object[2];
        Type [] typeList = new Type[2];
        StringBuffer buffer = new StringBuffer( "from org.glvnsjc.model.LoginProfile instructor where instructor.school.id = ? and instructor.teacherType = ? " );
        paramList[0] = school.getId();
        paramList[1] = TeacherType.PRINCIPAL;
        typeList[0] = Hibernate.INTEGER;
        typeList[1] = Hibernate.custom( TeacherType.class );
        
        try
        {
            Session session = SessionUtil.begin();
            Query query = session.createQuery( buffer.toString() );
            query.setParameters( paramList, typeList );
           

            List list = query.list();
            
            if ( list.size() != 0 )
            {
                LoginProfile principal = (LoginProfile) list.get( 0 );
                principalName = principal.getName().getFullName();
            }
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }        
        
        return principalName;
    }
    
}
