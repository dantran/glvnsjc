package org.glvnsjc.action.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.Grade;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.School;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.securityfilter.AppPrincipal;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class GiaolyAwardReportAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( GiaolyAwardReportAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception

    {

        List schoolYears = getAwardList( request );
        request.getSession().setAttribute( "list", schoolYears );

        return ( mapping.findForward( "success" ) );
    }

    public static List getAwardList( HttpServletRequest request )
    {
        List schoolYears = null;

        LoginProfile loginProfile = ( (AppPrincipal) request.getUserPrincipal() ).getLoginProfile();

        School school = loginProfile.getSchool();

        StringBuffer queryBuff = new StringBuffer();

        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? " );

        if ( school != null )
        {
            queryBuff.append( "and schoolYear.school = ? " );
        }

        queryBuff
            .append( " and ( schoolYear.giaolyClass.grade = ? or schoolYear.giaolyClass.grade = ? or schoolYear.giaolyClass.grade = ?)" );
        queryBuff.append( " order by schoolYear.giaolyClass.name" );

        //setup the params
        List paramList = new ArrayList();
        List typeList = new ArrayList();

        paramList.add( GlobalConfig.getInstance().getCurrentYear() );
        typeList.add( Hibernate.INTEGER );

        if ( school != null )
        {
            paramList.add( school.getId() );
            typeList.add( Hibernate.INTEGER );
        }

        paramList.add( Grade.FIRST );
        typeList.add( Hibernate.custom( Grade.class ) );
        paramList.add( Grade.SECOND );
        typeList.add( Hibernate.custom( Grade.class ) );
        paramList.add( Grade.THIRD );
        typeList.add( Hibernate.custom( Grade.class ) );

        Type[] types = new Type[typeList.size()];
        typeList.toArray( types );

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
            log.error( "Error found during loading hornor giaoly student", e );
            SessionUtil.rollback( e );
        }

        return schoolYears;

    }
}