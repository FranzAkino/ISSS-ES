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
public class MetasJpaController implements Serializable {

    public MetasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Metas metas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujano fkCirujano = metas.getFkCirujano();
            if (fkCirujano != null) {
                fkCirujano = em.getReference(fkCirujano.getClass(), fkCirujano.getIdCirujano());
                metas.setFkCirujano(fkCirujano);
            }
            em.persist(metas);
            if (fkCirujano != null) {
                fkCirujano.getMetasList().add(metas);
                fkCirujano = em.merge(fkCirujano);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Metas metas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metas persistentMetas = em.find(Metas.class, metas.getIdMetas());
            Cirujano fkCirujanoOld = persistentMetas.getFkCirujano();
            Cirujano fkCirujanoNew = metas.getFkCirujano();
            if (fkCirujanoNew != null) {
                fkCirujanoNew = em.getReference(fkCirujanoNew.getClass(), fkCirujanoNew.getIdCirujano());
                metas.setFkCirujano(fkCirujanoNew);
            }
            metas = em.merge(metas);
            if (fkCirujanoOld != null && !fkCirujanoOld.equals(fkCirujanoNew)) {
                fkCirujanoOld.getMetasList().remove(metas);
                fkCirujanoOld = em.merge(fkCirujanoOld);
            }
            if (fkCirujanoNew != null && !fkCirujanoNew.equals(fkCirujanoOld)) {
                fkCirujanoNew.getMetasList().add(metas);
                fkCirujanoNew = em.merge(fkCirujanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = metas.getIdMetas();
                if (findMetas(id) == null) {
                    throw new NonexistentEntityException("The metas with id " + id + " no longer exists.");
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
            Metas metas;
            try {
                metas = em.getReference(Metas.class, id);
                metas.getIdMetas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metas with id " + id + " no longer exists.", enfe);
            }
            Cirujano fkCirujano = metas.getFkCirujano();
            if (fkCirujano != null) {
                fkCirujano.getMetasList().remove(metas);
                fkCirujano = em.merge(fkCirujano);
            }
            em.remove(metas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Metas> findMetasEntities() {
        return findMetasEntities(true, -1, -1);
    }

    public List<Metas> findMetasEntities(int maxResults, int firstResult) {
        return findMetasEntities(false, maxResults, firstResult);
    }

    private List<Metas> findMetasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Metas.class));
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

    public Metas findMetas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Metas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Metas> rt = cq.from(Metas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
