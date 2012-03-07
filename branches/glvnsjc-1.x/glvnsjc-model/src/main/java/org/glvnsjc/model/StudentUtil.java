package org.glvnsjc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glvnsjc.converter.Convert;
import org.glvnsjc.model.hibernate.SessionUtil;
import org.glvnsjc.util.StringUtil;
import org.glvnsjc.util.UnicodeUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 * Student facade
 * 
 * @author dtran
 *
 */
public class StudentUtil
{
    private static Log log = LogFactory.getLog( StudentUtil.class );

    /**
     * remove all database records associate with a student
     * @param studentId
     */
    public static void deleteStudent( Integer studentId )
    {
        try
        {
            Session session = SessionUtil.begin();
            Student student = (Student) session.load( Student.class, studentId );
            session.delete( student );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public static Student loadStudent( Integer studentId )
    {
        Student student = null;
        try
        {
            Session session = SessionUtil.begin();
            student = (Student) session.load( Student.class, studentId );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        return student;

    }
    
    public static Student updateStudent( Student student)
    {
        try
        {
            Session session = SessionUtil.begin();
            session.update( student );
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        return student;

    }
    

    public static void updateStudent( Student student, SchoolYear schoolYear, String schoolId )
    {
        logDiff( student, schoolYear );

        try
        {
            Session session = SessionUtil.begin();

            session.update( student );

            if ( schoolYear != null )
            {
                School school = (School) session.load( School.class, new Integer( schoolId ) );

                schoolYear.setStudent( student );

                schoolYear.setSchool( school );

                //schoolYear is uniqued in student, new one replaces existing one
                student.getSchoolYears().add( schoolYear );

                if ( schoolYear.getId().intValue() == 0 )
                {
                    session.save( schoolYear );
                }
                else
                {
                    session.update( schoolYear );
                }
            }
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public static void addStudent( Student student, SchoolYear schoolYear, String schoolId )
    {
        try
        {
            Session session = SessionUtil.begin();

            School school = (School) session.load( School.class, new Integer( schoolId ) );

            session.save( student );

            session.flush(); //required due to application generated studentId

            schoolYear.setStudent( student );

            schoolYear.setSchool( school );

            student.getSchoolYears().add( schoolYear );

            session.save( schoolYear );

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public static void addSchoolYear( Integer studentId, SchoolYear schoolYear )
    {
        try
        {
            Session session = SessionUtil.begin();

            Student student = (Student) session.load( Student.class, studentId );

            schoolYear.setStudent( student );

            student.getSchoolYears().add( schoolYear );

            session.save( schoolYear );

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public static void deleteSchoolYear( Integer id )
    {
        try
        {
            Session session = SessionUtil.begin();

            SchoolYear schoolYear = (SchoolYear) session.load( SchoolYear.class, id );

            schoolYear.getStudent().getSchoolYears().remove( schoolYear );

            session.delete( schoolYear );

            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }
    }

    public static Student getStudent( Integer studentId )
    {
        Session session = HibernateSessionFactory.getCurrentSession();
        session.beginTransaction();
        Student student = (Student) session.load( Student.class, studentId );
        session.getTransaction().commit();
        return student;
    }

    /**
     * return either student list or or school year list depend on criteria
     * @param criteria
     * @return
     */
    public static List search( StudentSearchForm criteria )
    {
        if ( StringUtil.isBlank( criteria.getSchoolYear() ) && StringUtil.isBlank( criteria.getSchoolId() )
            && StringUtil.isBlank( criteria.getGiaolyClassName() )
            && StringUtil.isBlank( criteria.getGiaolyClassSubName() )
            && StringUtil.isBlank( criteria.getVietnguClassName() )
            && StringUtil.isBlank( criteria.getVietnguClassSubName() ) )
        {
            //stdudents
            return queryByStudent( criteria );
        }
        else
        {
            //schoolyear
            return queryBySchoolYear( criteria );
        }
    }

    private static List queryByStudent( StudentSearchForm criteria )
    {
        List students = null;
        ArrayList paramList = new ArrayList();
        ArrayList typeList = new ArrayList();
        StringBuffer buffer = new StringBuffer( "from org.glvnsjc.model.Student student" );

        boolean whereInserted = false;
        buildStudentCriteria( criteria, buffer, "student", whereInserted, paramList, typeList );

        students = query( buffer.toString(), paramList, typeList );
        return students;

    }

    private static List queryBySchoolYear( StudentSearchForm criteria )
    {
        List students = null;
        ArrayList paramList = new ArrayList();
        ArrayList typeList = new ArrayList();
        StringBuffer buffer = new StringBuffer(
                                                "select schoolYear.student from org.glvnsjc.model.SchoolYear schoolYear" );

        boolean whereInserted = false;

        String schoolYear = criteria.getSchoolYear();
        if ( !StringUtil.isBlank( schoolYear ) )
        {
            buffer = new StringBuffer( "from org.glvnsjc.model.SchoolYear schoolYear" );
            if ( whereInserted == false )
            {
                buffer.append( " where " );
                whereInserted = true;
            }
            buffer.append( "schoolYear.year = ? " );
            paramList.add( Integer.valueOf( schoolYear ) );
            typeList.add( Hibernate.INTEGER );
        }

        String schoolId = criteria.getSchoolId();
        if ( !StringUtil.isBlank( schoolId ) )
        {
            whereInserted = insertWhereOrAnd( whereInserted, buffer );
            buffer.append( "schoolYear.school = ? " );
            paramList.add( Integer.valueOf( schoolId ) );
            typeList.add( Hibernate.INTEGER );
        }

        String giaolyClassName = criteria.getGiaolyClassName();
        if ( !StringUtil.isBlank( giaolyClassName ) )
        {
            ClassName className = ClassName.fromString( giaolyClassName );
            whereInserted = insertWhereOrAnd( whereInserted, buffer );
            buffer.append( "schoolYear.giaolyClass.name = ? " );
            paramList.add( className );
            typeList.add( Hibernate.custom( ClassName.class ) );
        }

        String vietnguClassName = criteria.getVietnguClassName();
        if ( !StringUtil.isBlank( vietnguClassName ) )
        {
            ClassName className = ClassName.fromString( vietnguClassName );
            whereInserted = insertWhereOrAnd( whereInserted, buffer );
            buffer.append( "schoolYear.vietnguClass.name = ? " );
            paramList.add( className );
            typeList.add( Hibernate.custom( ClassName.class ) );
        }

        String giaolyClassSubName = criteria.getGiaolyClassSubName();
        if ( !StringUtil.isBlank( giaolyClassSubName ) )
        {
            ClassSubName subName = ClassSubName.fromString( giaolyClassSubName );
            whereInserted = insertWhereOrAnd( whereInserted, buffer );
            buffer.append( "schoolYear.giaolyClass.subName = ? " );
            paramList.add( subName );
            typeList.add( Hibernate.custom( ClassSubName.class ) );
        }

        String vietnguClassSubName = criteria.getVietnguClassSubName();
        if ( !StringUtil.isBlank( vietnguClassSubName ) )
        {
            ClassSubName subName = ClassSubName.fromString( vietnguClassSubName );
            whereInserted = insertWhereOrAnd( whereInserted, buffer );
            buffer.append( "schoolYear.vietnguClass.subName = ? " );
            paramList.add( subName );
            typeList.add( Hibernate.custom( ClassSubName.class ) );
        }

        buildStudentCriteria( criteria, buffer, "schoolYear.student", whereInserted, paramList, typeList );

        students = query( buffer.toString(), paramList, typeList );
        return students;

    }

    //////////////////////////////// helper ///////////////////////////////////

    private static List query( String queryStr, List paramList, List typeList )
        throws HibernateException
    {

        List list = null;
        Session session = HibernateSessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery( queryStr );

        if ( paramList.size() != 0 )
        {
            query.setParameters( paramList.toArray( new Object[0] ), (Type[]) typeList.toArray( new Type[0] ) );
        }

        list = query.list();

        tx.commit();

        return list;

    }

    private static boolean insertWhereOrAnd( boolean whereInserted, StringBuffer buffer )
    {
        if ( whereInserted == false )
        {
            buffer.append( " where " );
            whereInserted = true;
        }
        else
        {
            buffer.append( " and " );
        }

        return whereInserted;
    }

    private static boolean buildStudentCriteria( StudentSearchForm form, StringBuffer buffer, String studentVar,
                                                 boolean whereInserted, List paramList, List typeList )
    {

        String studentId = form.getStudentId();
        if ( !StringUtil.isBlank( studentId ) )
        {
            whereInserted = insertWhereOrAnd( whereInserted, buffer );

            buffer.append( studentVar ).append( ".id = ? " );
            paramList.add( Integer.valueOf( studentId ) );
            typeList.add( Hibernate.INTEGER );
        }

        String lastName = form.getLastName();
        if ( !StringUtil.isBlank( lastName ) )
        {
            whereInserted = insertWhereOrAnd( whereInserted, buffer );

            lastName = UnicodeUtil.unicode2Varchar( lastName ).toUpperCase();
            buffer.append( "upper(" ).append( studentVar ).append( ".name.lastNameRaw) like ? " );
            paramList.add( lastName );
            typeList.add( Hibernate.STRING );
        }

        String middleName = form.getMiddleName();
        if ( !StringUtil.isBlank( middleName ) )
        {
            whereInserted = insertWhereOrAnd( whereInserted, buffer );

            middleName = UnicodeUtil.unicode2Varchar( middleName ).toUpperCase();
            buffer.append( "upper(" ).append( studentVar ).append( ".name.middleNameRaw) like ? " );
            paramList.add( UnicodeUtil.unicode2Varchar( middleName ) );
            typeList.add( Hibernate.STRING );
        }

        String firstName = form.getFirstName();
        if ( !StringUtil.isBlank( firstName ) )
        {
            whereInserted = insertWhereOrAnd( whereInserted, buffer );

            firstName = UnicodeUtil.unicode2Varchar( firstName ).toUpperCase();
            buffer.append( "upper(" ).append( studentVar ).append( ".name.firstNameRaw) like ? " );
            paramList.add( UnicodeUtil.unicode2Varchar( firstName ) );
            typeList.add( Hibernate.STRING );
        }

        String phone = (String) form.getPhone();
        if ( !StringUtil.isBlank( phone ) )
        {
            whereInserted = insertWhereOrAnd( whereInserted, buffer );

            buffer.append( studentVar ).append( ".address.phone1 like ? " );
            paramList.add( phone );
            typeList.add( Hibernate.STRING );
        }

        //allow search DOB ranges
        String birthDateDisplay1 = form.getBirthDate();
        String birthDateDisplay2 = form.getBirthDate2();
        if ( !StringUtil.isBlank( birthDateDisplay2 ) )
        {

            whereInserted = insertWhereOrAnd( whereInserted, buffer );

            //exception takes care of the check
            java.util.Date birthDate1 = Convert.StringToDate( birthDateDisplay1 );
            java.util.Date birthDate2 = Convert.StringToDate( birthDateDisplay2 );
            buffer.append( studentVar ).append( ".birthDate > ? " );
            paramList.add( birthDate1 );
            typeList.add( Hibernate.DATE );
            buffer.append( " and " );
            buffer.append( studentVar ).append( ".birthDate < ? " );
            paramList.add( birthDate2 );
            typeList.add( Hibernate.DATE );

        }
        else if ( !StringUtil.isBlank( birthDateDisplay1 ) )
        {
            whereInserted = insertWhereOrAnd( whereInserted, buffer );

            java.util.Date birthDate1 = Convert.StringToDate( birthDateDisplay1 );
            buffer.append( studentVar ).append( ".birthDate = ? " );
            paramList.add( birthDate1 );
            typeList.add( Hibernate.DATE );
        }

        return whereInserted;
    }

    public static String getNameDiff( Name a1, Name a2 )
    {
        StringBuffer buff = new StringBuffer();

        buff.append( StringUtil.displayComparison( "lastName", a1.getLastName(), a2.getLastName() ) );
        buff.append( StringUtil.displayComparison( "firstName", a1.getFirstName(), a2.getFirstName() ) );
        buff.append( StringUtil.displayComparison( "middleName", a1.getMiddleName(), a2.getMiddleName() ) );

        return buff.toString();
    }

    public static String getNameDiff( Student a1, Student a2 )
    {
        return getNameDiff( a1.getName(), a2.getName() );
    }

    public static String getAddressDiff( Address a1, Address a2 )
    {
        StringBuffer buff = new StringBuffer();

        buff.append( StringUtil.displayComparison( "street1", a1.getStreet1(), a2.getStreet1() ) );
        buff.append( StringUtil.displayComparison( "street2", a1.getStreet2(), a2.getStreet2() ) );
        buff.append( StringUtil.displayComparison( "city", a1.getCity(), a2.getCity() ) );
        buff.append( StringUtil.displayComparison( "state", a1.getState(), a2.getState() ) );
        buff.append( StringUtil.displayComparison( "zipCode", a1.getZipCode(), a2.getZipCode() ) );
        buff.append( StringUtil.displayComparison( "email", a1.getEmail(), a2.getEmail() ) );
        buff.append( StringUtil.displayComparison( "phone1", a1.getPhone1(), a2.getPhone1() ) );
        buff.append( StringUtil.displayComparison( "phone2", a1.getPhone2(), a2.getPhone2() ) );

        return buff.toString();
    }

    public static String getAddressDiff( Student a1, Student a2 )
    {
        return getAddressDiff( a1.getAddress(), a2.getAddress() );
    }

    public static String getSchoolYearDiff( SchoolYear a1, SchoolYear a2 )
    {
        StringBuffer buff = new StringBuffer();

        buff.append( StringUtil.displayComparison( "GL Class", a1.getGiaolyClass().getFullClassName(), a2
            .getGiaolyClass().getFullClassName() ) );
        buff.append( StringUtil.displayComparison( "GL Grade", a1.getGiaolyClass().getGrade(), a2.getGiaolyClass()
            .getGrade() ) );

        buff.append( StringUtil.displayComparison( "VN Class", a1.getVietnguClass().getFullClassName(), a2
            .getVietnguClass().getFullClassName() ) );
        buff.append( StringUtil.displayComparison( "VN Grade", a1.getVietnguClass().getGrade(), a2.getVietnguClass()
            .getGrade() ) );

        return buff.toString();
    }

    /**
     * Log changed fields in student record
     * @param student
     * @param schoolYear
     */
    public static void logDiff( Student student, SchoolYear schoolYear )
    {
        StringBuffer buffer = new StringBuffer();

        try
        {
            Session session = SessionUtil.begin();

            Student dbStudent = (Student) session.load( Student.class, student.getId() );

            buffer.append( logStudentDiff( dbStudent, student ) );

            if ( schoolYear != null )
            {
                Iterator iter = dbStudent.getSchoolYears().iterator();
                while ( iter.hasNext() )
                {
                    SchoolYear sy = (SchoolYear) iter.next();
                    if ( sy.getYear() == schoolYear.getYear() )
                    {
                        buffer.append( getSchoolYearDiff( sy, schoolYear ) );
                        break;
                    }
                }

            }
            SessionUtil.end();
        }
        catch ( Exception e )
        {
            SessionUtil.rollback( e );
        }

        if ( buffer.toString().length() != 0 )
        {
            log.info( "\r\n" + buffer.toString() );
        }
    }

    private static String logStudentDiff( Student orig, Student change )
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append( getNameDiff( orig, change ) );
        buffer.append( StringUtil.displayComparison( "DOB", orig.getBirthDateDisplay(), change.getBirthDateDisplay() ) );
        buffer.append( getNameDiff( orig.getParentName(), change.getParentName() ) );
        buffer.append( getAddressDiff( orig, change ) );

        return buffer.toString();
    }

    /**
     * get the active school year of a student
     * @param studentId
     * @return
     */
    public static SchoolYear getCurrentSchoolYear( Integer studentId )
    {
        Student student = loadStudent( studentId );
        Iterator iterator = student.getSchoolYears().iterator();
        int currentSchoolYear = GlobalConfig.getInstance().getCurrentYear().intValue();

        SchoolYear ret = null;
        while ( iterator.hasNext() )
        {
            SchoolYear schoolYear = (SchoolYear) iterator.next();
            if ( schoolYear.getYear() == currentSchoolYear )
            {
                ret = schoolYear;
                break;
            }
        }

        return ret;
    }
}
