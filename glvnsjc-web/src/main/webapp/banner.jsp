<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>


<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Banner</title>
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <LINK REL=STYLESHEET HREF="./includes/admin.css" TYPE="text/css">
    <html-el:base />
</head>


<!-- Body -->

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="7171A5" background="./images/GreenTile.gif">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" valign="middle">
      <div class="masthead-title-text" align="center">
        <font  size=18>
          <c:out value="${bannerMessage}" />
        </font>
      </div>
    </td>

    <td align="right" valign="middle">
      <html-el:form action="/logout" target="workFrame">
          <html-el:submit>
            <fmt:message key="button.logout"/>
          </html-el:submit>
      </html-el:form>
    </td>
    
    <!--  
    <td width="1%">
      <div class="table-normal-text" align="left">&nbsp </div>
    </td>
    <td width="1%">
      <div class="table-normal-text" align="left">&nbsp </div>
    </td>
    -->
  </tr>
  <tr>
    <td align="left" >
      <div class="table-header-text">
          Welcome <c:out value="${loginProfile.name.firstName}"/> Privilege: <c:out value="${loginProfile.privilege}"/>
      </div>
    </td>
  </tr>
</table>

</html-el:html>
