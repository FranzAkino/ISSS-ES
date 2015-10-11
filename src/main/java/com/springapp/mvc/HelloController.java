package com.springapp.mvc;

import com.persistencia.*;
import com.persistencia.exceptions.Coasa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManagerFactory;
import java.util.List;


@Controller
//@RequestMapping("/")
public class HelloController {

    EntityManagerFactory emf = CreadorEntityManager.crearEMF();

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        Coasa asdf = new Coasa();
		model.addAttribute("message",asdf);
		return "hello";
	}

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

    @RequestMapping(value = "/reportes", method = RequestMethod.GET)
    public String reportes(ModelMap modelMap){
        Reportes rep = new Reportes();
        modelMap.put("lista",rep.findAll());
        return "reporte";
    }
}
