package org.glvnsjc.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.model.StudentUtil;

public class CreateAwardPDFAction
extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( CreateAwardPDFAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {
        
        //use a form to allow jsp to fill in addition argument
        DynaActionForm theForm = (DynaActionForm) form;
        
        Integer studentId = new Integer( (String) theForm.get( "studentId" ) );
        
        ClassType classType = ClassType.GIAOLY;
        int classTypeNum = Integer.parseInt( (String) theForm.get( "classType" ) );
        if ( classTypeNum != ClassType.GIAOLY_CODE )
        {
            classType = ClassType.VIETNGU;
        }

        SchoolYear schoolYear = StudentUtil.getCurrentSchoolYear( studentId );
        
        //response.setHeader( "Cache-Control", "no-cache" );
        response.setHeader( "Expires", "0" );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + "award.pdf" );
        response.setContentType( "application/pdf" );

        CreateCertificateAward award = new CreateCertificateAward( schoolYear, classType);
        award.pdfWrite( response.getOutputStream() );
        
        log.info( classType.toFriendlyName() + " Award Certification created for: " + schoolYear.getStudent().getName().getFullName() );
        
        return ( null );
    }

}
