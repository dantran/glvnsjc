package org.glvnsjc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.codehaus.plexus.util.StringUtils;

public class SendMail
    implements Runnable
{

    private static Log log = LogFactory.getLog( SendMail.class );

    private String from;

    private List to;

    private List cc;

    private List bcc;

    private String subject;

    private String body;

    private List attachments;

    private String smtpServerHost;

    private String smtpServerUserId;

    private String smtpServerPassword;

    public SendMail()
    {
    }

    public SendMail( String from, String to, String subject, String body )
    {
        this.from = from;
        this.to = new ArrayList();
        this.to.add( to );
        this.subject = subject;
        this.body = body;

    }

    public SendMail( String from, List to, List cc, List bcc, String subject, String body, List attachments )
    {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = cc;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }

    public void setFrom( String newValue )
    {
        this.from = newValue;
    }

    public String getFrom()
    {
        return this.from;
    }

    public void setSubject( String newValue )
    {
        this.subject = newValue;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public void setBody( String newValue )
    {
        this.body = newValue;
    }

    public String getBody()
    {
        return this.body;
    }

    public void setTo( List newValue )
    {
        this.to = newValue;
    }

    public List getTo()
    {
        return this.to;
    }

    public void setCc( List newValue )
    {
        this.cc = newValue;
    }

    public List getCc()
    {
        return this.cc;
    }

    public void setBcc( List newValue )
    {
        this.bcc = newValue;
    }

    public List getBcc()
    {
        return this.bcc;
    }

    public void setAttachments( List newValue )
    {
        this.attachments = newValue;
    }

    public List getAttachments()
    {
        return this.attachments;
    }

    public void setSmtpserverUserId( String value )
    {
        this.smtpServerUserId = value;
    }

    public String getSmtpServerUserId()
    {
        return this.smtpServerUserId;
    }

    public String getSmtpServerPassword()
    {
        return this.smtpServerPassword;
    }

    public void setSmtpserverPassword( String value )
    {
        this.smtpServerPassword = value;
    }

    public String getSmtpServerHost()
    {
        return this.smtpServerHost;
    }

    public void setSmtpServerHost( String value )
    {
        this.smtpServerHost = value;
    }

    public void run()
    {
        try
        {
            send();
        }
        catch ( Exception e )
        {
            log.error( "Unable to send mail", e );
        }
    }

    public void send()
        throws EmailException
    {

        Properties props = System.getProperties();

        log.info( "smtpHostAddress: " + this.getSmtpServerHost() );

        String[] tokens = StringUtils.split( this.getSmtpServerHost(), ":" );

        String smtpHost = tokens[0];

        String smtpPort = "25";

        if ( tokens.length > 1 )
        {
            smtpPort = tokens[1];
        }

        Email email = new SimpleEmail();
        email.setSmtpPort( Integer.parseInt( smtpPort ) );
        email.setAuthenticator( new DefaultAuthenticator( smtpServerUserId, smtpServerPassword ) );
        email.setDebug( false );
        email.setHostName( smtpHost );
        email.setFrom( from );
        email.setSubject( this.getSubject() );
        email.setMsg( this.body );
        email.setStartTLSEnabled( true );
        email.setSSLOnConnect( true );
        

        if ( this.to != null )
        {
            for ( int i = 0; i < this.to.size(); ++i )
            {
                email.addTo( this.to.get( i ).toString() );
            }
        }

        if ( this.cc != null )
        {
            for ( int i = 0; i < this.cc.size(); ++i )
            {
                email.addCc( this.cc.get( i ).toString() );
            }
        }

        if ( this.bcc != null )
        {
            for ( int i = 0; i < this.bcc.size(); ++i )
            {
                email.addBcc( this.cc.get( i ).toString() );
            }
        }
        
        //fixme add attachment
        
        email.send();

    }

}