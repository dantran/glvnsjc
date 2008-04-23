<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>


<html:html locale="true">
<head>
  <title>Menu Tree</title>
  <link rel="stylesheet" type="text/css" href="./includes/admin.css">
  <html:base />
</head>

<body background="./images/PaperTexture.gif">

<nested:form action="/updatemenu.do">

  <nested:write property="beanName" />
  <hr>
  <nested:nest property="monkeyTree" >
    <jsp:include page="treenode.jsp" />
  </nested:nest>

</nested:form>



</body>
</html:html>