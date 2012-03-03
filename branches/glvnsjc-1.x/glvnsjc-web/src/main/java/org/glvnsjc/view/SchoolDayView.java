package org.glvnsjc.view;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** A view class representing a calendar school day.
 *
 * @author Dan Tran
 * @hibernate.class  table="SchoolCalendarView"
 */

public class SchoolDayView
    implements java.io.Serializable
{

    private String day;

    private boolean isHoliday;

    private String schoolNote;

    private String teacherNote;

    public SchoolDayView()
    {
    }

    public SchoolDayView( String schoolDay, boolean isHoliday, String comment )
    {
        this.day = schoolDay;
        this.isHoliday = isHoliday;
        this.schoolNote = comment;
    }

    public SchoolDayView( String schoolDay )
    {
        this.day = schoolDay;
        this.isHoliday = false;
        this.schoolNote = "";
    }

    public String getDay()
    {
        return this.day;
    }

    public void setDay( String value )
    {
        this.day = value;
    }

    public boolean getIsHoliday()
    {
        return this.isHoliday;
    }

    public void setIsHoliday( boolean value )
    {
        this.isHoliday = value;
    }

    public String getSchoolNote()
    {
        return this.schoolNote;
    }

    public void setSchoolNote( String value )
    {
        this.schoolNote = value;
    }

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
        if ( !( other instanceof SchoolDayView ) )
            return false;
        SchoolDayView castOther = (SchoolDayView) other;
        return new EqualsBuilder().append( this.getDay(), castOther.getDay() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getDay() ).toHashCode();
    }

}
