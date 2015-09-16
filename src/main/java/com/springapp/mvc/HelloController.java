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
    Graficos g = new Graficos();

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

         return "Registros-informacion";
    }

    @RequestMapping(value = "/respuestas-informacion", method = RequestMethod.GET)
    public String respuestas_informacion(ModelMap model){

        model.addAttribute("add","add");
        return "respuestas-informacion";
    }

    @RequestMapping(value = "/estadisticas", method = RequestMethod.GET)
    public String estadisticas (ModelMap modelo){
//        String graf = g.dibujarBarrasMensual(TipoGraficos.MAYOR_ELECTIVA,Meses.NOVIEMBRE);
        modelo.addAttribute("grafico","graf");
        return "estadisticas";
    }

    @RequestMapping(value = "estadisticas/graficos", method = RequestMethod.GET)
    public String dibujarGrafico(ModelMap model, @RequestParam String id){
        return "grafico";
    }

}
