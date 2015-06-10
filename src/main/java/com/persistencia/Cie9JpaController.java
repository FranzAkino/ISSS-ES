package com.persistencia;

import com.persistencia.exceptions.IllegalOrphanException;
import com.persistencia.exceptions.NonexistentEntityException;
import com.persistencia.exceptions.PreexistingEntityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akino on 05-18-15.
 */
public class Cie9JpaController implements Serializable {

    public Cie9JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cie9 cie9) throws PreexistingEntityException, Exception {
        if (cie9.getCirujiaList() == null) {
            cie9.setCirujiaList(new ArrayList<Cirujia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cirujia> attachedCirujiaList = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListCirujiaToAttach : cie9.getCirujiaList()) {
                cirujiaListCirujiaToAttach = em.getReference(cirujiaListCirujiaToAttach.getClass(), cirujiaListCirujiaToAttach.getIdCirujia());
                attachedCirujiaList.add(cirujiaListCirujiaToAttach);
            }
            cie9.setCirujiaList(attachedCirujiaList);
            em.persist(cie9);
            for (Cirujia cirujiaListCirujia : cie9.getCirujiaList()) {
                Cie9 oldFkCie9OfCirujiaListCirujia = cirujiaListCirujia.getFkCie9();
                cirujiaListCirujia.setFkCie9(cie9);
                cirujiaListCirujia = em.merge(cirujiaListCirujia);
                if (oldFkCie9OfCirujiaListCirujia != null) {
                    oldFkCie9OfCirujiaListCirujia.getCirujiaList().remove(cirujiaListCirujia);
                    oldFkCie9OfCirujiaListCirujia = em.merge(oldFkCie9OfCirujiaListCirujia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCie9(cie9.getIdProcedimiento()) != null) {
                throw new PreexistingEntityException("Cie9 " + cie9 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cie9 cie9) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cie9 persistentCie9 = em.find(Cie9.class, cie9.getIdProcedimiento());
            List<Cirujia> cirujiaListOld = persistentCie9.getCirujiaList();
            List<Cirujia> cirujiaListNew = cie9.getCirujiaList();
            List<String> illegalOrphanMessages = null;
            for (Cirujia cirujiaListOldCirujia : cirujiaListOld) {
                if (!cirujiaListNew.contains(cirujiaListOldCirujia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cirujia " + cirujiaListOldCirujia + " since its fkCie9 field is not nullable.");
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
            cie9.setCirujiaList(cirujiaListNew);
            cie9 = em.merge(cie9);
            for (Cirujia cirujiaListNewCirujia : cirujiaListNew) {
                if (!cirujiaListOld.contains(cirujiaListNewCirujia)) {
                    Cie9 oldFkCie9OfCirujiaListNewCirujia = cirujiaListNewCirujia.getFkCie9();
                    cirujiaListNewCirujia.setFkCie9(cie9);
                    cirujiaListNewCirujia = em.merge(cirujiaListNewCirujia);
                    if (oldFkCie9OfCirujiaListNewCirujia != null && !oldFkCie9OfCirujiaListNewCirujia.equals(cie9)) {
                        oldFkCie9OfCirujiaListNewCirujia.getCirujiaList().remove(cirujiaListNewCirujia);
                        oldFkCie9OfCirujiaListNewCirujia = em.merge(oldFkCie9OfCirujiaListNewCirujia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cie9.getIdProcedimiento();
                if (findCie9(id) == null) {
                    throw new NonexistentEntityException("The cie9 with id " + id + " no longer exists.");
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
            Cie9 cie9;
            try {
                cie9 = em.getReference(Cie9.class, id);
                cie9.getIdProcedimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cie9 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cirujia> cirujiaListOrphanCheck = cie9.getCirujiaList();
            for (Cirujia cirujiaListOrphanCheckCirujia : cirujiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cie9 (" + cie9 + ") cannot be destroyed since the Cirujia " + cirujiaListOrphanCheckCirujia + " in its cirujiaList field has a non-nullable fkCie9 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cie9);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cie9> findCie9Entities() {
        return findCie9Entities(true, -1, -1);
    }

    public List<Cie9> findCie9Entities(int maxResults, int firstResult) {
        return findCie9Entities(false, maxResults, firstResult);
    }

    private List<Cie9> findCie9Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cie9.class));
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

    public Cie9 findCie9(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cie9.class, id);
        } finally {
            em.close();
        }
    }

    public int getCie9Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cie9> rt = cq.from(Cie9.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
