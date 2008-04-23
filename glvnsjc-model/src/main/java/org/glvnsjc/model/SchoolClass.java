package org.glvnsjc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SchoolClass
    implements Serializable
{

    /** nullable persistent field */
    private org.glvnsjc.model.ClassName name;

    /** nullable persistent field */
    private org.glvnsjc.model.ClassSubName subName;

    /** nullable persistent field */
    private org.glvnsjc.model.Grade grade;

    /** nullable persistent field */
    private String comment;

    /** default constructor */
    public SchoolClass()
    {
        this.name = ClassName.UNASSIGNED;
        this.subName = ClassSubName.UNASSIGNED;
        this.grade = Grade.UNASSIGNED;
        this.comment = "";
    }

    public org.glvnsjc.model.ClassName getName()
    {
        return this.name;
    }

    public void setName( org.glvnsjc.model.ClassName name )
    {
        this.name = name;
    }

    public org.glvnsjc.model.ClassSubName getSubName()
    {
        return this.subName;
    }

    public void setSubName( org.glvnsjc.model.ClassSubName subName )
    {
        this.subName = subName;
    }

    public org.glvnsjc.model.Grade getGrade()
    {
        return this.grade;
    }

    public void setGrade( org.glvnsjc.model.Grade grade )
    {
        this.grade = grade;
    }

    public java.lang.String getComment()
    {
        return this.comment;
    }

    public void setComment( java.lang.String comment )
    {
        this.comment = comment;
    }

    public String getFullClassName()
    {
        return this.name.toString() + this.subName;
    }

    public String toString()
    {
        return new ToStringBuilder( this ).toString();
    }

}
