package org.glvnsjc.view;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import org.apache.commons.lang.builder.ToStringBuilder;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassSubName;
import org.glvnsjc.model.Grade;
import org.glvnsjc.model.SchoolClass;

public class SchoolYearView
    implements java.io.Serializable
{

    private boolean editAllow = false;

    private String id;

    private int year;

    private boolean hasApplication;

    private String schoolId;

    private String schoolName;

    private SchoolClass giaolyClass = new SchoolClass();

    private SchoolClass vietnguClass = new SchoolClass();

    private String comment = new String();

    public SchoolYearView()
    {

    }

    public boolean getEditAllow()
    {
        return this.editAllow;
    }

    public void setEditAllow( boolean newValue )
    {
        this.editAllow = newValue;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId( String id )
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

    public String getSchoolId()
    {
        return this.schoolId;
    }

    public void setSchoolId( String schoolId )
    {
        this.schoolId = schoolId;
    }

    public String getSchoolName()
    {
        return this.schoolName;
    }

    public void setSchoolName( String schoolName )
    {
        this.schoolName = schoolName;
    }

    public SchoolClass getGiaolyClass()
    {
        return this.giaolyClass;
    }

    private void makeSchoolClassPropertiesNoneNull( SchoolClass schoolClass )
    {
        if ( schoolClass.getGrade() == null )
            schoolClass.setGrade( Grade.UNASSIGNED );
        if ( schoolClass.getName() == null )
            schoolClass.setName( ClassName.UNASSIGNED );
        if ( schoolClass.getSubName() == null )
            schoolClass.setSubName( ClassSubName.UNASSIGNED );

    }

    public void setGiaolyClass( SchoolClass giaolyClass )
    {
        if ( giaolyClass == null )
        {
            giaolyClass = new SchoolClass();
        }
        this.giaolyClass = giaolyClass;
        makeSchoolClassPropertiesNoneNull( this.giaolyClass );

    }

    public SchoolClass getVietnguClass()
    {
        return this.vietnguClass;
    }

    public void setVietnguClass( SchoolClass vietnguClass )
    {
        if ( vietnguClass == null )
        {
            vietnguClass = new SchoolClass();
        }
        this.vietnguClass = vietnguClass;
        makeSchoolClassPropertiesNoneNull( this.vietnguClass );

    }

    public void setComment( String comment )
    {
        if ( comment == null )
        {
            comment = "";
        }
        this.comment = comment;
    }

    public String getComment()
    {
        return this.comment;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

}