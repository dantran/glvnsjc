package org.glvnsjc.view;

/**
 * <p>Title: Student Summary</p>
 * <p>Description: TO be used when display in a list student summary</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Dan Tran
 * @version 1.0
 */

import org.glvnsjc.model.Student;
import org.glvnsjc.model.SchoolYear;

public class StudentSummary
    implements java.io.Serializable
{

    private Student student;

    private SchoolYear schoolYear;

    public StudentSummary()
    {
    }

    public void setStudent( Student newValue )
    {
        this.student = newValue;
    }

    public Student getStudent()
    {
        return this.student;
    }

    public void setSchoolYear( SchoolYear newValue )
    {
        this.schoolYear = newValue;
    }

    public SchoolYear getSchoolYear()
    {
        return this.schoolYear;
    }

}