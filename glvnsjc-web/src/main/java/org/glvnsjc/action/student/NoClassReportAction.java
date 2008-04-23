package org.glvnsjc.action.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.securityfilter.AppPrincipal;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.hibernate.SessionUtil;

public class NoClassReportAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( NoClassReportAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        LoginProfile loginProfile = ( (AppPrincipal) request.getUserPrincipal() ).getLoginProfile();

        //find all giaoly students in the current school year belong to user login

        //setup the query
        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? " );
        if ( loginProfile.getSchool() != null )
        {
            queryBuff.append( "and schoolYear.school = ? " );
        }
        queryBuff.append( " and (schoolYear.giaolyClass.name = ? " );
        queryBuff.append( " or schoolYear.vietnguClass.name = ? )" );
        queryBuff.append( " order by schoolYear.student.birthDate" );

        //setup the params
        List paramList = new ArrayList();
        List typeList = new ArrayList();
        paramList.add( GlobalConfig.getInstance().getCurrentYear() );
        typeList.add( Hibernate.INTEGER );
        if ( loginProfile.getSchool() != null )
        {
            paramList.add( loginProfile.getSchool().getId() );
            typeList.add( Hibernate.INTEGER );
        }
        paramList.add( ClassName.UNASSIGNED );
        typeList.add( Hibernate.custom( ClassName.class ) );
        paramList.add( ClassName.UNASSIGNED );
        typeList.add( Hibernate.custom( ClassName.class ) );

        Type[] types = new Type[typeList.size()];
        typeList.toArray( types );

        try
        {
            Session session = SessionUtil.begin();

            Query query = session.createQuery( queryBuff.toString() );

            query.setParameters( paramList.toArray(), types );

            List schoolYears = query.list();

            request.getSession().setAttribute( "list", schoolYears );
            
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            log.error( "Error found during loading No Class Report", e );
            SessionUtil.rollback( e );
        }

        return ( mapping.findForward( "success" ) );
    }

}