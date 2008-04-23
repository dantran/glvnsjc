package org.glvnsjc.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public final class StudentsToBeSubClassedForm
    extends ValidatorForm
    implements java.io.Serializable
{

    private List studentClassViews = new ArrayList( 1 );

    private String title;

    private String classType;

    private String className;

    private String studentName;

    public StudentsToBeSubClassedForm()
    {
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle( String newValue )
    {
        this.title = newValue;
    }

    public String getStudentName()
    {
        return this.studentName;
    }

    public void setStudentName( String newValue )
    {
        this.studentName = newValue;
    }

    public String getClassType()
    {
        return this.classType;
    }

    public void setClassType( String newValue )
    {
        this.classType = newValue;
    }

    public String getClassName()
    {
        return this.className;
    }

    public void setClassName( String newValue )
    {
        this.className = newValue;
    }

    public StudentClassView getStudentClassView( int index )
    {

        while ( index >= studentClassViews.size() )
        {
            studentClassViews.add( new StudentClassView() );
        }

        return (StudentClassView) studentClassViews.get( index );
    }

    public void setClassView( int index, StudentClassView schoolYear )
    {
        while ( index >= studentClassViews.size() )
        {
            studentClassViews.add( new StudentClassView() );
        }
        this.studentClassViews.set( index, schoolYear );
    }

    public int setStudentClassViewSize()
    {
        return this.studentClassViews.size();
    }

    public void removeAllStudentClassView()
    {
        this.studentClassViews.clear();
    }

    //required by logic:iterate
    public List getStudentClassViews()
    {
        return this.studentClassViews;
    }

    public void setStudentClassViews( List newValue )
    {
        this.studentClassViews = newValue;
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
