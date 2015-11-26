<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.persistencia.Cie9JpaController" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.Cie9" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-23-15
  Time: 07:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body><%
EntityManagerFactory emf= CreadorEntityManager.crearEMF();
Cie9JpaController cie9 = new Cie9JpaController(emf);


//int id_c = Integer.parseInt(request.getParameter("id_cie9"));
  int id_c = Integer.parseInt(request.getParameter("guardar_cie9"));
Cie9 editado = cie9.findCie9(id_c);


editado.setNombre(request.getParameter("cie9_nombre"));
editado.setDescripcion(request.getParameter("cie9_descripcion"));

cie9.edit(editado);
response.sendRedirect("/edicion");
%>
</body>
</html>
