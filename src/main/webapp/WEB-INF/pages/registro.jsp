<%--
  Created by IntelliJ IDEA.
  User: akino
  Date: 04-23-15
  Time: 03:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Registro de Operaciones Quirurgicas</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>

<div id="titulo">
  <h1>${att}</h1>
</div>
<div id="correlativo">
  <div id="numero">Numero</div>
  <div id="paciente">
    <div id="uno">
      <div id="fecha">fecha</div>
      <div id="hora">hora</div>
      <div id="afiliacion">afiliacion</div>
    </div>
    <div id="nombre">nombre nombre</div>
    <div id="tres">
      <div id="sexo">sexo</div>
      <div id="edad">edad</div>
      <div id="estado_civil">EstCiv</div>
      <div id="calidad_asociado">calAso</div>
    </div>
  </div>

  <div id="cirujia">
    <div id="cuatro">
      <div id="riesgo">riesgo</div>
      <div id="clase_intervencion">c_intervencion</div>
    </div>
    <div id="cinco">
      <div id="diagnostico_post-ope">diagnostico_post-op</div>
    </div>
    <div id="seis">
      <div id="region">region</div>
      <div id="tipo_anestecia">tipo_anestecia</div>
    </div>
    <div id="cirujano">nombre_cirujano</div>
    <div id="ayudante">nombre_ayudante</div>
  </div>
</div>
<div id="menu_inferior">
  <%--<%@include file="menu_inferoir.jspf"%>--%>
</div>

</body>
</html>
