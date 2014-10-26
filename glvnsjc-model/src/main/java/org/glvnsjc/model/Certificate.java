package org.glvnsjc.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Certificate
    implements Serializable
{

    /** identifier field */
    private Integer id;

    /** persistent field */
    private org.glvnsjc.model.CertificateType type;

    private Date date;

    private org.glvnsjc.model.LoginProfile teacher;

    public void setId( Integer id )
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public org.glvnsjc.model.CertificateType getType()
    {
        return type;
    }

    public void setType( org.glvnsjc.model.CertificateType type )
    {
        this.type = type;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public org.glvnsjc.model.LoginProfile getTeacher()
    {
        return teacher;
    }

    public void setTeacher( org.glvnsjc.model.LoginProfile teacher )
    {
        this.teacher = teacher;
    }

    // ////////////////////////////////////////////////////////////////

    public String toString()
    {
        return new ToStringBuilder( this ).append( "id", getId() ).toString();
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof Certificate ) )
        {
            return false;
        }

        Certificate castOther = (Certificate) other;
        return new EqualsBuilder().append( this.getId(), castOther.getId() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getId() ).toHashCode();
    }

}
