package org.glvnsjc.action.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.SchoolYear;

import de.schlichtherle.io.File;
import de.schlichtherle.io.FileOutputStream;

public class ExportAllPDFAwardAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( ExportAllPDFAwardAction.class );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        //use a form to allow jsp to fill in addition argument
        DynaActionForm theForm = (DynaActionForm) form;

        ClassType classType = ClassType.GIAOLY;
        int classTypeNum = Integer.parseInt( (String) theForm.get( "classType" ) );
        if ( classTypeNum != ClassType.GIAOLY_CODE )
        {
            classType = ClassType.VIETNGU;
        }

        java.io.File tmpZip = this.exportAndZip( request, classType );
        
        java.io.FileInputStream is = null;
        
        try
        {
            response.setHeader( "Expires", "0" );
            response.setHeader( "Content-Disposition", "attachment; filename=" + classType.toFriendlyShortName() + ".award.zip" );

            is = new java.io.FileInputStream( tmpZip );
            IOUtils.copy( is, response.getOutputStream() );
        }
        finally
        {
            IOUtils.closeQuietly( is );
            tmpZip.delete();
        }

        return ( null );
    }

    /**
     * export all pdfs and zip themp up in a temporary file
     * @param request
     * @param classType
     * @return
     * @throws IOException
     */
    private java.io.File exportAndZip( HttpServletRequest request, ClassType classType )
        throws IOException
    {
        java.io.File tmpFile = java.io.File.createTempFile( "award", ".zip" );
        tmpFile.delete();
        log.info( "temp zip file: " + tmpFile.getAbsolutePath() );

        File zipDir = new File( tmpFile.getAbsolutePath() );
        zipDir.mkdirs();

        try
        {
            List awards = getAwardList( request, classType );

            for ( int i = 0; i < awards.size(); ++i )
            {
                SchoolYear schoolYear = (SchoolYear) awards.get( i );
                String awardFileName = CreateAwardPDFAction.getAwardFileName( classType, schoolYear );

                FileOutputStream os = new FileOutputStream( new File( zipDir, awardFileName ) );

                CreateCertificateAward awardEngine = new CreateCertificateAward( schoolYear, classType );

                awardEngine.pdfWrite( os );

                os.close();
            }
        }
        finally
        {
            File.umount( zipDir );
        }
        
        return tmpFile;
        
    }
    
    
    private List getAwardList( HttpServletRequest request, ClassType classType )
    {
        if ( classType == ClassType.GIAOLY )
        {
            return GiaolyAwardReportAction.getAwardList( request );
        }

        return VietnguAwardReportAction.getAwardList( request );
    }

}
