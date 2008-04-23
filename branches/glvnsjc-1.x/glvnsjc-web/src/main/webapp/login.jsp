<%@ page language="java" contentType="text/html;charset=utf-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>

<html:html locale="true">

<head>
  <title>Login</title>
  <META HTTP-EQUIV="Expires" CONTENT="-1">
  <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
  <html:base />
  <!-- Make sure window is not in a frame -->
  <script language="JavaScript" type="text/javascript">
  <!--
    if (window.self != window.top) {
      window.open(".", "_top");
    }
  // -->
  </script>

  <script language="JavaScript" src="js/md5.js"></script>

</head>

<% String key = Long.toString(System.currentTimeMillis()); %>

<body >


<table width="100%" border="0" cellspacing="2" cellpadding="5">
  <tr>
    <td><img src="images/logo.gif" align="right"/></td>
    <td>
      <table >
        <tr align="center">
          <td ><font  size=15>Giáo Lý Việt Ngữ</font></td>
        </tr>
        <tr align="center">
          <td ><font  size=15>San Jose, CA</font></td>
        </tr>
        <tr/><tr/><tr/><tr/>
        <tr>
          <form method="POST" action="j_security_check" name="loginForm">
            <input type="hidden" name="j_username" />
            <input type="hidden" name="j_password" />
          </form>

          <form name="fakeForm"  >
            <input type="hidden" name="key" value="<%=key%>" />
           <table align="left">
             <tr>
               <th align="right"><font ><bean:message key="label.userName"/></font></th>
               <td align="left"> <input type="text" name="username" size="20" maxlength="64"/> </td>
             </tr>
             <tr>
               <th align="right"><font><bean:message key="label.password"/></font></th>
               <td align="left"><input type="password" name="password" size="20" maxlength="64"/></td>
             </tr>
             <tr>
               <td width="50%" valign="top"> <div align="right"></div> </td>
               <td width="55%" valign="top">&nbsp;</td>
             </tr>

             <!-- login reset buttons layout -->
             <tr>
               <td width="50%" valign="top">
                 <div align="right">
                   <input type="button" name="Login" onclick="javascript:login(username.value, password.value, key.value)"
                          value='<bean:message key="button.login"/>'>&nbsp;&nbsp;
                 </div>
               </td>
               <td width="55%" valign="top">
                 &nbsp;&nbsp;<input type="reset" value='<bean:message key="button.reset"/>'>
               </td>
              </tr>
            </table>
          </form>
        </tr>
     </table>
</table>

<script language="JavaScript" type="text/javascript">
    document.forms["fakeForm"].elements["username"].focus()
</script>


<script language="JavaScript" type="text/javascript">

  function login(username, password, key) {
    var form = document.forms["loginForm"];
    form.elements["j_username"].value = username;
    form.elements["j_password"].value = hex_md5(password + key) + key ;
    form.submit();
  }

</script>

</body>


</html:html>
