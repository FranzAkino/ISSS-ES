package com.springapp.mvc;

import com.googlecode.wickedcharts.highcharts.jackson.JsonRenderer;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
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
    Options options = new Options();
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

        TypedQuery<CirujanoEspecialidad> querySub =
                em.createNamedQuery("CirujanoEspecialidad.findByIdEspecialidad",CirujanoEspecialidad.class);


        TypedQuery<Especialidad> queryEspecialidades =
                em.createNamedQuery("Especialidad.findAll",Especialidad.class);
        List<Especialidad> especialidads = queryEspecialidades.getResultList();

        int i=0;
        for(Especialidad c: especialidads){
            List<CirujanoEspecialidad> resultado =
                querySub.setParameter("idEspecialidad",i).getResultList();
            if(!resultado.isEmpty()){

                int cuenta =((Number) em.createQuery(
                "select c.idCirujia from Cirujia c where func('MONTH',c.fecha) =:mes")
                    .setParameter("mes",mes).getSingleResult()).intValue();
//                "select c.idCirujia from Cirujia c where func('MONTH',c.fecha) =:mes and c.fkCirujano in :resultado")
//                .setParameter("mes",mes).setParameter("resultado",resultado).getSingleResult()).intValue();
                hash.put(new Integer(i), c.getEspecialidad());
                l.add(new Integer(cuenta));
                i++;
            }else {
                i++;
            }
        }
        return l;
    }

    private void electivaMayorMensual(Meses mes) {

        options.setChartOptions(new ChartOptions()
                .setType(SeriesType.BAR).setRenderTo("grafico"));

        options.setTitle(new Title("Cirujias por especialidad " + mes.name()));

        options.setxAxis(new Axis().setCategories(new ArrayList<String>(hash.values())));

        options.addSeries(new SimpleSeries().setData(cuentaPorEspecialidad(mes)));
    }

    public void dibujarPastel(){

    }



}
