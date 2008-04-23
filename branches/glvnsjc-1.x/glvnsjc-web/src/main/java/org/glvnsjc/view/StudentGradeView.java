package org.glvnsjc.view;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class StudentGradeView
    implements Serializable
{

    private String schoolYearId;

    private String studentName;

    private String grade;

    public StudentGradeView()
    {

    }

    public String getSchoolYearId()
    {
        return this.schoolYearId;
    }

    public void setSchoolYearId( String newValue )
    {
        this.schoolYearId = newValue;
    }

    public String getStudentName()
    {
        return this.studentName;
    }

    public void setStudentName( String newValue )
    {
        this.studentName = newValue;
    }

    public String getGrade()
    {
        return this.grade;
    }

    public void setGrade( String newValue )
    {
        this.grade = newValue;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

}
