package org.glvnsjc.view;

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
 *      name="userForm"
 */

public class LoginProfileForm
    extends ValidatorForm
    implements java.io.Serializable
{

    private String command; //determine the command to submit in user.jsp

    private String loginRole; // the loginned user role.  Help user.jsp to determine what to display with???

    private String id;

    private String userId;

    private String password;

    private String confirmPassword;

    private String birthDate;

    private String saintName;

    private String gender;

    private Name name;

    private Address address;

    private String privilege;

    //school info
    private String teacherType;

    private String schoolId;

    private boolean schoolPriviledgeOnly = true;

    private String giaolyClass;

    private String vietnguClass;

    private String giaolySubClass;

    private String vietnguSubClass;

    private String lastLoginDate;

    private boolean removable = true;

    private boolean loginable = false;

    public LoginProfileForm()
    {
    }

    public String getCommand()
    {
        return this.command;
    }

    public void setCommand( String newValue )
    {
        this.command = newValue;
    }

    public String getLoginRole()
    {
        return this.loginRole;
    }

    public void setLoginRole( String newValue )
    {
        this.loginRole = newValue;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getConfirmPassword()
    {
        return this.confirmPassword;
    }

    public void setConfirmPassword( String value )
    {
        this.confirmPassword = value;
    }

    public String getPrivilege()
    {
        return this.privilege;
    }

    public void setPrivilege( String privilege )
    {
        this.privilege = privilege;
    }

    public String getSchoolId()
    {
        return this.schoolId;
    }

    public void setSchoolId( String newValue )
    {
        this.schoolId = newValue;
    }

    public boolean getSchoolPriviledgeOnly()
    {
        return this.schoolPriviledgeOnly;
    }

    public void setSchoolPriviledgeOnly( boolean newValue )
    {
        this.schoolPriviledgeOnly = newValue;
    }

    public String getGender()
    {
        return this.gender;
    }

    public void setGender( String gender )
    {
        this.gender = gender;
    }

    public String getBirthDate()
    {
        return this.birthDate;
    }

    public void setBirthDate( String birthDate )
    {
        this.birthDate = birthDate;
    }

    public String getSaintName()
    {
        return this.saintName;
    }

    public void setSaintName( String saintName )
    {
        this.saintName = saintName;
    }

    public Name getName()
    {
        return this.name;
    }

    public void setName( Name newValue )
    {
        if ( newValue == null )
        {
            newValue = new Name();
        }
        this.name = newValue;
    }

    public Address getAddress()
    {
        return this.address;
    }

    public void setAddress( Address newValue )
    {
        if ( newValue == null )
        {
            newValue = new Address();
        }
        this.address = newValue;
    }

    public String getLastLoginDate()
    {
        return lastLoginDate;
    }

    public void setLastLoginDate( String newDate )
    {
        this.lastLoginDate = newDate;
    }

    public String getGiaolyClass()
    {
        return this.giaolyClass;
    }

    public void setGiaolyClass( String newValue )
    {
        this.giaolyClass = newValue;
    }

    public String getVietnguClass()
    {
        return this.vietnguClass;
    }

    public void setVietnguClass( String newValue )
    {
        this.vietnguClass = newValue;
    }

    public String getGiaolySubClass()
    {
        return this.giaolySubClass;
    }

    public void setGiaolySubClass( String newValue )
    {
        this.giaolySubClass = newValue;
    }

    public String getVietnguSubClass()
    {
        return this.vietnguSubClass;
    }

    public void setVietnguSubClass( String newValue )
    {
        this.vietnguSubClass = newValue;
    }

    public String getTeacherType()
    {
        return this.teacherType;
    }

    public void setTeacherType( String newValue )
    {
        this.teacherType = newValue;
    }

    public boolean getRemovable()
    {
        return this.removable;
    }

    public void setRemovable( boolean newValue )
    {
        this.removable = newValue;
    }

    public boolean getLoginable()
    {
        return this.loginable;
    }

    public void setLoginable( boolean newValue )
    {
        this.loginable = newValue;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }

    public void reset( ActionMapping mapping, HttpServletRequest request )
    {
        this.name = new Name();
        this.address = new Address();
        this.removable = false;
        this.loginable = false;
        this.schoolPriviledgeOnly = false;
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
