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

    String nombre= request.getParameter("c_nombre");
    String descripcion= request.getParameter("c_descripcion");

    Cie9 nueva= new Cie9(nombre,descripcion);

    EntityManagerFactory emf= CreadorEntityManager.crearEMF();
    Cie9JpaController nuevo_cie9 = new Cie9JpaController(emf);
    int id=nuevo_cie9.getCie9Count()+1;

    nuevo_cie9.create(nueva);

response.sendRedirect("Registros-informacion");

%>
<%-- Paciente --%>

<%--<%response.sendRedirect("Registros-informacion"); %>--%>



</body>


</html>