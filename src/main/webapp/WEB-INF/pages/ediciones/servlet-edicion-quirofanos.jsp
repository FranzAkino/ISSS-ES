<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.Cie9JpaController" %>
<%@ page import="com.persistencia.Quirofano" %>
<%@ page import="com.persistencia.QuirofanoJpaController" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-26-15
  Time: 12:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body><%
  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  QuirofanoJpaController quirofano = new QuirofanoJpaController(emf);


  int id_q = Integer.parseInt(request.getParameter("guardar_q"));
  Quirofano editado = quirofano.findQuirofano(id_q);

  editado.setDescripcion(request.getParameter("q_descripcion"));

  quirofano.edit(editado);
  response.sendRedirect("/edicion");
%>

</body>
</html>
