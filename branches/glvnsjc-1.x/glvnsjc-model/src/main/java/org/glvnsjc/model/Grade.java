package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.PersistentIntegerEnum;

public class Grade
    extends PersistentIntegerEnum
{
    public static final Grade UNASSIGNED = new Grade( "", 0 );

    public static final Grade PASS = new Grade( "P", 1 );

    public static final Grade FAIL = new Grade( "F", 2 );

    public static final Grade FIRST = new Grade( "1", 3 );

    public static final Grade SECOND = new Grade( "2", 4 );

    public static final Grade THIRD = new Grade( "3", 5 );

    public static final Grade INCOMPLETE = new Grade( "I", 6 );

    public static final Grade[] gradeList = { UNASSIGNED, PASS, FAIL, FIRST, SECOND, THIRD, INCOMPLETE };

    public Grade()
    {
    }

    private Grade( String name, int persistentValue )
    {
        super( name, persistentValue );
    }

    public static Grade fromString( Object strValue )
    {
        for ( int i = 0; i < gradeList.length && strValue != null; ++i )
        {
            if ( gradeList[i].toString().equals( strValue ) )
            {
                return gradeList[i];
            }
        }
        return UNASSIGNED;
    }

    /**
     * Simply return the enums name.
     *
     * @return the string representation of this enum.
     */
    public String getDisplay()
    {
        return name;
    }

}
