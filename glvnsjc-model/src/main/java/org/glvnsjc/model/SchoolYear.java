package org.glvnsjc.model;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SchoolYear
    implements Serializable, Comparator
{

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int year;

    /** nullable persistent field */
    private boolean hasApplication;

    /** persistent field */
    private org.glvnsjc.model.School school;

    /** persistent field */
    private org.glvnsjc.model.Student student;

    /** persistent field */
    private org.glvnsjc.model.SchoolClass giaolyClass;

    /** persistent field */
    private org.glvnsjc.model.SchoolClass vietnguClass;

    /** SchoolYear comments */
    private String comment;

    /** full constructor */
    public SchoolYear( int year, boolean hasApplication, org.glvnsjc.model.School school,
                       org.glvnsjc.model.Student student, org.glvnsjc.model.SchoolClass giaolyClass,
                       org.glvnsjc.model.SchoolClass vietnguClass )
    {
        this.year = year;
        this.hasApplication = hasApplication;
        this.school = school;
        this.student = student;
        this.giaolyClass = giaolyClass;
        this.vietnguClass = vietnguClass;
    }

    /** default constructor */
    public SchoolYear()
    {
    }

    /** minimal constructor */
    public SchoolYear( int year, org.glvnsjc.model.School school, org.glvnsjc.model.Student student,
                       org.glvnsjc.model.SchoolClass giaolyClass, org.glvnsjc.model.SchoolClass vietnguClass )
    {
        this.year = year;
        this.school = school;
        this.student = student;
        this.giaolyClass = giaolyClass;
        this.vietnguClass = vietnguClass;
    }

    public java.lang.Integer getId()
    {
        return this.id;
    }

    public void setId( java.lang.Integer id )
    {
        this.id = id;
    }

    public int getYear()
    {
        return this.year;
    }

    public void setYear( int year )
    {
        this.year = year;
    }

    public boolean isHasApplication()
    {
        return this.hasApplication;
    }

    public void setHasApplication( boolean hasApplication )
    {
        this.hasApplication = hasApplication;
    }

    public org.glvnsjc.model.School getSchool()
    {
        return this.school;
    }

    public void setSchool( org.glvnsjc.model.School school )
    {
        this.school = school;
    }

    public org.glvnsjc.model.Student getStudent()
    {
        return this.student;
    }

    public void setStudent( org.glvnsjc.model.Student student )
    {
        this.student = student;
    }

    public org.glvnsjc.model.SchoolClass getGiaolyClass()
    {
        return this.giaolyClass;
    }

    public void setGiaolyClass( org.glvnsjc.model.SchoolClass giaolyClass )
    {
        this.giaolyClass = giaolyClass;
    }

    public org.glvnsjc.model.SchoolClass getVietnguClass()
    {
        return this.vietnguClass;
    }

    public void setVietnguClass( org.glvnsjc.model.SchoolClass vietnguClass )
    {
        this.vietnguClass = vietnguClass;
    }

    public void setComment( String comment )
    {
        this.comment = comment;
    }

    public String getComment()
    {
        return this.comment;
    }

    public String toString()
    {
        return new ToStringBuilder( this ).append( "id", getId() ).toString();
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof SchoolYear ) )
        {
            return false;
        }

        SchoolYear castOther = (SchoolYear) other;
        return new EqualsBuilder().append( this.getId(), castOther.getId() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getId() ).toHashCode();
    }

    public int compare( Object o1, Object o2 )
    {
        int sy1 = ( (SchoolYear) o1 ).getYear();
        int sy2 = ( (SchoolYear) o2 ).getYear();

        if ( sy1 > sy2 )
        {
            return 1;
        }
        else if ( sy1 < sy2 )
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
