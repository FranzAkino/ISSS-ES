/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.NonexistentEntityException;

import java.beans.Expression;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

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
            Cirujano fkCirujano = cirujia.getFkCirujano();
            if (fkCirujano != null) {
                fkCirujano = em.getReference(fkCirujano.getClass(), fkCirujano.getIdCirujano());
                cirujia.setFkCirujano(fkCirujano);
            }
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
            if (fkCirujano != null) {
                fkCirujano.getCirujiaList().add(cirujia);
                fkCirujano = em.merge(fkCirujano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cirujia cirujia) throws NonexistentEntityException, Exception {
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
            Cirujano fkCirujanoOld = persistentCirujia.getFkCirujano();
            Cirujano fkCirujanoNew = cirujia.getFkCirujano();
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
            if (fkCirujanoNew != null) {
                fkCirujanoNew = em.getReference(fkCirujanoNew.getClass(), fkCirujanoNew.getIdCirujano());
                cirujia.setFkCirujano(fkCirujanoNew);
            }
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
            if (fkCirujanoOld != null && !fkCirujanoOld.equals(fkCirujanoNew)) {
                fkCirujanoOld.getCirujiaList().remove(cirujia);
                fkCirujanoOld = em.merge(fkCirujanoOld);
            }
            if (fkCirujanoNew != null && !fkCirujanoNew.equals(fkCirujanoOld)) {
                fkCirujanoNew.getCirujiaList().add(cirujia);
                fkCirujanoNew = em.merge(fkCirujanoNew);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
            Cirujano fkCirujano = cirujia.getFkCirujano();
            if (fkCirujano != null) {
                fkCirujano.getCirujiaList().remove(cirujia);
                fkCirujano = em.merge(fkCirujano);
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

}
