<%@ page import="java.util.List" %>
<%@ page import="com.persistencia.Cirujia" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page import ="com.persistencia.exceptions.Coasa"%>
<html>
<head>
	<%--<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>--%>
	<%--<link type="text/css" rel="stylesheet" href="css/main.css"/>--%>
	<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>


<%
	/*Vaya así es
	hay que crear un objeto del tipo que se va a recibir y hacer el cast pidiendo
	el objeto con el identificador, en este caso message.
	*/

	Coasa cosa = (Coasa) request.getAttribute("message");

	//Esta variable se puede llamar desde afuera del scriptlet
	String texto = cosa.getCasdf();

%>

<div id="titulo">
	TITULO
</div>


	<a href="${pageContext.request.contextPath}registro">asdf</a>

	<%--asi se puede imprimir--%>
	<h1><%= texto%></h1>
	<h2><%= cosa.getCasdf()%></h2>


</body>
</html>