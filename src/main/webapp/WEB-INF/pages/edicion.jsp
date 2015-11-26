<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.persistencia.*" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 10-14-15
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <link type="text/css" rel="stylesheet" href="/css/edicion-style.css" />

  <link href='http://fonts.googleapis.com/css?family=Wellfleet' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Wellfleet' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Arvo:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=Goudy+Bookletter+1911' rel='stylesheet' type='text/css'>
  <script>
  <%--Eliminar Cirujani--%>

  function myFunction() {
    var x = document.getElementById("list_cirujanos");
    x.remove(x.selectedIndex);

      }

  function confirmComplete() {

    var answer=confirm("Esta seguro que desea eliminar el elemento seleccionado?");
    if (answer==true)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  </script>
</head>
<body>
<!----- LINK BACK TO THE TUTORIAL--->


<!----- HEADER START--->

<%
  List<Cirujano> lista1= (List<Cirujano>)request.getAttribute("cirujanos");
  int num=lista1.size();
  Iterator<Cirujano> it_c=lista1.iterator();
  Cirujano lista_c;


  List<Cie9> lista2= (List<Cie9>)request.getAttribute("procedimientos");
  Iterator<Cie9> it_p=lista2.iterator();
  Cie9 lista_p;

  List<Especialidad> lista3= (List<Especialidad>)request.getAttribute("especialidad");
  Iterator<Especialidad> it_e=lista3.iterator();
  Especialidad lista_e;

  List<Horario> lista4= (List<Horario>)request.getAttribute("horarios");
  Iterator<Horario> it_h=lista4.iterator();
  Horario lista_h;

  List<Metas> lista5= (List<Metas>)request.getAttribute("metas");
  Iterator<Metas> it_m=lista5.iterator();
  Metas lista_m;


  List<Quirofano> lista6= (List<Quirofano>)request.getAttribute("quirofanos");
  Iterator<Quirofano> it_q=lista6.iterator();
  Quirofano lista_q;

  List<Riesgo> lista7= (List<Riesgo>)request.getAttribute("riesgos");
  Iterator<Riesgo> it_r=lista7.iterator();
  Riesgo lista_r;



%>
<header id="header">
  <div class="content">
    <div id="logo"><a href="/"> INICIO </a></div>

    <nav id="nav">
      <ul>
        <li><a href="#slide1" title="Next Section" >Cirujanos</a></li>
        <li><a href="#slide2" title="Next Section">Procedimientos</a></li>
        <li><a href="#slide3" title="Next Section">Especialidades</a></li>
        <li><a  href="#slide4" title="Next Section">Horarios</a></li>
        <li><a href="#slide5" title="Next Section">Metas</a></li>
        <li><a href="#slide6" title="Next Section">Quirofanos</a></li>
        <li><a href="#slide7" title="Next Section">Riesgos</a></li>
      </ul>
    </nav>
  </div>
</header>
<!----- HEADER END--->

<!----- SLIDES START --->
<div id="slide1">
  <div class="content">
    <h1>Cirujanos</h1>
    <form action="edicion-eliminar" method="get">

    <select id="list_cirujanos" size=12 autofocus name="cirujanos">

      <%
        while(it_c.hasNext()) {
          lista_c = it_c.next();
      %>
      <option value="<%= lista_c.getIdCirujano()%>"><%=lista_c.getNombres()%> <%=lista_c.getApellidos()%></option>
      <%}%>
    </select>

      <button class="boton" name="editar" value="Editar" onclick="">Editar</button>
      <button class="boton" name="eliminar" value="Eliminar" onclick="{return confirmComplete();}">Eliminar</button>

      </form>
  </div>

</div>

<div id="slide2">
  <div class="content" >
    <h1>Procedimientos</h1>
    <form action="edicion-eliminar" method="get">
    <select id="list_procedimientos" size=12 autofocus name="procedimientos">

      <%
        while(it_p.hasNext()) {
          lista_p = it_p.next();
      %>
      <option value="<%= lista_p.getIdProcedimiento()%>"><%=lista_p.getNombre()%></option>
      <%}%>
    </select>

    <button class="boton" name="p_editar" value="Editar"  >Editar</button>
    <button class="boton"  name="p_eliminar" value="Eliminar" onclick="{return confirmComplete();}">Eliminar</button>
    </form>

  </div>
</div>


<div id="slide3">
  <div class="content">
    <h1>Especialidad</h1>
    <form action="edicion-eliminar" method="get">
    <select id="list_especialidad" size=12 autofocus name="especialidad">

      <%
        while(it_e.hasNext()) {
          lista_e = it_e.next();
      %>
      <option value="<%= lista_e.getIdEspecialidad()%>"><%=lista_e.getEspecialidad()%></option>
      <%}%>
    </select>

      <button class="boton" name="e_editar" value="Editar"  onclick="alert('Desea editar esta especialidad?')">Editar</button>
      <button class="boton"  name="e_eliminar" value="Eliminar" onclick="{return confirmComplete();}">Eliminar</button>
    </form>
  </div>
</div>


<div id="slide4">
  <div class="content">
    <h1>Horarios</h1>
    <form action="edicion-eliminar" method="get">
    <select id="list_horarios" size=12 autofocus name="horarios">

      <%
        SimpleDateFormat formato= new SimpleDateFormat("h:mm a");

        while(it_h.hasNext()) {
          lista_h = it_h.next();
      %>
      <option value="<%= lista_h.getIdHorario()%>"><%=formato.format(lista_h.getEntrada())%> <%=formato.format(lista_h.getSalida())%></option>
      <%}%>
    </select>
      <button class="boton" onclick="{return confirmComplete();}">Eliminar</button>
      <button class="boton" type="button" onclick="alert('Desea editar este horario?')">Editar</button>
    </form>
  </div>
</div>


<div id="slide5">
  <div class="content">
    <h1>Metas</h1>
    <form action="edicion-eliminar" method="get">
    <select id="list_metas" size=12 autofocus name="metas">

      <%
        while(it_m.hasNext()) {
          lista_m = it_m.next();
      %>
      <option value="<%= lista_m.getMetasPK()%>"><%=lista_m.getCirujano().getNombres()%> <%=lista_m.getMetasPK().getAnio()%> <%=lista_m.getMetasPK().getMes()%> <%=lista_m.getMeta()%></option>
      <%}%>
    </select>
      <button class="boton" onclick="{return confirmComplete();}">Eliminar</button>
      <button class="boton" type="button" onclick="alert('Desea editar esta meta?')">Editar</button>
      </form>
  </div>
</div>

<div id="slide6">
  <div class="content">
    <h1>Quirofanos</h1>
    <form action="edicion-eliminar" method="get">
    <select id="list_quirofanos" size=12 autofocus name="quirofanos">

      <%
        while(it_q.hasNext()) {
          lista_q = it_q.next();
      %>
      <option value="<%= lista_q.getIdQuirofano()%>"><%=lista_q.getDescripcion()%></option>
      <%}%>
    </select>
      <button class="boton" name="q_editar" value="Editar"  onclick="alert('Desea editar este quirofano?')">Editar</button>
      <button class="boton"  name="q_eliminar" value="Eliminar" onclick="{return confirmComplete();}">Eliminar</button>


    </form>
  </div>
</div>

<div id="slide7">
  <div class="content">
    <h1>Riesgos</h1>
    <form action="edicion-eliminar" method="get">
    <select id="list_riesgos" size=12 autofocus name="riesgos">

      <%
        while(it_r.hasNext()) {
          lista_r = it_r.next();
      %>
      <option value="<%= lista_r.getIdRiesgo()%>"><%=lista_r.getNombre()%></option>
      <%}%>
    </select>
      <button class="boton" name="r_editar" value="Editar"  onclick="alert('Desea editar este riesgo?')">Editar</button>
      <button class="boton"  name="r_eliminar" value="Eliminar" onclick="{return confirmComplete();}">Eliminar</button>


    </form>
  </div>
</div>


<!----- SLIDES END --->


</body>
</html>
