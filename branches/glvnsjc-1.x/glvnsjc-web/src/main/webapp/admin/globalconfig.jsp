<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Global Configuration</title>
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
          <fmt:message key="title.admin.globalConfig" />
      </div>
    </td>
   </tr>
</table>



<br>
<%@ include file="../includes/validationError.inc" %>
<br>

  <html-el:form focus="currentSchoolYear" action="/saveGlobalConfig" >

  <table align="center" >

    <tr>
      <td><fmt:message key="label.siteActive" /></td>
      <td><html-el:checkbox property="siteActive"   /></td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.currentSchoolYear" />
      </td>
      <td align="left">
        <html-el:text property="currentSchoolYear" size="4" maxlength="4" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.smtpServer" />
      </td>
      <td align="left">
        <html-el:text property="smtpServer"  />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.smtpUserId" />
      </td>
      <td align="left">
        <html-el:text property="smtpUserId" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.smtpPassword" />
      </td>
      <td align="left">
        <html-el:password property="smtpPassword"  />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.feedbackEmail" />
      </td>
      <td align="left">
        <html-el:text property="feedbackEmail"  />
      </td>
    </tr>
    <tr>
      <td align="right"/>
      <td align="left">
        <html-el:submit property="update"><fmt:message key="button.update" /> </html-el:submit >
      </td>
    </tr>

  </table>

</html-el:form>

<%@ include file="../includes/statusMessage.inc" %>

</body>

</html-el:html>



