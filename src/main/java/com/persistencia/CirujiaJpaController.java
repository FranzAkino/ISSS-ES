/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.IllegalOrphanException;
import com.persistencia.exceptions.NonexistentEntityException;
import org.springframework.jdbc.object.SqlQuery;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author akino
 */
public class CirujiaJpaController implements Serializable {

    public CirujiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cirujia cirujia) {
        if (cirujia.getCirujanoCirujiaList() == null) {
            cirujia.setCirujanoCirujiaList(new ArrayList<CirujanoCirujia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suspenciones fkSuspencion = cirujia.getFkSuspencion();
            if (fkSuspencion != null) {
                fkSuspencion = em.getReference(fkSuspencion.getClass(), fkSuspencion.getIdSuspenciones());
                cirujia.setFkSuspencion(fkSuspencion);
            }
            Riesgo fkRiesgo = cirujia.getFkRiesgo();
            if (fkRiesgo != null) {
                fkRiesgo = em.getReference(fkRiesgo.getClass(), fkRiesgo.getIdRiesgo());
                cirujia.setFkRiesgo(fkRiesgo);
            }
            Quirofano fkQuirofano = cirujia.getFkQuirofano();
            if (fkQuirofano != null) {
                fkQuirofano = em.getReference(fkQuirofano.getClass(), fkQuirofano.getIdQuirofano());
                cirujia.setFkQuirofano(fkQuirofano);
            }
            Paciente fkPaciente = cirujia.getFkPaciente();
            if (fkPaciente != null) {
                fkPaciente = em.getReference(fkPaciente.getClass(), fkPaciente.getIdPaciente());
                cirujia.setFkPaciente(fkPaciente);
            }
            Cie9 fkCie9 = cirujia.getFkCie9();
            if (fkCie9 != null) {
                fkCie9 = em.getReference(fkCie9.getClass(), fkCie9.getIdProcedimiento());
                cirujia.setFkCie9(fkCie9);
            }
            List<CirujanoCirujia> attachedCirujanoCirujiaList = new ArrayList<CirujanoCirujia>();
            for (CirujanoCirujia cirujanoCirujiaListCirujanoCirujiaToAttach : cirujia.getCirujanoCirujiaList()) {
                cirujanoCirujiaListCirujanoCirujiaToAttach = em.getReference(cirujanoCirujiaListCirujanoCirujiaToAttach.getClass(), cirujanoCirujiaListCirujanoCirujiaToAttach.getIdCirujanoCirujia());
                attachedCirujanoCirujiaList.add(cirujanoCirujiaListCirujanoCirujiaToAttach);
            }
            cirujia.setCirujanoCirujiaList(attachedCirujanoCirujiaList);
            em.persist(cirujia);
            if (fkSuspencion != null) {
                fkSuspencion.getCirujiaList().add(cirujia);
                fkSuspencion = em.merge(fkSuspencion);
            }
            if (fkRiesgo != null) {
                fkRiesgo.getCirujiaList().add(cirujia);
                fkRiesgo = em.merge(fkRiesgo);
            }
            if (fkQuirofano != null) {
                fkQuirofano.getCirujiaList().add(cirujia);
                fkQuirofano = em.merge(fkQuirofano);
            }
            if (fkPaciente != null) {
                fkPaciente.getCirujiaList().add(cirujia);
                fkPaciente = em.merge(fkPaciente);
            }
            if (fkCie9 != null) {
                fkCie9.getCirujiaList().add(cirujia);
                fkCie9 = em.merge(fkCie9);
            }
            for (CirujanoCirujia cirujanoCirujiaListCirujanoCirujia : cirujia.getCirujanoCirujiaList()) {
                Cirujia oldFkidCirujiaOfCirujanoCirujiaListCirujanoCirujia = cirujanoCirujiaListCirujanoCirujia.getFkidCirujia();
                cirujanoCirujiaListCirujanoCirujia.setFkidCirujia(cirujia);
                cirujanoCirujiaListCirujanoCirujia = em.merge(cirujanoCirujiaListCirujanoCirujia);
                if (oldFkidCirujiaOfCirujanoCirujiaListCirujanoCirujia != null) {
                    oldFkidCirujiaOfCirujanoCirujiaListCirujanoCirujia.getCirujanoCirujiaList().remove(cirujanoCirujiaListCirujanoCirujia);
                    oldFkidCirujiaOfCirujanoCirujiaListCirujanoCirujia = em.merge(oldFkidCirujiaOfCirujanoCirujiaListCirujanoCirujia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cirujia cirujia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujia persistentCirujia = em.find(Cirujia.class, cirujia.getIdCirujia());
            Suspenciones fkSuspencionOld = persistentCirujia.getFkSuspencion();
            Suspenciones fkSuspencionNew = cirujia.getFkSuspencion();
            Riesgo fkRiesgoOld = persistentCirujia.getFkRiesgo();
            Riesgo fkRiesgoNew = cirujia.getFkRiesgo();
            Quirofano fkQuirofanoOld = persistentCirujia.getFkQuirofano();
            Quirofano fkQuirofanoNew = cirujia.getFkQuirofano();
            Paciente fkPacienteOld = persistentCirujia.getFkPaciente();
            Paciente fkPacienteNew = cirujia.getFkPaciente();
            Cie9 fkCie9Old = persistentCirujia.getFkCie9();
            Cie9 fkCie9New = cirujia.getFkCie9();
            List<CirujanoCirujia> cirujanoCirujiaListOld = persistentCirujia.getCirujanoCirujiaList();
            List<CirujanoCirujia> cirujanoCirujiaListNew = cirujia.getCirujanoCirujiaList();
            List<String> illegalOrphanMessages = null;
            for (CirujanoCirujia cirujanoCirujiaListOldCirujanoCirujia : cirujanoCirujiaListOld) {
                if (!cirujanoCirujiaListNew.contains(cirujanoCirujiaListOldCirujanoCirujia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CirujanoCirujia " + cirujanoCirujiaListOldCirujanoCirujia + " since its fkidCirujia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkSuspencionNew != null) {
                fkSuspencionNew = em.getReference(fkSuspencionNew.getClass(), fkSuspencionNew.getIdSuspenciones());
                cirujia.setFkSuspencion(fkSuspencionNew);
            }
            if (fkRiesgoNew != null) {
                fkRiesgoNew = em.getReference(fkRiesgoNew.getClass(), fkRiesgoNew.getIdRiesgo());
                cirujia.setFkRiesgo(fkRiesgoNew);
            }
            if (fkQuirofanoNew != null) {
                fkQuirofanoNew = em.getReference(fkQuirofanoNew.getClass(), fkQuirofanoNew.getIdQuirofano());
                cirujia.setFkQuirofano(fkQuirofanoNew);
            }
            if (fkPacienteNew != null) {
                fkPacienteNew = em.getReference(fkPacienteNew.getClass(), fkPacienteNew.getIdPaciente());
                cirujia.setFkPaciente(fkPacienteNew);
            }
            if (fkCie9New != null) {
                fkCie9New = em.getReference(fkCie9New.getClass(), fkCie9New.getIdProcedimiento());
                cirujia.setFkCie9(fkCie9New);
            }
            List<CirujanoCirujia> attachedCirujanoCirujiaListNew = new ArrayList<CirujanoCirujia>();
            for (CirujanoCirujia cirujanoCirujiaListNewCirujanoCirujiaToAttach : cirujanoCirujiaListNew) {
                cirujanoCirujiaListNewCirujanoCirujiaToAttach = em.getReference(cirujanoCirujiaListNewCirujanoCirujiaToAttach.getClass(), cirujanoCirujiaListNewCirujanoCirujiaToAttach.getIdCirujanoCirujia());
                attachedCirujanoCirujiaListNew.add(cirujanoCirujiaListNewCirujanoCirujiaToAttach);
            }
            cirujanoCirujiaListNew = attachedCirujanoCirujiaListNew;
            cirujia.setCirujanoCirujiaList(cirujanoCirujiaListNew);
            cirujia = em.merge(cirujia);
            if (fkSuspencionOld != null && !fkSuspencionOld.equals(fkSuspencionNew)) {
                fkSuspencionOld.getCirujiaList().remove(cirujia);
                fkSuspencionOld = em.merge(fkSuspencionOld);
            }
            if (fkSuspencionNew != null && !fkSuspencionNew.equals(fkSuspencionOld)) {
                fkSuspencionNew.getCirujiaList().add(cirujia);
                fkSuspencionNew = em.merge(fkSuspencionNew);
            }
            if (fkRiesgoOld != null && !fkRiesgoOld.equals(fkRiesgoNew)) {
                fkRiesgoOld.getCirujiaList().remove(cirujia);
                fkRiesgoOld = em.merge(fkRiesgoOld);
            }
            if (fkRiesgoNew != null && !fkRiesgoNew.equals(fkRiesgoOld)) {
                fkRiesgoNew.getCirujiaList().add(cirujia);
                fkRiesgoNew = em.merge(fkRiesgoNew);
            }
            if (fkQuirofanoOld != null && !fkQuirofanoOld.equals(fkQuirofanoNew)) {
                fkQuirofanoOld.getCirujiaList().remove(cirujia);
                fkQuirofanoOld = em.merge(fkQuirofanoOld);
            }
            if (fkQuirofanoNew != null && !fkQuirofanoNew.equals(fkQuirofanoOld)) {
                fkQuirofanoNew.getCirujiaList().add(cirujia);
                fkQuirofanoNew = em.merge(fkQuirofanoNew);
            }
            if (fkPacienteOld != null && !fkPacienteOld.equals(fkPacienteNew)) {
                fkPacienteOld.getCirujiaList().remove(cirujia);
                fkPacienteOld = em.merge(fkPacienteOld);
            }
            if (fkPacienteNew != null && !fkPacienteNew.equals(fkPacienteOld)) {
                fkPacienteNew.getCirujiaList().add(cirujia);
                fkPacienteNew = em.merge(fkPacienteNew);
            }
            if (fkCie9Old != null && !fkCie9Old.equals(fkCie9New)) {
                fkCie9Old.getCirujiaList().remove(cirujia);
                fkCie9Old = em.merge(fkCie9Old);
            }
            if (fkCie9New != null && !fkCie9New.equals(fkCie9Old)) {
                fkCie9New.getCirujiaList().add(cirujia);
                fkCie9New = em.merge(fkCie9New);
            }
            for (CirujanoCirujia cirujanoCirujiaListNewCirujanoCirujia : cirujanoCirujiaListNew) {
                if (!cirujanoCirujiaListOld.contains(cirujanoCirujiaListNewCirujanoCirujia)) {
                    Cirujia oldFkidCirujiaOfCirujanoCirujiaListNewCirujanoCirujia = cirujanoCirujiaListNewCirujanoCirujia.getFkidCirujia();
                    cirujanoCirujiaListNewCirujanoCirujia.setFkidCirujia(cirujia);
                    cirujanoCirujiaListNewCirujanoCirujia = em.merge(cirujanoCirujiaListNewCirujanoCirujia);
                    if (oldFkidCirujiaOfCirujanoCirujiaListNewCirujanoCirujia != null && !oldFkidCirujiaOfCirujanoCirujiaListNewCirujanoCirujia.equals(cirujia)) {
                        oldFkidCirujiaOfCirujanoCirujiaListNewCirujanoCirujia.getCirujanoCirujiaList().remove(cirujanoCirujiaListNewCirujanoCirujia);
                        oldFkidCirujiaOfCirujanoCirujiaListNewCirujanoCirujia = em.merge(oldFkidCirujiaOfCirujanoCirujiaListNewCirujanoCirujia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cirujia.getIdCirujia();
                if (findCirujia(id) == null) {
                    throw new NonexistentEntityException("The cirujia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujia cirujia;
            try {
                cirujia = em.getReference(Cirujia.class, id);
                cirujia.getIdCirujia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cirujia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CirujanoCirujia> cirujanoCirujiaListOrphanCheck = cirujia.getCirujanoCirujiaList();
            for (CirujanoCirujia cirujanoCirujiaListOrphanCheckCirujanoCirujia : cirujanoCirujiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cirujia (" + cirujia + ") cannot be destroyed since the CirujanoCirujia " + cirujanoCirujiaListOrphanCheckCirujanoCirujia + " in its cirujanoCirujiaList field has a non-nullable fkidCirujia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Suspenciones fkSuspencion = cirujia.getFkSuspencion();
            if (fkSuspencion != null) {
                fkSuspencion.getCirujiaList().remove(cirujia);
                fkSuspencion = em.merge(fkSuspencion);
            }
            Riesgo fkRiesgo = cirujia.getFkRiesgo();
            if (fkRiesgo != null) {
                fkRiesgo.getCirujiaList().remove(cirujia);
                fkRiesgo = em.merge(fkRiesgo);
            }
            Quirofano fkQuirofano = cirujia.getFkQuirofano();
            if (fkQuirofano != null) {
                fkQuirofano.getCirujiaList().remove(cirujia);
                fkQuirofano = em.merge(fkQuirofano);
            }
            Paciente fkPaciente = cirujia.getFkPaciente();
            if (fkPaciente != null) {
                fkPaciente.getCirujiaList().remove(cirujia);
                fkPaciente = em.merge(fkPaciente);
            }
            Cie9 fkCie9 = cirujia.getFkCie9();
            if (fkCie9 != null) {
                fkCie9.getCirujiaList().remove(cirujia);
                fkCie9 = em.merge(fkCie9);
            }
            em.remove(cirujia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cirujia> findCirujiaEntities() {
        return findCirujiaEntities(true, -1, -1);
    }

    public List<Cirujia> findCirujiaEntities(int maxResults, int firstResult) {
        return findCirujiaEntities(false, maxResults, firstResult);
    }

    private List<Cirujia> findCirujiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cirujia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cirujia findCirujia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cirujia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCirujiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cirujia> rt = cq.from(Cirujia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Cirujia> tipoCirujia(String tipoCirujia){    
        EntityManager em = getEntityManager();
        try{
            List<Cirujia> cirujias = new ArrayList<Cirujia>();
            TypedQuery<Cirujia> queryMayorMenor = em.createNamedQuery("Cirujia.findByTipoAnestecia", Cirujia.class);
            queryMayorMenor.setParameter("tipoAnestecia", tipoCirujia);
            cirujias = queryMayorMenor.getResultList();
            return cirujias;
        } finally {
            em.close();
        }
    }

    public List<Cirujia> getRealizadas(){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createNamedQuery("Cirujia.findByRealizada",Cirujia.class);
            query.setParameter("realizada",1);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public List<Cirujia> getRealizadasAnual(int anio){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :realizada " +
                    "and function('YEAR',c.fecha) = :anio", Cirujia.class);
            query.setParameter("realizada",1);
            query.setParameter("anio",anio);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public List<Cirujia> getRealizadasMensual(int anio, int mes){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :realizada " +
                    "and function('YEAR',c.fecha) = :anio and function('MONTH',c.fecha) = :mes", Cirujia.class);
            query.setParameter("realizada",1);
            query.setParameter("anio",anio);
            query.setParameter("mes", mes);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    public List<Cirujia> getSuspendidas(int year){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createNamedQuery("Cirujia.findByRealizadaByYear", Cirujia.class);
            query.setParameter("realizada", 0);
            query.setParameter("year", year);
            return query.getResultList();
        }finally{
            em.close();
        }            
    }

    public List<Cirujia> getSuspendidasPorMes(int year, int mes){
        EntityManager em = getEntityManager();
        try{
            int i = 1;
            TypedQuery<Cirujia> query = em.createQuery("SELECT c FROM Cirujia c WHERE c.realizada = :i " +
                    "AND FUNCTION('YEAR',c.fecha) = :year AND FUNCTION('MONTH',c.fecha) = :mes", Cirujia.class);
            query.setParameter("i", 0);
            query.setParameter("mes", mes);
            query.setParameter("year", year);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    public List<Cirujia> getAnualesEspecialidad(int year){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where " +
                    "c.realizada = :i and function('YEAR',c.fecha) = :year", Cirujia.class);
            query.setParameter("i",1);
            query.setParameter("year", year);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> getMayorElectivaAnual(int anio){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery(
                    "SELECT c FROM Cirujia c where FUNCTION('YEAR', c.fecha) = :anio and " +
                            "c.realizada = :r and c.emergencia = :e",
                    Cirujia.class);
            query.setParameter("anio",anio);
            query.setParameter("r",1);
            query.setParameter("e",0);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> getMayorElectivaMensual(int anio, int mes){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery(
                    "SELECT c FROM Cirujia c where FUNCTION('YEAR', c.fecha) = :anio " +
                            "and function('MONTH',c.fecha)= :m and c.realizada = :r and c.emergencia = :e " +
                            "and c.tipoAnestecia=:tipo",
                    Cirujia.class);
            query.setParameter("anio",anio);
            query.setParameter("r",1);
            query.setParameter("e",0);
            query.setParameter("m",mes);
            query.setParameter("tipo","General");
            return query.getResultList();
        }finally {
            em.close();
        }
    }


    //TODO: Graficar
    public List<Cirujia> getMayorEmergenciaAnual(int anio){
     EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :real " +
                    "and c.tipoAnestecia= :tipo and c.emergencia= :eme " +
                    "and function('YEAR',c.fecha)=:year"
                    ,Cirujia.class);
            query.setParameter("real",1);
            query.setParameter("tipo","General");
            query.setParameter("eme",1);
            query.setParameter("year",anio);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> getMayorEmergenciaMensual(int anio, int mes){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :real " +
                    "and c.tipoAnestecia= :tipo and c.emergencia= :eme and function('YEAR',c.fecha)=:year " +
                    "and function('MONTH',c.fecha)=:mes",Cirujia.class);
            query.setParameter("real",1);
            query.setParameter("tipo","General");
            query.setParameter("eme",1);
            query.setParameter("year",anio);
            query.setParameter("mes",mes);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> getMenorElectivaAnual(int anio){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :real " +
                    "and c.tipoAnestecia= :tipo and c.emergencia= :eme and function('YEAR',c.fecha)=:year",Cirujia.class);
            query.setParameter("real",1);
            query.setParameter("tipo","Local");
            query.setParameter("eme",0);
            query.setParameter("year",anio);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> getMenorElectivaMensual(int anio, int mes){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :real " +
                    "and c.tipoAnestecia= :tipo and c.emergencia= :eme and function('YEAR',c.fecha)=:year " +
                    "and function('MONTH',c.fecha)=:mes",Cirujia.class);
            query.setParameter("real",1);
            query.setParameter("tipo","Local");
            query.setParameter("eme",0);
            query.setParameter("year",anio);
            query.setParameter("mes",mes);
            return query.getResultList();
        }finally{
            em.close();
        }
    }




    //TODO: Graficar
    public List<Cirujia> getMenorEmergenciaAnual(int anio){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :real " +
                    "and c.tipoAnestecia= :tipo and c.emergencia= :eme and function('YEAR',c.fecha)=:year",Cirujia.class);
            query.setParameter("real",1);
            query.setParameter("tipo","Local");
            query.setParameter("eme",1);
            query.setParameter("year",anio);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> getMenorEmergenciaMensual(int anio, int mes){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c where c.realizada = :real " +
                    "and c.tipoAnestecia= :tipo and c.emergencia= :eme and function('YEAR',c.fecha)=:year " +
                    "and function('MONTH',c.fecha)=:mes",Cirujia.class);
            query.setParameter("real",1);
            query.setParameter("tipo","Local");
            query.setParameter("eme",1);
            query.setParameter("year",anio);
            query.setParameter("mes",mes);
            return query.getResultList();
        }finally{
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> suspendidasPorCausasInstitucional(int anio){
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c join c.fkSuspencion s " +
                    "where c.realizada= :r and s.tipo= :tip order by s.idSuspenciones",Cirujia.class);
            query.setParameter("r",1);
            query.setParameter("tip","Institucional");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    //TODO: Graficar
    public List<Cirujia> suspendidasPorCausasNoInstitucional(int anio){
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cirujia> query = em.createQuery("select c from Cirujia c join c.fkSuspencion s " +
                    "where c.realizada= :r and s.tipo= :tip order by s.idSuspenciones",Cirujia.class);
            query.setParameter("r",1);
            query.setParameter("tip","No Institucional");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}