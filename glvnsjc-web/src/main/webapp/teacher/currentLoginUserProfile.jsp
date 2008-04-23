<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>
<head>
    <title>Teacher Profile</title>
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
             <fmt:message key="title.user.myProfile"/>
      </div>
    </td>
   </tr>
</table>

<br/>


<%@ include file="../includes/validationError.inc" %>


<html-el:form  action="/updateCurrentLoginUserProfile"  >

  <table border="0" align="center" >



    <tr>
      <td><fmt:message key="label.userName" /></td>
      <td><html-el:text property="userId"   /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.password" /></td>
      <td><html-el:password property="password"   /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.confirmPassword" /></td>
      <td><html-el:password property="confirmPassword"   /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.lastName" /> </td>
      <td><html-el:text property="name.lastName"   /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.middleName" /> </td>
      <td><html-el:text property="name.middleName"    /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.firstName" /> </td>
      <td><html-el:text property="name.firstName"   />  </td>
    </tr>

    <tr>
      <td ><fmt:message key="label.DOB" /></td>
      <td><html-el:text property="birthDate"/><fmt:message key="label.dateFormat.short" /></td>
    </tr>

    <tr>
      <td > <fmt:message key="label.saintName" /> </td>
      <td><html-el:text property="saintName"   /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.gender" /> </td>
      <td>
        <html-el:select property="gender"  >
          <html-el:options collection="genderOptions" property="value" labelProperty="label"/>
        </html-el:select>
        <c:if test="${readonlyForm == 'true'}" >
          <html-el:hidden property = "gender" />
        </c:if>
      </td>
    </tr>

    <tr>
      <td><fmt:message key="label.streetAddress1" /></td>
      <td><html-el:text property="address.street1"  /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.streetAddress2" /></td>
      <td><html-el:text property="address.street2"  /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.city" /></td>
      <td><html-el:text  property="address.city"   /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.state" /> </td>
      <td>
        <html-el:select property="address.state"  >
          <html-el:options collection="stateOptions" property="value" labelProperty="label" />
        </html-el:select>
      </td>
    </tr>

    <tr>
      <td> <fmt:message key="label.zipCode" /> </td>
      <td> <html-el:text  property="address.zipCode"   /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.phone1" /></td>
      <td><html-el:text property="address.phone1"   /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.phone2" /></td>
      <td><html-el:text property="address.phone2"   /></td>
    </tr>

    <tr>
      <td > <fmt:message key="label.email" /> </td>
      <td><html-el:text property="address.email"   /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.giaoly" /> </td>
      <td>
<table> <tr>
      <td>
        <html-el:select property="giaolyClass" > 
          <html-el:options collection="classNameOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
      <td>
        <html-el:select property="giaolySubClass" >
          <html-el:options collection="classSubNameOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
</tr></table>
     </td>
     </tr>

    <tr>
      <td><fmt:message key="label.vietngu" /> </td>
      <td>
<table> <tr>
      <td>
        <html-el:select property="vietnguClass" > 
          <html-el:options collection="classNameOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
      <td>
        <html-el:select property="vietnguSubClass" >
          <html-el:options collection="classSubNameOptions" property="value" labelProperty="label"/>
        </html-el:select>
      </td>
</tr></table>
     </td>
     </tr>

</table>
<br>
<table align="center">
    <tr>
      <td/>
      <td>
            <html-el:submit>Update</html-el:submit>
     </td>
    </tr>

</table>

</html-el:form>

<%@ include file="../includes/statusMessage.inc" %>

</body>

</html-el:html>


