/*
 * $Header:
 * /cvs/glvndb/nglvnsjc/glvnsjc-model/src/main/java/org/glvnsjc/converter/StringConverter.java,v 1.1
 * 2006/07/06 21:20:01 danttran Exp $ $Revision: 1.1 $ $Date: 2006/07/06 21:20:01 $
 * 
 * ====================================================================
 * 
 * The Apache Software License, Version 1.1
 * 
 * Copyright (c) 1999-2002 The Apache Software Foundation. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * 3. The end-user documentation included with the redistribution, if any, must include the
 * following acknowlegement: "This product includes software developed by the Apache Software
 * Foundation (http://www.apache.org/)." Alternately, this acknowlegement may appear in the software
 * itself, if and wherever such third-party acknowlegements normally appear.
 * 
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software Foundation" must not be used
 * to endorse or promote products derived from this software without prior written permission. For
 * written permission, please contact apache@apache.org.
 * 
 * 5. Products derived from this software may not be called "Apache" nor may "Apache" appear in
 * their names without prior written permission of the Apache Group.
 * 
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR ITS CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.glvnsjc.converter;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * <p>Standard {@link Converter} implementation that converts an incoming
 * String into a <code>java.lang.String</code> object, optionally using a
 * default value or throwing a {@link ConversionException} if a conversion
 * error occurs.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2006/07/06 21:20:01 $
 * @since 1.3
 */

public final class StringConverter
    implements Converter
{

    // --------------------------------------------------------- Public Methods

    /**
     * Convert the specified input object into an output object of the
     * specified type.
     *
     * @param type Data type to which this value should be converted
     * @param value The input value to be converted
     *
     * @exception ConversionException if conversion cannot be performed
     *  successfully
     */
    public Object convert( Class type, Object value )
    {

        if ( value == null )
        {
            return ( (String) null );
        }
        else
        {
            if ( value instanceof java.util.Date )
            {
                return Convert.DateToString( (java.util.Date) value );
            }
            else
            {
                return ( value.toString() );
            }
        }

    }

}
