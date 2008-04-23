package org.glvnsjc.converter;

import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassSubName;
import org.glvnsjc.model.Gender;
import org.glvnsjc.model.Grade;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.model.TeacherType;

public class Converter
{
    public static void init()
    {
        ConvertUtils.register( new DateConverter(), Date.class );
        ConvertUtils.register( new GenderConverter(), Gender.class );
        ConvertUtils.register( new ClassNameConverter(), ClassName.class );
        ConvertUtils.register( new ClassSubNameConverter(), ClassSubName.class );
        ConvertUtils.register( new GradeConverter(), Grade.class );
        ConvertUtils.register( new PrivilegeConverter(), Privilege.class );
        ConvertUtils.register( new TeacherTypeConverter(), TeacherType.class );
        ConvertUtils.register( new StringConverter(), String.class );
    }
}
