package org.glvnsjc.view;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.glvnsjc.model.Address;
import org.glvnsjc.model.Baptism;
import org.glvnsjc.model.Eucharist;
import org.glvnsjc.model.Gender;
import org.glvnsjc.model.Name;

/** @author Hibernate CodeGenerator */
public class StudentView
    implements Serializable
{

    /** identifier field */
    private String id;

    /** nullable persistent field */
    private String birthDate;

    /** nullable persistent field */
    private String saintName;
    
    private String baptismDate;
    
    private String baptismLocation;
    
    private String eucharistDate;
    
    private String eucharistLocation;

    /** nullable persistent field */
    private org.glvnsjc.model.Gender gender;

    public void setEucharistLocation( String eucharistLocation )
    {
        this.eucharistLocation = eucharistLocation;
    }

    /** persistent field */
    private org.glvnsjc.model.Name name;

    /** persistent field */
    private org.glvnsjc.model.Name parentName;

    /** persistent field */
    private org.glvnsjc.model.Address address;

    public StudentView()
    {
        this.id = "";
        this.birthDate = null;
        this.saintName = "";
        this.gender = Gender.UNASSIGNED;
        this.name = new Name();
        this.parentName = new Name();
        this.address = new Address();
        
        this.baptismDate = null;
        this.baptismLocation = "";
        this.eucharistDate = null;
        this.eucharistLocation = "";
    }

    public java.lang.String getId()
    {
        return this.id;
    }

    public void setId( java.lang.String id )
    {
        this.id = id;
    }

    public String getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate( String birthDate )
    {
        this.birthDate = birthDate;
    }

    public java.lang.String getSaintName()
    {
        return this.saintName;
    }
    
    public void setSaintName( java.lang.String saintName )
    {
        this.saintName = saintName;
    }

    public org.glvnsjc.model.Gender getGender()
    {
        return this.gender;
    }

    public void setGender( org.glvnsjc.model.Gender gender )
    {
        this.gender = gender;
    }

    public org.glvnsjc.model.Name getName()
    {
        return this.name;
    }

    public void setName( org.glvnsjc.model.Name name )
    {
        this.name = name;
    }

    public org.glvnsjc.model.Name getParentName()
    {
        return this.parentName;
    }

    public void setParentName( org.glvnsjc.model.Name parentName )
    {
        this.parentName = parentName;
    }

    public org.glvnsjc.model.Address getAddress()
    {
        return this.address;
    }

    public void setAddress( org.glvnsjc.model.Address address )
    {
        this.address = address;
    }
    
    public String getBaptismDate()
    {
        return baptismDate;
    }

    public void setBaptismDate( String baptismDate )
    {
        this.baptismDate = baptismDate;
    }

    public String getBaptismLocation()
    {
        return baptismLocation;
    }

    public void setBaptismLocation( String baptismLocation )
    {
        this.baptismLocation = baptismLocation;
    }

    public String getEucharistDate()
    {
        return eucharistDate;
    }

    public void setEucharistDate( String eucharistDate )
    {
        this.eucharistDate = eucharistDate;
    }

    public String getEucharistLocation()
    {
        return eucharistLocation;
    }
    

    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof StudentView ) )
            return false;
        StudentView castOther = (StudentView) other;
        return new EqualsBuilder().append( this.getId(), castOther.getId() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getId() ).toHashCode();
    }

}
