<%@ page language="java" %>
<%@ page import="org.apache.struts.action.*,java.util.Iterator, org.glvnsjc.view.ImportForm"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<head>
    <title>Upload Import File</title>
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
          <fmt:message key="title.admin.import"/>
      </div>
    </td>
   </tr>
</table>

<br/><br/>

<!-- Find out if the maximum length has been exceeded. -->
<logic-el:present name="<%=  %>" scope="request">
    <%
        ActionErrors errors = (ActionErrors) request.getAttribute( Globals.ERROR_KEY );
        //note that this error is created in the validate() method of ImportForm
        Iterator iterator = errors.get(ImportForm.ERROR_PROPERTY_MAX_LENGTH_EXCEEDED);
        //there's only one possible error in this
        ActionError error = (ActionError) iterator.next();
        pageContext.setAttribute("maxlength.error", error, PageContext.REQUEST_SCOPE);
    %>
</logic-el:present>
<!-- If there was an error, print it out -->
<logic-el:present name="maxlength.error" scope="request">
    <font color="red"><bean:message name="maxlength.error" property="key" /></font>
</logic-el:present>


<!--
	The most important part is to declare your form's enctype to be "multipart/form-data",
	and to have a form:file element that maps to your ActionForm's FormFile property
-->
<html-el:form action="/import" enctype="multipart/form-data">

  <table border="0" align="center" >

    <tr>
      <td align="left">
        <fmt:message key="label.memberType" />
      </td>
      <td align="left">
        <html-el:select property="memberType" >
          <html-el:options collection="memberOptions" property="value" labelProperty="label"/> <br />
        </html-el:select>
      </td>
    </tr>
    <tr>
      <td align="left">
        <fmt:message key="label.encoding" />
      </td>
      <td align="left">
        <html-el:select property="encoding" >
          <html-el:options collection="encodingOptions" property="value" labelProperty="label"/> <br />
        </html-el:select>
      </td>
    </tr>

    <tr>
      <td align="left">
        <fmt:message key="label.fileName" />
      </td>
      <td align="left">
	<html-el:file property="theFile" size="64" /><br/>
      </td>
    </tr>

    <tr>
      <td align="left">
        <fmt:message key="label.separator" />
      </td>
      <td>
        <html-el:select property="separator" >
          <html-el:options collection="separatorOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
    </tr>
    <tr/>

    <tr>
      <td align="left">
        <fmt:message key="label.cleanDB" />
      </td>
      <td align="left">
         <html-el:checkbox property="cleanDB" />
      </td>
    </tr>

    <tr>
      <td align="left"/>
      <td align="left">
        <html-el:submit property="action">
           <fmt:message key="button.view"/>
        </html-el:submit>
        <html-el:submit property="action">
           <fmt:message key="button.import"/>
        </html-el:submit>
      </td>
    </tr>



   </table>

</html-el:form>
</body>
</html-el:html>