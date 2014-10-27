package org.glvnsjc.view.option;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;

/**
 * <p><strong>OptionsPlugIn</strong> Load all option values from database</p>
 *
 * @author Dan T. Tran
 */

public final class OptionsPlugIn
    implements org.apache.struts.action.PlugIn
{

    /**
     * Logging output for this plug in instance.
     */
    private static Log log = LogFactory.getLog( OptionsPlugIn.class );

    /**
     * Gracefully shut down this database, releasing any resources
     * that were allocated at initialization.
     */
    public void destroy()
    {

    }

    /**
     * Initialize and load our initial database from persistent storage.
     *
     * @param servlet The ActionServlet for this web application
     * @param config The ApplicationConfig for our owning module
     *
     * @exception ServletException if we cannot configure ourselves correctly
     */
    public void init( ActionServlet servlet, ModuleConfig config )
        throws ServletException
    {

        try
        {

            //drop down values helper
            servlet.getServletContext().setAttribute( "schoolOptions", SchoolOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "schoolNonEmptyOptions",
                                                      SchoolOptions.getInstance().getNonEmptyOptions() );
            servlet.getServletContext().setAttribute( "genderOptions", GenderOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "stateOptions", StateOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "memberOptions", MemberTypeOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "encodingOptions", EncodingOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "separatorOptions", SeparatorOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "classSubNameOptions",
                                                      ClassSubNameOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "classNameOptions", ClassNameOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "classTypeOptions", ClassTypeOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "gradeOptions", GradeOptions.getInstance().getOptions() );
            servlet.getServletContext()
                .setAttribute( "schoolYearOptions", SchoolYearOptions.getInstance().getOptions() );

            servlet.getServletContext().setAttribute( "privilegeOptions", PrivilegeOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "schoolPrivilegeOptions",
                                                      PrivilegeOptions.getInstance().getSchoolOptions() );
            servlet.getServletContext().setAttribute( "teacherTypeOptions",
                                                      TeacherTypeOptions.getInstance().getOptions() );
            servlet.getServletContext().setAttribute( "principalTeacherTypeOptions",
                                                      TeacherTypeOptions.getInstance().getPrincipalOptions() );
            servlet.getServletContext().setAttribute( "certificateTypeOptions",
                                                      CertificateTypeOptions.getInstance().getOptions() );

        }
        catch ( Throwable e )
        {

            log.error( "Cannot initialize OptionsPlugIn", e );
            throw new ServletException( "Cannot initialize OptionsPlugIn", e );
        }

    }

}
