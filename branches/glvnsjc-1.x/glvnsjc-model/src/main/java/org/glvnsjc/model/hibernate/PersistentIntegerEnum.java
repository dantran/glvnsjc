package org.glvnsjc.model.hibernate;

import java.io.Serializable;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.type.NullableType;

/**
 * Provides a base class for persistable, type-safe, comparable,
 * and serializable enums persisted as integers.
 *
 * <p>Create a subclass of this class implementing the enumeration:
 * <pre>package com.foo;
 *
 * public final class Gender extends PersistentCharacterEnum {
 *  public static final Gender MALE = new Gender("male", 0);
 *  public static final Gender FEMALE = new Gender("female", 1);
 *  public static final Gender UNDETERMINED = new Gender("undetermined", 2);
 *
 *  public Gender() {}
 *
 *  private Gender(String name, int persistentValue) {
 *   super(name, persistentValue);
 *  }
 * }
 * </pre>
 * Note that a no-op default constructor must be provided.</p>
 *
 * <p>Use this enumeration in your mapping file as:
 * <pre>&lt;property name="gender" type="com.foo.Gender"&gt;</pre></p>
 *
 * <p><code>
 * $Id: PersistentIntegerEnum.java,v 1.2 2006/12/27 08:41:12 danttran Exp $
 * </pre></p>
 *
 * @version $Revision: 1.2 $
 * @author &Oslash;rjan Nygaard Austvold
 */
public abstract class PersistentIntegerEnum
    extends PersistentEnum
{
    /**
     * Default constructor.  Hibernate need the default constructor
     * to retrieve an instance of the enum from a JDBC resultset.
     * The instance will be converted to the correct enum instance
     * in {@link #nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)}.
     */
    protected PersistentIntegerEnum()
    {
        // no-op -- instance will be tossed away once the equivalent enum is found.
    }

    /**
     * Constructs an enum with the given name and persistent representation.
     *
     * @param name name of enum.
     * @param persistentInteger persistent representation of the enum.
     */
    protected PersistentIntegerEnum( String name, int persistentInteger )
    {
        super( name, new Integer( persistentInteger ) );
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo( Object other )
    {
        if ( other == this )
        {
            return 0;
        }
        return ( (Integer) getEnumCode() ).compareTo( ( (Integer) other ) );
    }

    /**
     * @see PersistentEnum#getNullableType()
     */
    protected NullableType getNullableType()
    {
        return Hibernate.INTEGER;
    }

    public Object assemble( Serializable cached, Object owner )
        throws HibernateException
    {
        return owner;
    }

    public Serializable disassemble( Object value )
        throws HibernateException
    {
        return this.enumCode;
    }

    public boolean equals( Object x, Object y )
        throws HibernateException
    {
        if ( x == y )
        {
            return true;
        }
        else if ( x == null || y == null )
        {
            return false;
        }
        else
        {
        	return ((PersistentIntegerEnum)x).getEnumCode() == ((PersistentIntegerEnum)y).getEnumCode();
        }
    }

    public int hashCode( Object x )
    {
        return ( (Integer) x ).intValue();
    }

    public Object replace( Object original, Object target, Object owner )
        throws HibernateException
    {
        return original;
    }
}
