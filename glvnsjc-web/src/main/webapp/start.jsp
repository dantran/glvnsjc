<%@page contentType="text/html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html-el" prefix="html-el" %>



<html-el:html>

  <head>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache"> <META HTTP-EQUIV="EXPIRES" CONTENT="0">
    <META HTTP-EQUIV="Content-Type" CONTENT="text/html">
    <title>Giaoly Vietngu Menu</title>
    <html-el:base />
  </head>


<script language="JavaScript" type="text/javascript">

  <!--
    if (window.self != window.top) {
      window.open(".", "_top");
    }
  // -->

</script>

   <frameset rows="10%,*"  frameborder="yes" framespacing="1" border="1" >

     <html-el:frame href="./banner.do" scrolling="no" frameName="bannerFrame"/>
     <frameset cols="22%,*"  frameborder="yes" framespacing="1" border="1" >
       <html-el:frame href="./loadmenu.do" scrolling="yes" frameName="menuFrame"/>
       <html-el:frame href="${introductionLink}" scrolling="yes" frameName="workFrame"/>
     </frameset>
   </frameset>

</html-el:html>


