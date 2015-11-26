package com.springapp.mvc;

import com.dtos.CirujanoMetasDTO;
import com.persistencia.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
//@RequestMapping("/")
public class HelloController {

    EntityManagerFactory emf = CreadorEntityManager.crearEMF();

	/*@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        Coasa asdf = new Coasa();
		model.addAttribute("message",asdf);
		return "hello";
	}*/

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("index","index");
        return "index";
    }

    @RequestMapping(value = "/historial", method = RequestMethod.GET)
    public String historial(ModelMap model) {

        CirujiaJpaController cirujiaJpaController = new CirujiaJpaController(emf);
        List<Cirujia> lista = cirujiaJpaController.findCirujiaEntities();

        model.addAttribute("listita",lista);
        return "historial";
    }

    @RequestMapping(value = "/Registro", method = RequestMethod.GET)
    public String registro(ModelMap model){

        CirujanoJpaController cirujanoJpaController= new CirujanoJpaController(emf);
//        TypedQuery<Cirujano> query=emf.
//        Aca se da el error a veces en el controller
//        otro intentod
        List<Cirujano> lista_cirujanos= cirujanoJpaController.getActivos();
        List<Cirujano> lista_cirujanos1= cirujanoJpaController.getActivos();
        List<Cirujano> lista_cirujanos2= cirujanoJpaController.getActivos();
        model.addAttribute("cirujanos",lista_cirujanos);
        model.addAttribute("1ayudante",lista_cirujanos1);
        model.addAttribute("2ayudante",lista_cirujanos2);

        Cie9JpaController cie9JpaController = new Cie9JpaController(emf);
        List<Cie9> lista_cie9 = cie9JpaController.findCie9Entities();
        model.addAttribute("cie9",lista_cie9);

        QuirofanoJpaController quirofanoJpaController = new QuirofanoJpaController(emf);
        List<Quirofano> lista_quirofano = quirofanoJpaController.findQuirofanoEntities();
        model.addAttribute("quirofanos",lista_quirofano);

        RiesgoJpaController riesgoJpaController = new RiesgoJpaController(emf);
        List<Riesgo> lista_riesgo = riesgoJpaController.findRiesgoEntities();
        model.addAttribute("riesgo",lista_riesgo);

        SuspencionesJpaController suspencionesJpaController = new SuspencionesJpaController(emf);
        List<Suspenciones> lista_suspensiones = suspencionesJpaController.findSuspencionesEntities(16,1);
        model.addAttribute("suspenciones",lista_suspensiones);

        return "Registro";
    }

    @RequestMapping(value = "/respuesta", method = RequestMethod.GET)
    public String respuesta(ModelMap model){

        model.addAttribute("asxx","asxx");
                return "respuesta";
    }

    @RequestMapping(value = "/Registros-informacion", method = RequestMethod.GET)
    public String registros_informacion(ModelMap model){

        CirujanoJpaController cirujanoJpaController= new CirujanoJpaController(emf);
        List<Cirujano> lista_cirujanos= cirujanoJpaController.getActivos();
        model.addAttribute("cirujanos",lista_cirujanos);

        EspecialidadJpaController especialidadJpaController= new EspecialidadJpaController(emf);
        List<Especialidad> lista_especialidad= especialidadJpaController.findEspecialidadEntities();
        model.addAttribute("especialidades",lista_especialidad);

        HorarioJpaController horarioJpaController= new HorarioJpaController(emf);
        List<Horario> lista_horarios= horarioJpaController.findHorarioEntities();
        model.addAttribute("horarios",lista_horarios);
        return "Registros-informacion";
    }

    @RequestMapping(value = "/respuestas-informacion", method = RequestMethod.GET)
    public String respuestas_informacion(ModelMap model){

        model.addAttribute("add","add");
        return "respuestas-informacion";
    }

    @RequestMapping(value = "/respuestas-cirujano", method = RequestMethod.GET)
    public String respuestas_cirujano(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "respuestas-cirujano";
    }

    @RequestMapping(value = "/respuestas-cie9", method = RequestMethod.GET)
    public String respuestas_cie9(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "respuestas-cie9";
    }

    @RequestMapping(value = "/respuestas-especialidad", method = RequestMethod.GET)
    public String respuestas_especialidad(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "respuestas-especialidad";
    }

    @RequestMapping(value = "/respuestas-horarios", method = RequestMethod.GET)
    public String respuestas_horarios(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "respuestas-horarios";
    }

    @RequestMapping(value = "/respuestas-quirofanos", method = RequestMethod.GET)
    public String respuestas_quirofanos(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "respuestas-quirofanos";
    }

    @RequestMapping(value = "/respuestas-riesgo", method = RequestMethod.GET)
    public String respuestas_riesgo(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "respuestas-riesgo";
    }

    @RequestMapping(value = "/estadisticas/graficos", method = RequestMethod.POST)
    public String estadisticas (ModelMap modelo, @RequestParam int anio, @RequestParam int mes, @RequestParam int tipo){
        String graf = "";
        Graficos g = new Graficos();

        graf = g.dibujarBarras(tipo,mes,anio);
        modelo.addAttribute("grafico",graf);
        return "estadisticas";
    }

    @RequestMapping(value="/estadisticas")
    public String estadisticas (ModelMap modelo){
        return "estadisticas";
    }

    @RequestMapping(value = "/reportes")
    public String reportes (ModelMap model){
        return "reportes";
    }


    @RequestMapping(value = "/edicion", method = RequestMethod.GET)
    public String edicion(ModelMap modelMap){

        CirujanoJpaController cirujanoJpaController= new CirujanoJpaController(emf);
        List<Cirujano> lista_cirujanos= cirujanoJpaController.findCirujanoEntities();
        modelMap.addAttribute("cirujanos",lista_cirujanos);

        Cie9JpaController cie9JpaController= new Cie9JpaController(emf);
        List<Cie9> lista_cie9= cie9JpaController.findCie9Entities();
        modelMap.addAttribute("procedimientos",lista_cie9);

        EspecialidadJpaController especialidadJpaController= new EspecialidadJpaController(emf);
        List<Especialidad> lista_especialidad= especialidadJpaController.findEspecialidadEntities();
        modelMap.addAttribute("especialidad",lista_especialidad);

        HorarioJpaController horarioJpaController= new HorarioJpaController(emf);
        List<Horario> lista_horarios= horarioJpaController.findHorarioEntities();
        modelMap.addAttribute("horarios", lista_horarios);

        MetasJpaController metasJpaController= new MetasJpaController(emf);
        List<Metas> lista_metas= metasJpaController.findMetasEntities();
        modelMap.addAttribute("metas",lista_metas);

        QuirofanoJpaController quirofanoJpaController= new QuirofanoJpaController(emf);
        List<Quirofano> lista_quirofanos= quirofanoJpaController.findQuirofanoEntities();
        modelMap.addAttribute("quirofanos", lista_quirofanos);

        RiesgoJpaController riesgoJpaController= new RiesgoJpaController(emf);
        List<Riesgo> lista_riesgos= riesgoJpaController.findRiesgoEntities();
        modelMap.addAttribute("riesgos",lista_riesgos);

        return "edicion";
    }

    @RequestMapping(value = "/edicion-eliminar", method = RequestMethod.GET)
    public String edicion_cirujano(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "edicion-eliminar";
    }

    @RequestMapping(value = "/ediciones/edicion-cirujano", method = RequestMethod.GET)
    public String edi_cirujano(ModelMap model){

        EspecialidadJpaController especialidadJpaController= new EspecialidadJpaController(emf);
        List<Especialidad> lista_especialidad= especialidadJpaController.findEspecialidadEntities();
        model.addAttribute("especialidades",lista_especialidad);

        HorarioJpaController horarioJpaController= new HorarioJpaController(emf);
        List<Horario> lista_horarios= horarioJpaController.findHorarioEntities();
        model.addAttribute("horarios",lista_horarios);

        model.addAttribute("ajj","ajj");
        return "/ediciones/edicion-cirujano";
    }

    @RequestMapping(value = "/ediciones/servlet-edicion-cirujano", method = RequestMethod.GET)
    public String servlet_cirujano(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/servlet-edicion-cirujano";
    }

    @RequestMapping(value = "/ediciones/edicion-cie9", method = RequestMethod.GET)
    public String edi_cie9(ModelMap model){

        EspecialidadJpaController especialidadJpaController= new EspecialidadJpaController(emf);
        List<Especialidad> lista_especialidad= especialidadJpaController.findEspecialidadEntities();
        model.addAttribute("especialidades",lista_especialidad);

        HorarioJpaController horarioJpaController= new HorarioJpaController(emf);
        List<Horario> lista_horarios= horarioJpaController.findHorarioEntities();
        model.addAttribute("horarios",lista_horarios);

        model.addAttribute("ajj","ajj");
        return "/ediciones/edicion-cie9";
    }

    @RequestMapping(value = "/ediciones/servlet-edicion-cie9", method = RequestMethod.GET)
    public String servlet_cie9(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/servlet-edicion-cie9";
    }

    @RequestMapping(value = "/ediciones/edicion-especialidad", method = RequestMethod.GET)
    public String edicion_especialidad(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/edicion-especialidad";
    }

    @RequestMapping(value = "/ediciones/servlet-edicion-especialidad", method = RequestMethod.GET)
    public String servlet_especialidad(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/servlet-edicion-especialidad";
    }


    @RequestMapping(value = "/ediciones/edicion-quirofanos", method = RequestMethod.GET)
    public String edicion_quirofanos(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/edicion-quirofanos";
    }

    @RequestMapping(value = "/ediciones/servlet-edicion-quirofanos", method = RequestMethod.GET)
    public String servlet_quirofanos(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/servlet-edicion-quirofanos";
    }

    @RequestMapping(value = "/ediciones/edicion-riesgos", method = RequestMethod.GET)
    public String edicion_riesgos(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/edicion-riesgos";
    }

    @RequestMapping(value = "/ediciones/servlet-edicion-riesgos", method = RequestMethod.GET)
    public String servlet_riesgos(ModelMap model){

        model.addAttribute("ajj","ajj");
        return "/ediciones/servlet-edicion-riesgos";
    }












    @RequestMapping(value = "/reportes/generar")
    public ModelAndView generar(ModelAndView modelAndView,
                                @RequestParam (value = "reporte", defaultValue = "cg")String reporte,
                                @RequestParam (value = "hora", defaultValue = "4")String hora,
                                @RequestParam String tipo,
                                @RequestParam int anio){
        Reportes rep = new Reportes();
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        EspecialidadJpaController esjpac = new EspecialidadJpaController(CreadorEntityManager.crearEMF());
        List<CirujanoMetasDTO> cirujanos = null;
        String desc = "";

        int esp = 1;
        String view = "";

        if(reporte.equalsIgnoreCase("cg")){
            esp = esjpac.getCirujiaGeneral().getIdEspecialidad();
            desc = "Cirujía General";
        }

        if(reporte.equalsIgnoreCase("gyo")){
            esp = esjpac.getGyO().getIdEspecialidad();
            desc = "Ginecología y Obstetrícia";
        }




    if(reporte.equalsIgnoreCase("se")){
            desc = "Subespecialidades";
            view = "ReportSubEsp";

        }

        if(reporte.equalsIgnoreCase("dc")){
            desc = "Detalle Cirujanos";
            view = "ReportDetalle";
            cirujanos = rep.detalleCirujanos(anio);
        }

        int h = Integer.valueOf(hora);

        switch (h){
            case 1:
                desc = desc +" 7am a 3pm";
                if(!reporte.equalsIgnoreCase("se")){
                    cirujanos = rep.cirMetas(anio,esp,h,desc);
                    view = "Report";
                } else {
                    cirujanos = rep.subEspecialidades(anio,h,desc);
                }
                break;
            case 2:
                desc = desc + " 3pm a 7pm";
                if(!reporte.equalsIgnoreCase("se")){
                    cirujanos = rep.cirMetas(anio,esp,h,desc);
                    view = "Report";
                }else {
                    cirujanos = rep.subEspecialidades(anio,h,desc);
                }
                break;
            case 0:
                if(!reporte.equalsIgnoreCase("se")){
                    desc = desc + " Medico Asistencial";
                    cirujanos = rep.asisTotal(anio,esp,desc);
                    view = "ReportAsis";
                } else {
                    desc = desc + " Medico Asistencial ";
                    cirujanos = rep.asisSubEsp(anio, desc);
                    view = "ReportAsisSE";
                }
                break;
        }

        JRDataSource jrDataSource = new JRBeanCollectionDataSource(cirujanos);
        parameterMap.put("datasource",jrDataSource);
        parameterMap.put("exporterParameter",false);

        if(tipo.equalsIgnoreCase("pdf"))
            modelAndView = new ModelAndView("pdf"+view, parameterMap);
        if(tipo.equalsIgnoreCase("html"))
            modelAndView = new ModelAndView("html"+view, parameterMap);
        if(tipo.equalsIgnoreCase("excel"))
            modelAndView = new ModelAndView("excel"+view, parameterMap);
        if(tipo.equalsIgnoreCase("word"))
            modelAndView = new ModelAndView("word"+view, parameterMap);

        return modelAndView;

    }

    @RequestMapping(value="/reportes/html")
    public ModelAndView reporteHtml(ModelAndView modelAndView){
        Reportes rep = new Reportes();
        Map<String,Object> parameterMap = new HashMap<String,Object>();

        //TODO UNCOMMENT
        /**
         * OK
          */
        //List<CirujanoMetasDTO> cirujanos = rep.cirMetas();


        //List<ProduccionEspecialidadDTO> dtoList = rep.especialidadDTOs(2015);


//        List<Metas> metas = rep.cirMetas();
        //JRDataSource jrDataSource = new JRBeanCollectionDataSource(cirujanos);
        //parameterMap.put("datasource",jrDataSource);

        modelAndView = new ModelAndView("pdfReport", parameterMap);

        return modelAndView;
    }
}