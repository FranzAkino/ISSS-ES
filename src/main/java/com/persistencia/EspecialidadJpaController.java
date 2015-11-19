/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.IllegalOrphanException;
import com.persistencia.exceptions.NonexistentEntityException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akino
 */
public class EspecialidadJpaController implements Serializable {

    public EspecialidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especialidad especialidad) {
        if (especialidad.getCirujanoList() == null) {
            especialidad.setCirujanoList(new ArrayList<Cirujano>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cirujano> attachedCirujanoList = new ArrayList<Cirujano>();
            for (Cirujano cirujanoListCirujanoToAttach : especialidad.getCirujanoList()) {
                cirujanoListCirujanoToAttach = em.getReference(cirujanoListCirujanoToAttach.getClass(), cirujanoListCirujanoToAttach.getIdCirujano());
                attachedCirujanoList.add(cirujanoListCirujanoToAttach);
            }
            especialidad.setCirujanoList(attachedCirujanoList);
            em.persist(especialidad);
            for (Cirujano cirujanoListCirujano : especialidad.getCirujanoList()) {
                Especialidad oldFkidEspecialidadOfCirujanoListCirujano = cirujanoListCirujano.getFkidEspecialidad();
                cirujanoListCirujano.setFkidEspecialidad(especialidad);
                cirujanoListCirujano = em.merge(cirujanoListCirujano);
                if (oldFkidEspecialidadOfCirujanoListCirujano != null) {
                    oldFkidEspecialidadOfCirujanoListCirujano.getCirujanoList().remove(cirujanoListCirujano);
                    oldFkidEspecialidadOfCirujanoListCirujano = em.merge(oldFkidEspecialidadOfCirujanoListCirujano);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especialidad especialidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidad persistentEspecialidad = em.find(Especialidad.class, especialidad.getIdEspecialidad());
            List<Cirujano> cirujanoListOld = persistentEspecialidad.getCirujanoList();
            List<Cirujano> cirujanoListNew = especialidad.getCirujanoList();
            List<String> illegalOrphanMessages = null;
            for (Cirujano cirujanoListOldCirujano : cirujanoListOld) {
                if (!cirujanoListNew.contains(cirujanoListOldCirujano)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cirujano " + cirujanoListOldCirujano + " since its fkidEspecialidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cirujano> attachedCirujanoListNew = new ArrayList<Cirujano>();
            for (Cirujano cirujanoListNewCirujanoToAttach : cirujanoListNew) {
                cirujanoListNewCirujanoToAttach = em.getReference(cirujanoListNewCirujanoToAttach.getClass(), cirujanoListNewCirujanoToAttach.getIdCirujano());
                attachedCirujanoListNew.add(cirujanoListNewCirujanoToAttach);
            }
            cirujanoListNew = attachedCirujanoListNew;
            especialidad.setCirujanoList(cirujanoListNew);
            especialidad = em.merge(especialidad);
            for (Cirujano cirujanoListNewCirujano : cirujanoListNew) {
                if (!cirujanoListOld.contains(cirujanoListNewCirujano)) {
                    Especialidad oldFkidEspecialidadOfCirujanoListNewCirujano = cirujanoListNewCirujano.getFkidEspecialidad();
                    cirujanoListNewCirujano.setFkidEspecialidad(especialidad);
                    cirujanoListNewCirujano = em.merge(cirujanoListNewCirujano);
                    if (oldFkidEspecialidadOfCirujanoListNewCirujano != null && !oldFkidEspecialidadOfCirujanoListNewCirujano.equals(especialidad)) {
                        oldFkidEspecialidadOfCirujanoListNewCirujano.getCirujanoList().remove(cirujanoListNewCirujano);
                        oldFkidEspecialidadOfCirujanoListNewCirujano = em.merge(oldFkidEspecialidadOfCirujanoListNewCirujano);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = especialidad.getIdEspecialidad();
                if (findEspecialidad(id) == null) {
                    throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.");
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
            Especialidad especialidad;
            try {
                especialidad = em.getReference(Especialidad.class, id);
                especialidad.getIdEspecialidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especialidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cirujano> cirujanoListOrphanCheck = especialidad.getCirujanoList();
            for (Cirujano cirujanoListOrphanCheckCirujano : cirujanoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Especialidad (" + especialidad + ") cannot be destroyed since the Cirujano " + cirujanoListOrphanCheckCirujano + " in its cirujanoList field has a non-nullable fkidEspecialidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(especialidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especialidad> findEspecialidadEntities() {
        return findEspecialidadEntities(true, -1, -1);
    }

    public List<Especialidad> findEspecialidadEntities(int maxResults, int firstResult) {
        return findEspecialidadEntities(false, maxResults, firstResult);
    }

    private List<Especialidad> findEspecialidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especialidad.class));
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

    public Especialidad findEspecialidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especialidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecialidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especialidad> rt = cq.from(Especialidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Especialidad getCirujiaGeneral(){
        return findEspecialidad(1);
    }

    public Especialidad getGyO(){
        return findEspecialidad(2);
    }

    public Especialidad getGinecloobstetra(){
        return findEspecialidad(2);
    }

    public Especialidad getOftalmologo(){
        return findEspecialidad(3);
    }

    public Especialidad getOtorrino(){
        return findEspecialidad(4);
    }

    public Especialidad getOrtopeda(){
        return findEspecialidad(5);
    }

    public Especialidad getUrologo(){
        return findEspecialidad(6);
    }

    public Especialidad getNeuroCirujano(){
        return findEspecialidad(7);
    }

    public Especialidad getOncologo(){
        return findEspecialidad(8);
    }

    public Especialidad getPlastico(){
        return findEspecialidad(9);
    }

    public Especialidad getMastologo(){
        return findEspecialidad(10);
    }
    public Especialidad getVascular(){
        return findEspecialidad(11);
    }

}
