package org.glvnsjc.action.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.poi.hssf.usermodel.*;

import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.SchoolYear;
import org.glvnsjc.securityfilter.AppPrincipal;
import org.glvnsjc.model.GlobalConfig;
import org.glvnsjc.model.hibernate.SessionUtil;

public class School2ExcelAction
    extends org.apache.struts.action.Action
{

    private static Log log = LogFactory.getLog( School2ExcelAction.class );

    private static final short NAME_WIDTH = (short) ( 256 * 30 );

    private static final short PHONE_WIDTH = (short) ( 256 * 15 );

    private static final short ADDRESS_WIDTH = (short) ( 256 * 45 );

    private static final short PARENT_WIDTH = (short) ( 256 * 30 );

    private static final short EMAIL_WIDTH = (short) ( 256 * 25 );
    
    private static final short DOB_WIDTH = (short) ( 256 * 15 );

    private static final short LAST_WIDTH = (short) ( 256 * 15 );

    private static final short FIRST_WIDTH = (short) ( 256 * 15 );

    private static final short MIDDLE_WIDTH = (short) ( 256 * 15 );

    private static final short CLASS_WIDTH = (short) ( 256 * 6 );

    private HashMap otherSheets = new HashMap();

    public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response )
        throws Exception
    {

        this.otherSheets.clear();

        List schoolYears = this.getSchoolYears( request );
        HSSFWorkbook workbook = createWorkbook( schoolYears );

        response.setHeader( "Cache-Control", "no-cache" );
        response.setHeader( "Expires", "0" );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + "workbook.xls" );
        response.setContentType( "application/x-download" );
        workbook.write( response.getOutputStream() );

        return ( null );
    }

    private void insertNewRowToMainSheet( SchoolYear schoolYear, HSSFSheet sheet, short rowNo )
    {
        HSSFRow row = sheet.createRow( rowNo );

        short j = 0;
        //student id
        HSSFCell cell = row.createCell( j );
        cell.setCellValue( schoolYear.getStudent().getId().toString() );

        //student full name
        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( schoolYear.getStudent().getName().getLastName() );

        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( schoolYear.getStudent().getName().getMiddleName() );

        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( schoolYear.getStudent().getName().getFirstName() );

        //Parent
        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( schoolYear.getStudent().getParentName().getFullName() );

        //email
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getStudent().getAddress().getEmail() );

        //student address
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getStudent().getAddress().getAddressLine1() );

        j++;
        cell = row.createCell( j );
        
        cell.setCellValue( schoolYear.getStudent().getAddress().getAddressLine2() );
        
        //student phone
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getStudent().getAddress().getPhone1() );

        //student birthday
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getStudent().getBirthDateDisplay() );

        //student GL Class
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getSchool().getShortName() );
        
        //student GL Class
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getGiaolyClass().getName().toString() );

        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getGiaolyClass().getSubName().toString() );
        
        //student VN Class
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getVietnguClass().getName().toString() );

        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getVietnguClass().getSubName().toString() );
        
    }

    private HSSFSheet createMainSheet( HSSFWorkbook workbook )
    {
        HSSFSheet mainSheet = workbook.createSheet( "All" );
        HSSFRow row = mainSheet.createRow( (short) 0 );

        //create column names
        short j = 0;
        //student id
        HSSFCell cell = row.createCell( j );
        cell.setCellValue( "ID" );

        //student full name
        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( "Last" );
        mainSheet.setColumnWidth( j, LAST_WIDTH );

        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( "Middle" );
        mainSheet.setColumnWidth( j, MIDDLE_WIDTH );

        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( "First" );
        mainSheet.setColumnWidth( j, FIRST_WIDTH );

        //Parent
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "Parent" );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        mainSheet.setColumnWidth( j, PARENT_WIDTH );

        //Email
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "Email" );
        mainSheet.setColumnWidth( j, EMAIL_WIDTH );
        
        //student address
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "Address" );
        mainSheet.setColumnWidth( j, ADDRESS_WIDTH );

        j++;
        cell = row.createCell( j );
        cell.setCellValue( "Address2" );
        mainSheet.setColumnWidth( j, ADDRESS_WIDTH );
        
        //student phone
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "Phone" );
        mainSheet.setColumnWidth( j, PHONE_WIDTH );

        //student birthday
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "DOB" );
        mainSheet.setColumnWidth( j, DOB_WIDTH );

        //School
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "School" );
        
        //student GL Class
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "GL" );

        j++;
        cell = row.createCell( j );
        cell.setCellValue( "GLS" );
        
        //student VN Class
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "VN" );

        j++;
        cell = row.createCell( j );
        cell.setCellValue( "VNS" );
        
        return mainSheet;
    }

    private HSSFSheet createClassSheet( String className, HSSFWorkbook workbook )
    {
        HSSFSheet sheet = workbook.createSheet( className );
        HSSFRow row = sheet.createRow( (short) 0 );

        //create column names
        short j = 0;
        //student id
        HSSFCell cell = row.createCell( j );
        cell.setCellValue( "ID" );

        //student full name

        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( "Name" );
        sheet.setColumnWidth( j, NAME_WIDTH );

        //main class anme
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "Class" );
        sheet.setColumnWidth( j, CLASS_WIDTH );

        //other class anme
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "Class" );
        sheet.setColumnWidth( j, CLASS_WIDTH );

        //student birthday
        j++;
        cell = row.createCell( j );
        cell.setCellValue( "DOB" );
        sheet.setColumnWidth( j, DOB_WIDTH );

        return sheet;
    }

    private void addStudentIntoClassSheet( HSSFSheet sheet, String className, String otherClassName,
                                           SchoolYear schoolYear )
    {
        short nextRow = (short) ( sheet.getLastRowNum() + 1 );

        HSSFRow row = sheet.createRow( nextRow );

        short j = 0;
        //student id
        HSSFCell cell = row.createCell( j );
        cell.setCellValue( schoolYear.getStudent().getId().toString() );

        //student full name
        j++;
        cell = row.createCell( j );
        cell.setEncoding( HSSFCell.ENCODING_UTF_16 );
        cell.setCellValue( schoolYear.getStudent().getName().getFullName() );

        //Class Name
        j++;
        cell = row.createCell( j );
        cell.setCellValue( className );

        //Other Class Name
        j++;
        cell = row.createCell( j );
        cell.setCellValue( otherClassName );

        //student birthday
        j++;
        cell = row.createCell( j );
        cell.setCellValue( schoolYear.getStudent().getBirthDateDisplay() );

    }

    private void distributeNewRowToClassSheet( SchoolYear schoolYear, HSSFWorkbook workbook )
    {

        String vnClass = "VN" + schoolYear.getVietnguClass().getFullClassName();
        String glClass = "GL" + schoolYear.getGiaolyClass().getFullClassName();

        HSSFSheet vnSheet = (HSSFSheet) this.otherSheets.get( vnClass );
        if ( vnSheet == null )
        {
            vnSheet = this.createClassSheet( vnClass, workbook );
            this.otherSheets.put( vnClass, vnSheet );
        }
        this.addStudentIntoClassSheet( vnSheet, vnClass, glClass, schoolYear );

        HSSFSheet glSheet = (HSSFSheet) this.otherSheets.get( glClass );
        if ( glSheet == null )
        {
            glSheet = this.createClassSheet( glClass, workbook );
            this.otherSheets.put( glClass, glSheet );
        }
        this.addStudentIntoClassSheet( glSheet, glClass, vnClass, schoolYear );

    }

    private HSSFWorkbook createWorkbook( List schoolYears )
    {
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet mainSheet = this.createMainSheet( workbook );

        //create individual sheet of each class sorted by name to look nice on the workwork

        TreeMap treeMap = new TreeMap();
        for ( int i = 0; i < schoolYears.size(); ++i )
        {
            SchoolYear schoolYear = (SchoolYear) schoolYears.get( i );
            String glClass = "GL" + schoolYear.getGiaolyClass().getFullClassName();
            String vnClass = "VN" + schoolYear.getVietnguClass().getFullClassName();
            treeMap.put( glClass, glClass );
            treeMap.put( vnClass, vnClass );
        }
        Collection sortedClasses = treeMap.values();
        Iterator iter = sortedClasses.iterator();
        while ( iter.hasNext() )
        {
            String className = (String) iter.next();
            HSSFSheet sheet = this.createClassSheet( className, workbook );
            this.otherSheets.put( className, sheet );
        }

        for ( int i = 0; i < schoolYears.size(); ++i )
        {
            SchoolYear schoolYear = (SchoolYear) schoolYears.get( i );
            this.insertNewRowToMainSheet( schoolYear, mainSheet, (short) ( i + 1 ) );
            this.distributeNewRowToClassSheet( schoolYear, workbook );
        }
        return workbook;
    }

    private List getSchoolYears( HttpServletRequest request )
        throws Exception
    {

        LoginProfile loginProfile = ( (AppPrincipal) request.getUserPrincipal() ).getLoginProfile();

        //find all SchoolYears belongin to a school at current school year
        //setup the query
        StringBuffer queryBuff = new StringBuffer();
        queryBuff.append( "from org.glvnsjc.model.SchoolYear schoolYear " );
        queryBuff.append( "where schoolYear.year = ? " );
        if ( loginProfile.getSchool() != null )
        {
            queryBuff.append( "and schoolYear.school = ? " );
        }
        queryBuff.append( " order by schoolYear.student.birthDate" );
        //setup the params
        List paramList = new ArrayList();
        List typeList = new ArrayList();
        paramList.add( GlobalConfig.getInstance().getCurrentYear() );
        typeList.add( Hibernate.INTEGER );
        if ( loginProfile.getSchool() != null )
        {
            paramList.add( loginProfile.getSchool().getId() );
            typeList.add( Hibernate.INTEGER );
        }
        Type[] types = new Type[typeList.size()];
        typeList.toArray( types );

        List schoolYears = null;
        
        try
        {
            Session session = SessionUtil.begin();

            Query query = session.createQuery( queryBuff.toString() );

            query.setParameters( paramList.toArray(), types );

            schoolYears = query.list();

            SessionUtil.end();

        }
        catch ( Exception e )
        {
            log.error( "Error found during loading School2Excel action", e );
            SessionUtil.rollback( e );
        }

        return schoolYears;
    }
}