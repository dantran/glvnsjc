package org.glvnsjc.util;

import junit.framework.TestCase;

public class SendMailTest
    extends TestCase

{
    public void testSend()
        throws Exception
    {
        SendMail sendMail = new SendMail( "dantran@yahoo.com", "dantran@yahoo.com", "GLVNSJC Account Notification", "test" );
        sendMail.setSmtpServerHost( "smtp.dslextreme.com" );
        sendMail.setSmtpserverUserId( "danttran" );
        
        String password = System.getProperty( "glvnsjc.smtp.password" );
        
        if ( password != null )
        {
            sendMail.setSmtpserverPassword( password );
            sendMail.send();
        }

    }
}
