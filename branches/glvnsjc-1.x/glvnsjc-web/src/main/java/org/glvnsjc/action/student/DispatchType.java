package org.glvnsjc.action.student;

/**
 * <tt>DispatchType</tt> is an enumeration type for student dispatch command type
 * <br>
 * @author Dan Tran
 */

public class DispatchType
    implements java.io.Serializable
{

    private final int code;

    private DispatchType( int code )
    {
        this.code = code;
    }

    public static final int UPDATE_CODE = 1;

    public static final int DELETE_CODE = 2;

    public static final int ADD_CODE = 3;

    public static final String UPDATE_CODE_STR = "update";

    public static final String DELETE_CODE_STR = "delete";

    public static final String ADD_CODE_STR = "add";

    public static final DispatchType ADD = new DispatchType( ADD_CODE );

    public static final DispatchType UPDATE = new DispatchType( UPDATE_CODE );

    public static final DispatchType DELETE = new DispatchType( DELETE_CODE );

    public int toInt()
    {
        return code;
    }

    public static DispatchType fromInt( int code )
    {
        switch ( code )
        {
            case ADD_CODE:
                return ADD;
            case UPDATE_CODE:
                return UPDATE;
            case DELETE_CODE:
                return DELETE;
            default:
                throw new RuntimeException( "Unknown Student Action code" );
        }
    }

    public static DispatchType fromStr( String str )
    {
        if ( UPDATE_CODE_STR.equals( str ) )
        {
            return UPDATE;
        }
        else if ( DELETE_CODE_STR.equals( str ) )
        {
            return DELETE;
        }
        else if ( ADD_CODE_STR.equals( str ) )
        {
            return ADD;
        }

        throw new RuntimeException( "Unknown Student Action code(" + str + ")" );
    }

    public String toString()
    {
        switch ( code )
        {
            case ADD_CODE:
                return ADD_CODE_STR;
            case UPDATE_CODE:
                return UPDATE_CODE_STR;
            case DELETE_CODE:
                return DELETE_CODE_STR;
            default:
                throw new RuntimeException( "Unknown Student Action code" );
        }
    }

}
