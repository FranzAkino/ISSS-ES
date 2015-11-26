<%@ page import="com.persistencia.QuirofanoJpaController" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.Quirofano" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-26-15
  Time: 12:31 AM
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
  QuirofanoJpaController quirofano = new QuirofanoJpaController(emf);

  int id_q = Integer.parseInt(request.getParameter("quirofanos"));
  Quirofano actual= quirofano.findQuirofano(id_q);
%>

<form action="/ediciones/servlet-edicion-quirofanos" method="get">
  <div>
    <h1>Edicion de Quirofanos</h1>

    <%--<input type="text" id="id_cie9" name="id_cie9" value="<%=actual.getIdProcedimiento()%>" />--%>

    <span>Nombre</span><input type="text" id="q_descripcion" name="q_descripcion" value="<%=actual.getDescripcion()%>" />
    <%--<span>Descripcion</span> <input type="text"  id="cie9_descripcion" name="cie9_descripcion" value="<%= actual.getDescripcion()%>"/>--%>
    <%--&lt;%&ndash;<input type="text" placeholder="Especialidad" />&ndash;%&gt;--%>


    <button class="boton" name="guardar_q" type="submit" value="<%= id_q%>">GUARDAR</button>
    <%--<input type="submit" id="guargarc" value="Guardar" />--%>
    <input type="button" id="cancelar" value="CANCELAR" onclick="window.location='/edicion';return false;"/>

  </div>
</form>
</body>
</html>
