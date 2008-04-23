package org.glvnsjc.model;

/** Represent the student's semester info to be record in the report card
 *
 */

public class Semester
    implements java.io.Serializable
{

    /** identifier field */
    private Integer id;

    /** Instructor's comment to be sent home at the end of semester */
    private String comment;

    /* specific to Giaoly */
    private SubGrade memorizationGrade;

    private SubGrade understandingGrade;

    /* specific to viet ngu */
    private SubGrade readingGrade;

    private SubGrade writingGrade;

    private SubGrade speakingGrade;

    /*commons */
    private SubGrade homeworkGrade;

    private SubGrade classworkGrade;

    private SubGrade obeydienGrade;

    private SubGrade cooperationGrade;

    private SubGrade helpfulGrade;

    private SubGrade neatnessGrade;

    private SubGrade bookNeatnessGrade;

    private SubGrade respectGrade;

    private Integer absentCount;

    private Integer tardyCount;

    /** default constructor */
    public Semester()
    {
    }

    public java.lang.Integer getId()
    {
        return this.id;
    }

    public void setId( java.lang.Integer id )
    {
        this.id = id;
    }

    public String getComment()
    {
        return this.comment;
    }

    public void setComment( String comment )
    {
        this.comment = comment;
    }

    public SubGrade getMemorizationGrade()
    {
        return this.memorizationGrade;
    }

    public void setMemorizationGrade( SubGrade newVal )
    {
        this.memorizationGrade = newVal;
    }

    public SubGrade getUnderstandingGrade()
    {
        return this.understandingGrade;
    }

    public void setUnderstandingGrade( SubGrade newVal )
    {
        this.understandingGrade = newVal;
    }

    public SubGrade getReadingGrade()
    {
        return this.readingGrade;
    }

    public void setReadingGrade( SubGrade newVal )
    {
        this.readingGrade = newVal;
    }

    public SubGrade getWritingGrade()
    {
        return this.writingGrade;
    }

    public void setWritingGrade( SubGrade newVal )
    {
        this.writingGrade = newVal;
    }

    public SubGrade getSpeakingGrade()
    {
        return this.speakingGrade;
    }

    public void setSpeakingGrade( SubGrade newVal )
    {
        this.speakingGrade = newVal;
    }

    public SubGrade getHomeworkGrade()
    {
        return this.homeworkGrade;
    }

    public void setHomeworkGrade( SubGrade newVal )
    {
        this.homeworkGrade = newVal;
    }

    public SubGrade getClassworkGrade()
    {
        return this.classworkGrade;
    }

    public void setClassworkGrade( SubGrade newVal )
    {
        this.classworkGrade = newVal;
    }

    public SubGrade getObeydienGrade()
    {
        return this.obeydienGrade;
    }

    public void setObeydienGrade( SubGrade newVal )
    {
        this.obeydienGrade = newVal;
    }

    public SubGrade getCooperationGrade()
    {
        return this.cooperationGrade;
    }

    public void setCooperationGrade( SubGrade newVal )
    {
        this.cooperationGrade = newVal;
    }

    public SubGrade getHelpfulGrade()
    {
        return this.helpfulGrade;
    }

    public void setHelpfulGrade( SubGrade newVal )
    {
        this.helpfulGrade = newVal;
    }

    public SubGrade getNeatnessGrade()
    {
        return this.neatnessGrade;
    }

    public void setNeatnessGrade( SubGrade newVal )
    {
        this.neatnessGrade = newVal;
    }

    public SubGrade getRespectGrade()
    {
        return this.respectGrade;
    }

    public void setAbsentCount( SubGrade newVal )
    {
        this.respectGrade = newVal;
    }

    public Integer getAbsentCount()
    {
        return this.absentCount;
    }

    public void setAbsentCount( Integer count )
    {
        this.absentCount = count;
    }

    public Integer getTardyCount()
    {
        return this.tardyCount;
    }

    public void setTardyCount( Integer count )
    {
        this.tardyCount = count;
    }

}
