package org.glvnsjc.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SiteConfig;

/**
 * <tt>GenerateRoleSheetAction</tt> generate a class rolesheet
 * <br>
 * @author Dan Tran
 */

public class GenerateRoleSheetAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( GenerateRoleSheetAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        DynaActionForm theForm = (DynaActionForm) form;
        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );
        SiteConfig siteConfig = GlobalConfig.getInstance().getGlobalConfig();
        ClassType classType = ClassType.fromString( theForm.get( "classType" ) );

        try
        {
            //get the student list based on current schoolyear, school,

        }
        catch ( Exception e )
        {
            log.error( "Error found during loading students to be subclassed", e );
        }

        return ( mapping.findForward( "success" ) );
    }

}