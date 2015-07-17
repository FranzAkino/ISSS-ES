/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.NonexistentEntityException;
import com.persistencia.exceptions.PreexistingEntityException;
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

    public void create(Metas metas) throws PreexistingEntityException, Exception {
        if (metas.getMetasPK() == null) {
            metas.setMetasPK(new MetasPK());
        }
        metas.getMetasPK().setIdCirujanofk(metas.getCirujano().getIdCirujano());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujano cirujano = metas.getCirujano();
            if (cirujano != null) {
                cirujano = em.getReference(cirujano.getClass(), cirujano.getIdCirujano());
                metas.setCirujano(cirujano);
            }
            em.persist(metas);
            if (cirujano != null) {
                cirujano.getMetasList().add(metas);
                cirujano = em.merge(cirujano);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMetas(metas.getMetasPK()) != null) {
                throw new PreexistingEntityException("Metas " + metas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Metas metas) throws NonexistentEntityException, Exception {
        metas.getMetasPK().setIdCirujanofk(metas.getCirujano().getIdCirujano());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metas persistentMetas = em.find(Metas.class, metas.getMetasPK());
            Cirujano cirujanoOld = persistentMetas.getCirujano();
            Cirujano cirujanoNew = metas.getCirujano();
            if (cirujanoNew != null) {
                cirujanoNew = em.getReference(cirujanoNew.getClass(), cirujanoNew.getIdCirujano());
                metas.setCirujano(cirujanoNew);
            }
            metas = em.merge(metas);
            if (cirujanoOld != null && !cirujanoOld.equals(cirujanoNew)) {
                cirujanoOld.getMetasList().remove(metas);
                cirujanoOld = em.merge(cirujanoOld);
            }
            if (cirujanoNew != null && !cirujanoNew.equals(cirujanoOld)) {
                cirujanoNew.getMetasList().add(metas);
                cirujanoNew = em.merge(cirujanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MetasPK id = metas.getMetasPK();
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

    public void destroy(MetasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metas metas;
            try {
                metas = em.getReference(Metas.class, id);
                metas.getMetasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metas with id " + id + " no longer exists.", enfe);
            }
            Cirujano cirujano = metas.getCirujano();
            if (cirujano != null) {
                cirujano.getMetasList().remove(metas);
                cirujano = em.merge(cirujano);
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

    public Metas findMetas(MetasPK id) {
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
