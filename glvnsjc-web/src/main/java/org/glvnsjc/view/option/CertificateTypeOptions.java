package org.glvnsjc.view.option;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: Singleton to init torque
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */

import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;
import org.glvnsjc.model.CertificateType;

public class CertificateTypeOptions
{
    private static CertificateTypeOptions s_instance = null;

    private static boolean s_instanceFlag = false;

    private ArrayList _options = new ArrayList();

    public ArrayList getOptions()
    {
        return _options;
    }

    private CertificateTypeOptions()
    {
        for ( int i = 0; i < CertificateType.certificateTypeList.length; ++i )
        {
            _options.add( new LabelValueBean( CertificateType.certificateTypeList[i].toString(),
                                              CertificateType.certificateTypeList[i].toString() ) );
        }
    }

    public static CertificateTypeOptions getInstance()
    {
        if ( !s_instanceFlag )
        {
            s_instance = new CertificateTypeOptions();
            s_instanceFlag = true;
            return s_instance;
        }
        else
        {
            return s_instance;
        }
    }

}
