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
public class QuirofanoJpaController implements Serializable {

    public QuirofanoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Quirofano quirofano) throws PreexistingEntityException, Exception {
        if (quirofano.getCirujiaList() == null) {
            quirofano.setCirujiaList(new ArrayList<Cirujia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cirujia> attachedCirujiaList = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListCirujiaToAttach : quirofano.getCirujiaList()) {
                cirujiaListCirujiaToAttach = em.getReference(cirujiaListCirujiaToAttach.getClass(), cirujiaListCirujiaToAttach.getCirujiaPK());
                attachedCirujiaList.add(cirujiaListCirujiaToAttach);
            }
            quirofano.setCirujiaList(attachedCirujiaList);
            em.persist(quirofano);
            for (Cirujia cirujiaListCirujia : quirofano.getCirujiaList()) {
                Quirofano oldQuirofanoOfCirujiaListCirujia = cirujiaListCirujia.getQuirofano();
                cirujiaListCirujia.setQuirofano(quirofano);
                cirujiaListCirujia = em.merge(cirujiaListCirujia);
                if (oldQuirofanoOfCirujiaListCirujia != null) {
                    oldQuirofanoOfCirujiaListCirujia.getCirujiaList().remove(cirujiaListCirujia);
                    oldQuirofanoOfCirujiaListCirujia = em.merge(oldQuirofanoOfCirujiaListCirujia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findQuirofano(quirofano.getIdQuirofano()) != null) {
                throw new PreexistingEntityException("Quirofano " + quirofano + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Quirofano quirofano) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Quirofano persistentQuirofano = em.find(Quirofano.class, quirofano.getIdQuirofano());
            List<Cirujia> cirujiaListOld = persistentQuirofano.getCirujiaList();
            List<Cirujia> cirujiaListNew = quirofano.getCirujiaList();
            List<String> illegalOrphanMessages = null;
            for (Cirujia cirujiaListOldCirujia : cirujiaListOld) {
                if (!cirujiaListNew.contains(cirujiaListOldCirujia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cirujia " + cirujiaListOldCirujia + " since its quirofano field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cirujia> attachedCirujiaListNew = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListNewCirujiaToAttach : cirujiaListNew) {
                cirujiaListNewCirujiaToAttach = em.getReference(cirujiaListNewCirujiaToAttach.getClass(), cirujiaListNewCirujiaToAttach.getCirujiaPK());
                attachedCirujiaListNew.add(cirujiaListNewCirujiaToAttach);
            }
            cirujiaListNew = attachedCirujiaListNew;
            quirofano.setCirujiaList(cirujiaListNew);
            quirofano = em.merge(quirofano);
            for (Cirujia cirujiaListNewCirujia : cirujiaListNew) {
                if (!cirujiaListOld.contains(cirujiaListNewCirujia)) {
                    Quirofano oldQuirofanoOfCirujiaListNewCirujia = cirujiaListNewCirujia.getQuirofano();
                    cirujiaListNewCirujia.setQuirofano(quirofano);
                    cirujiaListNewCirujia = em.merge(cirujiaListNewCirujia);
                    if (oldQuirofanoOfCirujiaListNewCirujia != null && !oldQuirofanoOfCirujiaListNewCirujia.equals(quirofano)) {
                        oldQuirofanoOfCirujiaListNewCirujia.getCirujiaList().remove(cirujiaListNewCirujia);
                        oldQuirofanoOfCirujiaListNewCirujia = em.merge(oldQuirofanoOfCirujiaListNewCirujia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = quirofano.getIdQuirofano();
                if (findQuirofano(id) == null) {
                    throw new NonexistentEntityException("The quirofano with id " + id + " no longer exists.");
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
            Quirofano quirofano;
            try {
                quirofano = em.getReference(Quirofano.class, id);
                quirofano.getIdQuirofano();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The quirofano with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cirujia> cirujiaListOrphanCheck = quirofano.getCirujiaList();
            for (Cirujia cirujiaListOrphanCheckCirujia : cirujiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Quirofano (" + quirofano + ") cannot be destroyed since the Cirujia " + cirujiaListOrphanCheckCirujia + " in its cirujiaList field has a non-nullable quirofano field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(quirofano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Quirofano> findQuirofanoEntities() {
        return findQuirofanoEntities(true, -1, -1);
    }

    public List<Quirofano> findQuirofanoEntities(int maxResults, int firstResult) {
        return findQuirofanoEntities(false, maxResults, firstResult);
    }

    private List<Quirofano> findQuirofanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Quirofano.class));
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

    public Quirofano findQuirofano(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Quirofano.class, id);
        } finally {
            em.close();
        }
    }

    public int getQuirofanoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Quirofano> rt = cq.from(Quirofano.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
