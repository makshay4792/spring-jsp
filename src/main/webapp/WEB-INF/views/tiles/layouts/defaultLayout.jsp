<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ include file="../../tags/commonTags.jsp" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>
    
    <script type="text/javascript" src="<c:url value='/static/js/bootstrap.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/static/js/jquery-3.3.1.min.js' />"></script>
</head>
  
<body>
        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
     
        <section id="sidemenu">
            <tiles:insertAttribute name="menu" />
        </section>
             
        <section id="site-content">
            <tiles:insertAttribute name="body" />
        </section>
         
        <footer id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
</body>
</html>