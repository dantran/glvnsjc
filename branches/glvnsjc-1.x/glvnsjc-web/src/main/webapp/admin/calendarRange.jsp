<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Calendar Range</title>
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
          <fmt:message key="title.calendarRange" />
      </div>
    </td>
   </tr>
</table>



<br>
<%@ include file="../includes/validationError.inc" %>
<br>

  <html-el:form focus="startDate" action="/generateCalendar" >

  <table align="center" >

    <tr>
      <td align="right">
        <fmt:message key="label.startDate" />
      </td>
      <td align="left">
        <html-el:text property="startDate"  /><fmt:message key="label.dateFormat" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.endDate" />
      </td>
      <td align="left">
        <html-el:text property="endDate"  /><fmt:message key="label.dateFormat" />
      </td>
    </tr>

    <tr>
      <td align="right"/>
      <td align="left">
        <html-el:submit property="generate" ><fmt:message key="button.generate" /> </html-el:submit >
      </td>
    </tr>

  </table>

</html-el:form>

<%@ include file="../includes/statusMessage.inc" %>

</body>

</html-el:html>



