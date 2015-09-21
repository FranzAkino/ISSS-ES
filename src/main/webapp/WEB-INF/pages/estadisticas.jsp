<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: akino
  Date: 06-13-15
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.springapp.mvc.TipoGraficos" %>

<head>
  <title>Estadisticas</title>
  <link type="text/css" rel="stylesheet" href="/css/register_style_est.css" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="<c:url value="/css/highcharts.js"/>"></script>
  <script src="<c:url value="/css/exporting.js"/>"></script>
  <script src="<c:url value="/css/themes/sand-signika.js"/>"></script>


  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

  <script>
    $(function() {
      $( "#accordion" ).accordion({
        active: false,
        collapsible: true,
        heightStyle: "content"
      });
    });
    $(function() {
      $( "#mayor-acordeon" ).accordion({
        active: false,
        collapsible: true,
        heightStyle: "content"
      });
    });
    $(function() {
      $( "#mayor-y-m-acordeon" ).accordion({
        active: false,
        collapsible: true,
        heightStyle: "content"
      });
    });
    $(function() {
      $( "#menor-acordeon" ).accordion({
        active: false,
        collapsible: true,
        heightStyle: "content"
      });
    });
    $(function() {
      $( "#menor-acordeon-eme" ).accordion({
        active: false,
        collapsible: true,
        heightStyle: "content"
      });
    });
    $(function() {
      $( "#suspendidas-acordeon" ).accordion({
        active: false,
        collapsible: true,
        heightStyle: "content"
      });
    });
    $(function() {
      $( "#quirofano-acordeon" ).accordion({
        active: false,
        collapsible: true,
        heightStyle: "content"
      });



    });
  </script>
</head>
<body>
  <h2>Sala De Operaciones | Hospital Regional ISSS Santa Ana</h2>
  <h1>ESTADISTICAS</h1>


  <%--<a href="${pageContext.request.contextPath}/" class="button_inicio" id="toggle-inicio">Inicio</a>--%>


  <table style="width: 100%">
    <tr>
      <td style="width: 15%">
        <a href="/" class="button_inicio">Inicio</a>
        <div id="accordion">

          <%--------------------------------
                        MAYOR
          ----------------------------------%>
          <h3 class="button">Cirujía Mayor Electiva</h3>
          <div id="mayor-acordeon" style="height: inherit">
            <h4 class="button-right">Anual</h4>
            <div>
              <form action="/estadisticas/graficos" method="post" class="formula">
                <input type="number" name="anio" placeholder="Año" min="2015" />
                <input type="number" name="mes" value="0" hidden="hidden" />
                <input type="number" name="tipo" value="1" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
            <h4 class="button-right">Mensual</h4>
            <div>
              <form action="/estadisticas/graficos" method="post">
                <input type="number" name="mes" placeholder="Mes" min="1" max="12" />
                <input type="number" name="anio"placeholder="Año" min="2014" />
                <input type="number" name="tipo" value="1" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
          </div>
          <%--------------------------------
                      MAYOR Y EMERGENCIA
          ----------------------------------%>
            <h3 class="button">Cirujía Mayor Y Emergencia</h3>
            <div id="mayor-y-m-acordeon" style="height: inherit">
              <h4 class="button-right">Anual</h4>
              <div>
                <form action="/estadisticas/graficos" method="post" class="formula">
                  <input type="number" name="anio" placeholder="Año" min="2015" />
                  <input type="number" name="mes" value="0" hidden="hidden" />
                  <input type="number" name="tipo" value="9" hidden="hidden" />
                  <input type="submit" value="Dibujar" />
                </form>
              </div>
              <h4 class="button-right">Mensual</h4>
              <div>
                <form action="/estadisticas/graficos" method="post">
                  <input type="number" name="mes" placeholder="Mes" min="1" max="12" />
                  <input type="number" name="anio"placeholder="Año" min="2014" />
                  <input type="number" name="tipo" value="9" hidden="hidden" />
                  <input type="submit" value="Dibujar" />
                </form>
              </div>
            </div>



            <%---------------------------------------------------------
                        MENOR ELECTIVA
            ----------------------------------------------------------%>
            <h3 class="button">Cirujía Menor Electiva</h3>
            <div id="menor-acordeon">
              <h4 class="button-right">Anual</h4>
              <div>
                <form action="/estadisticas/graficos" method="post" class="formula">
                  <input type="number" name="anio" placeholder="Año" min="2014" />
                  <input type="number" name="mes" value="0" hidden="hidden" />
                  <input type="number" name="tipo" value="2" hidden="hidden" />
                  <input type="submit" value="Dibujar" />
                </form>
              </div>
              <h4 class="button-right">Mensual</h4>
              <div>
                <form action="/estadisticas/graficos" method="post">
                  <input type="number" name="mes" placeholder="Mes" min="1" max="12" />
                  <input type="number" name="anio" placeholder="Año" min="2014" />
                  <input type="number" name="tipo" value="2" hidden="hidden" />
                  <input type="submit" value="Dibujar" />
                </form>
              </div>
            </div>


            <%---------------------------------------------------------
                        MENOR Y EMERGENCIA
            ----------------------------------------------------------%>
          <h3 class="button">Cirujía Menor y Emergencia</h3>
          <div id="menor-acordeon-eme">
            <h4 class="button-right">Anual</h4>
            <div>
              <form action="/estadisticas/graficos" method="post" class="formula">
                <input type="number" name="anio" placeholder="Año" min="2014" />
                <input type="number" name="mes" value="0" hidden="hidden" />
                <input type="number" name="tipo" value="3" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
            <h4 class="button-right">Mensual</h4>
            <div>
              <form action="/estadisticas/graficos" method="post">
                <input type="number" name="mes" placeholder="Mes" min="1" max="12" />
                <input type="number" name="anio" placeholder="Año" min="2014" />
                <input type="number" name="tipo" value="3" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
          </div>



            <%---------------------------------------------------------
                                CIRUJIAS ANUALES
            ----------------------------------------------------------%>
          <h3 class="button">Cirujías anuales</h3>
          <div>
            <form action="/estadisticas/graficos" class="formula" method="post">
              <input type="number" name="anio" value="0" placeholder="Año" hidden="hidden"/>
              <input type="number" name="mes" value="0" hidden="hidden" />
              <input type="number" name="tipo" value="6" hidden="hidden" />
              <input type="submit" value="Dibujar" />
            </form>
          </div>

            <%---------------------------------------------------------
                                CIRUJIAS ANUALES POR ESPECIALIDAD
            ----------------------------------------------------------%>
            <h3 class="button">Cirujías anuales por especialidad</h3>
            <div>
              <form action="/estadisticas/graficos" class="formula" method="post">
                <input type="number" name="anio" min="2014"/>
                <input type="number" name="mes" value="0" hidden="hidden" />
                <input type="number" name="tipo" value="8" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
            <%---------------------------------------------------------
                        CIRUJIAS SUSPENDIDAS
            ----------------------------------------------------------%>

          <h3 class="button">Suspendidas</h3>
          <div id="suspendidas-acordeon">
            <h4 class="button-right">Anual</h4>
            <div>
              <form action="/estadisticas/graficos" method="post" class="formula">
                <input type="number" name="anio" placeholder="Año" min="2014" />
                <input type="number" name="mes" value="0" hidden="hidden" />
                <input type="number" name="tipo" value="4" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
            <h4 class="button-right">Mensual</h4>
            <div>
              <form action="/estadisticas/graficos" method="post" class="formula">
                <input type="number" name="mes"placeholder="Mes" min="1" max="12" />
                <input type="number" name="anio" placeholder="Año" min="2014" />
                <input type="number" name="tipo" value="4" hidden="hidden" />
                <input type="submit" value="Dibujar" onclick="doAjaxPost();"/>
              </form>
            </div>
          </div>


            <%---------------------------------------------------------
                        CIRUJIAS POR QUIROFANO
            ----------------------------------------------------------%>
          <h3 class="button">Por Quirofano</h3>
          <div id="quirofano-acordeon">
            <h4 class="button-right">Anual</h4>
            <div>
              <form action="/estadisticas/graficos" class="formula" method="post">
                <input type="number" name="anio" placeholder="Año" min="2014" />
                <input type="number" name="mes" value="0" hidden="hidden" />
                <input type="number" name="tipo" value="7" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
            <h4 class="button-right">Mensual</h4>
            <div>
              <form action="/estadisticas/graficos" class="formula" method="post">
                <input type="number" name="mes"placeholder="Mes" min="1" max="12" />
                <input type="number" name="anio" placeholder="Año" min="2014" />
                <input type="number" name="tipo" value="7" hidden="hidden" />
                <input type="submit" value="Dibujar" />
              </form>
            </div>
          </div>
        </div>
      </td>
      <td style="width: 75%">
        <div id="grafico">

        </div>
      </td>
    </tr>
  </table>
  <script>
    var jsonChart = ${grafico};
    $(document).ready(function(){
      var chart = new Highcharts.Chart(jsonChart);
    });
  </script>
</body>
</html>