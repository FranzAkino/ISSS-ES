package com.springapp.mvc;

/**
 * Created by akino on 09-17-15.
 */
import com.persistencia.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportes {
    CirujiaJpaController cirujiajpaC = new CirujiaJpaController(CreadorEntityManager.crearEMF());
    public List<Map<String,?>> findAll(){
        List<Map<String,?>> lista = new ArrayList<Map<String, ?>>();
        List<Cirujia> cirujias = cirujiajpaC.findCirujiaEntities();
        for(Cirujia c: cirujias){
            Map<String,Object> m = new HashMap<String, Object>();
            m.put("Fecha",c.getFecha().toString());
            m.put("Nombre Paciente",c.getFkPaciente().getNombres());
            m.put("Apellidos Paciente",c.getFkPaciente().getApellidos());
            m.put("Mayor/Menor",c.getTipoAnestecia());
            lista.add(m);
        }
        return lista;
    }
}
