<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 05-14-15
  Time: 04:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.persistencia.Cirujia" %>
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

      document.getElementById('login').style.display='none';
    });


  </script>
</head>
<body>

<a href="${pageContext.request.contextPath}/" class="button_inicio" id="toggle-inicio">Inicio</a>

<span href="#" class="button" id="toggle-login">Registro</span>

<div id="login">
  <div id="triangle"></div>
  <h1>Registro</h1>
  <form>
    <input type="date" placeholder="Fecha" />
    <input type="time" placeholder="Tiempo" />
    <input type="text" placeholder="Nombre del Paciente">
    <input type="text" placeholder="Numero de Afiliado">
    <br>
    <p>Sexo:</p>
    <input type="radio" id="gen1" name="sex" value="M"><label for="gen1" >Masculino</label>
    <input type="radio" id="gen2" name="sex"  value="F"><label for="gen2" >Femenino</label>

    <br>
    <p>Estado Civil:</p>
    <input type="radio" id="est1" name="estado" value="S"><label for="est1" >Soltero</label>
    <input type="radio" id="est2"name="estado" value="C"><label for="est2">Casado</label>
    <input type="radio" id="est3"name="estado" value="D"><label for="est3">Divorciado</label>
    <input type="radio" id="est4"name="estado" value="V"><label for="est4">Viudo</label>

    <hr>
    <input type="text" placeholder="Calidad de asegurado">
    <input type="text" placeholder="Riesgo">
    <input type="text" placeholder="Clase de intervencion">
    <input type="text" placeholder="Diagnostico post-operatorio">
    <input type="text" placeholder="Region" id="region">
    <br>
    <p>Emergencia:</p>
    <input type="radio" name="emer" id="radio1" value="S"><label for="radio1" >S I</label>
    <input type="radio" name="emer" id="radio2" value="N"><label for="radio2" >NO</label>
    <hr>
    <input type="text" placeholder="Anestesista">
    <input type="text" placeholder="Tipo de Anestesia">
    <input type="text" placeholder="Sala de Operaciones">
    <input type="text" placeholder="Cirujano">
    <input type="text" placeholder="Ayudante">



    <input type="submit" value="Registro" />
  </form>
</div>

</body>
</html>
