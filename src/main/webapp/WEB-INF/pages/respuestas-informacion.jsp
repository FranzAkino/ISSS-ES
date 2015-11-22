<%@ page import="java.nio.charset.Charset" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.*" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 06-15-15
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%

  // Grab the variables from the form.

   String fecha= request.getParameter("MES");
  String[] parts = fecha.split("-");
  String anyo = parts[0];
  String mes = parts[1];



  int meta =Integer.parseInt(request.getParameter("meta"));
  int cirujano =Integer.parseInt(request.getParameter("cirujanos")) ;
  int n_mes= Integer.parseInt(mes);
  int n_anyo= Integer.parseInt(anyo);

  Cirujano doc = new Cirujano(cirujano);

  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  MetasPK n_metaspk = new MetasPK(cirujano, n_mes, n_anyo);
  Metas n_meta2 = new Metas(cirujano, n_mes, n_anyo);
  n_meta2.setCirujano(doc);
  n_meta2.setMeta(meta);
  MetasJpaController nueva= new MetasJpaController(emf);
  nueva.create(n_meta2);



%>
<%-- Paciente --%>
<%--Metas <%= meta%>, ID del doctor <%= n_meta2.getCirujano().getIdCirujano()%> Fecha <%= n_mes%> <%= n_anyo%>--%>
<%response.sendRedirect("Registros-informacion"); %>



</body>


</html>