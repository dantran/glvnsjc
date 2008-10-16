<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>
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
          <fmt:message key="title.lostStudentList" />
        </div>
      </td>
     </tr>
  </table>

  <br/>
  
  <display:table name="sessionScope.list" pagesize="30" export="true" requestURI="./list.jsp">

    <display:column media="html" title="Edit" href="./loadLostStudent.do?command=update&startAt=list" paramId="id" paramProperty="id" >
      <img src=../images/edit.gif border=0>
    </display:column>
    <display:column media="html" title="Del" href="./loadLostStudent.do?command=delete&startAt=list" paramId="id" paramProperty="id" >
      <img src=../images/delete.gif border=0>
    </display:column>

    <display:column property="createdDateDisplay" titleKey="label.createdDate" />
    <display:column property="name.fullName" titleKey="label.name" />
    <display:column property="reportedBy.school.shortName" titleKey="label.schoolName.brief" />
    <display:column property="giaolyClass.fullClassName" titleKey="label.giaoly.brief" />
    <display:column property="vietnguClass.fullClassName" titleKey="label.vietngu.brief" />

    <display:column property="birthDateDisplay" titleKey="label.DOB"/>
    <display:column property="phone" titleKey="label.phone1"/>

  </display:table>

</body>

</html-el:html>

