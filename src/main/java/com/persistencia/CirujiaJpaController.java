/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.IllegalOrphanException;
import com.persistencia.exceptions.NonexistentEntityException;
import com.persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    public void create(Cirujia cirujia) throws PreexistingEntityException, Exception {
        if (cirujia.getCirujiaPK() == null) {
            cirujia.setCirujiaPK(new CirujiaPK());
        }
        if (cirujia.getCirujanoCirujiaList() == null) {
            cirujia.setCirujanoCirujiaList(new ArrayList<CirujanoCirujia>());
        }
        cirujia.getCirujiaPK().setFkCie9(cirujia.getCie9().getIdProcedimiento());
        cirujia.getCirujiaPK().setFkPaciente(cirujia.getPaciente().getIdPaciente());
        cirujia.getCirujiaPK().setFkQuirofano(cirujia.getQuirofano().getIdQuirofano());
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
            Quirofano quirofano = cirujia.getQuirofano();
            if (quirofano != null) {
                quirofano = em.getReference(quirofano.getClass(), quirofano.getIdQuirofano());
                cirujia.setQuirofano(quirofano);
            }
            Paciente paciente = cirujia.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getIdPaciente());
                cirujia.setPaciente(paciente);
            }
            Cie9 cie9 = cirujia.getCie9();
            if (cie9 != null) {
                cie9 = em.getReference(cie9.getClass(), cie9.getIdProcedimiento());
                cirujia.setCie9(cie9);
            }
            List<CirujanoCirujia> attachedCirujanoCirujiaList = new ArrayList<CirujanoCirujia>();
            for (CirujanoCirujia cirujanoCirujiaListCirujanoCirujiaToAttach : cirujia.getCirujanoCirujiaList()) {
                cirujanoCirujiaListCirujanoCirujiaToAttach = em.getReference(cirujanoCirujiaListCirujanoCirujiaToAttach.getClass(), cirujanoCirujiaListCirujanoCirujiaToAttach.getCirujanoCirujiaPK());
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
            if (quirofano != null) {
                quirofano.getCirujiaList().add(cirujia);
                quirofano = em.merge(quirofano);
            }
            if (paciente != null) {
                paciente.getCirujiaList().add(cirujia);
                paciente = em.merge(paciente);
            }
            if (cie9 != null) {
                cie9.getCirujiaList().add(cirujia);
                cie9 = em.merge(cie9);
            }
            for (CirujanoCirujia cirujanoCirujiaListCirujanoCirujia : cirujia.getCirujanoCirujiaList()) {
                Cirujia oldCirujiaOfCirujanoCirujiaListCirujanoCirujia = cirujanoCirujiaListCirujanoCirujia.getCirujia();
                cirujanoCirujiaListCirujanoCirujia.setCirujia(cirujia);
                cirujanoCirujiaListCirujanoCirujia = em.merge(cirujanoCirujiaListCirujanoCirujia);
                if (oldCirujiaOfCirujanoCirujiaListCirujanoCirujia != null) {
                    oldCirujiaOfCirujanoCirujiaListCirujanoCirujia.getCirujanoCirujiaList().remove(cirujanoCirujiaListCirujanoCirujia);
                    oldCirujiaOfCirujanoCirujiaListCirujanoCirujia = em.merge(oldCirujiaOfCirujanoCirujiaListCirujanoCirujia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCirujia(cirujia.getCirujiaPK()) != null) {
                throw new PreexistingEntityException("Cirujia " + cirujia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cirujia cirujia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        cirujia.getCirujiaPK().setFkCie9(cirujia.getCie9().getIdProcedimiento());
        cirujia.getCirujiaPK().setFkPaciente(cirujia.getPaciente().getIdPaciente());
        cirujia.getCirujiaPK().setFkQuirofano(cirujia.getQuirofano().getIdQuirofano());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujia persistentCirujia = em.find(Cirujia.class, cirujia.getCirujiaPK());
            Suspenciones fkSuspencionOld = persistentCirujia.getFkSuspencion();
            Suspenciones fkSuspencionNew = cirujia.getFkSuspencion();
            Riesgo fkRiesgoOld = persistentCirujia.getFkRiesgo();
            Riesgo fkRiesgoNew = cirujia.getFkRiesgo();
            Quirofano quirofanoOld = persistentCirujia.getQuirofano();
            Quirofano quirofanoNew = cirujia.getQuirofano();
            Paciente pacienteOld = persistentCirujia.getPaciente();
            Paciente pacienteNew = cirujia.getPaciente();
            Cie9 cie9Old = persistentCirujia.getCie9();
            Cie9 cie9New = cirujia.getCie9();
            List<CirujanoCirujia> cirujanoCirujiaListOld = persistentCirujia.getCirujanoCirujiaList();
            List<CirujanoCirujia> cirujanoCirujiaListNew = cirujia.getCirujanoCirujiaList();
            List<String> illegalOrphanMessages = null;
            for (CirujanoCirujia cirujanoCirujiaListOldCirujanoCirujia : cirujanoCirujiaListOld) {
                if (!cirujanoCirujiaListNew.contains(cirujanoCirujiaListOldCirujanoCirujia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CirujanoCirujia " + cirujanoCirujiaListOldCirujanoCirujia + " since its cirujia field is not nullable.");
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
            if (quirofanoNew != null) {
                quirofanoNew = em.getReference(quirofanoNew.getClass(), quirofanoNew.getIdQuirofano());
                cirujia.setQuirofano(quirofanoNew);
            }
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getIdPaciente());
                cirujia.setPaciente(pacienteNew);
            }
            if (cie9New != null) {
                cie9New = em.getReference(cie9New.getClass(), cie9New.getIdProcedimiento());
                cirujia.setCie9(cie9New);
            }
            List<CirujanoCirujia> attachedCirujanoCirujiaListNew = new ArrayList<CirujanoCirujia>();
            for (CirujanoCirujia cirujanoCirujiaListNewCirujanoCirujiaToAttach : cirujanoCirujiaListNew) {
                cirujanoCirujiaListNewCirujanoCirujiaToAttach = em.getReference(cirujanoCirujiaListNewCirujanoCirujiaToAttach.getClass(), cirujanoCirujiaListNewCirujanoCirujiaToAttach.getCirujanoCirujiaPK());
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
            if (quirofanoOld != null && !quirofanoOld.equals(quirofanoNew)) {
                quirofanoOld.getCirujiaList().remove(cirujia);
                quirofanoOld = em.merge(quirofanoOld);
            }
            if (quirofanoNew != null && !quirofanoNew.equals(quirofanoOld)) {
                quirofanoNew.getCirujiaList().add(cirujia);
                quirofanoNew = em.merge(quirofanoNew);
            }
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getCirujiaList().remove(cirujia);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getCirujiaList().add(cirujia);
                pacienteNew = em.merge(pacienteNew);
            }
            if (cie9Old != null && !cie9Old.equals(cie9New)) {
                cie9Old.getCirujiaList().remove(cirujia);
                cie9Old = em.merge(cie9Old);
            }
            if (cie9New != null && !cie9New.equals(cie9Old)) {
                cie9New.getCirujiaList().add(cirujia);
                cie9New = em.merge(cie9New);
            }
            for (CirujanoCirujia cirujanoCirujiaListNewCirujanoCirujia : cirujanoCirujiaListNew) {
                if (!cirujanoCirujiaListOld.contains(cirujanoCirujiaListNewCirujanoCirujia)) {
                    Cirujia oldCirujiaOfCirujanoCirujiaListNewCirujanoCirujia = cirujanoCirujiaListNewCirujanoCirujia.getCirujia();
                    cirujanoCirujiaListNewCirujanoCirujia.setCirujia(cirujia);
                    cirujanoCirujiaListNewCirujanoCirujia = em.merge(cirujanoCirujiaListNewCirujanoCirujia);
                    if (oldCirujiaOfCirujanoCirujiaListNewCirujanoCirujia != null && !oldCirujiaOfCirujanoCirujiaListNewCirujanoCirujia.equals(cirujia)) {
                        oldCirujiaOfCirujanoCirujiaListNewCirujanoCirujia.getCirujanoCirujiaList().remove(cirujanoCirujiaListNewCirujanoCirujia);
                        oldCirujiaOfCirujanoCirujiaListNewCirujanoCirujia = em.merge(oldCirujiaOfCirujanoCirujiaListNewCirujanoCirujia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CirujiaPK id = cirujia.getCirujiaPK();
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

    public void destroy(CirujiaPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujia cirujia;
            try {
                cirujia = em.getReference(Cirujia.class, id);
                cirujia.getCirujiaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cirujia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CirujanoCirujia> cirujanoCirujiaListOrphanCheck = cirujia.getCirujanoCirujiaList();
            for (CirujanoCirujia cirujanoCirujiaListOrphanCheckCirujanoCirujia : cirujanoCirujiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cirujia (" + cirujia + ") cannot be destroyed since the CirujanoCirujia " + cirujanoCirujiaListOrphanCheckCirujanoCirujia + " in its cirujanoCirujiaList field has a non-nullable cirujia field.");
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
            Quirofano quirofano = cirujia.getQuirofano();
            if (quirofano != null) {
                quirofano.getCirujiaList().remove(cirujia);
                quirofano = em.merge(quirofano);
            }
            Paciente paciente = cirujia.getPaciente();
            if (paciente != null) {
                paciente.getCirujiaList().remove(cirujia);
                paciente = em.merge(paciente);
            }
            Cie9 cie9 = cirujia.getCie9();
            if (cie9 != null) {
                cie9.getCirujiaList().remove(cirujia);
                cie9 = em.merge(cie9);
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

    public Cirujia findCirujia(CirujiaPK id) {
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
