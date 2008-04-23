package org.glvnsjc.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** A business entity class representing a calendar school day.
 *
 * @author Dan Tran
 * @hibernate.class  table="SCHOOLDAY"
 */

public class SchoolDay
    implements java.io.Serializable
{

    private Date day;

    private boolean isHoliday;

    private String schoolNote;

    private String teacherNote;

    public SchoolDay()
    {
    }

    /**
     * @hibernate.id  generator-class="assigned" type="java.util.Date"
     */
    public Date getDay()
    {
        return this.day;
    }

    public void setDay( Date value )
    {
        this.day = value;
    }

    /**
     * @hibernate.property
     */
    public boolean getIsHoliday()
    {
        return this.isHoliday;
    }

    public void setIsHoliday( boolean value )
    {
        this.isHoliday = value;
    }

    /**
     * @hibernate.property
     */
    public String getSchoolNote()
    {
        return this.schoolNote;
    }

    public void setSchoolNote( String value )
    {
        this.schoolNote = value;
    }

    /**
     * @hibernate.property
     */
    public String getTeacherNote()
    {
        return this.teacherNote;
    }

    public void setTeacherNote( String value )
    {
        this.teacherNote = value;
    }

    public String toString()
    {
        return new ToStringBuilder( this ).append( "id", getDay() ).toString();
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof SchoolDay ) )
            return false;
        SchoolDay castOther = (SchoolDay) other;
        return new EqualsBuilder().append( this.getDay(), castOther.getDay() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getDay() ).toHashCode();
    }

}
