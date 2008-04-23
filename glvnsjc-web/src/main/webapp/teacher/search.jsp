<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Filter</title>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="EXPIRES" CONTENT="0">
        <LINK REL=STYLESHEET HREF="../includes/admin.css" TYPE="text/css">
    <html-el:base />
</head>

<body bgcolor="white" background="../images/PaperTexture.gif">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="page-title-row">
    <td align="left" nowrap>
      <div class="page-title-text">
          <fmt:message key="title.user.search" />
      </div>
    </td>
   </tr>
</table>


<br/>

<html-el:errors />


  <html-el:form  action="/search" >

  <table border="0" align="center" >
<logic-el:present role="Community" >

    <tr>
      <td align="left">
        <fmt:message key="label.schoolName" />
      </td>
      <td align="left">
        <html-el:select property="schoolId">
          <html-el:options collection="schoolOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
    </tr>
</logic-el:present>

    <tr>
      <td align="left">
        <fmt:message key="label.teacherType" />
      </td>
      <td align="left">
<c:if test="${userSearchForm.map.schoolPriviledgeOnly == 'false'}" >
        <html-el:select property="teacherType">
          <html-el:options collection="teacherTypeOptions" property="value" labelProperty="label"/>
        </html-el:select>
</c:if>
<c:if test="${userSearchForm.map.schoolPriviledgeOnly == 'true'}" >
        <html-el:select property="teacherType">
          <html-el:options collection="principalTeacherTypeOptions" property="value" labelProperty="label"/>
        </html-el:select>
</c:if>
      </td>
    </tr>

    <tr>
      <td align="right"/>
      <td align="left">
      	<html-el:submit  ><fmt:message key="button.search" /> </html-el:submit >
      </td>
    </tr>

  </table>

</html-el:form>

</body>

</html-el:html>



