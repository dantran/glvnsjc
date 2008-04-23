package org.glvnsjc.view;

public class NextYearClassSummary
    implements java.io.Serializable
{

    private String id;

    private String schoolName;

    private String studentName;

    private String birthDate;

    private String giaolyClass; //for next year

    private String vietnguClass;

    private String nextGiaolyClass; //for next year

    private String nextVietnguClass;

    public NextYearClassSummary()
    {
    }

    public String getId()
    {
        return this.id;
    }

    public void setId( String newValue )
    {
        this.id = newValue;
    }

    public String getStudentName()
    {
        return this.studentName;
    }

    public void setStudentName( String newValue )
    {
        this.studentName = newValue;
    }

    public String getGiaolyClass()
    {
        return this.giaolyClass;
    }

    public void setGiaolyClass( String newValue )
    {
        this.giaolyClass = newValue;
    }

    public String getVietnguClass()
    {
        return this.vietnguClass;
    }

    public void setVietnguClass( String newValue )
    {
        this.vietnguClass = newValue;
    }

    public String getNextGiaolyClass()
    {
        return this.nextGiaolyClass;
    }

    public void setNextGiaolyClass( String newValue )
    {
        this.nextGiaolyClass = newValue;
    }

    public String getNextVietnguClass()
    {
        return this.nextVietnguClass;
    }

    public void setNextVietnguClass( String newValue )
    {
        this.nextVietnguClass = newValue;
    }

    public String getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate( String newValue )
    {
        this.birthDate = newValue;
    }

    public String getSchoolName()
    {
        return this.schoolName;
    }

    public void setSchoolName( String newValue )
    {
        this.schoolName = newValue;
    }

}