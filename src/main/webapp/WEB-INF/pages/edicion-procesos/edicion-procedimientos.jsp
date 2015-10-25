<%@ page import="com.persistencia.Cie9JpaController" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 10-25-15
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

// Grab the variables from the form.
<%
  int id_cie9= Integer.parseInt(request.getParameter("cirujanos")) ;

  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  Cie9JpaController eliminar = new Cie9JpaController(emf);
  eliminar.destroy(id_cie9);

  response.sendRedirect("edicion");


%>


</body>
</html>
