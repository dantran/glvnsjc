<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Filter</title>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="EXPIRES" CONTENT="0">
        <LINK REL=STYLESHEET HREF="../includes/admin.css" TYPE="text/css">
    <html-el:base />

<script language="JavaScript">

    function onChangeClassType(control)
    {
      var classType  = control.value;
      var className  = document.studentsToBeSubClassedForm.className.value;;
      location = "loadStudentsToBeSubClassed.do?className=" + className + "&classType=" + classType;
    }

    function onChangeClassName(control)
    {
      var className  = control.value;
      var classType  = document.studentsToBeSubClassedForm.classType.value;;
      location = "loadStudentsToBeSubClassed.do?className=" + className + "&classType=" + classType;
    }
</script >
</head>

<body bgcolor="white" background="../images/PaperTexture.gif">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="page-title-row">
    <td align="left" nowrap>
      <div class="page-title-text">
          <fmt:message key="title.splitClass" />
      </div>
    </td>
   </tr>
</table>


<br/>

<%@ include file="../includes/validationError.inc" %>
<%@ include file="../includes/statusMessage.inc" %>

  <html-el:form  action="/updateSubClassedStudents" >

  <table cellpadding=2 cellspacing=2 border=2  align="center" >
    <tr>
      <td align="right">
        <fmt:message key="label.class" />
      </td>
          <td align="left">
            <html-el:select property="classType" onchange="onChangeClassType(this)">
             <html-el:options collection="classTypeOptions" property="value" labelProperty="label"/>
            </html-el:select>
          </td>
          <td>
            <html-el:select property="className" onchange="onChangeClassName(this)">
            <html-el:options collection="classNameOptions" property="value" labelProperty="label"/>
            </html-el:select>
          </td>

    </tr>
   </table>

   <br/>

<table align="center" >
    <tr>
      <td align="left"/>
            <html-el:submit><fmt:message key="button.update" /></html-el:submit>
      </td>
</tr>
</table>

   <br/>

  <table cellpadding=2 cellspacing=2 border=2  align="center" >

          <!-- Show table header -->
          <tr>
            <th align="center" >
              No
            </th>
            <th align="center" >
              <fmt:message key="label.name" />
            </th>
            <th align="center" >
              <fmt:message key="label.DOB" />
            </th>
            <th align="center" >
              <fmt:message key="label.class" />
            </th>
          </tr>

          <c:set value="0" var="count"/>
          <c:forEach var="studentClassView" items="${studentsToBeSubClassedForm.studentClassViews}" >
              <c:set value="${count +1}" var="count"/>
              <html-el:hidden name="studentClassView" property ="schoolYearId" indexed="true"/>
              <tr>
                <td align="left">
                    <c:out value="${count}" />
                </td>
                <td align="left">
                    <c:out value="${studentClassView.studentName}" />
                    <html-el:hidden name="studentClassView" property ="studentName" indexed="true"/>
                </td>
                <td align="left">
                    <c:out value="${studentClassView.birthDate}" />
                    <html-el:hidden name="studentClassView" property ="birthDate" indexed="true"/>
                </td>

                <td><table><tr>
                <td align="left">
                    <html-el:hidden name="studentClassView" property ="className" indexed="true"/>
                  <html-el:select name="studentClassView"  property="classSubName" indexed="true">
                    <html-el:options collection="classSubNameOptions" property="value" labelProperty="label" />
                  </html-el:select>
                </td>
                </tr></table></td>
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



