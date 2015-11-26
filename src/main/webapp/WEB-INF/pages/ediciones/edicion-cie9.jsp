<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.persistencia.Cie9JpaController" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.Cie9" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-22-15
  Time: 11:08 PM
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
  Cie9JpaController procedimiento= new Cie9JpaController(emf);

  int id_p = Integer.parseInt(request.getParameter("procedimientos"));
  Cie9 actual= procedimiento.findCie9(id_p);
%>

<form action="/ediciones/servlet-edicion-cie9" method="get">
  <div>
    <h1>Edicion de Procedimientos</h1>

    <%--<input type="text" id="id_cie9" name="id_cie9" value="<%=actual.getIdProcedimiento()%>" />--%>

    <span>Nombre</span><input type="text" id="cie9_nombre" name="cie9_nombre" value="<%=actual.getNombre()%>" />
    <span>Descripcion</span> <input type="text"  id="cie9_descripcion" name="cie9_descripcion" value="<%= actual.getDescripcion()%>"/>
    <%--<input type="text" placeholder="Especialidad" />--%>


    <button class="boton" name="guardar_cie9" type="submit" value="<%= id_p%>">GUARDAR</button>
    <%--<input type="submit" id="guargarc" value="Guardar" />--%>
    <input type="button" id="cancelar" value="CANCELAR" onclick="window.location='/edicion';return false;"/>

  </div>
</form>
</body>
</html>
