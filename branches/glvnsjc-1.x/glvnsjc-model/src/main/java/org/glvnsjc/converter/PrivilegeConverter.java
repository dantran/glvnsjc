package org.glvnsjc.converter;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.glvnsjc.model.Privilege;

public final class PrivilegeConverter
    implements Converter
{

    /**
     * Create a {@link Converter} that will throw a {@link ConversionException}
     * if a conversion error occurs.
     */
    public PrivilegeConverter()
    {
        this.defaultValue = Privilege.UNASSIGNED;
        this.useDefault = true;
    }

    /**
     * Create a {@link Converter} that will return the specified default value
     * if a conversion error occurs.
     *
     * @param defaultValue The default value to be returned
     */
    public PrivilegeConverter( Object defaultValue )
    {
        this.defaultValue = defaultValue;
        this.useDefault = true;
    }

    // ----------------------------------------------------- Instance Variables

    /**
     * The default value specified to our Constructor, if any.
     */
    private Object defaultValue = null;

    /**
     * Should we return the default value on conversion errors?
     */
    private boolean useDefault = true;

    // --------------------------------------------------------- Public Methods

    /**
     * Convert the specified input object into an output object of the
     * specified type.
     *
     * @param type Data type to which this value should be converted
     * @param value The input value to be converted
     *
     * @exception ConversionException if conversion cannot be performed
     *  successfully
     */
    public Object convert( Class type, Object value )
    {
        if ( value == null )
        {
            if ( useDefault )
            {
                return ( defaultValue );
            }
            else
            {
                throw new ConversionException( "No value specified" );
            }
        }

        if ( value instanceof Privilege )
        {
            return ( value );
        }

        try
        {
            return ( Privilege.fromString( value.toString() ) );
        }
        catch ( Exception e )
        {
            if ( useDefault )
            {
                return ( defaultValue );
            }
            else
            {
                throw new ConversionException( e );
            }
        }
    }

}
