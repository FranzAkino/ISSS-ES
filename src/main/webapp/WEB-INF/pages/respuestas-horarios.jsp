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
    SimpleDateFormat formato= new SimpleDateFormat("hh:mm");

    Date entrada= formato.parse(request.getParameter("entrada")) ;
    Date salida= formato.parse(request.getParameter("salida")) ;

    Horario n_norario = new Horario(entrada,salida);

    EntityManagerFactory emf= CreadorEntityManager.crearEMF();
    HorarioJpaController nuevo_h= new HorarioJpaController(emf);
    nuevo_h.create(n_norario);

response.sendRedirect("Registros-informacion");

%>
<%-- Paciente --%>

<%--<%response.sendRedirect("Registros-informacion"); %>--%>



</body>


</html>