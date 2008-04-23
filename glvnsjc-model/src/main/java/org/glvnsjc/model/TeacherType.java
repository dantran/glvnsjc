package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.PersistentIntegerEnum;

public class TeacherType
    extends PersistentIntegerEnum
{

    public static final TeacherType UNASSIGNED = new TeacherType( "", 0 );

    public static final TeacherType TEACHER = new TeacherType( "Teacher", 1 );

    public static final TeacherType TEACHERAID = new TeacherType( "TeacherAid", 2 );

    public static final TeacherType PRINCIPAL = new TeacherType( "Principal", 3 );

    public static final TeacherType MASTER = new TeacherType( "Master", 4 );

    public static final TeacherType[] teacherTypeList = { UNASSIGNED, TEACHER, TEACHERAID, PRINCIPAL, MASTER };

    public TeacherType()
    {
    }

    private TeacherType( String name, int persistentValue )
    {
        super( name, persistentValue );
    }

    public static TeacherType fromString( Object strValue )
    {
        if ( strValue == null || strValue.toString().length() == 0 )
        {
            return UNASSIGNED;
        }

        for ( int i = 0; i < teacherTypeList.length; ++i )
        {
            if ( strValue.toString().equals( teacherTypeList[i].toString() ) )
            {
                return teacherTypeList[i];
            }
        }
        return UNASSIGNED;

    }

    public String getDisplay()
    {
        return this.toString();
    }
}