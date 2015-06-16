<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 06-15-15
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%

  // Grab the variables from the form.
  String fecha = request.getParameter("fecha");
  String hora = request.getParameter("hora");
  String paciente = request.getParameter("paciente");
  String ID = request.getParameter("ID");
  String sex = request.getParameter("sex");
  String estado = request.getParameter("estadoy");
%>
<%-- Print out the variables. --%>
<h1>Hello, <%=fecha%> <%=hora%>!</h1>
I see that you are <%=sex%>. You know, you remind me of a
<%=estado%> variable I once knew.

</body>
</html>
