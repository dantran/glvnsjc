<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>


<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Student</title>
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <LINK REL=STYLESHEET HREF="../includes/admin.css" TYPE="text/css">
    <html-el:base />

<script language="JavaScript">

    function onChangeClassType(control)
    {
      var classType  = control.value;
      location = "loadStudentsToBeGraded.do?classType=" + classType;
    }

</script >
</head>

<body bgcolor="white" background="../images/PaperTexture.gif">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="page-title-row">
    <td align="left" nowrap>
      <div class="page-title-text">
         <c:out value="${studentsToBeGradedForm.title}" />
      </div>
    </td>
   </tr>
</table>

<br/>

<%@ include file="../includes/validationError.inc" %>
<%@ include file="../includes/statusMessage.inc" %>


<html-el:form  action="/updateStudentGrades"  >
<html-el:hidden property ="classType"/>
<html-el:hidden property ="fullClassName"/>
<html-el:hidden property ="title"/>


  <table cellpadding=2 cellspacing=2 border=2  align="center" >

          <!-- Show table header -->
          <tr>
            <th align="center" >
              <fmt:message key="label.name" />
            </th>
            <th align="center" >
              <fmt:message key="label.grade" />
            </th>
          </tr>

          <c:forEach var="gradeView" items="${studentsToBeGradedForm.gradeViews}" >
              <html-el:hidden name="gradeView" property ="schoolYearId" indexed="true"/>
              <tr>
                <td align="left">
                    <c:out value="${gradeView.studentName}" />
                    <html-el:hidden name="gradeView" property ="studentName" indexed="true"/>
                </td>
                <td align="left">
                  <html-el:select name="gradeView"  property="grade" indexed="true">
                    <html-el:options collection="gradeOptions" property="value" labelProperty="label" />
                  </html-el:select>
                </td>
               </tr>
            </c:forEach>

</table>

<br/>

<table align="center" >
    <tr>
      <td align="left"/>
        <html-el:submit><fmt:message key="button.update" /></html-el:submit>
      </td>
</tr>
</table>

</html-el:form>

<%@ include file="../includes/statusMessage.inc" %>

</body>

</html-el:html>


