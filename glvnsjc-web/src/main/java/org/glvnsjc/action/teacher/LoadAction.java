package org.glvnsjc.action.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.CertificateType;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.securityfilter.AppPrincipal;
import org.glvnsjc.view.CertificateView;
import org.glvnsjc.view.LoginProfileForm;
import org.hibernate.Session;

// Load a student based on studentId

public class LoadAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( LoadAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {
        LoginProfileForm theForm = (LoginProfileForm) form;
        AppPrincipal principal = (AppPrincipal) request.getUserPrincipal();

        //the first time the form is loaded, the 'command' is in request's param
        //  set the command back to the form to be sent back later
        String action = request.getParameter( "command" );
        theForm.setCommand( action );
        String id = request.getParameter( "id" );

        //no need to load during a loading student for add
        if ( action.equals( "delete" ) || action.equals( "update" ) )
        {
            try
            {
                Session session = SessionUtil.begin();

                LoginProfile loginProfile = (LoginProfile) session.load( LoginProfile.class, new Integer( id ) );
                BeanUtils.copyProperties( theForm, loginProfile );
                if ( loginProfile.getSchool() != null )
                {
                    theForm.setSchoolId( loginProfile.getSchool().getId().toString() );
                }
                theForm.setConfirmPassword( theForm.getPassword() );

                if ( action.equals( "update" ) )
                {
                    theForm.getCertificateViews().add( new CertificateView( CertificateType.UNKNOWN.getDisplay(), "" ) );
                }

                SessionUtil.end();
            }
            catch ( Exception e )
            {
                log.error( "Error during loading a LoginProfile", e );
                SessionUtil.rollback( e );
            }
        }
        else
        {
            //during add, load the default school based on user security principal
            if ( principal.getLoginProfile().getSchool() != null )
            {
                theForm.setSchoolId( principal.getLoginProfile().getSchool().getId().toString() );
            }
        }

        //less privilege than master can not search for other school
        if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
        {
            theForm.setSchoolPriviledgeOnly( true );

        }
        return ( mapping.findForward( "success" ) );
    }
}