<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.persistencia.Cirujano" %>
<%@ page import="com.persistencia.CirujanoJpaController" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.Especialidad" %>
<%@ page import="com.persistencia.Horario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%

    // Grab the variables from the form.

    String nombre= request.getParameter("c_nombre");
    String apellido= request.getParameter("c_apellido");
    int especialidad =Integer.parseInt(request.getParameter("especialidades"));
    int horario =Integer.parseInt(request.getParameter("horarios")) ;
    int estado= Integer.parseInt(request.getParameter("estado"));


    Especialidad nueva= new Especialidad(especialidad);
    Horario nuevo= new Horario(horario);
    Cirujano doc = new Cirujano(nombre, apellido, estado);
    doc.setFkidEspecialidad(nueva);
    doc.setFkHorarios(nuevo);

    EntityManagerFactory emf= CreadorEntityManager.crearEMF();
    CirujanoJpaController nuevo_cirujano = new CirujanoJpaController(emf);
    nuevo_cirujano.create(doc);

response.sendRedirect("Registros-informacion");

%>
<%-- Paciente --%>

<%--<%response.sendRedirect("Registros-informacion"); %>--%>



</body>


</html>