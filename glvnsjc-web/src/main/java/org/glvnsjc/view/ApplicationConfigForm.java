package org.glvnsjc.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

// import org.apache.commons.beanutils.*;

public final class ApplicationConfigForm
    extends ValidatorForm
    implements java.io.Serializable
{

    private String currentSchoolYear;

    private List schools = new java.util.ArrayList();

    public ApplicationConfigForm()
    {
    }

    public void reset( ActionMapping mapping, HttpServletRequest request )
    {
        this.currentSchoolYear = "";
    }

    public String getCurrentSchoolYear()
    {
        return this.currentSchoolYear;
    }

    public void setCurrentSchoolYear( String newValue )
    {
        this.currentSchoolYear = newValue;
    }

    public List getSchools()
    {
        return this.schools;
    }

    public SchoolBean getSchool( int index )
    {
        while ( index >= schools.size() )
        {
            schools.add( new SchoolYearView() );
        }
        return (SchoolBean) schools.get( index );
    }

    public void setSchool( int index, SchoolBean school )
    {
        while ( index >= this.schools.size() )
        {
            this.schools.add( new SchoolBean() );
        }
        this.schools.set( index, school );
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

}
