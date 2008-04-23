package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.PersistentIntegerEnum;

public final class Gender
    extends PersistentIntegerEnum
{

    public static final int UNASSIGNED_CODE = 0;

    public static final int MALE_CODE = 1;

    public static final int FEMALE_CODE = 2;

    public static final String UNASSIGNED_STR_CODE = "";

    public static final String MALE_STR_CODE = "M";

    public static final String FEMALE_STR_CODE = "F";

    public static final Gender UNASSIGNED = new Gender( UNASSIGNED_STR_CODE, UNASSIGNED_CODE );

    public static final Gender MALE = new Gender( MALE_STR_CODE, MALE_CODE );

    public static final Gender FEMALE = new Gender( FEMALE_STR_CODE, FEMALE_CODE );

    public Gender()
    {
    }

    private Gender( String name, int persistentValue )
    {
        super( name, persistentValue );
    }

    public static Gender fromString( String strCode )
    {
        if ( strCode == null || strCode.length() == 0 )
        {
            return Gender.UNASSIGNED;
        }

        String firstChar = strCode.substring( 0, 1 );
        if ( firstChar.equalsIgnoreCase( MALE_STR_CODE ) )
        {
            return Gender.MALE;
        }
        else if ( firstChar.equalsIgnoreCase( FEMALE_STR_CODE ) )
        {
            return Gender.FEMALE;
        }
        else
        {
            throw new RuntimeException( "Unknown gender string code(" + strCode + ")" );
        }

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
