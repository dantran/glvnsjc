package org.glvnsjc.model;

import org.glvnsjc.model.hibernate.PersistentIntegerEnum;

public class CertificateType
    extends PersistentIntegerEnum
{

    public static final CertificateType UNASSIGNED = new CertificateType( "", 0 );

    public static final CertificateType SEXUAL_MISCONDUCD_WORKSHOP = new CertificateType( "Sexual Misconduct Workshop",
                                                                                         1 );
    public static final CertificateType FINGER_PRINT_PROCESS = new CertificateType( "Finger Print Process", 2 );

    public static final CertificateType INTRO_TO_CATACHIRST = new CertificateType( "Introduction To Catachrirt", 3 );

    public static final CertificateType FAITH_BEYOND_2000_PART_1 = new CertificateType( "Faith Beyong 2000 Part 1", 4 );

    public static final CertificateType FAITH_BEYOND_2000_PART_2 = new CertificateType( "Faith Beyond 2000 Part 2", 5 );

    public static final CertificateType CATACHIST_CERTIFICATION = new CertificateType( "Catachist Certification", 6 );

    public static final CertificateType[] certificateTypeList = { UNASSIGNED, SEXUAL_MISCONDUCD_WORKSHOP,
        FINGER_PRINT_PROCESS, INTRO_TO_CATACHIRST, FAITH_BEYOND_2000_PART_1, FAITH_BEYOND_2000_PART_2,
        CATACHIST_CERTIFICATION };

    public CertificateType()
    {
    }

    private CertificateType( String name, int persistentValue )
    {
        super( name, persistentValue );
    }

    public static CertificateType fromString( Object strValue )
    {
        if ( strValue == null || strValue.toString().length() == 0 )
        {
            return UNASSIGNED;
        }

        for ( int i = 0; i < certificateTypeList.length; ++i )
        {
            if ( strValue.toString().equals( certificateTypeList[i].toString() ) )
            {
                return certificateTypeList[i];
            }
        }
        return UNASSIGNED;

    }

    public String getDisplay()
    {
        return this.toString();
    }
}