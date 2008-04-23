<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>


<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>


<head>
    <title>Student List</title>
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
          <fmt:message key="title.student.calendar" />
      </div>
    </td>
   </tr>
</table>

<br/>
<ol>
  <li>Copy the below table into clipboard</li>
  <li>Paste the clipboard into a new spreadsheet</li>
  <li>Expand all columns to make all texts appear in one line</li>
  <li>Autoformat the sheet by select Format--&gt;Autoformat</li>
</ol>
<br/>

<table cellpadding=2 cellspacing=2 border=2  align="center" >
  <tr>
    <c:forEach var="item" items="${firstHeaders}" >
       <th><c:out value="${item}"/></th>
    </c:forEach>
  </tr>
  <tr>
    <c:forEach var="item" items="${secondHeaders}" >
       <th><c:out value="${item}"/></th>
    </c:forEach>
  </tr>
  <c:forEach var="schoolYear" items="${schoolYears}" >
    <tr>
      <td><c:out value="${schoolYear.student.name.fullName}"/></td>
      <td><c:out value="${schoolYear.student.address.phone1}"/></td>
      <td><c:out value="${schoolYear.student.birthDate}"/></td>
      <td><c:out value="${schoolYear.student.parentName.fullName}"/></td>
      <td><c:out value="${className}"/></td>
      <c:forEach begin="1" end="${schoolDaysSize}">
        <td align="center">.</td>
      </c:forEach>
    </tr>
  </c:forEach>
</table>



</body>

</html-el:html>

