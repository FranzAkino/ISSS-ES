package com.springapp.mvc;

import com.persistencia.Cirujano;
import com.persistencia.CirujanoJpaController;
import com.persistencia.Riesgo;
import com.persistencia.RiesgoJpaController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by akino on 05-21-15.
 */
public class AdminRegistros {
    public List<Cirujano> listaCirujanos;
    public List<Riesgo> listaRiesgos;
    EntityManagerFactory emf = CreadorEntityManager.crearEMF();
    public AdminRegistros(){
        CirujanoJpaController cjpa = new CirujanoJpaController(emf);
        RiesgoJpaController rjpa = new RiesgoJpaController(emf);
        listaCirujanos = cjpa.findCirujanoEntities();
        listaRiesgos = rjpa.findRiesgoEntities();
    }
}
