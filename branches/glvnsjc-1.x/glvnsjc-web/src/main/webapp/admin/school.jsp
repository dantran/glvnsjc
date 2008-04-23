<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>
<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>


<head>
    <title>School</title>
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <LINK REL=STYLESHEET HREF="../includes/admin.css" TYPE="text/css">
    <html-el:base />
</head>

<body bgcolor="white" background="../images/PaperTexture.gif">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="page-title-row">
    <td align="left" nowrap>
      <div class="page-title-text">
         <c:out value="${schoolForm.map.title}" />
      </div>
    </td>
   </tr>
</table>

<br/>

<%@ include file="../includes/validationError.inc" %>
<%@ include file="../includes/statusMessage.inc" %>

<html-el:form  action="/dispatchSchool"  >

  <html-el:hidden property="command"/>
  <!--html-el:hidden property="startAt"/-->
  <html-el:hidden property="id"/>
  <html-el:hidden property="submitButton"/>
  <html-el:hidden property="title"/>

  <table border="0" align="center" >


    <tr>
      <td><fmt:message key="label.schoolName.brief" /></td>
      <td>
        <html-el:text property="shortName" />
      </td>
    </tr>

    <tr>
      <td><fmt:message key="label.schoolName" /></td>
      <td>
        <html-el:text property="name" />
      </td>
    </tr>

  </table>

    <br>

<table align="center" >
    <tr>
      <td align="left"/>
      <td align="left">
            <html-el:submit property="action">
               <c:out value="${schoolForm.map.submitButton}" />
            </html-el:submit>
      </td>
    </tr>
</table>

</html-el:form>


</body>

</html-el:html>


