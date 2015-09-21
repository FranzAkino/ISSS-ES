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
    int numero = Integer.parseInt(request.getParameter("numero"));
    String comenr= request.getParameter("descripcion");

    Quirofano nuevo= new Quirofano(numero);
    nuevo.setDescripcion(comenr);

    EntityManagerFactory emf= CreadorEntityManager.crearEMF();
    QuirofanoJpaController nuevo_q= new QuirofanoJpaController(emf);
    nuevo_q.create(nuevo);

    response.sendRedirect("Registros-informacion");

%>
<%-- Paciente --%>

<%--<%response.sendRedirect("Registros-informacion"); %>--%>



</body>


</html>