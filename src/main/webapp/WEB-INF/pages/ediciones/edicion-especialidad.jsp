<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.Cie9JpaController" %>
<%@ page import="com.persistencia.EspecialidadJpaController" %>
<%@ page import="com.persistencia.Especialidad" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-23-15
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link type="text/css" rel="stylesheet" href="/css/edicion-cirujano-style.css" />
</head>
<body>
<%
  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  EspecialidadJpaController especialidad = new EspecialidadJpaController(emf);


  int id_e = Integer.parseInt(request.getParameter("especialidad"));
  Especialidad actual= especialidad.findEspecialidad(id_e);

%>
<form action="/ediciones/servlet-edicion-especialidad" method="get">
  <div>
    <h1>Edicion de Especialidad</h1>

    <%--<input type="text" id="id_cie9" name="id_cie9" value="<%=actual.getIdProcedimiento()%>" />--%>

    <span>Nombre</span><input type="text" id="e_nombre" name="e_nombre" value="<%=actual.getEspecialidad()%>" />

    <%--<input type="text" placeholder="Especialidad" />--%>


    <button class="boton" name="guardar_e" type="submit" value="<%= id_e%>">GUARDAR</button>
    <%--<input type="submit" id="guargarc" value="Guardar" />--%>
    <input type="button" id="cancelar" value="CANCELAR" onclick="window.location='/edicion';return false;"/>

  </div>
</form>


</body>
</html>
