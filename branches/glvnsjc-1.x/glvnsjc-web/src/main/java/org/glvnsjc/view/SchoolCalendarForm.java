package org.glvnsjc.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.glvnsjc.util.GenericFactory;

public final class SchoolCalendarForm
    extends ValidatorForm
    implements java.io.Serializable
{

    private List schoolDays = ListUtils.lazyList( new ArrayList(),
                                                  new GenericFactory( "org.glvnsjc.view.SchoolDayView" ) );

    public SchoolCalendarForm()
    {
    }

    //In order to insert/add a new element, you must call getXXXX, and populate the data
    //  lazyList cant handle direct insertion
    public SchoolDayView getSchoolDay( int index )
    {
        return (SchoolDayView) schoolDays.get( index );
    }

    public void removeSchoolDays()
    {
        this.schoolDays.clear();
    }

    public List getSchoolDays()
    {
        return this.schoolDays;
    }

    public void reset( ActionMapping mapping, HttpServletRequest request )
    {

    }

    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate( ActionMapping mapping, HttpServletRequest request )
    {

        // Perform validator framework validations
        ActionErrors errors = super.validate( mapping, request );

        return errors;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

}
