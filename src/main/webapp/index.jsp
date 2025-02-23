<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2025-02-22
  Time: 10:38 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if(session.getAttribute("userId")==null){
    response.sendRedirect("login.jsp");

  }
%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <p>aaslemaaaa</p>
  
  </body>
</html>
