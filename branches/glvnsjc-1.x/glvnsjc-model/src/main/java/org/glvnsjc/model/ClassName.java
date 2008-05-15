package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.PersistentIntegerEnum;

public class ClassName
    extends PersistentIntegerEnum
{

    public static final ClassName UNASSIGNED = new ClassName( "", 0 ); //unassigned

    public static final ClassName C1 = new ClassName( "1", 1 );

    public static final ClassName C2 = new ClassName( "2", 2 );

    public static final ClassName C3 = new ClassName( "3", 3 );

    public static final ClassName C4 = new ClassName( "4", 4 );

    public static final ClassName C5 = new ClassName( "5", 5 );

    public static final ClassName C6 = new ClassName( "6", 6 );

    public static final ClassName C7 = new ClassName( "7", 7 );

    public static final ClassName C8 = new ClassName( "8", 8 );

    public static final ClassName C9 = new ClassName( "9", 9 );

    public static final ClassName C10 = new ClassName( "10", 10 );

    public static final ClassName C11 = new ClassName( "11", 11 );

    public static final ClassName C12 = new ClassName( "12", 12 );

    public static final ClassName[] classNameList = { UNASSIGNED, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12 };

    public ClassName()
    {
    }

    private ClassName( String name, int persistentValue )
    {
        super( name, persistentValue );
    }

    public static ClassName fromString( Object strValue )
    {
        for ( int i = 0; i < classNameList.length && strValue != null && strValue.toString().length() != 0; ++i )
        {
            if ( strValue.toString().equals( classNameList[i].toString() ) )
            {
                return classNameList[i];
            }
        }
        return UNASSIGNED;

    }

    public ClassName nextClass()
    {
        for ( int i = 0; i < classNameList.length; ++i )
        {
            if ( this.equals( classNameList[i] ) )
            {
                if ( this.equals( C12 ) )
                {
                    return C12; // stay, no more class 
                }
                else
                {
                    return classNameList[i + 1];
                }

            }
        }
        return UNASSIGNED;
    }
}
