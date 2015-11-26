<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.RiesgoJpaController" %>
<%@ page import="com.persistencia.Riesgo" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-26-15
  Time: 12:57 AM
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
  RiesgoJpaController riesgo = new RiesgoJpaController(emf);

  int id_r = Integer.parseInt(request.getParameter("riesgos"));
  Riesgo actual = riesgo.findRiesgo(id_r);
%>

<form action="/ediciones/servlet-edicion-riesgos" method="get">
  <div>
    <h1>Edicion de Riesgos</h1>

    <%--<input type="text" id="id_cie9" name="id_cie9" value="<%=actual.getIdProcedimiento()%>" />--%>

    <span>Nombre</span><input type="text" id="r_descripcion" name="r_descripcion" value="<%=actual.getNombre()%>" />
    <%--<span>Descripcion</span> <input type="text"  id="cie9_descripcion" name="cie9_descripcion" value="<%= actual.getDescripcion()%>"/>--%>
    <%--&lt;%&ndash;<input type="text" placeholder="Especialidad" />&ndash;%&gt;--%>


    <button class="boton" name="guardar_r" type="submit" value="<%= id_r%>">GUARDAR</button>
    <%--<input type="submit" id="guargarc" value="Guardar" />--%>
    <input type="button" id="cancelar" value="CANCELAR" onclick="window.location='/edicion';return false;"/>

  </div>
</form>
</body>
</html>
