package org.glvnsjc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SiteConfig
    implements Serializable
{

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer currentSchoolYear;

    /** nullable persistent field */
    private boolean siteActive;

    /** nullable persistent field */
    private String smtpServer;

    /** nullable persistent field */
    private String smtpUserId;

    /** nullable persistent field */
    private String smtpPassword;

    /** nullable persistent field */
    private String feedbackEmail;

    private String headMasterName;
    
    /** full constructor */
    public SiteConfig( Integer currentSchoolYear, boolean siteActive )
    {
        this.currentSchoolYear = currentSchoolYear;
        this.siteActive = siteActive;
    }

    /** default constructor */
    public SiteConfig()
    {
    }

    public java.lang.Integer getId()
    {
        return this.id;
    }

    public void setId( java.lang.Integer id )
    {
        this.id = id;
    }

    public Integer getCurrentSchoolYear()
    {
        return this.currentSchoolYear;
    }

    public void setCurrentSchoolYear( Integer currentSchoolYear )
    {
        this.currentSchoolYear = currentSchoolYear;
    }

    public boolean isSiteActive()
    {
        return this.siteActive;
    }

    public void setSiteActive( boolean siteActive )
    {
        this.siteActive = siteActive;
    }

    public boolean getSiteActive()
    {
        return this.siteActive;
    }

    public String getSmtpServer()
    {
        return this.smtpServer;
    }

    public void setSmtpServer( String newValue )
    {
        this.smtpServer = newValue;
    }

    public String getSmtpUserId()
    {
        return this.smtpUserId;
    }

    public void setSmtpUserId( String newValue )
    {
        this.smtpUserId = newValue;
    }

    public String getSmtpPassword()
    {
        return this.smtpPassword;
    }

    public void setSmtpPassword( String newValue )
    {
        this.smtpPassword = newValue;
    }

    public String getFeedbackEmail()
    {
        return this.feedbackEmail;
    }

    public void setFeedbackEmail( String newValue )
    {
        this.feedbackEmail = newValue;
    }

    public String getHeadMasterName()
    {
        return this.headMasterName;
    }

    public void setHeadMasterName( String newValue )
    {
        this.headMasterName = newValue;
    }    
    public String toString()
    {
        return new ToStringBuilder( this ).append( "id", getId() ).toString();
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof SiteConfig ) )
            return false;
        SiteConfig castOther = (SiteConfig) other;
        return new EqualsBuilder().append( this.getId(), castOther.getId() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getId() ).toHashCode();
    }

}
