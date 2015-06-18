|<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: akino
  Date: 06-13-15
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.springapp.mvc.Graficos" %>
<html>
<head>
  <title>Estadisticas</title>
  <link type="text/css" rel="stylesheet" href="/css/register_style.css" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="<c:url value="/css/highcharts.js"/>"></script>


  <script>
    $(document).ready(function(){
      $("#toggle-login").click(function(){
        $("#login").toggle();
      });
      $("#toggle-Electiva").click(function(){
        $("#anual-mayor").toggle();
        $("#mensual-mayor").toggle();
      });
      $("#toggle-Menor-Emergencia").click(function(){
        $("#anual-menor").toggle();
        $("#mensual-menor").toggle();
      });
    });
  </script>






</head>
<body>
  <h2>Sala De Operaciones | Hospital Regional ISSS Santa Ana</h2>
  <h1>ESTADISTICAS</h1>
  <a href="${pageContext.request.contextPath}/" class="button_inicio" id="toggle-inicio">Inicio</a>
    <div href="#" class="button" id="toggle-Electiva">Cirujía Mayor Electiva</div>
    <div href="#" class="button-right" id="anual-mayor">Anuales</div>
    <div href="#" class="button-right" id="mensual-mayor">Mensuales</div>
    <div href="#" class="button" id="toggle-Menor-Emergencia">Cirujía Menor y Emergencia</div>
    <div href="#" class="button-right" id="anual-menor">Anuales</div>
    <div href="#" class="button-right" id="mensual-menor">Mensuales</div>
    <div href="#" class="button" id="toggle-Electiva-Emergencia">Cirujía Mayor Electiva y Emergencia</div>
    <div href="#" class="button" id="toggle-anuales">Cirujías anuales</div>
    <div href="#" class="button" id="toggle-suspendidas">Cirujias Suspendidas</div>

    <div id="grafico"></div>
  <script>
    var jsonChart = ${grafico};
    $(document).ready(function(){
      var chart = new Highcharts.Chart(jsonChart);
    });
  </script>
</body>
</html>
