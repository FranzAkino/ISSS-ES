package com.springapp.mvc;

import com.persistencia.Cie9JpaController;
import com.persistencia.Cirujia;
import com.persistencia.CirujiaJpaController;
import com.persistencia.exceptions.Coasa;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        model.addAttribute("asdf","asdf");
        return "Registros-informacion";
    }

    @RequestMapping(value = "/estadisticas", method = RequestMethod.GET)
    public String estadisticas (ModelMap modelo){
        String graf = g.dibujarBarrasMensual(TipoGraficos.MAYOR_ELECTIVA,Meses.NOVIEMBRE);
        modelo.addAttribute("grafico",graf);
        return "estadisticas";
    }

    @RequestMapping(value = "estadisticas/graficos", method = RequestMethod.GET)
    public String dibujarGrafico(ModelMap model, @RequestParam String id){
        return "grafico";
    }

}
