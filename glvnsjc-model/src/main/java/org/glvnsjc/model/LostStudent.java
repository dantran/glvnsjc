package org.glvnsjc.model;

import java.util.Date;

import org.glvnsjc.converter.Convert;
import org.glvnsjc.util.StringUtil;

/**
 * <tt>LostStudent</tt> has minimal information sent to admin to lookup stdudent not in a school database <br><br>
 * @author Dan Tran
 */

public class LostStudent
{

    /** identifier field */
    private Integer id;

    private Name name;

    private Date birthDate;

    private String phone;

    private SchoolClass giaolyClass;

    private SchoolClass vietnguClass;

    private boolean notifiedMaster = false;

    private LoginProfile reportedBy;

    private Date createdDate;

    public LostStudent()
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

    public Date getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate( Date birthDate )
    {
        this.birthDate = birthDate;
    }

    public String getBirthDateDisplay()
    {
        return Convert.DateToString( this.birthDate );
    }

    public Name getName()
    {
        return this.name;
    }

    public void setName( Name name )
    {
        this.name = name;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone( String newValue )
    {
        this.phone = newValue;
    }

    public LoginProfile getReportedBy()
    {
        return this.reportedBy;
    }

    public void setReportedBy( LoginProfile newValue )
    {
        this.reportedBy = newValue;
    }

    public SchoolClass getGiaolyClass()
    {
        return this.giaolyClass;
    }

    public void setGiaolyClass( SchoolClass newValue )
    {
        this.giaolyClass = newValue;
    }

    public SchoolClass getVietnguClass()
    {
        return this.vietnguClass;
    }

    public void setVietnguClass( SchoolClass newValue )
    {
        this.vietnguClass = newValue;
    }

    public boolean getNotifiedMaster()
    {
        return this.notifiedMaster;
    }

    public void setNotifiedMaster( boolean newValue )
    {
        this.notifiedMaster = newValue;
    }

    public Date getCreatedDate()
    {
        return this.createdDate;
    }

    public void setCreatedDate( Date newValue )
    {
        this.createdDate = newValue;
    }

    public String getCreatedDateDisplay()
    {
        return Convert.DateToString( this.createdDate );
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer( "Student:" ).append( this.name.getFullName() );
        buffer.append( ", School:" ).append( this.reportedBy.getSchool().getShortName() );
        if ( !StringUtil.isBlank( this.giaolyClass.getFullClassName() ) )
        {
            buffer.append( ", GL:" ).append( this.giaolyClass.getFullClassName() );
        }
        if ( !StringUtil.isBlank( this.vietnguClass.getFullClassName() ) )
        {
            buffer.append( ", VN:" ).append( this.vietnguClass.getFullClassName() );
        }
        if ( this.birthDate != null )
        {
            buffer.append( ", DOB:" ).append( Convert.DateToString( this.birthDate ) );
        }
        if ( !StringUtil.isBlank( this.phone ) )
        {
            buffer.append( ", Phone:" ).append( this.phone );
        }

        return buffer.toString();
    }
}