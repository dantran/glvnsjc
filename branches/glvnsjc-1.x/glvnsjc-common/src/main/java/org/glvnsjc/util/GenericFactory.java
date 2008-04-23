package org.glvnsjc.util;

// Here is the GenericFactory code

import org.apache.commons.collections.Factory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// use with org.apache.common.collection.ListUtil.lazyList

public class GenericFactory
    implements Factory
{
    private static final Log log = LogFactory.getLog( GenericFactory.class );

    private String className;

    private Object object;

    public GenericFactory( String className )
    {
        this.className = className;
    }

    public Object create()
    {
        try
        {
            object = null;
            Class classDefinition = Class.forName( className );
            object = classDefinition.newInstance();
        }
        catch ( InstantiationException ex )
        {
            log.error( ex.getMessage(), ex.fillInStackTrace() );
        }
        catch ( IllegalAccessException ex )
        {
            log.error( ex.getMessage(), ex.fillInStackTrace() );
        }
        catch ( ClassNotFoundException ex )
        {
            log.error( ex.getMessage(), ex.fillInStackTrace() );
        }

        return object;
    }
}
