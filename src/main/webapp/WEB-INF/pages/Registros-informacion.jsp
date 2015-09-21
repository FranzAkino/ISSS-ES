<%@ page import="com.persistencia.Cirujano" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.persistencia.Especialidad" %>
<%@ page import="com.persistencia.Horario" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 05-17-15
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link type="text/css" rel="stylesheet" href="/css/register_info_style.css" />
  <title></title>
  <%--<script src="${pageContext.request.contextPath}/css/http_ajax.googleapis.com_ajax_libs_jquery_1.11.2_jquery.js"></script>--%>
  <script src="<c:url value="/css/http_ajax.googleapis.com_ajax_libs_jquery_1.7.1_jquery.js"/>"></script>
  <script>
    $(document).ready(function(){


      $("#toggle-login2").click(function(){
        $("#cirujano").toggle();
      });

      $("#toggle-login3").click(function(){
        $("#CIE9").toggle();
      });

      $("#toggle-login4").click(function(){
        $("#especialidad").toggle();
      });

      $("#toggle-login5").click(function(){
        $("#horario").toggle();
      });

      $("#toggle-login6").click(function(){
        $("#metas").toggle();
      });

      $("#toggle-login7").click(function(){
        $("#quirofanos").toggle();
      });

      $("#toggle-login8").click(function(){
        $("#riesgo").toggle();
      });


      document.getElementById('cirujano').style.display='none';
      document.getElementById('CIE9').style.display='none';
      document.getElementById('especialidad').style.display='none';
      document.getElementById('horario').style.display='none';
      document.getElementById('metas').style.display='none';
      document.getElementById('quirofanos').style.display='none';
      document.getElementById('riesgo').style.display='none';
    });
  </script>
</head>

<body>
<%
  List<Cirujano> lista1= (List<Cirujano>)request.getAttribute("cirujanos");
  int num=lista1.size();
  Iterator<Cirujano> it=lista1.iterator();
  Cirujano lista;

  List<Especialidad> lista_e= (List<Especialidad>)request.getAttribute("especialidades");
  Iterator<Especialidad> it_e=lista_e.iterator();
  Especialidad lista_especialidad;

  List<Horario> lista_h= (List<Horario>)request.getAttribute("horarios");
  Iterator<Horario> it_h=lista_h.iterator();
  Horario lista_horario;


%>

<div id="content-main">

  <a href="${pageContext.request.contextPath}/" class="button_inicio" id="toggle-inicio">Inicio</a>

  <span href="#" class="button2" id="toggle-login2">Cirujano</span>

  <div id="cirujano">
    <div id="triangle2"></div>
    <h1>Cirujano</h1>
    <form action="respuestas-cirujano" method="get">
      <input type="text" placeholder="Nombre" id="c_nombre" name="c_nombre" />
      <input type="text" placeholder="Apellido" id="c_apellido" name="c_apellido" />
      <%--<input type="text" placeholder="Especialidad" />--%>
      <select id="list_especialidad" size=6 autofocus name="especialidades">

        <%
          while(it_e.hasNext()) {
            lista_especialidad = it_e.next();
        %>
        <option value="<%= lista_especialidad.getIdEspecialidad()%>"><%= lista_especialidad.getEspecialidad()%></option>
        <%}%>
      </select>
      <%--<input type="text" placeholder="Horario" />--%>
      <select id="list_horario" size=6 autofocus name="horarios">

        <%
          SimpleDateFormat formato= new SimpleDateFormat("h:mm a");

          while(it_h.hasNext()) {
            lista_horario = it_h.next();
        %>
        <option value="<%= lista_horario.getIdHorario()%>"><%= formato.format(lista_horario.getEntrada())%> a <%= formato.format(lista_horario.getSalida()) %></option>
        <%}%>
      </select>
       <br>
      <span>Estado:</span>
      <input type="radio" id="est1" name="estado" value="1"><label for="est1" >Activo</label>
      <input type="radio" id="est2" name="estado"  value="0"><label for="est2" >Inactivo</label>
      <input type="submit" id="guargarc" value="Guardar" />
    </form>
  </div>

  <%--Seccion de CIE9--%>
  <span href="#" class="button3" id="toggle-login3">Procedimientos</span>

  <div id="CIE9">
    <div id="triangle3"></div>
    <h1>Procedimientos Frecuentes</h1>
    <form action="respuestas-cie9" method="get">
      <input type="text" placeholder="Nombre" name="c_nombre" id="c_nombre"/>
      <input type="text" placeholder="Descripcion" name="c_descripcion" id="c_descripcion" />
      <input type="submit" id="gCIE9" value="Guardar" />
    </form>
  </div>

  <%--Seccion de ESPECIALIDAD--%>
  <span href="#" class="button4" id="toggle-login4">Especialidad</span>

  <div id="especialidad">
    <div id="triangle4"></div>
    <h1>ESPECIALIDAD</h1>
    <form action="respuestas-especialidad" method="get" >
      <input type="text" placeholder="Nombre" name="nombre_e" id="nombre_e" />
      <input type="text" placeholder="Descripcion" />
      <input type="submit" id="Gespe" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Horario--%>
  <span href="#" class="button5" id="toggle-login5">HORARIO</span>

  <div id="horario">
    <div id="triangle5"></div>
    <h1>Horario Quirurjico</h1>
    <form action="respuestas-horarios" method="get" >
      <input type="time" placeholder="Hora de Entrada" name="entrada" id="entrada"/>
      <input type="time" placeholder="Hora de Salida" name="salida" id="salida"/>
      <input type="submit" id="Ghorario" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Meta--%>
  <span href="#" class="button6" id="toggle-login6">METAS</span>

  <div id="metas">
    <div id="triangle6"></div>
    <h1>Metas Mensuales</h1>
    <form action="respuestas-informacion" method="get">
      <input type="month" name="MES" id="MES" />
      <input type="number" placeholder="Meta" min="1" id="meta" name="meta"/>
      <span>Cirujanos:</span>
      <%--<input type="text" placeholder="Cirujano" />--%>
      <select id="list_cirujanos" size=6 autofocus name="cirujanos">

        <%
          while(it.hasNext()) {
            lista = it.next();
        %>
        <option value="<%= lista.getIdCirujano()%>"><%= lista.getNombres()%> <%= lista.getApellidos()%></option>
        <%}%>
      </select>

      <input type="submit" id="GMeta" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Quirofanos--%>
  <span href="#" class="button7" id="toggle-login7">QUIROFANOS</span>

  <div id="quirofanos">
    <div id="triangle7"></div>
    <h1>QUIROFANOS</h1>
    <form action="respuestas-quirofanos" method="get">
      <input type="number" placeholder="Numero de Quirofano" min="1" name="numero" id="numero"/>
      <input type="text" placeholder="Descripcion" name="descripcion" id="descripcion"/>

      <input type="submit" id="GQuirofano" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Riesgo--%>
  <span href="#" class="button8" id="toggle-login8">RIESGO</span>

  <div id="riesgo">
    <div id="triangle8"></div>
    <h1>RIESGO</h1>
    <form  action="respuestas-riesgo" method="get">

      <input type="text" placeholder="Tipo de Riesgo" id="nombre" name="nombre"/>

      <input type="submit" id="GRiesgo" value="Guardar" />
    </form>
  </div>
</div>
</body>
</html>
