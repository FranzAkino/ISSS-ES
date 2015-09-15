<%@ page import="java.nio.charset.Charset" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.*" %>
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

  SimpleDateFormat formato= new SimpleDateFormat("MM");
  SimpleDateFormat formato2= new SimpleDateFormat("YYYY");

  Date mes = formato.parse(request.getParameter("MES")) ;
  Date anyo = formato2.parse(request.getParameter("MES")) ;
  int meta =Integer.parseInt(request.getParameter("meta"));
  int cirujano =Integer.parseInt(request.getParameter("cirujanos")) ;


%>
<%-- Paciente --%>




</body>
Metas <%= meta%>, ID del doctor<%= cirujano%> Fecha <%= mes%> <%= anyo%>
<%--<%response.sendRedirect("Registros-informacion"); %>--%>

</html>