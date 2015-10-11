package com.springapp.mvc;

import com.googlecode.wickedcharts.highcharts.jackson.JsonRenderer;
import com.googlecode.wickedcharts.highcharts.options.*;
import com.googlecode.wickedcharts.highcharts.options.series.Series;
import com.googlecode.wickedcharts.highcharts.theme.DarkBlueTheme;
import com.googlecode.wickedcharts.highcharts.theme.SkiesTheme;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
import com.persistencia.*;
import com.sun.istack.internal.Nullable;
import org.joda.time.DateTime;
import org.springframework.format.datetime.joda.JodaTimeFormatterRegistrar;

import javax.persistence.EntityManagerFactory;
import java.util.*;

/**
 * Created by akino on 06-13-15.
 */
public class Graficos {
    EntityManagerFactory emf = CreadorEntityManager.crearEMF();


    Options options;
    List<Especialidad> especialidades;
    EspecialidadJpaController espjpac;
    CirujiaJpaController cirujiaJpaController;
    CirujanoJpaController cirujanoJpaController;
    QuirofanoJpaController quirofanoJpaController;

    CirujanoCirujiaJpaController cirujanoCirujiaJpaController;

    List<EspecialidadValor> especialidadValor;
    List<String> stringEspecialidades;


    public Graficos(){
        espjpac = new EspecialidadJpaController(emf);
        cirujiaJpaController = new CirujiaJpaController(emf);
        cirujanoJpaController = new CirujanoJpaController(emf);
        quirofanoJpaController = new QuirofanoJpaController(emf);
        cirujanoCirujiaJpaController = new CirujanoCirujiaJpaController(emf);
        especialidades = espjpac.findEspecialidadEntities();
        especialidadValor = new ArrayList<EspecialidadValor>();
        stringEspecialidades = espList();
    }

    public String dibujarBarras(int t, @Nullable int mes, @Nullable int year) {


        switch (TipoGraficos.getValue(t)) {
            case MAYOR_ELECTIVA:
                mayorElectiva(year, mes);
                break;
            case MAYOR_EMERGENCIA:
                mayorEmergencia(year,mes);
                break;
            case MENOR_ELECTIVA:
                menorElectiva(year,mes);
                break;
            case MENOR_EMERGENCIA:
                menorEmergencia(year,mes);
                break;
            case ANUALES:
                cirujiasAnuales();
                break;
            case SUSPENDIDAS:
                cirujiasSuspendidasanio(mes, year);
                break;
            case ANUALES_ESPECIALIDAD:
                cirujiasAnualesEsp(year);
                break;
            case QUIROFANO:
                cirujiasPorQuirofano(mes, year);
                break;
        }
        JsonRenderer render = new JsonRenderer();
        String cadenaJson = "";


        cadenaJson = render.toJson(options);
        System.out.print(cadenaJson);

        return cadenaJson;
    }

    private void cirujiasPorQuirofano(int mes, int year) {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.BAR).setRenderTo("grafico"));
        List<Cirujia> cirujias;
        int quirofanos = quirofanoJpaController.getQuirofanoCount();
        HashMap<String, Integer> quirofanoCount = new HashMap<String, Integer>();
        for(int i = 1; i<=quirofanos;i++){
            quirofanoCount.put("Quirofano " + String.valueOf(i),0);
        }
        if(mes==0){
            cirujias = cirujiaJpaController.getRealizadasAnual(year);
            for(Cirujia c: cirujias){
                String q = "Quirofano "+String.valueOf(c.getFkQuirofano().getIdQuirofano());
                Integer i = quirofanoCount.get(q);
                int a = i.intValue();
                a++;
                i = a;
                quirofanoCount.put(q,i);
            }
            options.setTitle(new Title("CIRUJÍAS POR QUIROFANO PARA EL AÑO  " + year));
        }else{
            cirujias = cirujiaJpaController.getRealizadasMensual(year, mes);
            for(Cirujia c: cirujias){
                String q = "Quirofano " +String.valueOf(c.getFkQuirofano().getIdQuirofano());
                Integer i = quirofanoCount.get(q);
                int a = i.intValue();
                a++;
                i = a;
                quirofanoCount.put(q,i);
            }
            String m = mes(mes);
            options.setTitle(new Title("CIRUJÍAS POR QUIROFANO PARA   " + m + " DEL " + year));
        }
        //TODO: lista de quirofanos dinamica
        options.setxAxis(new Axis().setCategories("1","2","3","4","5","6","7","8","9"));
        List<Number> valores = new ArrayList<Number>(quirofanoCount.values());
        options.addSeries(new SimpleSeries().setData(valores).setName("Número de Cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Quirofanos")));

    }

    private void menorEmergencia(int year, int mes) {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.BAR).setRenderTo("grafico"));
        List<Cirujia> cirujias;
        if(mes==0) {
            cirujias = cirujiaJpaController.getMenorEmergenciaAnual(year);
            options.setTitle(new Title("CIRUJÍAS MENOR Y EMERGENCIA EL AÑO  " + year));
        }
        else {
            cirujias = cirujiaJpaController.getMenorEmergenciaMensual(year, mes);
            options.setTitle(new Title("CIRUJÍAS MENOR Y EMERGENCIA PARA  "+mes+" DEL "+year));
        }
        options.setxAxis(new Axis().setCategories(stringEspecialidades));
        options.addSeries(new SimpleSeries().setData(obtenerValores(cirujias)).setName("Número de cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Cirujias")));
    }

    private void menorElectiva(int year, int mes) {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.BAR).setRenderTo("grafico"));
        List<Cirujia> cirujias;
        if(mes==0) {
            cirujias = cirujiaJpaController.getMenorElectivaAnual(year);
            options.setTitle(new Title("CIRUJÍAS MENOR ELECTIVA EL AÑO  " + year));
        }
        else {
            cirujias = cirujiaJpaController.getMenorElectivaMensual(year, mes);
            options.setTitle(new Title("CIRUJÍAS MENOR ELECTIVA PARA  "+mes+" DEL "+year));
        }
        options.setxAxis(new Axis().setCategories(stringEspecialidades));
        options.addSeries(new SimpleSeries().setData(obtenerValores(cirujias)).setName("Número de cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Cirujias")));
    }

    private void mayorEmergencia(int year, int mes) {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.BAR).setRenderTo("grafico"));
        List<Cirujia> cirujias;
        if(mes==0) {
            cirujias = cirujiaJpaController.getMayorEmergenciaAnual(year);
            options.setTitle(new Title("CIRUJÍAS MAYOR Y EMERGENCIA PARA EL AÑO  " + year));
        }
        else {
            cirujias = cirujiaJpaController.getMayorEmergenciaMensual(year, mes);
            options.setTitle(new Title("CIRUJÍAS MAYOR Y EMERGENCIA PARA EL MES  " + mes + " DEL " + year));
        }
        options.setxAxis(new Axis().setCategories(stringEspecialidades));
        options.addSeries(new SimpleSeries().setData(obtenerValores(cirujias)).setName("Número de cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Cirujias")));
    }

    private void mayorElectiva(int year, int mes) {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.BAR).setRenderTo("grafico"));
        List<Cirujia> cirujias;
        if(mes==0) {
            cirujias = cirujiaJpaController.getMayorElectivaAnual(year);
            options.setTitle(new Title("CIRUJÍAS MAYOR ELECTIVA PARA EL AÑO  " + year));
        }
        else {
            cirujias = cirujiaJpaController.getMayorElectivaMensual(year, mes);
            options.setTitle(new Title("CIRUJÍAS MAYOR ELECTIVA PARA EL MES  " + mes + " DEL " + year));
        }
        options.setxAxis(new Axis().setCategories(stringEspecialidades));
        options.addSeries(new SimpleSeries().setData(obtenerValores(cirujias)).setName("Número de cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Cirujias")));
    }

    private void cirujiasAnuales() {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.BAR).setRenderTo("grafico"));
        List<Cirujia> cirujias = cirujiaJpaController.getRealizadas();
        HashMap<String,Number> año_Total = new HashMap<String, Number>(cirujias.size());
        List<String> años = new ArrayList<String>();
        DateTime dt;
        for(Cirujia c: cirujias){
            dt = new DateTime(c.getFecha());
            String año = String.valueOf(dt.getYear());
            if(año_Total.containsKey(año)){
                Number i = año_Total.get(año);
                int j = i.intValue();
                j++;
                i = (Number) j;
                año_Total.put(año,i);
            }else{
                año_Total.put(año,0);
                años.add(año);
            }
        }
        List<Number> total = new ArrayList<Number>(año_Total.values());

        options.setxAxis(new Axis().setCategories(años));
//        options.setTitle(new Title("Cirujías realizadas desde "+ arrayAnios[0] +" hasta "+ arrayAnios[arrayAnios.length]));
        options.setTitle(new Title("TOTAL CIRUJÍAS REALIZADAS POR AÑO"));
        options.addSeries(new SimpleSeries().setData(total).setName("Número de cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Cirujías")));
    }

    private void cirujiasAnualesEsp(int year) {
        options = new Options();
        options.setTitle(new Title("CIRUJÍAS REALIZADAS ANUALEMENTE POR ESPECIALIDAD"));
        options.setChartOptions(new ChartOptions().setType(SeriesType.BAR).setRenderTo("grafico"));
        options.setxAxis(new Axis().setCategories(stringEspecialidades));
        List<Cirujia> cirujiasAnuales = cirujiaJpaController.getAnualesEspecialidad(year);

        options.addSeries(new SimpleSeries().setData(obtenerValores(cirujiasAnuales)).setName("Número de cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Cirujías")));
    }

    private List<Number> obtenerValores(List<Cirujia> cirujias){
        List<Number> valores;
        for(Cirujia c: cirujias){
            for(CirujanoCirujia cc: c.getCirujanoCirujiaList()){
                //Si el cirujano es titular
                if(cc.getTitular()==1){ //
                    String es = cc.getFkidCirujano().getFkidEspecialidad().getEspecialidad();
                    int b = indiceEspecialidad(es);
                    EspecialidadValor ev = especialidadValor.get(b);
                    Number val = ev.valor;
                    int a = val.intValue();
                    a++;
                    val = (Number) a;
                    ev.valor=val;
                    especialidadValor.set(b,ev);
                }
            }
        }
        valores = new ArrayList<Number>();
        for(EspecialidadValor es: especialidadValor)
            valores.add(es.valor);
        return valores;
    }

    private void cirujiasSuspendidasanio(int mes, int year) {
        options = new Options();
        options.setChartOptions(new ChartOptions()
                .setType(SeriesType.BAR).setRenderTo("grafico"));

        options.setxAxis(new Axis().setCategories(stringEspecialidades));
        List<Cirujia> suspendidas;
        if(mes == 0) {
            suspendidas = cirujiaJpaController.getSuspendidas(year);
            options.setTitle(new Title("CIRUJIAS SUSPENDIDAS PARA EL AÑO " + year +" POR ESPECIALIDADES"));
        }
        else{
            suspendidas = cirujiaJpaController.getSuspendidasPorMes(mes,year);
            options.setTitle(new Title("CIRUJIAS SUSPENDIDAS PARA " + mes +
                    " DEL AÑO " + year +" POR ESPECIALIDADES"));
        }

        options.addSeries(new SimpleSeries().setData(obtenerValores(suspendidas)).setName("Número de cirujías"));
        options.setyAxis(new Axis().setTitle(new Title("Cirujías")));
    }

    private List<String> espList(){
        List<String> esps = new ArrayList<String>(especialidades.size());
        for(Especialidad e: especialidades){
            esps.add(e.getEspecialidad());
            especialidadValor.add(new EspecialidadValor(e.getEspecialidad(),0));
        }
        return esps;
    }



    public int indiceEspecialidad(String especialidad){
        int i = 0;
        for(Especialidad e: especialidades){
            if(e.getEspecialidad().equalsIgnoreCase(especialidad))
                return i;
            else i++;
        }
        return 0;
    }

    private class EspecialidadValor {
        public String especialidad;
        public Number valor;

        public EspecialidadValor(String especialidad, Number valor) {
            this.especialidad = especialidad;
            this.valor = valor;
        }
    }

    protected String mes(int mes){
        switch (mes){

            case 1:
                return "ENERO";
            case 2:
                return "FEBRERO";
            case 3:
                return "MARZO";
            case 4:
                return "ABRIL";
            case 5:
                return "MAYO";
            case 6:
                return "JUNIO";
            case 7:
                return "JULIO";
            case 8:
                return "AGOSTO";
            case 9:
                return "SEPTIEMBRE";
            case 10:
                return "OCTUBRE";
            case 11:
                return "NOVIEMBRE";
            case 12:
                return "DICIEMBRE";
            default:
                return "MES DESCONOCIDO";




        }
    }

}