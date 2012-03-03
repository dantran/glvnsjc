package org.glvnsjc.model;

/**
 * Student Criteria Bean
 * @author dtran
 *
 */
public class StudentSearchForm
{
    private String studentId = "";

    private String lastName = "";

    private String middleName = "";

    private String firstName = "";

    private String birthDate = "";

    private String phone = "";

    private String schoolYear = "";

    private String schoolId = "";

    private String giaolyClassName = "";

    private String vietnguClassName = "";

    private String giaolyClassSubName = "";

    private String vietnguClassSubName = "";

    //trigger DOB search range
    private String birthDate2 = "";

    public StudentSearchForm()
    {

    }

    public void setStudentId( String value )
    {
        this.studentId = value;
    }

    public String getStudentId()
    {
        return this.studentId;
    }

    public void setLastName( String value )
    {
        this.lastName = value;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setFirstName( String value )
    {
        this.firstName = value;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setMiddleName( String value )
    {
        this.middleName = value;
    }

    public String getMiddleName()
    {
        return this.middleName;
    }

    public void setBirthDate( String value )
    {
        this.birthDate = value;
    }

    public String getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate2( String value )
    {
        this.birthDate2 = value;
    }

    public String getBirthDate2()
    {
        return this.birthDate2;
    }

    public void setPhone( String value )
    {
        this.phone = value;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setSchoolYear( String value )
    {
        this.schoolYear = value;
    }

    public String getSchoolYear()
    {
        return this.schoolYear;
    }

    public void setSchoolId( String value )
    {
        this.schoolId = value;
    }

    public String getSchoolId()
    {
        return this.schoolId;
    }

    public void setGiaolyClassName( String value )
    {
        this.giaolyClassName = value;
    }

    public String getGiaolyClassName()
    {
        return this.giaolyClassName;
    }

    public void setVietnguClassName( String value )
    {
        this.vietnguClassName = value;
    }

    public String getVietnguClassName()
    {
        return this.vietnguClassName;
    }

    public void setGiaolyClassSubName( String value )
    {
        this.giaolyClassSubName = value;
    }

    public String getGiaolyClassSubName()
    {
        return this.giaolyClassSubName;
    }

    public void setVietnguClassSubName( String value )
    {
        this.vietnguClassSubName = value;
    }

    public String getVietnguClassSubName()
    {
        return this.vietnguClassSubName;
    }

}
