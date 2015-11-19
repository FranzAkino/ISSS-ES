package com.springapp.mvc;

/**
 * Created by akino on 09-17-15.
 */

import com.dtos.CirujanoMetasDTO;
import com.dtos.ProduccionEspecialidadDTO;
import com.persistencia.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportes {
    private CirujiaJpaController cirujiajpaC = new CirujiaJpaController(CreadorEntityManager.crearEMF());

    private CirujanoJpaController cirujanoJpa = new CirujanoJpaController(CreadorEntityManager.crearEMF());

    private EspecialidadJpaController espJpa = new EspecialidadJpaController(CreadorEntityManager.crearEMF());

    private MetasJpaController mjpa = new MetasJpaController(CreadorEntityManager.crearEMF());

    private MetasPK produceMetasPK(int anio, int mes, int idCirujano){
        return new MetasPK(idCirujano,mes,anio);
    }




    /******************************************
     *              CG Y GYO                  *
     ******************************************/
    public List<CirujanoMetasDTO> cirMetas(int anio, int especialidad, int hora, String tipo){
        List<Metas> metas = mjpa.getMetasByEspecialidadesAndAnio(especialidad,anio,hora);
        ModelMapper mapper = new ModelMapper();
        List<CirujanoMetasDTO> cirujanoMetasDTOs = new ArrayList<CirujanoMetasDTO>();
        int metaTotal,realizadasTotal;
        metaTotal = realizadasTotal = 0;

        int cirAct = 0;
        if(metas.size() == 0) {
            return cirujanoMetasDTOs;
        } else {
            cirAct = metas.get(0).getCirujano().getIdCirujano();
        }
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        for(Metas m: metas){
            CirujanoMetasDTO cirDTO = mapper.map(m,CirujanoMetasDTO.class);
            String nombreCompleto = "Dr " +cirDTO.getNombres() + " "+ m.getCirujano().getApellidos();
            cirDTO.setNombres(nombreCompleto);
            cirDTO.setTipoReporte(tipo);
            if(cirAct != m.getCirujano().getIdCirujano()){
                metaTotal = 0;
                realizadasTotal = 0;
            }
            metaTotal = metaTotal + m.getMeta();
            realizadasTotal = realizadasTotal + m.getRealizadas();
            cirDTO.setMetaTotal(metaTotal);
            cirDTO.setRealizadasTotal(realizadasTotal);
            double rend =  ((double)realizadasTotal/metaTotal)*100;
            //cirDTO.setRendimiento(String.format("%.2f", rend)+"%");
            cirDTO.setRendimiento(Math.floor(rend));

            cirAct = m.getCirujano().getIdCirujano();

            cirujanoMetasDTOs.add(cirDTO);

        }
        return cirujanoMetasDTOs;

    }


    /******************************************
     *       MEDICO ASISTENCIAL CG Y GYO      *
     ******************************************/
    public List<CirujanoMetasDTO> asisTotal(int anio, int especialidad, String desc){
        CirujanoJpaController cjpa = new CirujanoJpaController(CreadorEntityManager.crearEMF());
        List<CirujanoMetasDTO> cirTotal = new ArrayList<CirujanoMetasDTO>();
        List<Cirujano> cirujanos = cjpa.getByEspecialidadAndAnio(especialidad,anio);
        for(Cirujano c: cirujanos){
            DateTime fechai = new DateTime(c.getCirujanoCirujiaList().get(0).getFkidCirujia().getFecha());
            int mes = fechai.getMonthOfYear();
            int real = 1;
            for(CirujanoCirujia cc: c.getCirujanoCirujiaList()){
                CirujanoMetasDTO cmDto = new CirujanoMetasDTO();
                DateTime fecha = new DateTime(cc.getFkidCirujia().getFecha());
                cmDto.setNombres(
                        "Dr " + c.getNombres()+" "+c.getApellidos()
                );
                if(fecha.getMonthOfYear()==mes){
                    real = cmDto.getRealizadas();
                    real++;
                    cmDto.setRealizadas(real);
                }else {
                    cmDto.setRealizadas(real);
                }
                mes = fecha.getMonthOfYear();
                cmDto.setTipoReporte(desc);
                cmDto.setAnio(anio);
                cmDto.setMes(fecha.getMonthOfYear());
                cirTotal.add(cmDto);
            }
        }
        return cirTotal;
    }

    /*  System.out.println(cirDTO.getNombres()+" "+ cirDTO.getApellidos());
            System.out.println("Mes:"+cirDTO.getMes()+" "+ "año:"+cirDTO.getAnio());
            System.out.println("Suspendidas:"+cirDTO.getSuspendidas()+" "+ "Realizadas:"+cirDTO.getRealizadas());
            System.out.println("Meta:"+cirDTO.getMeta());
            System.out.println("----------------------" +
                    "");*/



    /******************************************
     *        MEDICO ASISTENCIAL SE           *
     ******************************************/

    public List<CirujanoMetasDTO> asisSubEsp(int anio, String desc){
        CirujanoJpaController cjpa = new CirujanoJpaController(CreadorEntityManager.crearEMF());
        List<CirujanoMetasDTO> cirTotal = new ArrayList<CirujanoMetasDTO>();
        List<Cirujano> cirujanos = cjpa.getBySubEspecialidadAndAnio(anio);
        for(Cirujano c: cirujanos){
            DateTime fechai = new DateTime(c.getCirujanoCirujiaList().get(0).getFkidCirujia().getFecha());
            int mes = fechai.getMonthOfYear();
            int real = 1;
            for(CirujanoCirujia cc: c.getCirujanoCirujiaList()){
                CirujanoMetasDTO cmDto = new CirujanoMetasDTO();
                DateTime fecha = new DateTime(cc.getFkidCirujia().getFecha());
                cmDto.setNombres(
                        "Dr " + c.getNombres()+" "+c.getApellidos()
                );
                if(fecha.getMonthOfYear()==mes){
                    real = cmDto.getRealizadas();
                    real++;
                    cmDto.setRealizadas(real);
                }else {
                    cmDto.setRealizadas(real);
                }
                mes = fecha.getMonthOfYear();
                cmDto.setTipoReporte(desc);
                cmDto.setAnio(anio);
                cmDto.setMes(fecha.getMonthOfYear());
                cirTotal.add(cmDto);
            }
        }
        return cirTotal;
    }

    /******************************************
     *          SUBESPECIALIDADES             *
     ******************************************/
    public List<CirujanoMetasDTO> subEspecialidades(int anio, int hora, String tipo){
        List<Metas> metas = mjpa.getMetasBySubEspecialidadAndAnio(anio,hora);
        ModelMapper mapper = new ModelMapper();
        List<CirujanoMetasDTO> cirujanoMetasDTOs = new ArrayList<CirujanoMetasDTO>();
        int metaTotal,realizadasTotal;
        metaTotal = realizadasTotal = 0;

        int cirAct = 0;
        if(metas.size() == 0) {
            return cirujanoMetasDTOs;
        } else {
            cirAct = metas.get(0).getCirujano().getIdCirujano();
        }
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        for(Metas m: metas){
            CirujanoMetasDTO cirDTO = mapper.map(m,CirujanoMetasDTO.class);
            String nombreCompleto = "Dr " +cirDTO.getNombres() + " "+ m.getCirujano().getApellidos();
            cirDTO.setNombres(nombreCompleto);
            cirDTO.setEspecialidad(m.getCirujano().getFkidEspecialidad().getEspecialidad());
            cirDTO.setTipoReporte(tipo);
            if(cirAct != m.getCirujano().getIdCirujano()){
                metaTotal = 0;
                realizadasTotal = 0;
            }
            metaTotal = metaTotal + m.getMeta();
            realizadasTotal = realizadasTotal + m.getRealizadas();
            cirDTO.setMetaTotal(metaTotal);
            cirDTO.setRealizadasTotal(realizadasTotal);
            double rend =  ((double)realizadasTotal/metaTotal)*100;
            //cirDTO.setRendimiento(String.format("%.2f", rend)+"%");
            cirDTO.setRendimiento(Math.floor(rend));

            cirAct = m.getCirujano().getIdCirujano();

            cirujanoMetasDTOs.add(cirDTO);

        }
        return cirujanoMetasDTOs;
    }



    /******************************************
     *          DETALLE ANUAL CIRUJANO        *
     ******************************************/
    public List<CirujanoMetasDTO> detalleCirujanos(int anio){
        List<Metas> metas = mjpa.getMetasByAnio(anio);

        List<CirujanoMetasDTO> metasDTO = new ArrayList<CirujanoMetasDTO>();

        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        if (metas == null) {
            return metasDTO;
        }

        int act = metas.get(0).getMetasPK().getIdCirujanofk();
        int ant = act;
        CirujanoMetasDTO cirDTO = new CirujanoMetasDTO(), cirAnt = cirDTO;
        for(Metas m: metas){
            cirDTO = cirAnt;
            if(act == ant){

                cirDTO.setMeta(cirDTO.getMeta()+m.getMeta());
                cirDTO.setRealizadas(cirDTO.getRealizadas()+m.getRealizadas());
                cirDTO.setSuspendidas(cirDTO.getSuspendidas()+m.getSuspendidas());
                cirDTO.setProgramadas(cirDTO.getRealizadas()+cirDTO.getSuspendidas());

                cirDTO.setNombres("Dr " + m.getCirujano().getNombres() + " " + m.getCirujano().getApellidos());
                cirDTO.setEspecialidad(m.getCirujano().getFkidEspecialidad().getEspecialidad());
                cirDTO.setAnio(anio);
                cirAnt = cirDTO;

            } else {
                metasDTO.add(cirDTO);

                cirDTO = new CirujanoMetasDTO();

                cirDTO.setMeta(m.getMeta());
                cirDTO.setRealizadas(m.getRealizadas());
                cirDTO.setSuspendidas(m.getSuspendidas());
                cirDTO.setProgramadas(cirDTO.getRealizadas() + cirDTO.getSuspendidas());

                cirDTO.setNombres("Dr " + m.getCirujano().getNombres() + " " + m.getCirujano().getApellidos());
                cirDTO.setEspecialidad(m.getCirujano().getFkidEspecialidad().getEspecialidad());
                cirDTO.setAnio(anio);
                cirAnt = cirDTO;

            }
            ant = act;
            act = m.getMetasPK().getIdCirujanofk();
        }
        return metasDTO;
    }

    public List<ProduccionEspecialidadDTO> especialidadDTOs(int anio){

        List<Especialidad> especialidades = espJpa.findEspecialidadEntities();

        List<ProduccionEspecialidadDTO> dtoList = new ArrayList<ProduccionEspecialidadDTO>();

        for (Especialidad e: especialidades){
            ProduccionEspecialidadDTO peDTo = new ProduccionEspecialidadDTO();
            for (int i = 1 ; i<=12; i++){
                peDTo.setEspecialidad(e.getEspecialidad());
                peDTo.setMes(i);
                int realizadas = cirujiajpaC.getRealizadarEspecialidadAnual(anio,i,e.getIdEspecialidad());
                peDTo.setProduccion(realizadas);
                peDTo.setAnio(anio);

                System.out.println(peDTo.getEspecialidad() +" " + peDTo.getMes()+" "+ peDTo.getProduccion());


                dtoList.add(peDTo);
            }
        }
        return dtoList;
    }


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

    public List<Cirujia> findAllCirujias(){
        return cirujiajpaC.findCirujiaEntities();
    }

    public List<Cirujano> findAllCirujanos(){
        return  cirujanoJpa.findCirujanoEntities();
    }
}
