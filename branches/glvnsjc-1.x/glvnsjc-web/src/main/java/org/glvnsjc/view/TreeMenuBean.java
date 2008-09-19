package org.glvnsjc.view;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.util.MessageResources;

import org.glvnsjc.action.ActionUtil;
import org.glvnsjc.action.student.DispatchType;
import org.glvnsjc.model.ClassName;
import org.glvnsjc.model.ClassType;
import org.glvnsjc.model.LoginProfile;
import org.glvnsjc.model.Privilege;
import org.glvnsjc.util.StringUtil;
import org.glvnsjc.model.*;

public class TreeMenuBean
    extends org.apache.struts.action.ActionForm
{

    /* empty constructor for the bean */
    public TreeMenuBean()
    {
        this.beanName = "Menu";
    }

    private void loadClassPrivilegeMenu( HttpServletRequest request )
        throws Exception
    {

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        //setup teacher folder
        TreeNode teacherFolder = null;
        teacherFolder = new TreeNode( "Teachers", 1 );
        teacherFolder.setShowChildren( true );
        teacherFolder.addChild( new TreeNode( "Search", 2 ).setHref( "./teacher/loadsearch.do" ) );
        teacherFolder.addChild( new TreeNode( "Calendar", 2 ).setHref( "./teacher/schoolCalendar.do" ) );

        TreeNode myGiaolyFolder = new TreeNode( "My Gia\u0301o Ly\u0301", 1 );
        myGiaolyFolder.setShowChildren( true );
        if ( loginProfile.getGiaolyClass() != null && !loginProfile.getGiaolyClass().equals( ClassName.UNASSIGNED ) )
        {
            myGiaolyFolder.addChild( new TreeNode( "List", 2 ).setHref( "./student/myStudents.do?classType="
                + ClassType.GIAOLY ) );
            myGiaolyFolder.addChild( new TreeNode( "Grade ", 2 )
                .setHref( "./student/loadStudentsToBeGraded.do?classType=" + ClassType.GIAOLY ) );
            myGiaolyFolder.addChild( new TreeNode( "Role Sheet", 2 )
                .setHref( "./student/loadStudentsCalendar.do?classType=" + ClassType.GIAOLY ) );
        }

        TreeNode myVietnguFolder = new TreeNode( "My Viê\u0323t Ng\u01B0\u0303", 1 );
        myVietnguFolder.setShowChildren( true );
        if ( loginProfile.getVietnguClass() != null && !loginProfile.getVietnguClass().equals( ClassName.UNASSIGNED ) )
        {
            myVietnguFolder.addChild( new TreeNode( "List", 2 ).setHref( "./student/myStudents.do?classType="
                + ClassType.VIETNGU ) );
            myVietnguFolder.addChild( new TreeNode( "Grade", 2 )
                .setHref( "./student/loadStudentsToBeGraded.do?classType=" + ClassType.VIETNGU ) );
            myVietnguFolder.addChild( new TreeNode( "Role Sheet", 2 )
                .setHref( "./student/loadStudentsCalendar.do?classType=" + ClassType.VIETNGU ) );
        }

        TreeNode myFolder = new TreeNode( "My Tasks", 1 );
        myFolder.setShowChildren( true );
        TreeNode myProfile = new TreeNode( "Profile", 2 );
        myProfile.setHref( "./teacher/currenLoginUserProfile.do" );
        myFolder.addChild( myProfile );

        this.monkeyTree = new TreeNode( "Menu", 0 );
        this.monkeyTree.setShowChildren( true );

        //put all folder in the root using following order
        if ( myGiaolyFolder.getHasChildren() )
        {
            this.monkeyTree.addChild( myGiaolyFolder );
        }
        if ( myVietnguFolder.getHasChildren() )
        {
            this.monkeyTree.addChild( myVietnguFolder );
        }

        this.monkeyTree.addChild( teacherFolder );
        this.monkeyTree.addChild( myFolder );
        this.monkeyTree.addChild( new TreeNode( "Logout", 1 ).setHref( "./logout.do" ) );

    }

    public void load( HttpServletRequest request )
        throws Exception
    {

        boolean isAdminPrivilege = request.isUserInRole( Privilege.ADMIN.toString() );
        boolean isCommunityPrivilege = request.isUserInRole( Privilege.COMMUNITY.toString() );
        boolean isPrincipalPrivilege = request.isUserInRole( Privilege.PRINCIPAL.toString() );
        boolean isSchoolPrivilege = request.isUserInRole( Privilege.SCHOOL.toString() );
        boolean isClassPrivilege = request.isUserInRole( Privilege.CLASS.toString() );

        if ( isClassPrivilege && !isSchoolPrivilege )
        {
            loadClassPrivilegeMenu( request );
            return;
        }

        LoginProfile loginProfile = ActionUtil.getCurrentUserLoginProfile( request );

        this.monkeyTree = new TreeNode( "Menu", 0 );
        this.monkeyTree.setShowChildren( true );

        //setup teacher folder
        TreeNode teacherFolder = null;
        teacherFolder = new TreeNode( "Teachers", 1 );
        teacherFolder.setShowChildren( true );
        teacherFolder.addChild( new TreeNode( "Search", 2 ).setHref( "./teacher/loadsearch.do" ) );
        teacherFolder.addChild( new TreeNode( "School Calendar", 2 ).setHref( "./teacher/schoolCalendar.do" ) );

        //any one with beter privilege than teacher can create teacher account
        if ( isPrincipalPrivilege )
        {
            TreeNode userAdd = new TreeNode( "Add", 2 );
            userAdd.setHref( "./teacher/load.do?command=add" );
            teacherFolder.addChild( userAdd );
        }

        TreeNode adminFolder = null;
        if ( isAdminPrivilege )
        {
            adminFolder = new TreeNode( "Admin", 1 );
            adminFolder.setShowChildren( true );
            TreeNode configNode = new TreeNode( "Configuration", 2 );
            configNode.setHref( "./admin/loadGlobalConfig.do" );
            adminFolder.addChild( configNode );
            adminFolder.addChild( new TreeNode( "Generate Calendar", 2 ).setHref( "./admin/promptCalendarRange.do" ) );
            adminFolder.addChild( new TreeNode( "Update Calendar", 2 ).setHref( "./admin/loadSchoolCalendar.do" ) );
        }

        //student folder
        if ( isSchoolPrivilege )
        {
            TreeNode studentFolder = new TreeNode( "Students", 1 );
            studentFolder.setShowChildren( true );

            TreeNode studentSearchNode = new TreeNode( "Search", 2 );
            studentSearchNode.setHref( "./student/loadSearch.do" );
            studentFolder.addChild( studentSearchNode );

            if ( isCommunityPrivilege )
            {
                TreeNode studentAddNode = new TreeNode( "Add", 2 );
                studentAddNode.setHref( "./student/load.do?command=" + DispatchType.ADD );
                studentFolder.addChild( studentAddNode );
            }

            if ( isAdminPrivilege )
            {
                TreeNode importNode = new TreeNode( "Import", 2 );
                importNode.setHref( "./admin/loadImport.do" );
                studentFolder.addChild( importNode );
            }

            TreeNode myFolder = new TreeNode( "My Tasks", 1 );
            myFolder.setShowChildren( true );
            TreeNode myProfile = new TreeNode( "Profile", 2 );
            myProfile.setHref( "./teacher/currenLoginUserProfile.do" );
            myFolder.addChild( myProfile );

            //principal only
            if ( isPrincipalPrivilege && !isCommunityPrivilege )
            {
                myFolder
                    .addChild( new TreeNode( "Split Class", 2 ).setHref( "./student/loadStudentsToBeSubClassed.do" ) );
                myFolder.addChild( new TreeNode( "Excel Workbook", 2 ).setHref( "./student/school2Excel.do" ) );

            }

            TreeNode myGiaolyFolder = new TreeNode( "My Gia\u0301o Ly\u0301", 1 );
            myGiaolyFolder.setShowChildren( true );
            if ( loginProfile.getGiaolyClass() != null && !loginProfile.getGiaolyClass().equals( ClassName.UNASSIGNED ) )
            {
                myGiaolyFolder.addChild( new TreeNode( "List", 2 ).setHref( "./student/myStudents.do?classType="
                    + ClassType.GIAOLY ) );
                myGiaolyFolder.addChild( new TreeNode( "Grade ", 2 )
                    .setHref( "./student/loadStudentsToBeGraded.do?classType=" + ClassType.GIAOLY ) );
                myGiaolyFolder.addChild( new TreeNode( "Role Sheet ", 2 )
                    .setHref( "./student/loadStudentsCalendar.do?classType=" + ClassType.GIAOLY ) );
            }

            TreeNode myVietnguFolder = new TreeNode( "My Viê\u0323t Ng\u01B0\u0303", 1 );
            myVietnguFolder.setShowChildren( true );
            if ( loginProfile.getVietnguClass() != null
                && !loginProfile.getVietnguClass().equals( ClassName.UNASSIGNED ) )
            {
                myVietnguFolder.addChild( new TreeNode( "List", 2 ).setHref( "./student/myStudents.do?classType="
                    + ClassType.VIETNGU ) );
                myVietnguFolder.addChild( new TreeNode( "Grade ", 2 )
                    .setHref( "./student/loadStudentsToBeGraded.do?classType=" + ClassType.VIETNGU ) );
                myVietnguFolder.addChild( new TreeNode( "Role Sheet ", 2 )
                    .setHref( "./student/loadStudentsCalendar.do?classType=" + ClassType.VIETNGU ) );
            }

            TreeNode reportFolder = new TreeNode( "Reports", 1 );
            reportFolder.setShowChildren( true );
            reportFolder.addChild( new TreeNode( "End Of Year", 2 ).setHref( "./student/nextYearReport.do" ) );
            reportFolder.addChild( new TreeNode( "No Class Assignment", 2 )
                .setHref( "./student/noClassAssignedReport.do" ) );
            reportFolder.addChild( new TreeNode( "Giaoly Awards", 2 ).setHref( "./student/giaolyAwardReport.do" ) );
            reportFolder.addChild( new TreeNode( "Vietngu Awards", 2 ).setHref( "./student/vietnguAwardReport.do" ) );
            if ( isCommunityPrivilege )
            {
                reportFolder.addChild( new TreeNode( "Student Statistics", 2 ).setHref( "./student/studentStats.do" ) );
            }
            
            TreeNode helpFolder = new TreeNode( "Documentation", 1 );
            helpFolder.setShowChildren( false );
            helpFolder.addChild( new TreeNode( "Motivation", 2 ).setHref( "./doc/motivations.htm" ) );
            helpFolder.addChild( new TreeNode( "Solution", 2 ).setHref( "./doc/CommonTasks.htm" ) );
            helpFolder.addChild( new TreeNode( "Principal", 2 ).setHref( "./doc/principaltasks.htm" ) );
            helpFolder.addChild( new TreeNode( "Teacher", 2 ).setHref( "./doc/teachertasks.htm" ) );
            helpFolder.addChild( new TreeNode( "Search Student", 2 ).setHref( "./doc/StudentSearch.htm" ) );
            helpFolder.addChild( new TreeNode( "FAQ", 2 ).setHref( "./doc/faq.htm" ) );
            helpFolder.addChild( new TreeNode( "Privilege", 2 ).setHref( "./doc/privilege.htm" ) );
            helpFolder.addChild( new TreeNode( "Unicode", 2 ).setHref( "./doc/unicodehowto.htm" ) );

            TreeNode lostStudentFolder = new TreeNode( "Report Missing Students", 1 );
            lostStudentFolder.setShowChildren( false );
            lostStudentFolder.addChild( new TreeNode( "List", 2 ).setHref( "./student/listLostStudents.do" ) );
            if ( !request.isUserInRole( Privilege.COMMUNITY.toString() ) )
            {
                lostStudentFolder.addChild( new TreeNode( "Add", 2 )
                    .setHref( "./student/loadLostStudent.do?command=add" ) );
            }

            TreeNode schoolFolder = new TreeNode( "School", 1 );
            schoolFolder.setShowChildren( true );
            schoolFolder.addChild( new TreeNode( "List", 2 ).setHref( "./admin/loadSchools.do" ) );
            schoolFolder.addChild( new TreeNode( "Add", 2 ).setHref( "./admin/loadSchool.do?command=add" ) );//TODO: Use button.add

            //put all folder in the root using following order
            if ( isCommunityPrivilege )
            {
                this.monkeyTree.addChild( schoolFolder );
            }

            if ( myGiaolyFolder.getHasChildren() )
            {
                this.monkeyTree.addChild( myGiaolyFolder );
            }
            if ( myVietnguFolder.getHasChildren() )
            {
                this.monkeyTree.addChild( myVietnguFolder );
            }

            this.monkeyTree.addChild( studentFolder );
            this.monkeyTree.addChild( teacherFolder );
            if ( adminFolder != null )
            {
                this.monkeyTree.addChild( adminFolder );
            }

            this.monkeyTree.addChild( myFolder );
            this.monkeyTree.addChild( reportFolder );
            this.monkeyTree.addChild( lostStudentFolder );
            this.monkeyTree.addChild( helpFolder );
            String feedBackEmail = GlobalConfig.getInstance().getGlobalConfig().getFeedbackEmail();
            if ( !StringUtil.isBlank( feedBackEmail ) )
            {
                this.monkeyTree.addChild( new TreeNode( "Feedback", 1 ).setHref( "mailto:" + feedBackEmail ) );
            }
            this.monkeyTree.addChild( new TreeNode( "Logout", 1 ).setHref( "./logout.do" ) );

        }

    }

    /* getter method for the "beanName" property */
    public String getBeanName()
    {
        return this.beanName;
    }

    /* returns the reference to the monkey tree */
    public TreeNode getMonkeyTree()
    {
        return this.monkeyTree;
    }

    private String beanName;

    private TreeNode monkeyTree;
}