package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.PersistentIntegerEnum;

public class ClassSubName
    extends PersistentIntegerEnum
{

    public static final ClassSubName UNASSIGNED = new ClassSubName( "", 0 );

    public static final ClassSubName A = new ClassSubName( "A", 1 );

    public static final ClassSubName B = new ClassSubName( "B", 2 );

    public static final ClassSubName C = new ClassSubName( "C", 3 );

    public static final ClassSubName D = new ClassSubName( "D", 4 );

    public static final ClassSubName E = new ClassSubName( "E", 5 );

    public static final ClassSubName[] subNameList = { UNASSIGNED, A, B, C, D, E };

    public ClassSubName()
    {
    }

    private ClassSubName( String name, int persistentValue )
    {
        super( name, persistentValue );
    }

    public static ClassSubName fromString( Object strValue )
    {
        for ( int i = 0; i < subNameList.length && strValue != null; ++i )
        {
            if ( subNameList[i].toString().equals( strValue ) )
            {
                return subNameList[i];
            }
        }
        return UNASSIGNED;
    }

}