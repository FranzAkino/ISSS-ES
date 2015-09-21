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

  SimpleDateFormat formato= new SimpleDateFormat("YYYY-MM-DD");

  Date fecha = formato.parse(request.getParameter("fecha")) ;
  String pnombre = request.getParameter("Pnombre");
  String papellido = request.getParameter("Papellido");
  int ID =Integer.parseInt(request.getParameter("ID")) ;
  int edad = Integer.parseInt(request.getParameter("edad")) ;
  String region = request.getParameter("region");
  String sex =  request.getParameter("sex");
  char sexo= sex.charAt(0);
  String estado = request.getParameter("estado");
  char estado_civ=estado.charAt(0);
  String calidad = request.getParameter("cal");
  int riesgo =Integer.parseInt(request.getParameter("riesgo"));



  int id_cie9 =Integer.parseInt(request.getParameter("list_cie9"));
  String diagnostico = request.getParameter("diag");
  String tipo_anestecia = request.getParameter("anestecia");
  int quirofano =Integer.parseInt(request.getParameter("sala"));
  String anestesista = request.getParameter("anestesista");
  String instrumentista = request.getParameter("instrumentista");
  String circular = request.getParameter("circular");
  int emergencia =Integer.parseInt(request.getParameter("emer")) ;
  int realizada = Integer.parseInt(request.getParameter("realizada")) ;
  int suspendida;
  if(realizada==1){

    suspendida=1;
  }
  else {
    suspendida =Integer.parseInt(request.getParameter("suspencion"));
  }

  String anestesiologo = request.getParameter("anestesiologo");

  SimpleDateFormat formato_hora= new SimpleDateFormat("hh:mm");
  Date horaI =formato_hora.parse(request.getParameter("horaI"));
  Date horaF =formato_hora.parse(request.getParameter("horaF"));

  int ID_cirujia=0;//Se debe obtener el ultimo id

  EntityManagerFactory emf= CreadorEntityManager.crearEMF();
  CirujiaJpaController id=new CirujiaJpaController(emf);

  ID_cirujia= id.getCirujiaCount()+1;




//  Paciente
//  ID,pnombre, papellido, edad, sex, estado, calidad;

//  Cirujia
//  fecha, ID, id_cie9, diagnostico,region,tipo_anestecia,quirofano, anestesista,instrumentista,
//          circular,emergencia, riesgo,realizada, suspendida, anestesiologo,horaI, horaF;

//CirujanoCirujia
//  ID_cirujia, id_Cirujano, id_ayudante, id_ayudante2

  Paciente nuevo_paciente= new Paciente(ID,pnombre,papellido,edad,sexo,estado_civ,calidad);
  Cie9 nuevo_cie9=new Cie9(id_cie9);
  Quirofano nuevo_quirofano= new Quirofano(quirofano);
  Riesgo nuevo_riesgo=new Riesgo(riesgo);
  Suspenciones nuevo_suspencion= new Suspenciones(suspendida);


  Cirujia nueva_cirujia= new Cirujia(ID_cirujia,fecha, nuevo_paciente, nuevo_cie9, diagnostico, region, tipo_anestecia,
          nuevo_quirofano,anestesista, instrumentista, circular, emergencia,nuevo_riesgo, realizada,nuevo_suspencion,
          anestesiologo, horaI, horaF );

  PacienteJpaController nuevo_p= new PacienteJpaController(emf);
  nuevo_p.create(nuevo_paciente);


  CirujiaJpaController nueva_c= new CirujiaJpaController(emf);
  nueva_c.create(nueva_cirujia);

//  Contar si estan 3 doctores y en base a eso hacer 3 registros o 2 segun sea el caso e indicar que el primero es el titular

  int cirujano =Integer.parseInt(request.getParameter("cirujanos")) ;
//  int ayudante1 =Integer.parseInt(request.getParameter("ayudante1")) ;
//  int ayudante2 =Integer.parseInt(request.getParameter("ayudante2")) ;

  int ayudante1=0;
  int ayudante2=0;

  if(request.getParameter("ayudante1") == null){
    ayudante1=0;
  }
  else{
    ayudante1 =Integer.parseInt(request.getParameter("ayudante1")) ;
  }
  if(request.getParameter("ayudante2") == null){
    ayudante2=0;
  }
  else{
    ayudante2 =Integer.parseInt(request.getParameter("ayudante2")) ;
  }
  Cirujano n_cirujano= new Cirujano(cirujano);
  Cirujano n_ayudante1= new Cirujano(ayudante1);
  Cirujano n_ayudante2= new Cirujano(ayudante2);

  CirujanoCirujiaJpaController nuevo_cirujano_cirujia=new CirujanoCirujiaJpaController(emf);


  if(ayudante1==0 && ayudante2==0){
    CirujanoCirujia nuevo_cc= new CirujanoCirujia(nueva_cirujia, n_cirujano, 1);
    nuevo_cirujano_cirujia.create(nuevo_cc);
  }
  if(ayudante1>0 && ayudante2==0){
    CirujanoCirujia nuevo_cc= new CirujanoCirujia(nueva_cirujia, n_cirujano, 1);
    CirujanoCirujia nuevo_cc2= new CirujanoCirujia(nueva_cirujia, n_ayudante1, 0);

    nuevo_cirujano_cirujia.create(nuevo_cc);
    nuevo_cirujano_cirujia.create(nuevo_cc2);
  }
  if(ayudante1>0 && ayudante2>0){
    CirujanoCirujia nuevo_cc= new CirujanoCirujia(nueva_cirujia, n_cirujano, 1);
    CirujanoCirujia nuevo_cc2= new CirujanoCirujia(nueva_cirujia, n_ayudante1, 0);
    CirujanoCirujia nuevo_cc3= new CirujanoCirujia(nueva_cirujia, n_ayudante2, 0);

    nuevo_cirujano_cirujia.create(nuevo_cc);
    nuevo_cirujano_cirujia.create(nuevo_cc2);
    nuevo_cirujano_cirujia.create(nuevo_cc3);
  }



%>
<%-- Paciente --%>


Numero de cirujano <%= cirujano%>,<%= ayudante1%>, <%= ayudante2%>
<%response.sendRedirect("Registro"); %>

</body>


</html>