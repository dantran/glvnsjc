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
        <fmt:message key="title.student.search" />
      </div>
    </td>
   </tr>
</table>


<br/>

<%@ include file="../includes/validationError.inc" %>

  <html-el:form focus="lastName" action="/search" >

  <table border="0" align="center" >

    <tr>
      <td align="right">
        <fmt:message key="label.glvnId" />
      </td>
      <td align="left">
        <html-el:text property="studentId" size="16" maxlength="16" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.lastName" />
      </td>
      <td align="left">
        <html-el:text property="lastName" size="16" maxlength="16" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.middleName" />
      </td>
      <td align="left">
         <html-el:text property="middleName" size="16" maxlength="16" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.firstName" />
      </td>
      <td align="left">
        <html-el:text property="firstName" size="16" maxlength="16" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.DOB" />
      </td>
      <td align="left">
        <html-el:text property="birthDate" size="16" maxlength="16" /> <fmt:message key="label.dateFormat" />
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.phone1" />
      </td>
      <td align="left">
        <html-el:text property="phone" size="16" maxlength="16" />
      </td>
    </tr>

<logic-el:present role="Community" >
    <tr>
      <td align="right">
        <fmt:message key="label.schoolName.brief" />
      </td>
      <td align="left">
        <html-el:select property="schoolId" >
          <html-el:options collection="schoolOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
    </tr>

    <tr>
      <td align="right">
        <fmt:message key="label.schoolYear.brief" />
      </td>
      <td align="left">
        <html-el:select property="schoolYear">
          <html-el:options collection="schoolYearOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
    </tr>
</logic-el:present>

    <tr>
      <td align="right">
        <fmt:message key="label.giaoly" />
      </td>
     <td align="left">
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
      <td align="right">
        <fmt:message key="label.vietngu" />
      </td>
      <td align="left">
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

    <tr>
      <td align="right"/>
      <td align="left">
        <table>
          <tr>
            <td><html-el:submit property="action"><fmt:message key="button.search" /> </html-el:submit></td>
	    <td><a href="../doc/StudentSearch.htm"><img src="../images/question.gif"  border=0></a></td>
         </tr>
         </table>
      </td>
    </tr>

  </table>

</html-el:form>
</body>

</html-el:html>



