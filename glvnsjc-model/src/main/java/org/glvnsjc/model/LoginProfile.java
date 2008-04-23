package org.glvnsjc.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoginProfile
    implements Serializable
{

    /** persistent field */
    private String userId;

    /** nullable persistent field */
    private String password;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String birthDate;

    /** nullable persistent field */
    private String saintName;

    /** nullable persistent field */
    private org.glvnsjc.model.Gender gender;

    /** persistent field */
    private org.glvnsjc.model.Name name;

    /** persistent field */
    private org.glvnsjc.model.Name parentName;

    /** persistent field */
    private org.glvnsjc.model.Address address;

    /** persistent field */
    private org.glvnsjc.model.Privilege privilege;

    /** nullable persistent field */
    private org.glvnsjc.model.School school;

    /** persistent field */
    private org.glvnsjc.model.TeacherType teacherType;

    /** persistent field */
    private org.glvnsjc.model.ClassName giaolyClass;

    private org.glvnsjc.model.ClassName vietnguClass;

    private org.glvnsjc.model.ClassSubName giaolySubClass;

    private org.glvnsjc.model.ClassSubName vietnguSubClass;

    private Date lastLoginDate;

    private boolean removable = true;

    private boolean loginable = false;

    public LoginProfile()
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

    public java.lang.String getUserId()
    {
        return this.userId;
    }

    public void setUserId( java.lang.String userId )
    {
        this.userId = userId;
    }

    public java.lang.String getPassword()
    {
        return this.password;
    }

    public void setPassword( java.lang.String password )
    {
        this.password = password;
    }

    public Privilege getPrivilege()
    {
        return this.privilege;
    }

    public void setPrivilege( Privilege privilege )
    {
        this.privilege = privilege;
    }

    public org.glvnsjc.model.School getSchool()
    {
        return this.school;
    }

    public void setSchool( org.glvnsjc.model.School school )
    {
        this.school = school;
    }

    public org.glvnsjc.model.Gender getGender()
    {
        return this.gender;
    }

    public void setGender( org.glvnsjc.model.Gender gender )
    {
        this.gender = gender;
    }

    public String getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate( String birthDate )
    {
        this.birthDate = birthDate;
    }

    public java.lang.String getSaintName()
    {
        return this.saintName;
    }

    public void setSaintName( java.lang.String saintName )
    {
        this.saintName = saintName;
    }

    public org.glvnsjc.model.Name getName()
    {
        return this.name;
    }

    public void setName( org.glvnsjc.model.Name name )
    {
        this.name = name;
    }

    public org.glvnsjc.model.Address getAddress()
    {
        return this.address;
    }

    public void setAddress( org.glvnsjc.model.Address address )
    {
        this.address = address;
    }

    public Date getLastLoginDate()
    {
        return lastLoginDate;
    }

    public void setLastLoginDate( Date newDate )
    {
        this.lastLoginDate = newDate;
    }

    public ClassName getGiaolyClass()
    {
        return this.giaolyClass;
    }

    public void setGiaolyClass( ClassName newValue )
    {
        this.giaolyClass = newValue;
    }

    public ClassName getVietnguClass()
    {
        return this.vietnguClass;
    }

    public void setVietnguClass( ClassName newValue )
    {
        this.vietnguClass = newValue;
    }

    public ClassSubName getGiaolySubClass()
    {
        return this.giaolySubClass;
    }

    public void setGiaolySubClass( ClassSubName newValue )
    {
        this.giaolySubClass = newValue;
    }

    public ClassSubName getVietnguSubClass()
    {
        return this.vietnguSubClass;
    }

    public void setVietnguSubClass( ClassSubName newValue )
    {
        this.vietnguSubClass = newValue;
    }

    public TeacherType getTeacherType()
    {
        return this.teacherType;
    }

    public void setTeacherType( TeacherType newValue )
    {
        this.teacherType = newValue;
    }

    public boolean getRemovable()
    {
        return this.removable;
    }

    public void setRemovable( boolean newValue )
    {
        this.removable = newValue;
    }

    public boolean getLoginable()
    {
        return this.loginable;
    }

    public void setLoginable( boolean newValue )
    {
        this.loginable = newValue;
    }

    public String toString()
    {
        return new ToStringBuilder( this ).append( "id", getId() ).toString();
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof LoginProfile ) )
            return false;
        LoginProfile castOther = (LoginProfile) other;
        return new EqualsBuilder().append( this.getId(), castOther.getId() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getId() ).toHashCode();
    }

    //helpers
    public String getGiaolyClassFullName()
    {
        if ( this.giaolyClass != null )
        {
            return this.giaolyClass.toString() + this.giaolySubClass;
        }
        else
        {
            return "";
        }
    }

    public String getVietnguClassFullName()
    {
        if ( this.vietnguClass != null )
        {
            return this.vietnguClass.toString() + this.vietnguSubClass;
        }
        else
        {
            return "";
        }
    }

}
