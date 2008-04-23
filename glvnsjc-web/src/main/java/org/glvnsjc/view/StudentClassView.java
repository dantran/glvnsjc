package org.glvnsjc.view;

public class StudentClassView
    implements java.io.Serializable
{

    private String schoolYearId;

    private String studentName;

    private String className;

    private String classSubName;

    private String birthDate;

    public StudentClassView()
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

    public String getClassName()
    {
        return this.className;
    }

    public void setClassName( String newValue )
    {
        this.className = newValue;
    }

    public String getClassSubName()
    {
        return this.classSubName;
    }

    public void setClassSubName( String newValue )
    {
        this.classSubName = newValue;
    }

    public String getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate( String newValue )
    {
        this.birthDate = newValue;
    }

}