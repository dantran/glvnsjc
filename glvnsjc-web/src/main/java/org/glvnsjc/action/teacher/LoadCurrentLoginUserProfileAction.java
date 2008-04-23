package org.glvnsjc.action.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.model.Address;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Name;

import org.glvnsjc.securityfilter.AppPrincipal;

//Load a student based on studentId

public class LoadCurrentLoginUserProfileAction
    extends org.apache.struts.action.Action
{

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {
        DynaActionForm theForm = (DynaActionForm) form;

        LoginProfile loginProfile = ( (AppPrincipal) request.getUserPrincipal() ).getLoginProfile();
        if ( loginProfile.getName() == null )
        {
            loginProfile.setName( new Name() );
        }
        if ( loginProfile.getAddress() == null )
        {
            loginProfile.setAddress( new Address() );
        }
        BeanUtils.copyProperties( theForm, loginProfile );
        theForm.set( "confirmPassword", theForm.get( "password" ) );

        return ( mapping.findForward( "success" ) );
    }

}
