package org.glvnsjc.action.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;

import org.codehaus.plexus.util.StringUtils;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.Grade;
import org.glvnsjc.model.SchoolUtil;
import org.glvnsjc.model.SchoolYear;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class CreateCertificateAward
{
    private Properties properties;
    
    private SchoolYear schoolYear;
    
    private ClassType  classType;
    
    private Grade      grade;
    
    public CreateCertificateAward( SchoolYear schoolYear, ClassType classType )
        throws IOException
    {
        this.schoolYear = schoolYear;
        this.classType = classType;
        
        InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream( "pdf.properties.ascii");
        
        properties = new Properties();
        properties.load( propertiesStream );

        propertiesStream.close();   
        
        grade = schoolYear.getGiaolyClass().getGrade();
        if ( classType.equals( ClassType.VIETNGU ) )
        {
            grade = schoolYear.getVietnguClass().getGrade();
        }        
    }
    
    public void pdfWrite( OutputStream ostream  )
    {
        Document document = new Document();
        
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            
            // step 2: creation of a writer
            PdfWriter.getInstance( document, ostream );
            
            // step 3: we open the document
            document.open();
            
            //reusable attributes
            BaseFont verdanaBaseFont = createBaseFont( "verdana" );
            BaseFont arialBaseFont = createBaseFont( "arial" );
            BaseFont timesBaseFont = createBaseFont( "times" );
            Paragraph emptyPara = new Paragraph( " ", new Font() );

            Paragraph para;
            Font font;
            
            font = new Font( verdanaBaseFont, 14, Font.ITALIC );
            para = new Paragraph( properties.getProperty( "award.diocese.name" ), font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );
            
            para = new Paragraph( properties.getProperty( "award.parish.name" ), font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );
            
            document.add( emptyPara );
            
            font = new Font( verdanaBaseFont, 14, Font.BOLD );
            para = new Paragraph( properties.getProperty( "award.group.name" ), font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );
            
            URL imageUrl = classLoader.getResource( "glvnsjc-logo-award.jpg" );
            Image glvnsjcImage = Image.getInstance(imageUrl);
            glvnsjcImage.setAlignment(Image.MIDDLE);
            document.add( glvnsjcImage );            
            
            document.add( emptyPara );
            
            font = new Font( arialBaseFont, 22, Font.BOLD );
            para = new Paragraph( getAwardName() , font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );

            font = new Font( arialBaseFont, 16, Font.BOLDITALIC );
            para = new Paragraph( properties.getProperty( "award.to" ), font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );
 
            
            font = new Font( timesBaseFont, 36, Font.BOLDITALIC );
            para = new Paragraph( schoolYear.getStudent().getName().getFullName() , font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );

            document.add( emptyPara );
            
            font = new Font( arialBaseFont, 16, Font.BOLDITALIC );
            para = new Paragraph( getAwardDescription(), font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );
            
            String schoolYearLabel = " " + schoolYear.getYear() + "-" + ( schoolYear.getYear() + 1 );
            String schoolYearDisplay = properties.getProperty( "award.in.schoolyear" ) + schoolYearLabel;
            para = new Paragraph( schoolYearDisplay, font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );
            
            document.add( emptyPara );

            font = new Font( timesBaseFont, 12, Font.BOLDITALIC );
            para = new Paragraph( getPrintedDate(), font  );
            para.setAlignment( Paragraph.ALIGN_CENTER );
            document.add( para );

            URL signatureUrl = classLoader.getResource( "president-signature.jpg" );
            Image signImage = Image.getInstance(signatureUrl);
            signImage.setAlignment(Image.MIDDLE);
            document.add( signImage );
            
            String precident =  properties.getProperty( "award.president.name" ) ;

             para = new Paragraph( precident, font  );
             para.setAlignment( Paragraph.ALIGN_CENTER );
             document.add( para );
            
             para = new Paragraph( properties.getProperty( "award.president.title" ), font  );
             para.setAlignment( Paragraph.ALIGN_CENTER );
             document.add( para );
             
             document.add( emptyPara );
             
             para = new Paragraph( getPrincipalName(), font  );
             para.setAlignment( Paragraph.ALIGN_CENTER );
             document.add( para );

             para = new Paragraph( properties.getProperty( "award.principal" ), font  );
             para.setAlignment( Paragraph.ALIGN_CENTER );
             document.add( para );
             
             
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        finally
        {
            if ( document != null )
            {
                document.close();
            }
        }
                
    }
    
    private BaseFont createBaseFont( String fontName )
        throws IOException, DocumentException
    {
        return BaseFont.createFont("c:\\windows\\fonts\\" + fontName + ".ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    }
    
    private String getAwardName()
    {
        String awardName = getProperty( "award.certificate" ) + " ";
        
        String key = "award." + grade.getDisplay();
        
        awardName += getProperty( key ) + " ";
        
        awardName += classType.toFriendlyName(); 
        
        awardName += " " ;
        
        if ( this.classType.equals( ClassType.GIAOLY ))
        {
            awardName += this.schoolYear.getGiaolyClass().getName().getEnumCode();
        }
        else
        {
            awardName += this.schoolYear.getVietnguClass().getName().getEnumCode();
        }
        
        return awardName.toUpperCase();
    }
    
    private String getAwardDescription()
    {
        String propertyName="award.grade.description";
        
        if ( grade.equals( Grade.FIRST ) )
        {
            propertyName="award.grade.description.1";
        }
        if ( grade.equals( Grade.SECOND ) )
        {
            propertyName="award.grade.description.2";
        }
        if ( grade.equals( Grade.THIRD ) )
        {
            propertyName="award.grade.description.3";
        }  
        
        return getProperty( propertyName );
        
    }
    
    private String getPrintedDate()
    {
        Calendar cl = Calendar.getInstance();
        cl.get(  Calendar.DATE );
        
        String awardDate = properties.getProperty( "award.city" )  + ", " +
                           properties.getProperty( "award.day" )   + " " + cl.get(  Calendar.DATE ) + " " +
                           properties.getProperty( "award.month" ) + " " + ( cl.get(  Calendar.MONTH ) + 1 ) + " " +
                           properties.getProperty( "award.year" )  + " " + cl.get(  Calendar.YEAR );     
        return awardDate;
    }
    
    private String getProperty( String key )
    {
        return this.properties.getProperty( key );
    }
    
    private String getPrincipalName()
    {
        String name = SchoolUtil.getPrincipalName( schoolYear.getSchool() );
        if ( StringUtils.isEmpty( name ) )
        {
            name = "Please assign a principal to your school database.";
        }
        
        return name;
    }
}
