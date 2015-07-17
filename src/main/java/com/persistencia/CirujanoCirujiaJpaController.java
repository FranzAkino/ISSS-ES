/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author akino
 */
public class CirujanoCirujiaJpaController implements Serializable {

    public CirujanoCirujiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CirujanoCirujia cirujanoCirujia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujia fkidCirujia = cirujanoCirujia.getFkidCirujia();
            if (fkidCirujia != null) {
                fkidCirujia = em.getReference(fkidCirujia.getClass(), fkidCirujia.getIdCirujia());
                cirujanoCirujia.setFkidCirujia(fkidCirujia);
            }
            Cirujano fkidCirujano = cirujanoCirujia.getFkidCirujano();
            if (fkidCirujano != null) {
                fkidCirujano = em.getReference(fkidCirujano.getClass(), fkidCirujano.getIdCirujano());
                cirujanoCirujia.setFkidCirujano(fkidCirujano);
            }
            em.persist(cirujanoCirujia);
            if (fkidCirujia != null) {
                fkidCirujia.getCirujanoCirujiaList().add(cirujanoCirujia);
                fkidCirujia = em.merge(fkidCirujia);
            }
            if (fkidCirujano != null) {
                fkidCirujano.getCirujanoCirujiaList().add(cirujanoCirujia);
                fkidCirujano = em.merge(fkidCirujano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CirujanoCirujia cirujanoCirujia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CirujanoCirujia persistentCirujanoCirujia = em.find(CirujanoCirujia.class, cirujanoCirujia.getIdCirujanoCirujia());
            Cirujia fkidCirujiaOld = persistentCirujanoCirujia.getFkidCirujia();
            Cirujia fkidCirujiaNew = cirujanoCirujia.getFkidCirujia();
            Cirujano fkidCirujanoOld = persistentCirujanoCirujia.getFkidCirujano();
            Cirujano fkidCirujanoNew = cirujanoCirujia.getFkidCirujano();
            if (fkidCirujiaNew != null) {
                fkidCirujiaNew = em.getReference(fkidCirujiaNew.getClass(), fkidCirujiaNew.getIdCirujia());
                cirujanoCirujia.setFkidCirujia(fkidCirujiaNew);
            }
            if (fkidCirujanoNew != null) {
                fkidCirujanoNew = em.getReference(fkidCirujanoNew.getClass(), fkidCirujanoNew.getIdCirujano());
                cirujanoCirujia.setFkidCirujano(fkidCirujanoNew);
            }
            cirujanoCirujia = em.merge(cirujanoCirujia);
            if (fkidCirujiaOld != null && !fkidCirujiaOld.equals(fkidCirujiaNew)) {
                fkidCirujiaOld.getCirujanoCirujiaList().remove(cirujanoCirujia);
                fkidCirujiaOld = em.merge(fkidCirujiaOld);
            }
            if (fkidCirujiaNew != null && !fkidCirujiaNew.equals(fkidCirujiaOld)) {
                fkidCirujiaNew.getCirujanoCirujiaList().add(cirujanoCirujia);
                fkidCirujiaNew = em.merge(fkidCirujiaNew);
            }
            if (fkidCirujanoOld != null && !fkidCirujanoOld.equals(fkidCirujanoNew)) {
                fkidCirujanoOld.getCirujanoCirujiaList().remove(cirujanoCirujia);
                fkidCirujanoOld = em.merge(fkidCirujanoOld);
            }
            if (fkidCirujanoNew != null && !fkidCirujanoNew.equals(fkidCirujanoOld)) {
                fkidCirujanoNew.getCirujanoCirujiaList().add(cirujanoCirujia);
                fkidCirujanoNew = em.merge(fkidCirujanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cirujanoCirujia.getIdCirujanoCirujia();
                if (findCirujanoCirujia(id) == null) {
                    throw new NonexistentEntityException("The cirujanoCirujia with id " + id + " no longer exists.");
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
            CirujanoCirujia cirujanoCirujia;
            try {
                cirujanoCirujia = em.getReference(CirujanoCirujia.class, id);
                cirujanoCirujia.getIdCirujanoCirujia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cirujanoCirujia with id " + id + " no longer exists.", enfe);
            }
            Cirujia fkidCirujia = cirujanoCirujia.getFkidCirujia();
            if (fkidCirujia != null) {
                fkidCirujia.getCirujanoCirujiaList().remove(cirujanoCirujia);
                fkidCirujia = em.merge(fkidCirujia);
            }
            Cirujano fkidCirujano = cirujanoCirujia.getFkidCirujano();
            if (fkidCirujano != null) {
                fkidCirujano.getCirujanoCirujiaList().remove(cirujanoCirujia);
                fkidCirujano = em.merge(fkidCirujano);
            }
            em.remove(cirujanoCirujia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CirujanoCirujia> findCirujanoCirujiaEntities() {
        return findCirujanoCirujiaEntities(true, -1, -1);
    }

    public List<CirujanoCirujia> findCirujanoCirujiaEntities(int maxResults, int firstResult) {
        return findCirujanoCirujiaEntities(false, maxResults, firstResult);
    }

    private List<CirujanoCirujia> findCirujanoCirujiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CirujanoCirujia.class));
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

    public CirujanoCirujia findCirujanoCirujia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CirujanoCirujia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCirujanoCirujiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CirujanoCirujia> rt = cq.from(CirujanoCirujia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
