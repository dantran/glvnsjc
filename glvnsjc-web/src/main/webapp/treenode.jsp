<%@page contentType="text/html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>


<nested:root>

  <img src="./images/spacer.gif" width="<nested:write property="nodeIndent" />" height="1">

  <nested:equal property="showChildren" value="true">

    <nested:image src="./images/minus.gif" property="toggle"/>
    <nested:write property="nodeName" /><br>

    <nested:iterate property="childCollection">
      <jsp:include page="treenode.jsp" />
    </nested:iterate>

  </nested:equal>

  <nested:equal property="showChildren" value="false">

    <nested:equal property="hasChildren" value="true">
      <nested:image src="./images/plus.gif" property="toggle"/>
    </nested:equal>

    <nested:equal property="hasChildren" value="false">
      <img src="./images/empty.gif">
    </nested:equal>

    <nested:notEmpty property="href">
      <a href="<nested:write property="href" />" target="workFrame">
        <nested:write property="nodeName" />
      </a>
      <br>
    </nested:notEmpty>

    <nested:empty property="href">
      <nested:write property="nodeName" /><br>
    </nested:empty>

  </nested:equal>

</nested:root>
