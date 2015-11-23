<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.persistencia.CirujanoJpaController" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.Cirujano" %>
<%@ page import="com.persistencia.Especialidad" %>
<%@ page import="com.persistencia.Horario" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-21-15
  Time: 07:14 PM
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
  CirujanoJpaController cirujano = new CirujanoJpaController(emf);
  int id_c = Integer.parseInt(request.getParameter("cirujanos"));

  Cirujano actual= cirujano.findCirujano(id_c);

  List<Especialidad> lista_e= (List<Especialidad>)request.getAttribute("especialidades");
  Iterator<Especialidad> it_e=lista_e.iterator();
  Especialidad lista_especialidad;

  List<Horario> lista_h= (List<Horario>)request.getAttribute("horarios");
  Iterator<Horario> it_h=lista_h.iterator();
  Horario lista_horario;
%>
<form action="/ediciones/servlet-edicion-cirujano" method="get">
  <div>
    <h1>Edicion de Cirujano</h1>

    <input type="text" id="id_c" name="id_c" value="<%=actual.getIdCirujano()%>" />

    <span>Nombres</span><input type="text" placeholder="Nombre" id="c_nombre" name="c_nombre" value="<%=actual.getNombres()%>" />
    <span>Apellidos</span> <input type="text" placeholder="Apellido" id="c_apellido" name="c_apellido" value="<%= actual.getApellidos()%>"/>
  <%--<input type="text" placeholder="Especialidad" />--%>
    <span>Especialidad</span>
    <select id="list_especialidad" size=6 autofocus name="especialidades">

    <%
      while(it_e.hasNext()) {
        lista_especialidad = it_e.next();

        if(lista_especialidad.getIdEspecialidad() == actual.getFkidEspecialidad().getIdEspecialidad()){%>
    <option selected value="<%= lista_especialidad.getIdEspecialidad()%>"><%= lista_especialidad.getEspecialidad()%></option>

    <%} else{%>
    <option value="<%= lista_especialidad.getIdEspecialidad()%>"><%= lista_especialidad.getEspecialidad()%></option>
    <%}}%>
  </select>
  <%--<input type="text" placeholder="Horario" />--%>
    <span>Horario</span>
  <select id="list_horario" size=6 autofocus name="horarios">

    <%
      SimpleDateFormat formato= new SimpleDateFormat("h:mm a");

      while(it_h.hasNext()) {
        lista_horario = it_h.next();

        if(lista_horario.getIdHorario() == actual.getFkHorarios().getIdHorario()){%>
    <option selected value="<%= lista_horario.getIdHorario()%>"><%= formato.format(lista_horario.getEntrada())%> a <%= formato.format(lista_horario.getSalida()) %></option>
    <%}else{%>
     <option value="<%= lista_horario.getIdHorario()%>"><%= formato.format(lista_horario.getEntrada())%> a <%= formato.format(lista_horario.getSalida()) %></option>
    <%}}%>
  </select>
  <br>
  <span>Estado:</span>

  <%if(actual.getActivo() == 0){%>
    <input type="radio" id="est1" name="estado" value="1"><label for="est1" >Activo</label>
    <input type="radio" id="est2" name="estado" checked="checked" value="0"><label for="est2" >Inactivo</label>

  <%}
    if(actual.getActivo() == 1){%>
  <input type="radio" id="est1" name="estado" checked="checked" value="1"><label for="est1" >Activo</label>
  <input type="radio" id="est2" name="estado"  value="0"><label for="est2" >Inactivo</label>
<%}%>

  <input type="submit" id="guargarc" value="Guardar" />
  <input type="button" id="cancelar" value="Cancelar" onclick="window.location='/edicion';return false;"/>

  </div>
</form>



</body>
</html>
