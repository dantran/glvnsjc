<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic-el" prefix="logic-el"%>


<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages" />

<head>
<title>Student</title>
<META HTTP-EQUIV="Expires" CONTENT="-1">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<LINK REL=STYLESHEET HREF="../includes/admin.css" TYPE="text/css">
<html-el:base />
</head>

<body bgcolor="white" background="../images/PaperTexture.gif">

  <c:set value="${userForm.command}" var="command" />

  <!-- disable all field during delete -->
  <c:set value="${command == 'delete'}" var="readonlyForm" />

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr class="page-title-row">
      <td align="left" nowrap>
        <div class="page-title-text">
          <c:choose>
            <c:when test="${command == 'add' }">
              <fmt:message key="title.user.add" />
            </c:when>
            <c:when test="${command == 'update' }">
              <fmt:message key="title.user.update" />
            </c:when>
            <c:when test="${command == 'delete' }">
              <fmt:message key="title.user.delete" />
            </c:when>
          </c:choose>
        </div>
      </td>
    </tr>
  </table>

  <br />


  <%@ include file="../includes/validationError.inc"%>
  <%@ include file="../includes/statusMessage.inc"%>

  <html-el:form action="/dispatch">

    <html-el:hidden property="command" />
    <html-el:hidden property="id" />

    <table border="0" align="center">

      <tr>
        <td><fmt:message key="label.loginAllow" /></td>
        <td><html-el:checkbox property="loginable" disabled="${istrue}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.privilege" /></td>
        <td><table>
            <tr>
              <td><c:if test="${userForm.schoolPriviledgeOnly != 'true'}">
                  <html-el:select property="privilege" disabled="${readonlyForm}">
                    <html-el:options collection="privilegeOptions" property="value" labelProperty="label" />
                  </html-el:select>
                </c:if> <c:if test="${userForm.schoolPriviledgeOnly == 'true'}">
                  <html-el:select property="privilege" disabled="${readonlyForm}">
                    <html-el:options collection="schoolPrivilegeOptions" property="value" labelProperty="label" />
                  </html-el:select>
                </c:if>
              <td>
            </tr>
          </table></td>
      </tr>

      <tr>
        <td><fmt:message key="label.instructorType" /></td>
        <td><table>
            <tr>
              <td><c:if test="${readonlyForm == 'true'}">
                  <c:if test="${userForm.schoolPriviledgeOnly != 'true'}">
                    <html-el:select property="teacherType" disabled="${readonlyForm}">
                      <html-el:options collection="teacherTypeOptions" property="value" labelProperty="label" />
                    </html-el:select>
                  </c:if>
                  <c:if test="${userForm.schoolPriviledgeOnly == 'true'}">
                    <html-el:select property="teacherType" disabled="${readonlyForm}">
                      <html-el:options collection="principalTeacherTypeOptions" property="value" labelProperty="label" />
                    </html-el:select>
                  </c:if>
                  <html-el:hidden property="teacherType" />
                </c:if> <c:if test="${readonlyForm != 'true'}">
                  <c:if test="${userForm.schoolPriviledgeOnly != 'true'}">
                    <html-el:select property="teacherType">
                      <html-el:options collection="teacherTypeOptions" property="value" labelProperty="label" />
                    </html-el:select>
                  </c:if>
                  <c:if test="${userForm.schoolPriviledgeOnly == 'true'}">
                    <html-el:select property="teacherType">
                      <html-el:options collection="principalTeacherTypeOptions" property="value" labelProperty="label" />
                    </html-el:select>
                  </c:if>
                </c:if>
              <td>
            </tr>
          </table></td>
      </tr>

      <logic-el:present role="Community">
        <tr>
          <td><fmt:message key="label.schoolName" /></td>
          <td><table>
              <tr>
                <td><html-el:select property="schoolId">
                    <html-el:options collection="schoolOptions" property="value" labelProperty="label" />
                  </html-el:select>
                <td>
              </tr>
            </table></td>
        </tr>
      </logic-el:present>


      <tr>
        <td><fmt:message key="label.giaoly" /></td>
        <td>
          <table>
            <tr>
              <td><html-el:select property="giaolyClass" disabled="${readonlyForm}"> >
          <html-el:options collection="classNameOptions" property="value" labelProperty="label" />
                </html-el:select></td>
              <td><html-el:select property="giaolySubClass" disabled="${readonlyForm}">
                  <html-el:options collection="classSubNameOptions" property="value" labelProperty="label" />
                </html-el:select></td>
            </tr>
          </table>
        </td>
      </tr>

      <tr>
        <td><fmt:message key="label.vietngu" /></td>
        <td>
          <table>
            <tr>
              <td><html-el:select property="vietnguClass" disabled="${readonlyForm}"> >
          <html-el:options collection="classNameOptions" property="value" labelProperty="label" />
                </html-el:select></td>
              <td><html-el:select property="vietnguSubClass" disabled="${readonlyForm}">
                  <html-el:options collection="classSubNameOptions" property="value" labelProperty="label" />
                </html-el:select></td>
            </tr>
          </table>
        </td>
      </tr>


      <tr>
        <td><fmt:message key="label.userName" /></td>
        <td><html-el:text property="userId" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.password" /></td>
        <td><html-el:password property="password" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.confirmPassword" /></td>
        <td><html-el:password property="confirmPassword" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.lastName" /></td>
        <td><html-el:text property="name.lastName" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.middleName" /></td>
        <td><html-el:text property="name.middleName" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.firstName" /></td>
        <td><html-el:text property="name.firstName" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.DOB" /></td>
        <td><html-el:text property="birthDate" readonly="${readonlyForm}" /> <fmt:message
            key="label.dateFormat.short" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.saintName" /></td>
        <td><html-el:text property="saintName" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.gender" /></td>
        <td><html-el:select property="gender" disabled="${readonlyForm}">
            <html-el:options collection="genderOptions" property="value" labelProperty="label" />
          </html-el:select> <c:if test="${readonlyForm == 'true'}">
            <html-el:hidden property="gender" />
          </c:if></td>
      </tr>

      <tr>
        <td><fmt:message key="label.streetAddress1" /></td>
        <td><html-el:text property="address.street1" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.streetAddress2" /></td>
        <td><html-el:text property="address.street2" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.city" /></td>
        <td><html-el:text property="address.city" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.state" /></td>
        <td><html-el:select property="address.state" disabled="${readonlyForm}">
            <html-el:options collection="stateOptions" property="value" labelProperty="label" />
          </html-el:select></td>
      </tr>

      <tr>
        <td><fmt:message key="label.zipCode" /></td>
        <td><html-el:text property="address.zipCode" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.phone1" /></td>
        <td><html-el:text property="address.phone1" readonly="${readonlyForm}" /></td>
      </tr>
      <tr>
        <td><fmt:message key="label.phone2" /></td>
        <td><html-el:text property="address.phone2" readonly="${readonlyForm}" /></td>
      </tr>

      <tr>
        <td><fmt:message key="label.email" /></td>
        <td><html-el:text property="address.email" readonly="${readonlyForm}" /></td>
      </tr>
    </table>
    <br>

    <table cellpadding=2 cellspacing=2 border=2 align="center" width="50%">
      <!-- Show table header -->
      <tr>
        <th align="center"><fmt:message key="label.certificate.description" /></th>
        <th align="center"><fmt:message key="label.certificate.date" /></th>
      </tr>

      <c:forEach var="certificate" items="${userForm.certificates}">

        <c:if test="${certificate.editAllow == 'false'}">
          <tr>
            <td align="left"><c:out value="${certificate.description}" />
              <html-el:hidden name="certificate" property="certificateTypeIds" indexed="true" />
            </td>
            <td align="left"><c:out value="${certificate.date}" />
              <html-el:hidden name="certificate" property="date" indexed="true" />
           </td>
          </tr>
        </c:if>

        <c:if test="${certificate.editAllow == 'true'}">
        </c:if>

      </c:forEach>
    </table>

    <table align="center">
      <tr>
        <td align="left" />
        <td align="left"><c:choose>
            <c:when test="${command == 'add'}">
              <html-el:submit property="action">
                <fmt:message key="button.add" />
              </html-el:submit>
            </c:when>
            <c:when test="${command == 'update'}">
              <tr>
                <td><html-el:submit property="action">
                    <fmt:message key="button.update" />
                  </html-el:submit>
                <td><logic-el:present role="Principal">
                    <td><html-el:submit property="action">
                        <fmt:message key="button.teacher.resetPassword" />
                      </html-el:submit>
                    <td>
                  </logic-el:present>
            </c:when>
            <c:when test="${command == 'delete'}">
              <html-el:submit property="action">
                <fmt:message key="button.delete" />
              </html-el:submit>
            </c:when>
          </c:choose></td>
      </tr>

    </table>

  </html-el:form>

</body>

</html-el:html>


