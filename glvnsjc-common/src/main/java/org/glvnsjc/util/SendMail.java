package org.glvnsjc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
        throws AddressException, MessagingException
    {

        Properties props = System.getProperties();
        
        log.info( "smtpHostAddress: " + this.getSmtpServerHost() );
        
        String [] tokens = StringUtils.split( this.getSmtpServerHost(), ":" );
        
        String smtpHost = tokens[0];
        
        String smtpPort = "25";
        
        if ( tokens.length > 1 )
        {
        	smtpPort = tokens[1];
        }
        
        props.put("mail.smtp.port", smtpPort );
        
        if ( StringUtil.isBlank( getSmtpServerUserId() ) )
        {
            props.put( "mail.smtp.auth", "false" );
        }
        else
        {
            props.put( "mail.smtp.auth", "true" );
        }

        Session session = Session.getInstance( props, null );
        Message message = new MimeMessage( session );
        message.setFrom( new InternetAddress( this.getFrom().trim() ) );
        message.setSubject( this.getSubject() );

        int i = 0;
        if ( this.getTo() != null )
        {
            for ( i = 0; i < this.getTo().size(); ++i )
            {
                message.addRecipient( Message.RecipientType.TO, new InternetAddress( this.getTo().get( i ).toString()
                    .trim() ) );
            }
        }

        if ( this.getCc() != null )
        {
            for ( i = 0; i < this.getCc().size(); ++i )
            {
                message.addRecipient( Message.RecipientType.CC, new InternetAddress( this.getCc().get( i ).toString()
                    .trim() ) );
            }
        }

        if ( this.getBcc() != null )
        {
            for ( i = 0; i < this.getBcc().size(); ++i )
            {
                message.addRecipient( Message.RecipientType.BCC, new InternetAddress( this.getBcc().get( i ).toString()
                    .trim() ) );
            }
        }

        if ( this.getAttachments() != null && this.getAttachments().size() != 0 )
        {
            Multipart multipart = new MimeMultipart();

            BodyPart bodyText = new MimeBodyPart();
            bodyText.setText( this.getBody() );
            multipart.addBodyPart( bodyText );

            //associate file attachments to the email
            for ( i = 0; i < this.getAttachments().size(); ++i )
            {
                BodyPart bodyFile = new MimeBodyPart();
                FileDataSource fileSource = new FileDataSource( this.getAttachments().get( i ).toString() );
                bodyFile.setDataHandler( new DataHandler( fileSource ) );
                bodyFile.setFileName( fileSource.getName() );
                multipart.addBodyPart( bodyFile );
            }

            message.setContent( multipart );
        }
        else
        {
            //email has no attachments
            message.setText( this.getBody() );
        }

        Transport transport = session.getTransport( "smtp" );
        transport.connect( smtpHost, getSmtpServerUserId(), getSmtpServerPassword() );
        try
        {
            message.saveChanges();
            transport.sendMessage( message, message.getAllRecipients() );
        }
        finally
        {
            transport.close();
        }

    }

}