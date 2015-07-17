package com.springapp.mvc;

import com.googlecode.wickedcharts.highcharts.jackson.JsonRenderer;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
import com.persistencia.CirujanoCirujia;
import com.persistencia.Cirujia;
import com.persistencia.CirujiaJpaController;
import com.persistencia.Especialidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by akino on 06-13-15.
 */
public class Graficos {
    /*Options options = new Options();
    EntityManagerFactory emf = CreadorEntityManager.crearEMF();
    HashMap<Integer,String> hash = new HashMap<Integer, String>();

    public Graficos(){}

    public String dibujarBarrasMensual(TipoGraficos t, Meses m) {

        switch (t) {
            case MAYOR_ELECTIVA:
                electivaMayorMensual(m);
                break;
            case MENOR_ELECTIVA:
                break;
            case MAYOR_ELECTIVA_EMERGENCIA:
                break;
            case MENOR_EMERGENCIA:
                break;
            case SUSPENDIDAS:
                break;
        }

        JsonRenderer render = new JsonRenderer();
        String cadenaJson = render.toJson(options);

        return cadenaJson;
    }

    private List<Number> cuentaPorEspecialidad(Meses mes) {
        CirujiaJpaController jpaController = new CirujiaJpaController(emf);
        EntityManager em = emf.createEntityManager();
        List<Number> l = new ArrayList<Number>();


        //Se obtienen todas las especialidades;
        List<Especialidad> especialidades =
                em.createNamedQuery("Especialidad.findAll", Especialidad.class).getResultList();

        //Llena un hash y un @List<Number> con
        // (@int cantidad, @String nombre especialidad)
        for(Especialidad esp: especialidades){
            String nombre = esp.getEspecialidad();
            TypedQuery<Cirujia> queryCirujia =
                    em.createQuery("select c.cirujiaPK.idCirujia from Cirujia c join CirujanoCirujia cc where " +
                            "cc.cirujano.especialidad.especialidad =: nombre and " +
                            "func('MONTH',c.fecha) =: mes",Cirujia.class);
            int cantidad = queryCirujia.getResultList().size();
            hash.put(cantidad, nombre);
            l.add(new Integer(cantidad));
        }
        return l;
    }

    private void electivaMayorMensual(Meses mes) {

        options.setChartOptions(new ChartOptions()
                .setType(SeriesType.BAR).setRenderTo("grafico"));
        options.setTitle(new Title("Cirujias por especialidades " + mes.name()));
        options.setxAxis(new Axis().setCategories(new ArrayList<String>(hash.values())));
        options.addSeries(new SimpleSeries().setData(cuentaPorEspecialidad(mes)));
    }

    public void dibujarPastel(){

    }


*/
}
