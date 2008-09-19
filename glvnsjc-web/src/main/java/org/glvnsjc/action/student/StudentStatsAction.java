package org.glvnsjc.action.student;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.School;
import org.glvnsjc.model.SchoolList;
import org.glvnsjc.model.StudentSearchForm;
import org.glvnsjc.model.StudentUtil;

public class StudentStatsAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( StudentStatsAction.class );

    private static final short SCHOOL_WIDTH = (short) ( 256 * 30 );

    private static final short TOTAL_WIDTH = (short) ( 256 * 10 );

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        HSSFWorkbook workbook = createWorkbook();

        response.setHeader( "Cache-Control", "no-cache" );
        response.setHeader( "Expires", "0" );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + "schoolstats.xls" );
        response.setContentType( "application/x-download" );
        workbook.write( response.getOutputStream() );

        return ( null );
    }

    private HSSFWorkbook createWorkbook( )
    {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet mainSheet = this.createMainSheet( workbook );

        this.createSchoolSheet( workbook, ClassType.GIAOLY );

        this.createSchoolSheet( workbook, ClassType.VIETNGU );
        
        return workbook;
    }

    private HSSFSheet createMainSheet( HSSFWorkbook workbook )
    {
        HSSFSheet mainSheet = workbook.createSheet( "Main" );
        HSSFRow row = mainSheet.createRow( (short) 0 );

        //create column names
        short j = 0;
        //student id
        HSSFCell cell = row.createCell( j );
        cell.setCellValue( "School" );
        mainSheet.setColumnWidth( j, SCHOOL_WIDTH );

        //student full name
        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( "Total" );
        mainSheet.setColumnWidth( j, TOTAL_WIDTH );

        short rowNo = 1;
        
        List schoolList = SchoolList.getInstance().getSchoolList();
        
        for ( int i = 0 ; i < schoolList.size(); ++i )
        {
            School school = (School) schoolList.get(i);
            
            String schoolName = school.getShortName();
            
            short col = 0; 
            row = mainSheet.createRow( rowNo );

            //school name
            cell = row.createCell( col );
            cell.setCellValue( schoolName );

            //head counts
            col++;
            cell = row.createCell( col );
            cell.setCellValue( getStudentCount( school, GlobalConfig.getInstance().getCurrentYear(), null, null ) );
            
            rowNo++;
        }
        return mainSheet;
    }

    private HSSFSheet createSchoolSheet( HSSFWorkbook workbook, ClassType classType )
    {
        HSSFSheet sheet = workbook.createSheet( classType.toFriendlyShortName() );
        HSSFRow row = sheet.createRow( (short) 0 );
        
        //create column titles
        //create column names
        short colNum = 0;
        //student id
        HSSFCell cell = row.createCell( colNum );
        cell.setCellValue( "School" );

        //student full name
        ClassName[] classNames =  ClassName.classNameList;
        for ( int i = 0 ; i < classNames.length; ++i )
        {
            colNum++;
            cell = row.createCell( colNum );
            cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
            cell.setCellValue( classType.toFriendlyShortName() + classNames[i].toString() );
        }

        short rowNo = 1;
        
        List<?> schoolList = SchoolList.getInstance().getSchoolList();
        
        for ( int i = 0; i < schoolList.size(); ++i )
        {
            School school = (School) schoolList.get( i );
            
            String schoolName = school.getShortName();
            
            row = sheet.createRow( rowNo );

            //school name
            colNum = 0;  
            cell = row.createCell( colNum );
            cell.setCellValue( schoolName );

            for ( int j = 0 ; j < classNames.length; ++j )
            {
                colNum++;
                cell = row.createCell( colNum );
                cell.setCellValue( getStudentCount( school, GlobalConfig.getInstance().getCurrentYear(), classType, classNames[j] ) );
            }
            
            rowNo++;
        }

        return sheet;        
        
    }
    
    public static int getStudentCount( School school, Integer schoolYear, ClassType classType, ClassName className  )
    {
        StudentSearchForm criteria = new StudentSearchForm();
        
        criteria.setSchoolYear( schoolYear.toString() );
        criteria.setSchoolId( school.getId().toString() );
        
        if ( ClassType.GIAOLY.equals( classType ))
        {
            criteria.setGiaolyClassName( className.toString() );
        }

        if ( ClassType.VIETNGU.equals( classType ))
        {
            criteria.setGiaolyClassName( className.toString() );
        }

        return StudentUtil.search( criteria ).size();
    }
    
}