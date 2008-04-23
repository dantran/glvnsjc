package org.glvnsjc.action.admin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.glvnsjc.action.ActionUtil;
//import org.glvnsjc.util.ImportStudent;
import org.glvnsjc.view.ImportForm;

/**
 * <tt>ImportDispatchAction</tt> allows adminitrator to import school-year student records into glvnsjc database<br><br>
 *
 *
 * <br>
 * @author Dan Tran
 */

public class ImportDispatchAction
    extends org.apache.struts.actions.LookupDispatchAction
{

    private static Log log = LogFactory.getLog( ImportDispatchAction.class );

    protected Map getKeyMethodMap()
    {
        Map map = new HashMap();
        map.put( "button.import", "importAction" );
        map.put( "button.view", "viewAction" );
        return map;
    }

    public ActionForward importAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response )
        throws Exception
    {

        ImportForm importForm = (ImportForm) form;
        List studentList = buildStudentList( form, request, response );
        this.saveMessages( request, ActionUtil.createActionMessages( "message.import.success" ) );
        return ( mapping.findForward( "update" ) );
    }

    public ActionForward viewAction( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response )
        throws Exception
    {

        List studentList = buildStudentList( form, request, response );
        request.setAttribute( "list", studentList );

        //return a forward to display.jsp
        return mapping.findForward( "view" );

    }

    public List buildStudentList( ActionForm form, HttpServletRequest request, HttpServletResponse response )
        throws Exception
    {

        ImportForm theForm = (ImportForm) form;

        FormFile file = theForm.getTheFile();
        String fileName = file.getFileName();
        String encoding = theForm.getEncoding();
        String separator = theForm.getSeparator();
        String memberType = theForm.getMemberType();

        ArrayList students = new ArrayList();
        InputStream inputStream = file.getInputStream();
        Charset charset = Charset.forName( encoding );
        BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream, charset ) );


        try
        {
            //ImportStudent importStudent = new ImportStudent( reader, separator );
            //importStudent.run( session );
        }
        finally
        {
        }

        return students;

    }

}