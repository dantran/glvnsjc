<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>
<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>


<head>
    <title>Student</title>
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
         <fmt:message key="title.lostStudent" />
      </div>
    </td>
   </tr>
</table>

<br/>

<%@ include file="../includes/validationError.inc" %>
<%@ include file="../includes/statusMessage.inc" %>

<html-el:form  action="/lostStudentDispatch"  >

  <html-el:hidden property="id" />
  <html-el:hidden property="command"/>

  <table border="0" align="center" >

    <tr>
      <td><fmt:message key="label.lastName" /> </td>
      <td><html-el:text property="lastName"  /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.middleName" /> </td>
      <td><html-el:text property="middleName"  /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.firstName" /> </td>
      <td><html-el:text property="firstName" />  </td>
    </tr>

    <tr>
      <td ><fmt:message key="label.DOB" /></td>
      <td><html-el:text property="birthDate"/><fmt:message key="label.dateFormat" /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.phone1" /></td>
      <td><html-el:text property="phone" /></td>
    </tr>

    <tr>
      <td >
        <fmt:message key="label.giaoly" />
      </td>
     <td >
       <table>
         <tr>
           <td align="left">
             <html-el:select property="giaolyClassName">
               <html-el:options collection="classNameOptions" property="value" labelProperty="label"/>
             </html-el:select>
           </td>
           <td align="left">
             <html-el:select property="giaolyClassSubName" >
               <html-el:options collection="classSubNameOptions" property="value" labelProperty="label"/>
             </html-el:select>
           </td>
         </tr>
      </table>
     </td>
    </tr>

    <tr>
      <td >
        <fmt:message key="label.vietngu" />
      </td>
      <td >
        <table>
          <tr>
            <td align="left">
              <html-el:select property="vietnguClassName" >
                <html-el:options collection="classNameOptions" property="value" labelProperty="label"/>
              </html-el:select>
            </td>

            <td align="left">
              <html-el:select property="vietnguClassSubName" >
                <html-el:options collection="classSubNameOptions" property="value" labelProperty="label"/>
              </html-el:select>
            </td>
          </tr>
        </table>
      </td>
    </tr>

    <c:if test="${lostStudentForm.map.command == 'delete' }" >
    <tr>
      <td><fmt:message key="label.reasonToDeleteMissingStudent" /></td>
      <td><html-el:text property="reasonToDelete" /></td>
    </tr>
    </c:if>

</table>

<br>

<table align="center" >
    <tr>
      <td align="left"/>
      <td align="left">
            <html-el:submit property="action">
               <c:out value="${lostStudentForm.map.submitButtonName}" />
            </html-el:submit>
            <html-el:hidden property="submitButtonName"/>
      </td>

    </tr>
</table>

</html-el:form>


</body>

</html-el:html>


