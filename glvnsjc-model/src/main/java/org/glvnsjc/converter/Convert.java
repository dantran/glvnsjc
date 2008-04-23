package org.glvnsjc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Convert
{

    public static String DateToString( Date date )
    {
        if ( date == null )
        {
            return "";
        }
        SimpleDateFormat dt = new SimpleDateFormat( "MM/dd/yyyy" );
        StringBuffer buf = new StringBuffer();
        buf = dt.format( date, buf, new java.text.FieldPosition( 0 ) );
        return buf.toString();

    }

    public static Date StringToDate( String from )
    {
        Date to = null;
        if ( from != null && from.trim().length() != 0 )
        {
            SimpleDateFormat df = new SimpleDateFormat( "MM/dd/yyyy" );
            try
            {
                to = df.parse( from );
            }
            catch ( ParseException e )
            {
                throw new java.lang.IllegalArgumentException( "Invalid date string: " + from );
            }
        }
        return to;
    }

}