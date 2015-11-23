<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: akino
  Date: 05-07-15
  Time: 05:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="<c:url value="/css/home.css"/>" rel="stylesheet">
    <title>Sistema de Captación de Datos Estadisticos de la Sala de Operaciones | ISSS Santa Ana | isssEQ</title>
</head>
<body>


<div id="content-main">
    <div id="header-wrapper">
        <div id="header" class="container">
            <div id="logo">
                <h1><a href="#">ISSS</a></h1>
            </div>
            <div id="menu">
                <ul>
                    <li class="current_page_item"><a href="#" accesskey="1" title="">Inicio</a></li>
                    <li><a href="/historial" accesskey="2" title="">Historial</a></li>
                    <li><a href="/Registro" accesskey="3" title="">Registro</a></li>
                    <li><a href="/Registros-informacion" accesskey="4" title="">Registro 2</a></li>
                    <li><a href="/edicion" accesskey="5" title="">Edicion</a></li>
                    <li><a href="/estadisticas" accesskey="6" title="">Estadisticas</a></li>
                    <li><a href="/reportes" accesskey="7" title="">Reportes</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="content">

        <h2>SALA DE OPERACIONES | Hospital Regional ISSS Santa Ana</h2>
        <hr id="header-line"/>
        <h1>SISTEMA DE CAPTACION DE DATOS ESTADISTICOS</h1>

        <%--<img id="icon" src="css/media/hospitals3.png"/>--%>
        <h3 id="isss"><img id="line" src="css/media/line.png"> isssEQ<img id="line2" src="css/media/line.png"></h3>




        <div class="buttons">

            <a class="flask" href="${pageContext.request.contextPath}historial"><img src="css/media/folder.png" /></a>
            <a class="heartbeat" href="${pageContext.request.contextPath}Registro"><img src="css/media/menu.png" /></a>
            <a class="scissors" href="${pageContext.request.contextPath}Registros-informacion"><img src="css/media/usuarios.png" /></a>
            <a class="scissors" href="${pageContext.request.contextPath}edicion"><img src="css/media/edicion.png" /></a>
            <a class="scissors" href="${pageContext.request.contextPath}estadisticas"><img src="css/media/estadisticas.png" /></a>
            <a class="scissors" href="${pageContext.request.contextPath}reportes"><img src="css/media/reportes.png" /></a>

        </div>
    </div>


</div>

</body>
</html>
