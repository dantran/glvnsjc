package org.glvnsjc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** A bussiness entity class representing a Student
 * @author Dan Tran
 * @since 1.0
 * @hibernate.class table="student"
 */

public class Student
    implements Serializable
{

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private java.util.Date birthDate;

    /** nullable persistent field */
    private String saintName;
    
    private org.glvnsjc.model.Baptism baptism;
    
    private org.glvnsjc.model.Eucharist eucharist;

    /** nullable persistent field */
    private org.glvnsjc.model.Gender gender;

    /** persistent field */
    private org.glvnsjc.model.Name name;

    /** persistent field */
    private org.glvnsjc.model.Name parentName;

    /** persistent field */
    private org.glvnsjc.model.Address address;

    /** persistent field */
    private Set schoolYears = new HashSet();

    /** full constructor */
    public Student( java.lang.Integer id, java.util.Date birthDate, java.lang.String saintName,
                    org.glvnsjc.model.Gender gender, org.glvnsjc.model.Name name, org.glvnsjc.model.Name parentName,
                    org.glvnsjc.model.Address address )
    {
        this.id = id;
        this.birthDate = birthDate;
        this.saintName = saintName;
        this.gender = gender;
        this.name = name;
        this.parentName = parentName;
        this.address = address;
        this.baptism = new Baptism();
        this.eucharist = new Eucharist();
    }

    /** default constructor */
    public Student()
    {
        this.name = new Name();
        this.parentName = new Name();
        this.address = new Address();
        this.baptism = new Baptism();
        this.eucharist = new Eucharist();

    }

    /**
     * @hibernate.id generator-class="native"
     */

    public java.lang.Integer getId()
    {
        return this.id;
    }

    public void setId( java.lang.Integer id )
    {
        this.id = id;
    }

    /**
     * @hibernate.property
     */

    public java.util.Date getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate( java.util.Date birthDate )
    {
        this.birthDate = birthDate;
    }

    public String getBirthDateDisplay()
    {
        return org.glvnsjc.converter.Convert.DateToString( this.birthDate );
    }

    /**
     * @hibernate.property
     */

    public java.lang.String getSaintName()
    {
        return this.saintName;
    }

    public void setSaintName( java.lang.String saintName )
    {
        this.saintName = saintName;
    }
    
    public org.glvnsjc.model.Baptism getBaptism()
    {
        if ( this.baptism == null ) {
            this.baptism = new Baptism();
        }
        return baptism;
    }

    public void setBaptism( org.glvnsjc.model.Baptism baptism )
    {
        this.baptism = baptism;
    }

    public org.glvnsjc.model.Eucharist getEucharist()
    {
        if ( this.eucharist == null ) {
            this.eucharist = new Eucharist();
        }
        return eucharist;
    }

    public void setEucharist( org.glvnsjc.model.Eucharist eucharist )
    {
        this.eucharist = eucharist;
    }
    

    /**
     * @hibernate.property type="org.glvnsjc.model.Gender
     */

    public org.glvnsjc.model.Gender getGender()
    {
        return this.gender;
    }

    public void setGender( org.glvnsjc.model.Gender gender )
    {
        this.gender = gender;
    }

    /**
     * @hibernate.component class="org.glvnsjc.model.Name"
     */

    public org.glvnsjc.model.Name getName()
    {
        return this.name;
    }

    public void setName( org.glvnsjc.model.Name name )
    {
        this.name = name;
    }

    /**
     * @hibernate.component
     */
    public org.glvnsjc.model.Name getParentName()
    {
        return this.parentName;
    }

    public void setParentName( org.glvnsjc.model.Name parentName )
    {
        this.parentName = parentName;
    }

    /**
     * @hibernate.component
     */

    public org.glvnsjc.model.Address getAddress()
    {
        return this.address;
    }

    public void setAddress( org.glvnsjc.model.Address address )
    {
        this.address = address;
    }

    public Set getSchoolYears()
    {
        return schoolYears;
    }

    public void setSchoolYears( Set schoolYears )
    {
        this.schoolYears = schoolYears;
    }

    //////////////////////////////////////// helpers //////////////////////////
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof Student ) )
            return false;
        Student castOther = (Student) other;
        return new EqualsBuilder().append( this.getId(), castOther.getId() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getId() ).toHashCode();
    }

}
