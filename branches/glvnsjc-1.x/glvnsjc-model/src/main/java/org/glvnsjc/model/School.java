package org.glvnsjc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** A bussiness entity class representing a School
 * @author Dan Tran
 * @since 1.0
 * @hibernate.class table="school"
 */

public class School
    implements Serializable
{

    private Integer id;

    private String name;

    private String shortName;

    private org.glvnsjc.model.Address address;

    /** full constructor */
    public School( java.lang.String name, java.lang.String shortName, org.glvnsjc.model.Address address )
    {
        this.name = name;
        this.shortName = shortName;
        this.address = address;
    }

    /** default constructor */
    public School()
    {
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

    /** Login Name
     * @hibernate.property unique="true"
     */

    public java.lang.String getName()
    {
        return this.name;
    }

    public void setName( java.lang.String name )
    {
        this.name = name;
    }

    /** Abrivation name
     * @hibernate.property unique="true"
     */
    public java.lang.String getShortName()
    {
        return this.shortName;
    }

    public void setShortName( java.lang.String shortName )
    {
        this.shortName = shortName;
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

    public String toString()
    {
        return new ToStringBuilder( this ).append( "id", getId() ).toString();
    }

    public boolean equals( Object other )
    {
        if ( !( other instanceof School ) )
            return false;
        School castOther = (School) other;
        return new EqualsBuilder().append( this.getId(), castOther.getId() ).isEquals();
    }

    public int hashCode()
    {
        return new HashCodeBuilder().append( getId() ).toHashCode();
    }

}
