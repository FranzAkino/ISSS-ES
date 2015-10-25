<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="com.springapp.mvc.CreadorEntityManager" %>
<%@ page import="com.persistencia.*" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 10-18-15
  Time: 11:42 AM
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


//  int id_cirujano= Integer.parseInt(request.getParameter("cirujanos")) ;

  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  CirujanoJpaController eliminar_cirujano = new CirujanoJpaController(emf);
  Cie9JpaController eliminar_cie9 = new Cie9JpaController(emf);
  EspecialidadJpaController eliminar_especialidades = new EspecialidadJpaController(emf);
  HorarioJpaController eliminar_horarios = new HorarioJpaController(emf);
  MetasJpaController eliminar_metas= new MetasJpaController(emf);

  String id= request.getParameter("cirujanos");
  String id_cie9 = request.getParameter("procedimientos");
  String id_especialidad = request.getParameter("especialidad");
  String id_horarios = request.getParameter("horarios");
  String id_metas = request.getParameter("metas");


  if(id != null){
    int id_cirujano= Integer.parseInt(id);
    eliminar_cirujano.destroy(id_cirujano);
  }
  if(id_cie9 != null){
    int cie9= Integer.parseInt(id_cie9);
    eliminar_cie9.destroy(cie9);
  }
  if(id_especialidad != null){
    int especialidad= Integer.parseInt(id_especialidad);
    eliminar_especialidades.destroy(especialidad);
  }
  if(id_horarios != null){
    int horarios= Integer.parseInt(id_horarios);
    eliminar_horarios.destroy(horarios);
  }
  if(id_metas != null){
    int metas= Integer.parseInt(id_metas);

  }




  response.sendRedirect("edicion");


%>
</body>
</html>
