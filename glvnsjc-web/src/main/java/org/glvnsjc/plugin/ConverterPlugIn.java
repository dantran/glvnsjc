package org.glvnsjc.plugin;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.glvnsjc.converter.Converter;

/**
 * <p><strong>ConverterPlugIn</strong> initializes and finalizes
 * custom BeanUtils Converters.</p>
 *
 * @author Dan T. Tran
 */

public final class ConverterPlugIn
    implements org.apache.struts.action.PlugIn
{

    private String dateFormat = "MM/dd/yyyy";

    public static void register()
    {
        Converter.init();
    }

    public void destroy()
    {
    }

    public void init( ActionServlet servlet, ModuleConfig config )
        throws ServletException
    {
        register();
    }

    public String getDateFormat()
    {
        return dateFormat;
    }

    public void setDateFormat( String dateFormat )
    {
        this.dateFormat = dateFormat;
    }

}
