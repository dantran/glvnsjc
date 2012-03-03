package org.glvnsjc.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.glvnsjc.model.Address;
import org.glvnsjc.model.Name;

/**
 *
 * @struts.form
 *      name="studentForm"
 */

public final class StudentForm
    extends ValidatorForm
    implements java.io.Serializable
{

    //use this var figure out where to return after updating a this form
    private String startAt;

    //what kind of action to perform
    private String command;

    //title of the page that display this form, it is varied base on command type
    private String title;

    private String submitKey;

    private StudentView student;

    private List schoolOptions;

    // = true when command = delete
    private boolean readonlyPage;

    private boolean readonlySchoolYear;

    private boolean readonlySchool;

    private List schoolYears = new ArrayList( 1 );

    //the action will determine wether the user is allow to add and empty
    //  current school year
    private boolean optionToAddSchoolYear = false;

    private boolean optionRemoveCurrentYear = false;

    private boolean cancelAllow = false;

    private boolean hasAward = false;

    public StudentForm()
    {
    }

    public String getStartAt()
    {
        return this.startAt;
    }

    public void setStartAt( String newValue )
    {
        this.startAt = newValue;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle( String newValue )
    {
        this.title = newValue;
    }

    public String getSubmitKey()
    {
        return this.submitKey;
    }

    public void setSubmitKey( String newValue )
    {
        this.submitKey = newValue;
    }

    public String getCommand()
    {
        return this.command;
    }

    public void setCommand( String newValue )
    {
        this.command = newValue;
    }

    public StudentView getStudent()
    {
        return this.student;
    }

    public void setStudent( StudentView newValue )
    {
        this.student = newValue;
    }

    public boolean getOptionToAddSchoolYear()
    {
        return this.optionToAddSchoolYear;
    }

    public void setOptionToAddSchoolYear( boolean newValue )
    {
        this.optionToAddSchoolYear = newValue;
    }

    public boolean getOptionToRemoveCurrentYear()
    {
        return this.optionRemoveCurrentYear;
    }

    public void setOptionToRemoveCurrentYear( boolean newValue )
    {
        this.optionRemoveCurrentYear = newValue;
    }

    public List getSchoolOptions()
    {
        return this.schoolOptions;
    }

    public void setSchoolOptions( List newValue )
    {
        this.schoolOptions = newValue;
    }

    public boolean getReadonlyPage()
    {
        return this.readonlyPage;
    }

    public void setReadonlyPage( boolean newValue )
    {
        this.readonlyPage = newValue;
    }

    public boolean getReadonlySchool()
    {
        return this.readonlySchool;
    }

    public void setReadonlySchool( boolean newValue )
    {
        this.readonlySchool = newValue;
    }

    public boolean getReadonlySchoolYear()
    {
        return this.readonlySchoolYear;
    }

    public void setReadonlySchoolYear( boolean newValue )
    {
        this.readonlySchoolYear = newValue;
    }

    public boolean getCancelAllow()
    {
        return this.cancelAllow;
    }

    public void setCancelAllow( boolean newValue )
    {
        this.cancelAllow = newValue;
    }

    public boolean getHasAward()
    {
        return this.hasAward;
    }

    public void setHasAward( boolean newValue )
    {
        this.hasAward = newValue;
    }

    public SchoolYearView getSchoolYear( int index )
    {

        while ( index >= schoolYears.size() )
        {
            schoolYears.add( new SchoolYearView() );
        }

        return (SchoolYearView) schoolYears.get( index );
    }

    public void setSchoolYear( int index, SchoolYearView schoolYear )
    {
        while ( index >= schoolYears.size() )
        {
            schoolYears.add( new SchoolYearView() );
        }
        this.schoolYears.set( index, schoolYear );
    }

    public int getSchoolYearSize()
    {
        return this.schoolYears.size();
    }

    public void removeAllSchoolYear()
    {
        this.schoolYears.clear();
    }

    //required by logic:iterate
    public List getSchoolYears()
    {
        return this.schoolYears;
    }

    //public void setSchoolYears(List schoolYears) {  this.schoolYears = schoolYears;}

    public void reset( ActionMapping mapping, HttpServletRequest request )
    {

        this.student = new StudentView();
        this.student.setAddress( new Address() );
        this.student.setName( new Name() );
        this.student.setParentName( new Name() );
        this.schoolYears.clear();
        this.optionToAddSchoolYear = false;
        this.readonlyPage = false;
        this.readonlySchool = false;
        this.readonlySchoolYear = false;
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
