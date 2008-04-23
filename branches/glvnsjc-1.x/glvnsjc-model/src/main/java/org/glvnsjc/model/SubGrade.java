package org.glvnsjc.model;

public class SubGrade
    implements java.io.Serializable
{
    private final int code;

    private SubGrade( int code )
    {
        this.code = code;
    }

    public static final SubGrade UNASSIGNED = new SubGrade( 0 );

    public static final SubGrade VERY_GOOD = new SubGrade( 1 );

    public static final SubGrade GOOD = new SubGrade( 2 );

    public static final SubGrade SATISFY = new SubGrade( 3 );

    public static final SubGrade NEED_IMPROVEMENT = new SubGrade( 4 );

    public static final SubGrade[] gradeList = { UNASSIGNED, VERY_GOOD, GOOD, SATISFY, NEED_IMPROVEMENT };

    public int toInt()
    {
        return code;
    }

    public static SubGrade fromInt( int code )
    {
        switch ( code )
        {
            case 0:
                return UNASSIGNED;
            case 1:
                return VERY_GOOD;
            case 2:
                return GOOD;
            case 3:
                return SATISFY;
            case 4:
                return NEED_IMPROVEMENT;
            default:
                throw new RuntimeException( "Unknown sub grade code" );
        }
    }

    public static SubGrade fromString( String strCode )
    {
        if ( strCode == null || strCode.length() == 0 )
        {
            return UNASSIGNED;
        }

        switch ( strCode.charAt( 0 ) )
        {
            case 'g':
            case 'G':
                return VERY_GOOD;
            case 'k':
            case 'K':
                return GOOD;
            case 't':
            case 'T':
                return SATISFY;
            case 'c':
            case 'C':
                return NEED_IMPROVEMENT;

            default:
                throw new RuntimeException( "Unknown sub grade code" );
        }
    }

    public static int getMaxCode()
    {
        return 4;
    }

    public String toString()
    {
        switch ( this.code )
        {
            case 0:
                return "";
            case 1:
                return "G";
            case 2:
                return "K";
            case 3:
                return "T";
            case 4:
                return "C";
            default:
                throw new RuntimeException( "Unknown grade code" );
        }

    }

}
