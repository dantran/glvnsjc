package org.glvnsjc.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;

public class ImportForm
    extends org.apache.struts.action.ActionForm
{
    public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED = "org.apache.struts.webapp.upload.MaxLengthExceeded";

    /**
     * charset type the upload file
     */
    protected String _encoding = "UTF-16";

    /**
     * separator of the import file
     */
    protected String _separator = ",";

    /**
     * Object type to import
     */
    protected String _memberType = "";

    /**
     * Action will clear out existing member data before import
     */
    protected boolean _cleanDB = false;

    /**
     * The file that the user has uploaded
     */
    protected FormFile _theFile;

    public void setEncoding( String value )
    {
        this._encoding = value.trim();
    }

    public String getEncoding()
    {
        return this._encoding;
    }

    public void setSeparator( String value )
    {
        this._separator = value; //do not trim it since \t will be truncated
    }

    public String getSeparator()
    {
        return this._separator;
    }

    /**
     * Retrieve the value of the text the user has sent as form data
     */
    public String getMemberType()
    {
        return _memberType;
    }

    /**
     * Set the value of the form data text
     */
    public void setMemberType( String value )
    {
        this._memberType = value;
    }

    public boolean getCleanDB()
    {
        return _cleanDB;
    }

    public void setCleanDB( boolean value )
    {
        _cleanDB = value;
    }

    /**
     * Retrieve a representation of the file the user has uploaded
     */
    public FormFile getTheFile()
    {
        return _theFile;
    }

    /**
     * Set a representation of the file the user has uploaded
     */
    public void setTheFile( FormFile value )
    {
        this._theFile = value;
    }

    public void reset()
    {

    }

    /**
     * Check to make sure the client hasn't exceeded the maximum allowed upload size inside of this
     * validate method.
     */
    public ActionErrors validate( ActionMapping mapping, HttpServletRequest request )
    {
        ActionErrors errors = null;
        //has the maximum length been exceeded?
        Boolean maxLengthExceeded = (Boolean) request
            .getAttribute( MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED );
        if ( ( maxLengthExceeded != null ) && ( maxLengthExceeded.booleanValue() ) )
        {
            errors = new ActionErrors();
            errors.add( ERROR_PROPERTY_MAX_LENGTH_EXCEEDED, new ActionMessage( "maxLengthExceeded" ) );
        }
        return errors;

    }
}
