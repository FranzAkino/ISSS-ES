/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.IllegalOrphanException;
import com.persistencia.exceptions.NonexistentEntityException;
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
public class EspecialidadJpaController implements Serializable {

    public EspecialidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especialidad especialidad) {
        if (especialidad.getCirujanoEspecialidadList() == null) {
            especialidad.setCirujanoEspecialidadList(new ArrayList<CirujanoEspecialidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CirujanoEspecialidad> attachedCirujanoEspecialidadList = new ArrayList<CirujanoEspecialidad>();
            for (CirujanoEspecialidad cirujanoEspecialidadListCirujanoEspecialidadToAttach : especialidad.getCirujanoEspecialidadList()) {
                cirujanoEspecialidadListCirujanoEspecialidadToAttach = em.getReference(cirujanoEspecialidadListCirujanoEspecialidadToAttach.getClass(), cirujanoEspecialidadListCirujanoEspecialidadToAttach.getCirujanoEspecialidadPK());
                attachedCirujanoEspecialidadList.add(cirujanoEspecialidadListCirujanoEspecialidadToAttach);
            }
            especialidad.setCirujanoEspecialidadList(attachedCirujanoEspecialidadList);
            em.persist(especialidad);
            for (CirujanoEspecialidad cirujanoEspecialidadListCirujanoEspecialidad : especialidad.getCirujanoEspecialidadList()) {
                Especialidad oldEspecialidadOfCirujanoEspecialidadListCirujanoEspecialidad = cirujanoEspecialidadListCirujanoEspecialidad.getEspecialidad();
                cirujanoEspecialidadListCirujanoEspecialidad.setEspecialidad(especialidad);
                cirujanoEspecialidadListCirujanoEspecialidad = em.merge(cirujanoEspecialidadListCirujanoEspecialidad);
                if (oldEspecialidadOfCirujanoEspecialidadListCirujanoEspecialidad != null) {
                    oldEspecialidadOfCirujanoEspecialidadListCirujanoEspecialidad.getCirujanoEspecialidadList().remove(cirujanoEspecialidadListCirujanoEspecialidad);
                    oldEspecialidadOfCirujanoEspecialidadListCirujanoEspecialidad = em.merge(oldEspecialidadOfCirujanoEspecialidadListCirujanoEspecialidad);
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
            List<CirujanoEspecialidad> cirujanoEspecialidadListOld = persistentEspecialidad.getCirujanoEspecialidadList();
            List<CirujanoEspecialidad> cirujanoEspecialidadListNew = especialidad.getCirujanoEspecialidadList();
            List<String> illegalOrphanMessages = null;
            for (CirujanoEspecialidad cirujanoEspecialidadListOldCirujanoEspecialidad : cirujanoEspecialidadListOld) {
                if (!cirujanoEspecialidadListNew.contains(cirujanoEspecialidadListOldCirujanoEspecialidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CirujanoEspecialidad " + cirujanoEspecialidadListOldCirujanoEspecialidad + " since its especialidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CirujanoEspecialidad> attachedCirujanoEspecialidadListNew = new ArrayList<CirujanoEspecialidad>();
            for (CirujanoEspecialidad cirujanoEspecialidadListNewCirujanoEspecialidadToAttach : cirujanoEspecialidadListNew) {
                cirujanoEspecialidadListNewCirujanoEspecialidadToAttach = em.getReference(cirujanoEspecialidadListNewCirujanoEspecialidadToAttach.getClass(), cirujanoEspecialidadListNewCirujanoEspecialidadToAttach.getCirujanoEspecialidadPK());
                attachedCirujanoEspecialidadListNew.add(cirujanoEspecialidadListNewCirujanoEspecialidadToAttach);
            }
            cirujanoEspecialidadListNew = attachedCirujanoEspecialidadListNew;
            especialidad.setCirujanoEspecialidadList(cirujanoEspecialidadListNew);
            especialidad = em.merge(especialidad);
            for (CirujanoEspecialidad cirujanoEspecialidadListNewCirujanoEspecialidad : cirujanoEspecialidadListNew) {
                if (!cirujanoEspecialidadListOld.contains(cirujanoEspecialidadListNewCirujanoEspecialidad)) {
                    Especialidad oldEspecialidadOfCirujanoEspecialidadListNewCirujanoEspecialidad = cirujanoEspecialidadListNewCirujanoEspecialidad.getEspecialidad();
                    cirujanoEspecialidadListNewCirujanoEspecialidad.setEspecialidad(especialidad);
                    cirujanoEspecialidadListNewCirujanoEspecialidad = em.merge(cirujanoEspecialidadListNewCirujanoEspecialidad);
                    if (oldEspecialidadOfCirujanoEspecialidadListNewCirujanoEspecialidad != null && !oldEspecialidadOfCirujanoEspecialidadListNewCirujanoEspecialidad.equals(especialidad)) {
                        oldEspecialidadOfCirujanoEspecialidadListNewCirujanoEspecialidad.getCirujanoEspecialidadList().remove(cirujanoEspecialidadListNewCirujanoEspecialidad);
                        oldEspecialidadOfCirujanoEspecialidadListNewCirujanoEspecialidad = em.merge(oldEspecialidadOfCirujanoEspecialidadListNewCirujanoEspecialidad);
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
            List<CirujanoEspecialidad> cirujanoEspecialidadListOrphanCheck = especialidad.getCirujanoEspecialidadList();
            for (CirujanoEspecialidad cirujanoEspecialidadListOrphanCheckCirujanoEspecialidad : cirujanoEspecialidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Especialidad (" + especialidad + ") cannot be destroyed since the CirujanoEspecialidad " + cirujanoEspecialidadListOrphanCheckCirujanoEspecialidad + " in its cirujanoEspecialidadList field has a non-nullable especialidad field.");
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

}
