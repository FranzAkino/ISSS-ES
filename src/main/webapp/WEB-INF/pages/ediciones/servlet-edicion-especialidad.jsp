<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.EspecialidadJpaController" %>
<%@ page import="com.persistencia.Especialidad" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-24-15
  Time: 12:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  EspecialidadJpaController especialidad = new EspecialidadJpaController(emf);


  int id_e = Integer.parseInt(request.getParameter("guardar_e"));
  Especialidad editado = especialidad.findEspecialidad(id_e);


  editado.setEspecialidad(request.getParameter("e_nombre"));


 especialidad.edit(editado);
  response.sendRedirect("/edicion");
%>
</body>
</html>
