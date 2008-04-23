<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu"%>
<html>
    <head>
        <title>Struts Menu :: Index</title>
        <link rel="stylesheet" type="text/css" media="screen"
            href="styles/global.css" />
    </head>
<body>

<menu:useMenuDisplayer name="Simple"
        bundle="org.apache.struts.action.MESSAGE"
        permissions="rolesAdapter">
  <table cellpadding=0 cellspacing=0>
    <tr>
      <td>
        <menu:displayMenu name="indexMenu"/>
      </td>
    </tr>
  </table>
</menu:useMenuDisplayer>

</body>
</html>
