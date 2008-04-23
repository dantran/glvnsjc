package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.PersistentIntegerEnum;

public class Privilege
    extends PersistentIntegerEnum
{

    public static final int ADMIN_CODE = 1;

    public static final int COMMUNITY_CODE = 2;

    public static final int PRINCIPAL_CODE = 3;

    public static final int SCHOOL_CODE = 4;

    public static final int CLASS_CODE = 5;

    public static final int UNASSIGNED_CODE = 99; //lowest priviledge

    public static final Privilege UNASSIGNED = new Privilege( "", UNASSIGNED_CODE );

    public static final Privilege ADMIN = new Privilege( "Administrator", ADMIN_CODE );

    public static final Privilege COMMUNITY = new Privilege( "Community", COMMUNITY_CODE );

    public static final Privilege PRINCIPAL = new Privilege( "Principal", PRINCIPAL_CODE );

    public static final Privilege SCHOOL = new Privilege( "School", SCHOOL_CODE );

    public static final Privilege CLASS = new Privilege( "Class", CLASS_CODE );

    public static final Privilege privilegeList[] = { ADMIN, COMMUNITY, PRINCIPAL, SCHOOL, CLASS, UNASSIGNED };

    public Privilege()
    {
    }

    private Privilege( String name, int persistentValue )
    {
        super( name, persistentValue );
    }

    public boolean isInRole( String name )
    {
        Privilege role = fromString( name );
        return isInRole( role );

    }

    // Does current role have more capabilities then the passing role?
    public boolean isInRole( Privilege role )
    {
        Integer currentRoleLevel = (Integer) this.getEnumCode();
        Integer passingRoleLevel = (Integer) role.getEnumCode();
        return currentRoleLevel.compareTo( passingRoleLevel ) <= 0;
    }

    public static Privilege fromString( Object code )
    {
        if ( code == null || code.toString().length() == 0 )
        {
            return UNASSIGNED;
        }
        for ( int i = 0; i < privilegeList.length; ++i )
        {
            if ( code.toString().equals( privilegeList[i].toString() ) )
            {
                return privilegeList[i];
            }
        }
        return UNASSIGNED;
    }

    public String getDisplay()
    {
        return toString();
    }
}
