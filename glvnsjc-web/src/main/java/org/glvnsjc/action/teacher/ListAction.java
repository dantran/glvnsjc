package org.glvnsjc.action.teacher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.Address;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Name;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.School;
import org.glvnsjc.model.TeacherType;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.util.StringUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class ListAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( ListAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        //overide school and school year search when privilege is principal or less
        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            theForm.set( "schoolId", loginProfile.getSchool().getId().toString() );
        }

        String schoolId = (String) theForm.get( "schoolId" );
        Integer schoolObjId = null;
        if ( !StringUtil.isBlank( schoolId ) )
        {
            schoolObjId = Integer.valueOf( schoolId );
        }

        StringBuffer whereClause = new StringBuffer();
        List paramList = new ArrayList();
        List typeList = new ArrayList();
        if ( schoolObjId != null )
        {
            whereClause.append( " loginProfile.school = ?" );
            paramList.add( schoolObjId );
            typeList.add( Hibernate.INTEGER );
        }

        TeacherType teacherType = TeacherType.fromString( (String) theForm.get( "teacherType" ) );
        if ( !teacherType.equals( TeacherType.UNASSIGNED ) )
        {
            if ( whereClause.length() != 0 )
            {
                whereClause.append( " and" );
            }
            whereClause.append( " loginProfile.teacherType=?" );
            paramList.add( teacherType );
            typeList.add( Hibernate.custom( TeacherType.class ) );
        }

        StringBuffer searchBuffer = new StringBuffer( "from org.glvnsjc.model.LoginProfile loginProfile" );
        if ( whereClause.length() != 0 )
        {
            searchBuffer.append( " where" ).append( whereClause );
        }

        searchBuffer
            .append( " order by loginProfile.school.shortName, loginProfile.name.lastNameRaw, loginProfile.name.firstNameRaw" );
        List list = null;
        try
        {
            Session session = SessionUtil.begin();

            Query query = session.createQuery( searchBuffer.toString() );
            if ( paramList.size() != 0 )
            {
                Type[] types = new Type[typeList.size()];
                typeList.toArray( types );
                query.setParameters( paramList.toArray(), types );
                list = query.list();
            }
            else
            {
                list = query.list();
            }

            SessionUtil.end();

        }
        catch ( Exception e )
        {
            log.error( "Error found during loading a list of LoginProfile" );
            SessionUtil.rollback( e );
        }

        //help the view to display empty schools
        School emptySchool = new School();
        Name emptyName = new Name();
        Address emptyAddress = new Address();
        for ( Iterator i = list.iterator(); i.hasNext(); )
        {
            LoginProfile profile = (LoginProfile) i.next();
            if ( profile.getSchool() == null )
            {
                profile.setSchool( emptySchool );
            }
            if ( profile.getName() == null )
            {
                profile.setName( emptyName );
            }
            if ( profile.getAddress() == null )
            {
                profile.setAddress( emptyAddress );
            }
        }

        request.getSession().setAttribute( "list", list );

        //less previledge than Principal can not edit for other school
        request.setAttribute( "editableList", new Boolean( true ) );
        if ( !request.isUserInRole( Privilege.PRINCIPAL.toString() ) )
        {
            request.setAttribute( "editableList", new Boolean( false ) );
        }

        return ( mapping.findForward( "success" ) );
    }

}