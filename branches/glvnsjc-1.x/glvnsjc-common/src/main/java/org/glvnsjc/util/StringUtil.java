package org.glvnsjc.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.ArrayList;


public class StringUtil
{

    public static boolean isBlank( String str )
    {
        if ( str == null || str.trim().length() == 0 )
            return true;

        return false;
    }

    ////////////////////////// CSV Stuff ////////////////////////////////////////
    public static String[] split( String src, char separator )
    {

        ArrayList tokens = new ArrayList();
        parseLine( src, separator, tokens );

        String[] result = new String[tokens.size()];
        tokens.toArray( result );
        return result;
    }

    // Break an individual line into tokens. This is a recursive function
    // that extracts the first token, then recursively parses the
    // remainder of the line.
    private static void parseLine( String curLine, char separator, ArrayList vsToken )
    {

        String firstToken = null;
        String remainderOfLine = null;
        int commaIndex = locateFirstDelimiter( curLine, separator );
        if ( commaIndex > -1 )
        {
            firstToken = curLine.substring( 0, commaIndex );
            remainderOfLine = curLine.substring( commaIndex + 1 );
        }
        else
        {
            // no commas, so the entire line is the token
            firstToken = curLine;
        }

        // remove redundant quotes
        firstToken = cleanupQuotes( firstToken );

        vsToken.add( firstToken );

        // recursively process the remainder of the line
        if ( remainderOfLine != null )
        {
            parseLine( remainderOfLine, separator, vsToken );
        }
    }

    // locate the position of the comma, taking into account that
    // a quoted token may contain ignorable commas.
    private static int locateFirstDelimiter( String curLine, char separator )
    {
        if ( curLine.startsWith( "\"" ) )
        {
            boolean inQuote = true;
            int numChars = curLine.length();
            for ( int i = 1; i < numChars; i++ )
            {
                char curChar = curLine.charAt( i );
                if ( curChar == '"' )
                {
                    inQuote = !inQuote;
                }
                else if ( curChar == separator && !inQuote )
                {
                    return i;
                }
            }
            return -1;
        }
        else
        {
            return curLine.indexOf( separator );
        }
    }

    // remove quotes around a token, as well as pairs of quotes
    // within a token.
    private static String cleanupQuotes( String token )
    {
        StringBuffer buf = new StringBuffer();
        int length = token.length();
        int curIndex = 0;

        if ( token.startsWith( "\"" ) && token.endsWith( "\"" ) )
        {
            curIndex = 1;
            length--;
        }

        boolean oneQuoteFound = false;
        boolean twoQuotesFound = false;

        while ( curIndex < length )
        {
            char curChar = token.charAt( curIndex );
            if ( curChar == '"' )
            {
                twoQuotesFound = ( oneQuoteFound ) ? true : false;
                oneQuoteFound = true;
            }
            else
            {
                oneQuoteFound = false;
                twoQuotesFound = false;
            }

            if ( twoQuotesFound )
            {
                twoQuotesFound = false;
                oneQuoteFound = false;
                curIndex++;
                continue;
            }

            buf.append( curChar );
            curIndex++;
        }

        return buf.toString();
    }

    //////////////////////////////////////////////////////////////////////////////
    //quote a string if it has a separator in the middle
    public static String ToCSVField( String from, String separator )
    {

        if ( from.indexOf( separator ) != -1 )
        {
            return "\"" + from + "\"";
        }
        return from;
    }

    /**
     * Helper to return a string showing the changed value of a field
     * @param fieldName
     * @param obj1
     * @param obj2
     * @return
     */
    public static String displayComparison( String fieldName, Object obj1, Object obj2 )
    {
        if ( obj1 != null && !obj1.equals( obj2 ) )
        {
            return "    " + fieldName + ":" + obj1 + " ----> " + obj2 + "\n\r";
        }
        
        return "";
    }    
}