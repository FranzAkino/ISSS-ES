package com.springapp.mvc;

import com.persistencia.*;
import com.persistencia.exceptions.Coasa;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
        List<Cirujano> lista_cirujanos= cirujanoJpaController.getActivos();
        List<Cirujano> lista_cirujanos1= cirujanoJpaController.getActivos();
        List<Cirujano> lista_cirujanos2= cirujanoJpaController.getActivos();
        model.addAttribute("asdf","asdf");
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

    @RequestMapping(value = "/estadisticas", method = RequestMethod.GET)
    public String estadisticas (ModelMap modelo){
//        String graf = g.dibujarBarrasMensual(TipoGraficos.MAYOR_ELECTIVA,Meses.NOVIEMBRE);
        modelo.addAttribute("grafico","graf");
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
