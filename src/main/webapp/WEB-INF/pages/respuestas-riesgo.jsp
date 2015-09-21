<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%

    // Grab the variables from the form.
   String nombre= request.getParameter("nombre");

    Riesgo nuevo_r = new Riesgo(nombre);
    EntityManagerFactory emf= CreadorEntityManager.crearEMF();
    RiesgoJpaController nuevo= new RiesgoJpaController(emf);
    nuevo.create(nuevo_r);

    response.sendRedirect("Registros-informacion");

%>
<%-- Paciente --%>

<%--<%response.sendRedirect("Registros-informacion"); %>--%>



</body>


</html>