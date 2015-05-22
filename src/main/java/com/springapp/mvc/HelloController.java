package com.springapp.mvc;

import com.persistencia.Cie9JpaController;
import com.persistencia.Cirujia;
import com.persistencia.CirujiaJpaController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Controller
//@RequestMapping("/")
public class HelloController {

    EntityManagerFactory emf = CreadorEntityManager.crearEMF();

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message","");
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
//        List<Cirujia> lista = cirujiaJpaController.findCirujiaEntities(3,0);
        model.addAttribute("asdf", "asfd");
        return "historial";
    }




    @RequestMapping(value = "/Registro", method = RequestMethod.GET)
    public String registro(ModelMap model){


        model.addAttribute("asdf","asdf");
        return "Registro";
    }

    @RequestMapping(value = "/Registros-informacion", method = RequestMethod.GET)
    public String registros_informacion(ModelMap model){
        model.addAttribute("asdf","asdf");
        return "Registros-informacion";
    }

}
