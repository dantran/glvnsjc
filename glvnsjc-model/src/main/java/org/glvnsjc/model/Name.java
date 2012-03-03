package org.glvnsjc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class Name
    implements Serializable
{

    /** nullable persistent field */
    private String lastName;

    /** nullable persistent field */
    private String middleName;

    /** nullable persistent field */
    private String firstName;

    /** full constructor */
    public Name( java.lang.String lastName, java.lang.String middleName, java.lang.String firstName )
    {
        this.setLastName( lastName );
        this.setMiddleName( middleName );
        this.setFirstName( firstName );
    }

    /** default constructor */
    public Name()
    {
    }

    /**
     * @hibernate.property column="lastName"
     */
    public java.lang.String getLastNameRaw()
    {
        return this.lastName;
    }

    public void setLastNameRaw( java.lang.String lastName )
    {
        this.lastName = lastName;
    }

    /**
     * @hibernate.property column="middleName"
     */

    public java.lang.String getMiddleNameRaw()
    {
        return this.middleName;
    }

    public void setMiddleNameRaw( java.lang.String middleName )
    {
        this.middleName = middleName;
    }

    /**
     * @hibernate.property column="firstName"
     */

    public java.lang.String getFirstNameRaw()
    {
        return this.firstName;
    }

    public void setFirstNameRaw( java.lang.String firstName )
    {
        this.firstName = firstName;
    }

    public void setFirstName( String newValue )
    {
        //firstName = UnicodeUtil.unicode2Varchar(newValue);
        this.firstName = newValue;
    }

    public String getFirstName()
    {
        //return UnicodeUtil.varchar2Unicode(firstName);
        return this.firstName;
    }

    public void setLastName( String newValue )
    {
        //lastName = UnicodeUtil.unicode2Varchar(newValue);
        this.lastName = newValue;
    }

    public String getLastName()
    {
        //return UnicodeUtil.varchar2Unicode(lastName);
        return this.lastName;
    }

    public void setMiddleName( String newValue )
    {
        //middleName = UnicodeUtil.unicode2Varchar(newValue);
        this.middleName = newValue;
    }

    public String getMiddleName()
    {
        //return UnicodeUtil.varchar2Unicode(middleName);
        return this.middleName;
    }

    public String getFullName()
    {

        StringBuffer buffer = new StringBuffer();

        if ( lastName != null )
        {
            buffer.append( lastName ).append( " " );
        }
        if ( middleName != null )
        {
            buffer.append( middleName ).append( " " );
        }
        if ( firstName != null )
        {
            buffer.append( firstName );
        }
        //return UnicodeUtil.varchar2Unicode(buffer.toString());
        return buffer.toString();
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

}
