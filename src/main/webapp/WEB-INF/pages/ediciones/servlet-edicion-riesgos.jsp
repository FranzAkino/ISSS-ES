<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.persistencia.Riesgo" %>
<%@ page import="com.persistencia.RiesgoJpaController" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-26-15
  Time: 01:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body><%
  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  RiesgoJpaController riesgo = new RiesgoJpaController(emf);


  int id_r = Integer.parseInt(request.getParameter("guardar_r"));
  Riesgo editado = riesgo.findRiesgo(id_r);

  editado.setNombre(request.getParameter("r_descripcion"));

  riesgo.edit(editado);
  response.sendRedirect("/edicion");
%>

</body>
</html>
