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

public final class ClassGradeListForm
    extends ValidatorForm
    implements java.io.Serializable
{

    private List gradeViews = ListUtils.lazyList( new ArrayList(),
                                                  new GenericFactory( "org.glvnsjc.view.StudentGradeView" ) );

    private String title;

    private String classType;

    private String fullClassName;

    public ClassGradeListForm()
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

    public String getFullClassName()
    {
        return this.fullClassName;
    }

    public void setFullClassName( String newValue )
    {
        this.fullClassName = newValue;
    }

    public String getClassType()
    {
        return this.classType;
    }

    public void setClassType( String newValue )
    {
        this.classType = newValue;
    }

    //In order to insert/add a new element, you must call getXXXX, and populate the data
    //  lazyList cant handle direct insertion
    public void removeAllGradeView()
    {
        this.gradeViews.clear();
    }

    public StudentGradeView getGradeView( int index )
    {
        return (StudentGradeView) gradeViews.get( index );
    }

    public List getGradeViews()
    {
        return this.gradeViews;
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
