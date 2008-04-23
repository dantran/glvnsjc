<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>


<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>


<head>
    <title>Student List</title>
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <LINK REL=STYLESHEET HREF="../css/screen.css" TYPE="text/css">
    <html-el:base />
</head>


<body bgcolor="white" background="../images/PaperTexture.gif">

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr class="page-title-row">
      <td align="left" nowrap>
        <div class="page-title-text">
          <fmt:message key="title.student.nextYearReport" />
        </div>
      </td>
    </tr>
  </table>
  
  <display:table   name="sessionScope.nextYearReport" pagesize="30" export="true" requestURI="./nextYearReport.do" >
    <display:column property="id" titleKey="label.glvnId" />
    <display:column property="schoolName" titleKey="label.schoolName.brief" />
    <display:column property="studentName" titleKey="label.name" />
    <display:column property="birthDate" titleKey="label.DOB"/>
    <display:column property="giaolyClass" titleKey="label.giaoly.brief" sortable="true"/>
    <display:column property="vietnguClass" titleKey="label.vietngu.brief"/>
    <display:column property="nextGiaolyClass" titleKey="label.nextGiaoly.brief"/>
    <display:column property="nextVietnguClass" titleKey="label.nextVietngu.brief"/>
  </display:table>

</body>

</html-el:html>

