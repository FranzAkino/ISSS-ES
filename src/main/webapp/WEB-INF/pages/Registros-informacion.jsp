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
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
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

<div id="content-main">
  <span href="#" class="button2" id="toggle-login2">Cirujano</span>

  <div id="cirujano">
    <div id="triangle2"></div>
    <h1>Log in</h1>
    <form>
      <input type="text" placeholder="Nombre" />
      <input type="text" placeholder="Apellido" />
      <input type="text" placeholder="Especialidad" />
      <input type="text" placeholder="Horario" />
      <input type="number" id="metac" placeholder="Meta" min="1" />
      <br>
      <p>Estado:</p>
      <input type="radio" id="est1" name="estado" value="A"><label for="est1" >Activo</label>
      <input type="radio" id="est2" name="estado"  value="I"><label for="est2" >Inactivo</label>
      <input type="submit" id="guargarc" value="Guardar" />
    </form>
  </div>

  <%--Seccion de CIE9--%>
  <span href="#" class="button3" id="toggle-login3">CIE9</span>

  <div id="CIE9">
    <div id="triangle3"></div>
    <h1>CIE9</h1>
    <form>
      <input type="text" placeholder="Nombre" />
      <input type="text" placeholder="Descripcion" />
      <input type="submit" id="gCIE9" value="Guardar" />
    </form>
  </div>

  <%--Seccion de ESPECIALIDAD--%>
  <span href="#" class="button4" id="toggle-login4">Especialidad</span>

  <div id="especialidad">
    <div id="triangle4"></div>
    <h1>ESPECIALIDAD</h1>
    <form>
      <input type="text" placeholder="Nombre" />
      <input type="text" placeholder="Descripcion" />
      <input type="submit" id="Gespe" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Horario--%>
  <span href="#" class="button5" id="toggle-login5">HORARIO</span>

  <div id="horario">
    <div id="triangle5"></div>
    <h1>HORARIO</h1>
    <form>
      <input type="time" placeholder="Hora de Entrada" />
      <input type="time" placeholder="Hora de Salida" />
      <input type="submit" id="Ghorario" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Meta--%>
  <span href="#" class="button6" id="toggle-login6">METAS</span>

  <div id="metas">
    <div id="triangle6"></div>
    <h1>METAS</h1>
    <form>
      <input type="month" placeholder="MES" />
      <input type="number" placeholder="Meta" min="1" />
      <input type="text" placeholder="Cirujano" />
      <input type="submit" id="GMeta" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Quirofanos--%>
  <span href="#" class="button7" id="toggle-login7">QUIROFANOS</span>

  <div id="quirofanos">
    <div id="triangle7"></div>
    <h1>QUIROFANOS</h1>
    <form>
      <input type="number" placeholder="Numero de Quirofano" min="1" />
      <input type="text" placeholder="Descripcion" />

      <input type="submit" id="GQuirofano" value="Guardar" />
    </form>
  </div>

  <%--Seccion de Riesgo--%>
  <span href="#" class="button8" id="toggle-login8">RIESGO</span>

  <div id="riesgo">
    <div id="triangle8"></div>
    <h1>RIESGO</h1>
    <form>

      <input type="text" placeholder="Tipo de Riesgo" />

      <input type="submit" id="GRiesgo" value="Guardar" />
    </form>
  </div>
</div>
</body>
</html>
