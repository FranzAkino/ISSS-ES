<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.*" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 11-22-15
  Time: 09:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  CirujanoJpaController cirujano = new CirujanoJpaController(emf);

  int id_c = Integer.parseInt(request.getParameter("id_c"));

  HorarioJpaController horario = new HorarioJpaController(emf);
  Horario edi_horario = horario.findHorario(Integer.parseInt(request.getParameter("horarios")));

  EspecialidadJpaController especialidad = new EspecialidadJpaController(emf);
  Especialidad edi_especialidad = especialidad.findEspecialidad(Integer.parseInt(request.getParameter("especialidades")));


  Cirujano editado= cirujano.findCirujano(id_c);
  editado.setNombres(request.getParameter("c_nombre"));
  editado.setApellidos(request.getParameter("c_apellido"));
  editado.setFkHorarios(edi_horario);
  editado.setActivo(Integer.parseInt(request.getParameter("estado")));
  editado.setFkidEspecialidad(edi_especialidad);

  cirujano.edit(editado);
  response.sendRedirect("/edicion");

%>
</body>
</html>
