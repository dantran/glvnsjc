package org.glvnsjc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.glvnsjc.util.StringUtil;


public class Address
    implements Serializable
{

    /** nullable persistent field */
    private String street1;

    /** nullable persistent field */
    private String street2;

    /** nullable persistent field */
    private String city;

    /** nullable persistent field */
    private String state;

    /** nullable persistent field */
    private String zipCode;

    /** nullable persistent field */
    private String email;

    /** nullable persistent field */
    private String phone1;

    /** nullable persistent field */
    private String phone2;

    /** full constructor */
    public Address( java.lang.String street1, java.lang.String street2, java.lang.String city, java.lang.String state,
                   java.lang.String zipCode, java.lang.String email, java.lang.String phone1, java.lang.String phone2 )
    {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    /** default constructor */
    public Address()
    {
    }

    public java.lang.String getStreet1()
    {
        return this.street1;
    }

    public void setStreet1( java.lang.String street1 )
    {
        this.street1 = street1;
    }

    public java.lang.String getStreet2()
    {
        return this.street2;
    }

    public void setStreet2( java.lang.String street2 )
    {
        this.street2 = street2;
    }

    public java.lang.String getCity()
    {
        return this.city;
    }

    public void setCity( java.lang.String city )
    {
        this.city = city;
    }

    public java.lang.String getState()
    {
        return this.state;
    }

    public void setState( java.lang.String state )
    {
        this.state = state;
    }

    public java.lang.String getZipCode()
    {
        return this.zipCode;
    }

    public void setZipCode( java.lang.String zipCode )
    {
        this.zipCode = zipCode;
    }

    public java.lang.String getEmail()
    {
        return this.email;
    }

    public void setEmail( java.lang.String email )
    {
        this.email = email;
    }

    public java.lang.String getPhone1()
    {
        return this.phone1;
    }

    public void setPhone1( java.lang.String phone1 )
    {
        this.phone1 = phone1;
    }

    public java.lang.String getPhone2()
    {
        return this.phone2;
    }

    public void setPhone2( java.lang.String phone2 )
    {
        this.phone2 = phone2;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

    ///////////////////////////////////////////////////////////////////////////
    // helpers
    ///////////////////////////////////////////////////////////////////////////
    public String getFullAddress()
    {
        StringBuffer buffer = new StringBuffer();
        if ( !StringUtil.isBlank( this.street1 ) )
        {
            buffer.append( this.street1 );
        }
        if ( !StringUtil.isBlank( this.street2 ) )
        {
            buffer.append( " " ).append( this.street2 );
        }
        if ( !StringUtil.isBlank( this.city ) )
        {
            buffer.append( " " ).append( this.city );
        }
        if ( !StringUtil.isBlank( this.state ) )
        {
            buffer.append( " " ).append( this.state );
        }
        if ( !StringUtil.isBlank( this.zipCode ) )
        {
            buffer.append( " " ).append( this.zipCode );
        }
        return buffer.toString();
    }
    
    public String getAddressLine1()
    {
        StringBuffer buffer = new StringBuffer();
        if ( !StringUtil.isBlank( this.street1 ) )
        {
            buffer.append( this.street1 );
        }
        if ( !StringUtil.isBlank( this.street2 ) )
        {
            buffer.append( " " ).append( this.street2 );
        }
        return buffer.toString();
    }
    
    public String getAddressLine2()
    {
        StringBuffer buffer = new StringBuffer();
        if ( !StringUtil.isBlank( this.city ) )
        {
            buffer.append( this.city );
        }
        if ( !StringUtil.isBlank( this.state ) )
        {
            buffer.append( ", " ).append( this.state );
        }
        if ( !StringUtil.isBlank( this.zipCode ) )
        {
            buffer.append( " " ).append( this.zipCode );
        }
        return buffer.toString();
    }
    
    /*
    public boolean equals(Address obj) 
    {
        if ( obj instanceof Address == false )
        {
            return false;
        }
        
        if ( this == obj )
        {
            return true;
        }
        
        Address rhs = (Address) obj;
        
        return new EqualsBuilder()
                           .appendSuper(super.equals(obj))
                           .append(street1, rhs.street1)
                           .append(street2, rhs.street2)
                           .append(city, rhs.city)
                           .append(state, rhs.state)
                           .append(zipCode, rhs.zipCode)
                           .append(email, rhs.email)
                           .append(phone1, rhs.phone1)
                           .append(phone2, rhs.phone2)
                           .isEquals();
 
     }
     */
    

     
}
