package org.glvnsjc.action.student;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.glvnsjc.model.Address;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassSubName;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Name;
import org.glvnsjc.model.SchoolYear;

import org.glvnsjc.action.ActionUtil;

import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.util.StringUtil;

public class StudentUtils
{

    private static Log log = LogFactory.getLog( StudentUtils.class );

    static public List getCurrentLoginUserSchoolYears( HttpServletRequest request, ClassType classType )
        throws Exception
    {

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        //find all giaoly students in the current school year belong to current login user

        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? and schoolYear.school = ? and" );
        if ( classType == ClassType.GIAOLY )
        {
            queryBuff.append( " schoolYear.giaolyClass.name = ? and schoolYear.giaolyClass.subName = ?" );
        }
        else
        {
            queryBuff.append( " schoolYear.vietnguClass.name = ? and schoolYear.vietnguClass.subName = ?" );
        }

        queryBuff.append( " order by schoolYear.student.name.lastNameRaw" );

        List paramList = new ArrayList();
        paramList.add( GlobalConfig.getInstance().getCurrentYear() );
        paramList.add( loginProfile.getSchool().getId() );
        if ( classType == ClassType.GIAOLY )
        {
            paramList.add( loginProfile.getGiaolyClass() );
            paramList.add( loginProfile.getGiaolySubClass() );
        }
        else
        {
            paramList.add( loginProfile.getVietnguClass() );
            paramList.add( loginProfile.getVietnguSubClass() );
        }

        List typeList = new ArrayList();
        typeList.add( Hibernate.INTEGER );
        typeList.add( Hibernate.INTEGER );
        typeList.add( Hibernate.custom( ClassName.class ) );
        typeList.add( Hibernate.custom( ClassSubName.class ) );
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
            log.error( "Error loading " + classType.toFriendlyName() + " students", e );
            SessionUtil.rollback( e );
        }

        return schoolYears;
    }
    

    
}