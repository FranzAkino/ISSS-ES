<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 05-14-15
  Time: 04:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.persistencia.*" %>
<html>
<head>
  <link type="text/css" rel="stylesheet" href="/css/register_style.css" />
  <title></title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script>
    $(document).ready(function(){
      $("#toggle-login").click(function(){
        $("#login").toggle();
      });
//      document.getElementById('login').style.display='none';
    });

    function validar(){
        var texto = document.getElementById("pnombre");
        if (texto.length == 0){
            alert("Ingrese nombre");
            return false;
        }
        else return true;
    }

  </script>
</head>
<body>

<a href="${pageContext.request.contextPath}/" class="button_inicio" id="toggle-inicio">Inicio</a>

<span href="#" class="button" id="toggle-login">Registro</span>

<div id="login">
  <div id="triangle"></div>
  <h1>Registro</h1>
  <form action="respuesta" method="get">

    <%
      List<Cirujano> lista1= (List<Cirujano>)request.getAttribute("cirujanos");
      int num=lista1.size();
      Iterator<Cirujano> it=lista1.iterator();
      Cirujano lista;

      List<Cirujano> lista2= (List<Cirujano>)request.getAttribute("1ayudante");
//      int num=lista1.size();
      Iterator<Cirujano> it1=lista1.iterator();
      Cirujano ayudante_1;

      List<Cirujano> lista3= (List<Cirujano>)request.getAttribute("2ayudante");
//      int num=lista1.size();
      Iterator<Cirujano> it2=lista1.iterator();
      Cirujano ayudante_2;


        List<Cie9> lista_c= (List<Cie9>)request.getAttribute("cie9");
        Iterator<Cie9> it_cie9=lista_c.iterator();
        Cie9 l_cie9;

        List<Quirofano> lista_Q= (List<Quirofano>)request.getAttribute("quirofanos");
        Iterator<Quirofano> it_quirofano=lista_Q.iterator();
        Quirofano l_quirofano;

//        riesgo

        List<Riesgo> lista_riesgo= (List<Riesgo>)request.getAttribute("riesgo");
        Iterator<Riesgo> it_riesgo=lista_riesgo.iterator();
        Riesgo l_riesgo;

//        suspensiones
        List<Suspenciones> lista_suspenciones= (List<Suspenciones>)request.getAttribute("suspenciones");
        Iterator<Suspenciones> it_suspenciones=lista_suspenciones.iterator();
        Suspenciones l_suspenciones;



    %>


    <span>Fecha:</span>
    <input type="date" name="fecha" placeholder="Fecha" />



    <span>Paciente:</span>
    <input id="pnombre" type="text" name="Pnombre" placeholder="Nombre del Paciente">

      <input id="paciente" type="text" name="Papellido" placeholder="Apellido del Paciente">



    <span>Numero de afiliado:</span>
    <input id="afiliacion"type="text" name="ID" placeholder="Numero de Afiliado">

      <span>Edad:</span>
      <input id="edad "type="number" name="edad" placeholder="edad">

    <span>  Region op:</span>
    <input id="region" type="text" placeholder="Region" name="region" placeholder="Region de operacion">

    <br>
    <span>Sexo:</span>
    <input type="radio" id="gen1" name="sex" value="M"><label for="gen1" >Masculino</label>
    <input type="radio" id="gen2" name="sex"  value="F"><label for="gen2" >Femenino</label>

    <%--<br>--%>
    <span>Estado Civil:</span>
    <input type="radio" id="est1" name="estado" value="S"><label for="est1" >Soltero</label>
    <input type="radio" id="est2"name="estado" value="C"><label for="est2">Casado</label>
    <input type="radio" id="est3"name="estado" value="D"><label for="est3">Divorciado</label>
    <input type="radio" id="est4"name="estado" value="V"><label for="est4">Viudo</label>

    <hr>
    <span>Calidad de asegurado:</span>
    <input type="radio" id="cal1" name="cal" value="S"><label for="cal1" >S</label>
    <input type="radio" id="cal2" name="cal"  value="C"><label for="cal2" >C</label>

    <span>Riesgo:</span>
    <%--<input id="riesgo" type="text" name="riesgo" placeholder="Riesgo">--%>

      <select id="riesgo" name="riesgo" size=2 autofocus>

          <%
              while(it_riesgo.hasNext()) {
                  l_riesgo = it_riesgo.next();
          %>
          <option value="<%= l_riesgo.getIdRiesgo()%>"><%= l_riesgo.getNombre()%></option>
          <%}%>
      </select>

    <br>

    <span>Cirujano:</span>
    <%--<input id="cirujano" type="text" placeholder="Cirujano">--%>


    <select id="list_cirujanos" size=6 autofocus name="cirujanos">

      <%
        while(it.hasNext()) {
          lista = it.next();
      %>
      <option value="<%= lista.getIdCirujano()%>"><%= lista.getNombres()%> <%= lista.getApellidos()%></option>
      <%}%>
    </select>

    <span>Instrumentista:</span>
    <input id="instrumentista" name="instrumentista" type="text" placeholder="Instrumentista">

    <br>
    <span>1er Ayudante:</span>
    <select id="list_ayudante1" size=6 autofocus name="ayudante1">

      <%
        while(it1.hasNext()) {
          ayudante_1 = it1.next();
      %>
      <option value="<%= ayudante_1.getIdCirujano()%>"><%= ayudante_1.getNombres()%> <%= ayudante_1.getApellidos()%></option>
      <%}%>
    </select>

    <span>Circular:</span>
    <input id="circular" name="circular" type="text" placeholder="Circular">

    <br>
    <span>2do Ayudante:</span>
    <select id="list_ayudante2" size=6 autofocus name="ayudante2">

      <%
        while(it2.hasNext()) {
          ayudante_2 = it2.next();
      %>
      <option value="<%= ayudante_2.getIdCirujano()%>"><%= ayudante_2.getNombres()%> <%= ayudante_2.getApellidos()%></option>
      <%}%>
    </select>

    <span>Anestesiologo:</span>
    <input id="anestesiologo" name="anestesiologo" type="text" placeholder="Anestesiologo">

    <span>Anestesista:</span>
    <input id="anestesista" name="anestesista" type="text" placeholder="Anestesista">

    <span>Tipo de anestesia:</span>
      <input type="radio" name="anestecia" id="anestecia1" value="local"><label for="anestecia1" >Local</label>
      <input type="radio" name="anestecia" id="anestecia2" value="general"><label for="anestecia2" >General</label>

      <hr>

    <span>Hora de Inicio:</span>
    <input type="time" name="horaI" placeholder="Tiempo" />

    <span>Hora de Fin:</span>
    <input type="time" name="horaF" placeholder="Tiempo" />
        <br>

      <span>Emergencia:</span>
      <input type="radio" name="emer" id="radio1" value="1"><label for="radio1" >S I</label>
      <input type="radio" name="emer" id="radio2" value="0"><label for="radio2" >NO</label>

    <span>Diagnostico post-operatorio</span>
    <input id="diag" type="text" name="diag" placeholder="Diagnostico post-operatorio">

    <br>


      <span>CIE9:</span>
      <%--<input id="cie9" type="text" name="cie9" placeholder="Clase de intervencion">--%>
      <select id="list_cie9" name="list_cie9" size=6 autofocus>

          <%
              while(it_cie9.hasNext()) {
                  l_cie9 = it_cie9.next();
          %>
          <option value="<%=l_cie9.getIdProcedimiento()%>" ><%= l_cie9.getNombre()%></option>
          <%}%>
      </select>



      <span>Sala:</span>
      <%--<input id="sala" type="text" name="sala" placeholder="Sala de Operaciones">--%>
      <select id="sala" name="sala" size=6 autofocus>

          <%
              while(it_quirofano.hasNext()) {
                  l_quirofano = it_quirofano.next();
          %>
          <option value="<%=l_quirofano.getIdQuirofano()%>" ><%= l_quirofano.getDescripcion()%></option>
          <%}%>
      </select>

      <span>Realizada:</span>
      <input type="radio" name="realizada" id="realizada1" value="1"><label for="realizada1" >S I</label>
      <input type="radio" name="realizada" id="realizada2" value="0"><label for="realizada2" >NO</label>

      <span>Suspendida:</span>
      <%--<input id="suspencion" name="suspencion" type="text" placeholder="Suspencion">--%>
      <select id="suspencion" name="suspencion" size=6 autofocus>

          <%
              while(it_suspenciones.hasNext()) {
                  l_suspenciones = it_suspenciones.next();
          %>
          <option value="<%=l_suspenciones.getIdSuspenciones()%>" ><%= l_suspenciones.getCausa()%></option>
          <%}%>
      </select>



    <input type="submit" value="Registro" onclick="if(validar()){this.submit();}"/>
  </form>
</div>

</body>
</html>
