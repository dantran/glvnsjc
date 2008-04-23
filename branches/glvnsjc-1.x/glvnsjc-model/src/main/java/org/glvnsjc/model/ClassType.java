package org.glvnsjc.model;

public class ClassType
    implements java.io.Serializable
{

    private final int code;

    private ClassType( int code )
    {
        this.code = code;
    }

    public static final int GIAOLY_CODE = 1;

    public static final int VIETNGU_CODE = 2;

    public static final ClassType GIAOLY = new ClassType( GIAOLY_CODE );

    public static final ClassType VIETNGU = new ClassType( VIETNGU_CODE );

    public int toInt()
    {
        return code;
    }

    public static ClassType fromString( Object strCode )
    {
        return fromInt( Integer.parseInt( strCode.toString() ) );
    }

    public static ClassType fromInt( int code )
    {
        switch ( code )
        {
            case GIAOLY_CODE:
                return GIAOLY;
            case VIETNGU_CODE:
                return VIETNGU;
            default:
                throw new RuntimeException( "Unknown ClassType code(" + code + ")" );
        }
    }

    public String toString()
    {
        return Integer.toString( code );
    }

    public String toFriendlyName()
    {
        if ( this.code == GIAOLY_CODE )
        {
            return ( "Gia\u0301o Ly\u0301" );
        }
        else
        {
            return ( "Viê\u0323t Ng\u01B0\u0303" );
        }
    }

}
