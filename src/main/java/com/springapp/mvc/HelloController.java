package com.springapp.mvc;

import com.persistencia.Cie9JpaController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Controller
//@RequestMapping("/")
public class HelloController {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        String nombre = "asdf";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        Cie9JpaController cta = new Cie9JpaController(emf);
        nombre = cta.findCie9(1).getNombre();
		model.addAttribute("message",nombre);
		return "hello";
	}


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model){
        model.addAttribute("index","index");
        return "index";
    }

    @RequestMapping(value = "/historial", method = RequestMethod.GET)
    public String historial(ModelMap model){
        model.addAttribute("asdf","asdf");
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