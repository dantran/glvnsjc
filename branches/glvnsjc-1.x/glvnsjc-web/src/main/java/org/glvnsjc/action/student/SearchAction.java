package org.glvnsjc.action.student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.StudentSearchForm;
import org.glvnsjc.model.StudentUtil;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.util.StringUtil;
import org.glvnsjc.model.GlobalConfig;

public class SearchAction
    extends org.apache.struts.actions.LookupDispatchAction
{

    private static Log log = LogFactory.getLog( SearchAction.class );

    protected Map getKeyMethodMap()
    {
        Map map = new HashMap();
        map.put( "button.search", "search" );

        return map;
    }

    public ActionForward search( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response )
        throws Exception
    {

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
        DynaActionForm theForm = (DynaActionForm) form;

        //override school and school year search when privilege is principal or less
        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            theForm.set( "schoolId", loginProfile.getSchool().getId().toString() );
            theForm.set( "schoolYear", GlobalConfig.getInstance().getCurrentYear().toString() );
        }

        StudentSearchForm criteria = new StudentSearchForm() ;
        BeanUtils.copyProperties( criteria, theForm );
        
        List list = StudentUtil.search(  criteria );


        //tell the view the return list type
        if ( !StringUtil.isBlank( criteria.getSchoolYear() ) )
        {
            request.getSession().setAttribute( "isSchoolYearType", new Boolean( true ) );
        }
        else
        {
            request.getSession().setAttribute( "isSchoolYearType", new Boolean( false ) );
        }
        request.getSession().setAttribute( "list", list );

        if ( log.isDebugEnabled() )
        {
            log.debug( loginProfile.getUserId() + " searched " + list.size() + " students." );
        }

        return ( mapping.findForward( "normalList" ) );
    }

}