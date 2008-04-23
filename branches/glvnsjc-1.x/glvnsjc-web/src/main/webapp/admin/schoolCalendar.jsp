<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>School Calendar</title>
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
          <fmt:message key="title.schoolCalendar" />
      </div>
    </td>
   </tr>
</table>

<br>
<%@ include file="../includes/validationError.inc" %>
<br>

  <html-el:form action="/saveSchoolCalendar" >

  <table cellpadding=2 cellspacing=2 border=2  align="center" width="50%" >
    <tr>
      <th align="center" >
        <fmt:message key="label.date" />
      </th>
      <th align="center" >
        <fmt:message key="label.holiday" />
      </th>
      <th align="center" >
        <fmt:message key="label.schoolNote" />
      </th>
      <th align="center" >
        <fmt:message key="label.teacherNote" />
      </th>
    </tr>
    <c:forEach var="schoolDay" items="${schoolCalendarForm.schoolDays}" >
      <tr>
        <td align="left">
          <c:out value="${schoolDay.day}" />
          <html-el:hidden name="schoolDay" property="day"  indexed="true"/>
        </td>

        <td align="center">
          <html-el:checkbox name="schoolDay" property="isHoliday" indexed="true"  />
        </td>

        <td>
          <html-el:text name="schoolDay" property="schoolNote" indexed="true" size="64" />
        </td>
        <td>
          <html-el:text name="schoolDay" property="teacherNote" indexed="true" size="64" />
        </td>
      </tr>
    </c:forEach>
   </table>
   <br/> <br/>
   <table align="center">
    <tr>
      <td align="right"/>
      <td align="left">
        <html-el:submit property="save" ><fmt:message key="button.save" /> </html-el:submit >
      </td>
    </tr>
  </table>

</html-el:form>

<%@ include file="../includes/statusMessage.inc" %>

</body>

</html-el:html>



