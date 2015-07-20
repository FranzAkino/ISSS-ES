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
public class RiesgoJpaController implements Serializable {

    public RiesgoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Riesgo riesgo) {
        if (riesgo.getCirujiaList() == null) {
            riesgo.setCirujiaList(new ArrayList<Cirujia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cirujia> attachedCirujiaList = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListCirujiaToAttach : riesgo.getCirujiaList()) {
                cirujiaListCirujiaToAttach = em.getReference(cirujiaListCirujiaToAttach.getClass(), cirujiaListCirujiaToAttach.getIdCirujia());
                attachedCirujiaList.add(cirujiaListCirujiaToAttach);
            }
            riesgo.setCirujiaList(attachedCirujiaList);
            em.persist(riesgo);
            for (Cirujia cirujiaListCirujia : riesgo.getCirujiaList()) {
                Riesgo oldFkRiesgoOfCirujiaListCirujia = cirujiaListCirujia.getFkRiesgo();
                cirujiaListCirujia.setFkRiesgo(riesgo);
                cirujiaListCirujia = em.merge(cirujiaListCirujia);
                if (oldFkRiesgoOfCirujiaListCirujia != null) {
                    oldFkRiesgoOfCirujiaListCirujia.getCirujiaList().remove(cirujiaListCirujia);
                    oldFkRiesgoOfCirujiaListCirujia = em.merge(oldFkRiesgoOfCirujiaListCirujia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Riesgo riesgo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Riesgo persistentRiesgo = em.find(Riesgo.class, riesgo.getIdRiesgo());
            List<Cirujia> cirujiaListOld = persistentRiesgo.getCirujiaList();
            List<Cirujia> cirujiaListNew = riesgo.getCirujiaList();
            List<String> illegalOrphanMessages = null;
            for (Cirujia cirujiaListOldCirujia : cirujiaListOld) {
                if (!cirujiaListNew.contains(cirujiaListOldCirujia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cirujia " + cirujiaListOldCirujia + " since its fkRiesgo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cirujia> attachedCirujiaListNew = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListNewCirujiaToAttach : cirujiaListNew) {
                cirujiaListNewCirujiaToAttach = em.getReference(cirujiaListNewCirujiaToAttach.getClass(), cirujiaListNewCirujiaToAttach.getIdCirujia());
                attachedCirujiaListNew.add(cirujiaListNewCirujiaToAttach);
            }
            cirujiaListNew = attachedCirujiaListNew;
            riesgo.setCirujiaList(cirujiaListNew);
            riesgo = em.merge(riesgo);
            for (Cirujia cirujiaListNewCirujia : cirujiaListNew) {
                if (!cirujiaListOld.contains(cirujiaListNewCirujia)) {
                    Riesgo oldFkRiesgoOfCirujiaListNewCirujia = cirujiaListNewCirujia.getFkRiesgo();
                    cirujiaListNewCirujia.setFkRiesgo(riesgo);
                    cirujiaListNewCirujia = em.merge(cirujiaListNewCirujia);
                    if (oldFkRiesgoOfCirujiaListNewCirujia != null && !oldFkRiesgoOfCirujiaListNewCirujia.equals(riesgo)) {
                        oldFkRiesgoOfCirujiaListNewCirujia.getCirujiaList().remove(cirujiaListNewCirujia);
                        oldFkRiesgoOfCirujiaListNewCirujia = em.merge(oldFkRiesgoOfCirujiaListNewCirujia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = riesgo.getIdRiesgo();
                if (findRiesgo(id) == null) {
                    throw new NonexistentEntityException("The riesgo with id " + id + " no longer exists.");
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
            Riesgo riesgo;
            try {
                riesgo = em.getReference(Riesgo.class, id);
                riesgo.getIdRiesgo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The riesgo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cirujia> cirujiaListOrphanCheck = riesgo.getCirujiaList();
            for (Cirujia cirujiaListOrphanCheckCirujia : cirujiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Riesgo (" + riesgo + ") cannot be destroyed since the Cirujia " + cirujiaListOrphanCheckCirujia + " in its cirujiaList field has a non-nullable fkRiesgo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(riesgo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Riesgo> findRiesgoEntities() {
        return findRiesgoEntities(true, -1, -1);
    }

    public List<Riesgo> findRiesgoEntities(int maxResults, int firstResult) {
        return findRiesgoEntities(false, maxResults, firstResult);
    }

    private List<Riesgo> findRiesgoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Riesgo.class));
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

    public Riesgo findRiesgo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Riesgo.class, id);
        } finally {
            em.close();
        }
    }

    public int getRiesgoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Riesgo> rt = cq.from(Riesgo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
