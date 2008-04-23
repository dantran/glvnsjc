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

  <table cellpadding=2 cellspacing=2 border=2  align="center"  >
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
        </td>
        <td align="center">
          <html-el:checkbox name="schoolDay" property="isHoliday" indexed="true" disabled="true" />
        </td>
        <td>
          <c:out value="${schoolDay.schoolNote}" />
          <c:if test="${empty schoolDay.schoolNote}" >
            <c:out value="." />
          </c:if>
        </td>
        <td>
          <c:out value="${schoolDay.teacherNote}" />
          <c:if test="${empty schoolDay.teacherNote}" >
            <c:out value="." />
          </c:if>
        </td>
      </tr>
    </c:forEach>
   </table>


</body>

</html-el:html>



