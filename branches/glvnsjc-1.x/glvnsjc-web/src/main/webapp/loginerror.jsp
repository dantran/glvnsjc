<!-- Standard Struts Entries -->

<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>

<html-el:html locale="true">
<fmt:setBundle basename="org.glvnsjc.messages"/>

<!-- Standard Content -->

<head>
    <title>Student List</title>
    <META HTTP-EQUIV="Expires" CONTENT="-1">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<LINK REL=STYLESHEET HREF="site.css" TYPE="text/css">
    <html-el:base />
</head>

<!-- Body -->

<body class=mainframe background="images/PaperTexture.gif">

<center>

<h2>
  <fmt:message key="error.login"/>
  <br>
  <fmt:message key="error.tryagain"/>
  <html-el:link page="/">
    <fmt:message key="error.here"/>
  </html-el:link>
</h2>

</center>

</body>

<!-- Standard Footer -->


</html-el:html>
