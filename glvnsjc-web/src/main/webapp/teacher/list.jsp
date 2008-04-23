<%@ page contentType="text/html; charset=utf-8" %>


<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>

<html:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Student List</title>
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <LINK REL=STYLESHEET HREF="../css/screen.css" TYPE="text/css">
    <html:base />
</head>


<body bgcolor="white" background="../images/PaperTexture.gif">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="page-title-row">
    <td align="left" nowrap>
      <div class="page-title-text">
          <fmt:message key="title.user.list" />
      </div>
    </td>
   </tr>
</table>

<br/>

<c:if test="${editableList == 'true'}" >
    <display:table name="sessionScope.list" export="true" pagesize="40" requestURI="./search.do">
      <display:column media="html" title="Edit" href="./load.do?command=update" paramId="id" paramProperty="id" >
         <img src="../images/edit.gif" border="0">
      </display:column>
      <display:column media="html" title="Del" href="./load.do?command=delete" paramId="id" paramProperty="id" >
         <img src="../images/delete.gif" border="0">
      </display:column>
      <display:column property="school.shortName"  titleKey="label.schoolName.brief" sortable="true" />
      <display:column property="name.fullName"  titleKey="label.name" sortable="true" />
      <display:column property="teacherType.display"  titleKey="label.instructorType.brief"  sortable="true"/>
      <display:column property="giaolyClassFullName"  titleKey="label.giaoly.brief" sortable="true"/>
      <display:column property="vietnguClassFullName"  titleKey="label.vietngu.brief" sortable="true" />
      <display:column property="userId"  titleKey="label.userName" sortable="true"/>
      <display:column property="loginable"  titleKey="label.loginAllow" sortable="true"/>
      <display:column property="privilege.display"  titleKey="label.privilege" sortable="true"/>
      <display:column property="address.phone1"  titleKey="label.phone1" sortable="true"/>
      <display:column property="address.phone2"  titleKey="label.phone2" sortable="true"/>
      <display:column property="address.email"  titleKey="label.email"  autolink="true" sortable="true"/>
  </display:table>
</c:if>

<c:if test="${editableList == 'false'}" >
  <display:table name="sessionScope.list" export="true" pagesize="40" requestURI="./search.do">
    <display:column property="name.fullName"  title="label.name" sortable="true"/>
      <display:column property="teacherType.display"  titleKey="label.instructorType.brief"  sortable="true"/>
      <display:column property="giaolyClassFullName"  titleKey="label.giaoly.brief" sortable="true"/>
      <display:column property="vietnguClassFullName"  titleKey="label.vietngu.brief" sortable="true" />
      <display:column property="address.phone1"  titleKey="label.phone1" sortable="true"/>
      <display:column property="address.phone2"  titleKey="label.phone2" sortable="true"/>
      <display:column property="address.email"  titleKey="label.email"  autolink="true" sortable="true"/>
  </display:table>
</c:if>


</body>

</html:html>

