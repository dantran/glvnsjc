package org.glvnsjc.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class UnicodeUtil
{

    /**
     * Make a string database-safe. Replace "'" by "\'" and "\" by
     * "\\". Replace characters with codes from 0x00 to 0x1f and codes
     * larger than 0xfe by "&#code;". This will help us storing
     * Unicode characters in a non-Unicode database, such as MySQL 3.
     **/
    public static String unicode2Varchar( String s )
    {

        if ( s == null )
        {
            return "";
        }

        StringBuffer b = new StringBuffer( s.length() + 2 );
        for ( int i = 0; i < s.length(); i++ )
        {
            char c = s.charAt( i );
            if ( c < ' ' || c > 254 )
            {
                b.append( "&#0" );
                b.append( (int) c );
                b.append( ';' );
            }
            else
            {
                if ( c == '\'' || c == '\\' )
                {
                    b.append( '\\' );
                }
                b.append( c );
            }
        }
        return b.toString();
    }

    private static final int S_TEXT = 0;

    private static final int S_AMP = 1;

    private static final int S_HASH = 2;

    private static final int S_DIGIT = 3;

    /**
     * Resolve our funny notation &#code; for special Unicode
     * characters.
     **/
    public static String varchar2Unicode( String s )
    {

        if ( s == null )
        {
            return "";
        }

        int len = s.length();
        StringBuffer b = new StringBuffer( len );
        int i = 0;
        int pos = s.indexOf( "&#0", i );
        if ( pos < 0 )
        {
            return s;
        }
        int state = S_TEXT;
        int start = 0;
        while ( i < len )
        {
            char c = s.charAt( i );
            switch ( state )
            {
                case S_TEXT:
                    // if we are parsing normal text and encounter an
                    // ampersand, go to ampersand state. otherwise, simply
                    // append the character to the result.
                    if ( c == '&' )
                    {
                        state = S_AMP;
                    }
                    else
                    {
                        b.append( c );
                    }
                    i++;
                    break;
                case S_AMP:
                    // if we've seen an ampersand and now get a hash,
                    // expect a zero. otherwise, append the ampersand and
                    // the current character to the result.
                    if ( c == '#' )
                    {
                        state = S_HASH;
                    }
                    else
                    {
                        b.append( '&' );
                        b.append( c );
                        state = S_TEXT;
                    }
                    i++;
                    break;
                case S_HASH:
                    // things are getting exciting now: we've seen an
                    // ampersand, a hash symbol and may now get a zero,
                    // indicating an encoded Unicode character. if there
                    // is not a zero, treat the entire sequence as normal
                    // text.
                    if ( c == '0' )
                    {
                        state = S_DIGIT;
                        start = i;
                    }
                    else
                    {
                        b.append( '&' );
                        b.append( '#' );
                        b.append( c );
                        state = S_TEXT;
                    }
                    i++;
                    break;
                case S_DIGIT:
                    // sanity check: if there is no digit after the zero,
                    // treat everything as normal text. otherwise: parse
                    // the integer up to a semicolon and turn it into a
                    // Unicode character.
                    if ( c < '0' || c > '9' )
                    {
                        b.append( '&' );
                        b.append( '#' );
                        b.append( '0' );
                        b.append( c );
                        state = S_TEXT;
                        i++;
                    }
                    else
                    {
                        int val = 0;
                        while ( true )
                        {
                            val = 10 * val + ( c - '0' );
                            i++;
                            if ( i == len )
                            {
                                b.append( '&' );
                                b.append( '#' );
                                i = start;
                                break;
                            }
                            c = s.charAt( i );
                            if ( c == ';' )
                            {
                                b.append( (char) val );
                                i++;
                                break;
                            }
                        }
                        state = S_TEXT;
                    }
                    break;
            }
        }
        return b.toString();
    }

}