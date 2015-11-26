<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%

    // Grab the variables from the form.

    String nombre= request.getParameter("nombre_e");


    Especialidad nueva= new Especialidad(nombre);

    EntityManagerFactory emf= CreadorEntityManager.crearEMF();
    EspecialidadJpaController nuevo_espe = new EspecialidadJpaController(emf);
    nuevo_espe.create(nueva);

response.sendRedirect("Registros-informacion");

%>
<%-- Paciente --%>

<%--<%response.sendRedirect("Registros-informacion"); %>--%>



</body>


</html>