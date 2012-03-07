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
         <c:out value="${studentForm.title}" />
      </div>
    </td>
   </tr>
</table>

<br/>

<%@ include file="../includes/validationError.inc" %>
<%@ include file="../includes/statusMessage.inc" %>

<html-el:form  action="/dispatch"  >

  <html-el:hidden property="command"/>
  <html-el:hidden property="startAt"/>
  <html-el:hidden property="title"/>
  <html-el:hidden property="submitKey"/>
  <html-el:hidden property="readonlySchoolYear"/>
  <html-el:hidden property="readonlySchool"/>
  <html-el:hidden property="readonlyPage"/>

  <table border="0" align="center" >


    <tr>
      <td><fmt:message key="label.glvnId" /></td>

      <td>
        <c:if test="${studentForm.submitKey != 'Add'}" > <!--FIXME-->
          <c:out value="${studentForm.student.id}" />
          <html-el:hidden property="student.id" />
        </c:if>
        <c:if test="${studentForm.submitKey == 'Add'}" >
	  <html-el:text property="student.id" />
        </c:if>
      </td>
    </tr>

    <tr>
      <td><fmt:message key="label.lastName" /> </td>
      <td><html-el:text property="student.name.lastName"  readonly="${studentForm.readonlyPage}" /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.middleName" /> </td>
      <td><html-el:text property="student.name.middleName"   readonly="${studentForm.readonlyPage}" /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.firstName" /> </td>
      <td><html-el:text property="student.name.firstName"  readonly="${studentForm.readonlyPage}" />  </td>
    </tr>

    <tr>
      <td ><fmt:message key="label.DOB" /></td>
      <td><html-el:text property="student.birthDate"  readonly="${studentForm.readonlyPage}" /><fmt:message key="label.dateFormat" /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.gender" /> </td>
      <td>
        <html-el:select property="student.gender" disabled="${studentForm.readonlyPage}" >
          <html-el:options collection="genderOptions" property="value" labelProperty="label"/>
        </html-el:select>
        <c:if test="${studentForm.readonlyPage == 'true'}" >
          <html-el:hidden property = "student.gender" />
        </c:if>
      </td>
    </tr>

    <tr>
      <td><fmt:message key="label.parentName" /></td>
      <td><html-el:text  property="student.parentName.lastName" readonly="${studentForm.readonlyPage}" /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.streetAddress1" /></td>
      <td><html-el:text property="student.address.street1" readonly="${studentForm.readonlyPage}" /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.streetAddress2" /></td>
      <td><html-el:text property="student.address.street2" readonly="${studentForm.readonlyPage}" /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.city" /></td>
      <td><html-el:text  property="student.address.city"  readonly="${studentForm.readonlyPage}" /></td>
    </tr>

    <tr>
      <td><fmt:message key="label.state" /> </td>
      <td>
        <html-el:select property="student.address.state"  disabled="${studentForm.readonlyPage}">
          <html-el:options collection="stateOptions" property="value" labelProperty="label" />
        </html-el:select>
      </td>
    </tr>

    <tr>
      <td> <fmt:message key="label.zipCode" /> </td>
      <td> <html-el:text  property="student.address.zipCode"  readonly="${studentForm.readonlyPage}" /> </td>
    </tr>

    <tr>
      <td><fmt:message key="label.phone1" /></td>
      <td><html-el:text property="student.address.phone1"  readonly="${studentForm.readonlyPage}" /></td>
    </tr>

    <tr>
      <td > <fmt:message key="label.email" /> </td>
      <td><html-el:text property="student.address.email"  readonly="${studentForm.readonlyPage}" /> </td>
    </tr>

    <tr>
      <td > <fmt:message key="label.baptismDate" /> </td>
      <td><html-el:text property="student.baptismDate"  readonly="${studentForm.readonlyCertificates}" /> <fmt:message key="label.dateFormat" /> </td>
    </tr>
    
    <tr>
      <td > <fmt:message key="label.baptismLoc" /> </td>
      <td><html-el:text property="student.baptismLocation"  readonly="${studentForm.readonlyCertificates}" /> </td>
    </tr>
    
    <tr>
      <td > <fmt:message key="label.eucharistDate" /> </td>
      <td><html-el:text property="student.eucharistDate"  readonly="${studentForm.readonlyCertificates}" /> <fmt:message key="label.dateFormat" /> </td>
    </tr>
    
    <tr>
      <td > <fmt:message key="label.eucharistLoc" /> </td>
      <td><html-el:text property="student.eucharistLocation"  readonly="${studentForm.readonlyCertificates}" /> </td>
    </tr>
    
</table>

    <!-- Show editable school year list -->

    <br/> <br/>
    <table cellpadding=2 cellspacing=2 border=2  align="center" width="75%" >
      <tr>
          <!-- Show table header -->
          <tr>
            <th align="center" >
              <fmt:message key="label.schoolYear.brief" />
            </th>
            <th align="center" >
              <fmt:message key="label.schoolName" />
            </th>
            <th align="center" >
              <fmt:message key="label.giaoly" />
            </th>

            <th align="center" >
              <fmt:message key="label.vietngu" />
            </th>
          </tr>

          <c:forEach var="schoolYear" items="${studentForm.schoolYears}" >
          
            <c:if test="${schoolYear.editAllow == 'true'}" >
              <html-el:hidden name="schoolYear" property ="editAllow" indexed="true"/>
              <html-el:hidden name="schoolYear" property ="id" indexed="true"/>
              <tr>
                <td align="left">
                    <c:out value="${schoolYear.year}-${schoolYear.year+1}" />
                    <html-el:hidden name="schoolYear" property="year"  indexed="true"/>
                </td>

                <td align="left">
                  <html-el:select name="schoolYear"  property="schoolId" disabled="${studentForm.readonlySchool}" indexed="true">
                    <html-el:options collection="schoolOptions" property="value" labelProperty="label" />
                  </html-el:select>
                  <c:if test="${studentForm.readonlySchool == 'true'}" >
                    <html-el:hidden name="schoolYear" property="schoolId" indexed="true" />
                  </c:if>
                </td>
                <td>
                  <table>
                    <tr>
                      <td align="left">
                        <html-el:select name="schoolYear"  property="giaolyClass.name" disabled="${studentForm.readonlyPage}" indexed="true"> >
                          <html-el:options collection="classNameOptions" property="value" labelProperty="label"/>
                        </html-el:select>
                      </td>
                      <td align="left">
                        <html-el:select name="schoolYear"  property="giaolyClass.subName" disabled="${studentForm.readonlyPage}" indexed="true">
                          <html-el:options collection="classSubNameOptions" property="value" labelProperty="label"/>
                        </html-el:select>
                      </td>
                      <td align="left">
                         <fmt:message key="label.grade" />
                      </td>
                      <td align="left">
                        <html-el:select  name="schoolYear" property="giaolyClass.grade" disabled="${studentForm.readonlyPage}" indexed="true">
                          <html-el:options collection="gradeOptions" property="value" labelProperty="label"/>
                        </html-el:select>
                      </td>
                    </tr>
                  </table>
                </td>

                <td>
                  <table>
                    <tr>
                      <td align="left">
                        <html-el:select name="schoolYear"  property="vietnguClass.name" disabled="${studentForm.readonlyPage}" indexed="true">
                          <html-el:options collection="classNameOptions" property="value" labelProperty="label"/>
                        </html-el:select>
                      </td>
                      <td align="left">
                        <html-el:select name="schoolYear"  property="vietnguClass.subName" disabled="${studentForm.readonlyPage}" indexed="true">
                          <html-el:options collection="classSubNameOptions" property="value" labelProperty="label"/>
                        </html-el:select>
                      </td>
                      <td align="left">
                         <fmt:message key="label.grade" />
                      </td>
                      <td align="left">
                        <html-el:select name="schoolYear"  property="vietnguClass.grade" disabled="${studentForm.readonlyPage}" indexed="true" >
                          <html-el:options collection="gradeOptions" property="value" labelProperty="label"/>
                        </html-el:select>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </c:if>

            <c:if test="${schoolYear.editAllow == 'false'}" >
            <tr>
              <td align="left">
                <c:out value="${schoolYear.year}-${schoolYear.year+1}" />
                <html-el:hidden name="schoolYear" property="year"  indexed="true"/>
              </td>
              <td align="left">
                <c:out value="${schoolYear.schoolName}"/>
                <html-el:hidden name="schoolYear" property="schoolId"  indexed="true"/>
                <html-el:hidden name="schoolYear" property="schoolName"  indexed="true"/>
              </td>
              <td align="center">
                <c:out value="${schoolYear.giaolyClass.name}${schoolYear.giaolyClass.subName} "/>
                <fmt:message key="label.grade" />:<c:out value="${schoolYear.giaolyClass.grade}"/>
                <html-el:hidden name="schoolYear" property="giaolyClass.name"  indexed="true"/>
                <html-el:hidden name="schoolYear" property="giaolyClass.subName"  indexed="true"/>
                <html-el:hidden name="schoolYear" property="giaolyClass.grade"  indexed="true"/>
              </td>
              <td align="center">
                <c:out value="${schoolYear.vietnguClass.name}${schoolYear.vietnguClass.subName} "/>
                <fmt:message key="label.grade" />:<c:out value="${schoolYear.vietnguClass.grade}"/>
                <html-el:hidden name="schoolYear" property="vietnguClass.name"  indexed="true"/>
                <html-el:hidden name="schoolYear" property="vietnguClass.subName"  indexed="true"/>
                <html-el:hidden name="schoolYear" property="vietnguClass.grade"  indexed="true"/>
                </td>
            </tr>
            </c:if>

          </c:forEach>

    </table>

    <br>

  <table align="center" >
    <tr>
      <td align="left"/>
      <td align="left">
        <html-el:submit property="action">
          <c:out value="${studentForm.submitKey}" />
        </html-el:submit>
      </td>
      
      <html-el:hidden property="optionToAddSchoolYear"/>
      <c:if test="${studentForm.optionToAddSchoolYear == 'true'}" >
        <td>
          <html-el:submit property="action">
            <fmt:message key="button.addSchoolYear"/>
          </html-el:submit>
        </td>
      </c:if>
      
      <html-el:hidden property="optionToRemoveCurrentYear"/>
      <c:if test="${studentForm.optionToRemoveCurrentYear == 'true'}" >
          <td>
            <html-el:submit property="action">
               <fmt:message key="button.removeCurrentYear"/>
            </html-el:submit>
          </td>
      </c:if>
      
      <html-el:hidden property="cancelAllow"/>
       <c:if test="${studentForm.cancelAllow == 'true'}" >
          <td>
            <html-el:submit property="action">
               <fmt:message key="button.cancel"/>
            </html-el:submit>
          </td>
      </c:if>

    </tr>
  </table>

</html-el:form>

</body>

</html-el:html>


