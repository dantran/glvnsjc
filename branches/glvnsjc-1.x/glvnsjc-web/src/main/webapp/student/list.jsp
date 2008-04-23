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
    <LINK REL=STYLESHEET HREF="../css/screen.css" TYPE="text/css" media="screen, print">
    <html-el:base />
</head>


<body>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr class="page-title-row">
      <td align="left" nowrap>
        <div class="page-title-text">
            <fmt:message key="title.student.list" />
        </div>
      </td>
     </tr>
  </table>

<logic-el:present role="Community" >
  <c:if test="${isSchoolYearType == 'true'}" >
    <display:table name="sessionScope.list" pagesize="40" export="true">
      <display:column media="html" title="Edit" href="./load.do?command=update&startAt=list" paramId="id" paramProperty="student.id" >
         <img src=../images/edit.gif border=0>
      </display:column>
      <display:column media="html" title="Del" href="./load.do?command=delete&startAt=list" paramId="id" paramProperty="student.id" >
         <img src=../images/delete.gif border=0>
      </display:column>

      <display:column property="student.id" titleKey="label.glvnId" sortable="true" />
      <display:column property="student.name.fullName" title="Name"  sortable="true"/>
      <display:column property="giaolyClass.fullClassName" title="GL" sortable="true" />
      <display:column property="vietnguClass.fullClassName" title="VN" sortable="true" />
      <display:column property="student.birthDateDisplay" titleKey="label.DOB" sortable="true"/>
      <display:column property="student.address.phone1" titleKey="label.phone1" sortable="true"/>
      <display:column property="student.gender.display" titleKey="label.gender.brief" sortable="true" />
      <display:column property="student.parentName.fullName" titleKey="label.parentName"sortable="true"/>
    </display:table>
  </c:if>

  <c:if test="${isSchoolYearType == 'false'}" >
    <display:table name="sessionScope.list" pagesize="40" export="true">
      <display:column media="html" title="Edit" href="./load.do?command=update&startAt=list" paramId="id" paramProperty="id" >
         <img src=../images/edit.gif border=0>
      </display:column>
      <display:column media="html" title="Del" href="./load.do?command=delete&startAt=list" paramId="id" paramProperty="id" >
         <img src=../images/delete.gif border=0>
      </display:column>
      <display:column property="id" title="label.glvnId" sortable="true"/>
      <display:column property="name.fullName" titleKey="label.name" sortable="true" />

      <display:column property="birthDateDisplay" titleKey="label.DOB" sortable="true"/>
      <display:column property="address.phone1" titleKey="label.phone1" sortable="true"/>
      <display:column property="gender.display" titleKey="label.gender.brief"sortable="true"/>
      <display:column property="parentName.fullName" titleKey="label.parentName" sortable="true"/>
    </display:table>
  </c:if>
</logic-el:present>


<logic-el:notPresent role="Community" >
  <c:if test="${isSchoolYearType == 'true'}" >
    <display:table name="sessionScope.list" pagesize="40" export="true">
      <display:column media="html" title="Edit" href="./load.do?command=update&startAt=list" paramId="id" paramProperty="student.id" >
         <img src=../images/edit.gif border=0>
      </display:column>
      <display:column property="student.id"  titleKey="label.glvnId" sortable="true" />
      <display:column property="student.name.fullName" titleKey="label.name" sortable="true" />
      <display:column property="giaolyClass.fullClassName" titleKey="label.giaoly.brief" sortable="true"/>
      <display:column property="vietnguClass.fullClassName" titleKey="label.vietngu.brief" sortable="true" />
      <display:column property="student.birthDateDisplay" titleKey="label.DOB" sortable="true"/>
      <display:column property="student.address.phone1" titleKey="label.phone1" sortable="true"/>
      <display:column property="student.gender.display" titleKey="label.gender.brief" sortable="true"/>
      <display:column property="student.parentName.fullName" titleKey="label.parentName" sortable="true"/>
    </display:table>
  </c:if>

  <c:if test="${isSchoolYearType == 'false'}" >
    <display:table name="sessionScope.list" pagesize="40" export="true">
      <display:column media="html" title="Edit" href="./load.do?command=update&startAt=list" paramId="id" paramProperty="id" >
         <img src=../images/edit.gif border=0>
      </display:column>
      <display:column property="id" titleKey="label.glvnId" sortable="true"/>
      <display:column property="name.fullName" titleKey="label.name" sortable="true"/>
      <display:column property="birthDateDisplay" titleKey="label.DOB" sortable="true"/>
      <display:column property="address.phone1" titleKey="label.phone1" sortable="true"/>
      <display:column property="gender.display" titleKey="label.gender.brief" sortable="true"/>
      <display:column property="parentName.fullName" titleKey="label.parentName" sortable="true"/>
  </display:table>
  </c:if>

</logic-el:notPresent>


</body>

</html-el:html>

