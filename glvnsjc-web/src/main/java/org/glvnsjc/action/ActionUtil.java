package org.glvnsjc.action;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.securityfilter.AppPrincipal;

public class ActionUtil
{

    static public ActionMessages createActionMessages( final String key )
    {

        ActionMessages messages = new ActionMessages();
        ActionMessage message = new ActionMessage( key );
        messages.add( ActionMessages.GLOBAL_MESSAGE, message );
        return messages;
    }

    static public LoginProfile getCurrentUserLoginProfile( HttpServletRequest request )
    {
        return ( (AppPrincipal) request.getUserPrincipal() ).getLoginProfile();

    }
}
