package org.glvnsjc.model;

import java.util.ArrayList;
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

        Object[] paramList = new Object[2];
        Type[] typeList = new Type[2];
        StringBuffer buffer = new StringBuffer(
                                                "from org.glvnsjc.model.LoginProfile instructor where instructor.school.id = ? and instructor.teacherType = ? " );
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

    public static int getStudentCount( School school, Integer schoolYear, ClassType classType )
    {
        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? " );
        queryBuff.append( "and schoolYear.school = ? " );

        //setup the params
        List paramList = new ArrayList();
        List typeList = new ArrayList();

        paramList.add( schoolYear );
        typeList.add( Hibernate.INTEGER );

        paramList.add( school.getId() );
        typeList.add( Hibernate.INTEGER );

        Type[] types = new Type[typeList.size()];
        typeList.toArray( types );

        List schoolYears = null;

        try
        {
            Session session = SessionUtil.begin();

            Query query = session.createQuery( queryBuff.toString() );

            query.setParameters( paramList.toArray(), types );

            schoolYears = query.list();

            SessionUtil.end();

        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        return schoolYears.size();
    }
}
