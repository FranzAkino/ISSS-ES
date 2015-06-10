package com.persistencia;

/**
 * Created by akino on 06-09-15.
 */
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.persistencia.exceptions.NonexistentEntityException;
import com.persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author akino
 */
public class SuspencionesJpaController implements Serializable {

    public SuspencionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Suspenciones suspenciones) throws PreexistingEntityException, Exception {
        if (suspenciones.getCirujiaList() == null) {
            suspenciones.setCirujiaList(new ArrayList<Cirujia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cirujia> attachedCirujiaList = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListCirujiaToAttach : suspenciones.getCirujiaList()) {
                cirujiaListCirujiaToAttach = em.getReference(cirujiaListCirujiaToAttach.getClass(), cirujiaListCirujiaToAttach.getIdCirujia());
                attachedCirujiaList.add(cirujiaListCirujiaToAttach);
            }
            suspenciones.setCirujiaList(attachedCirujiaList);
            em.persist(suspenciones);
            for (Cirujia cirujiaListCirujia : suspenciones.getCirujiaList()) {
                Suspenciones oldFkSuspencionOfCirujiaListCirujia = cirujiaListCirujia.getFkSuspencion();
                cirujiaListCirujia.setFkSuspencion(suspenciones);
                cirujiaListCirujia = em.merge(cirujiaListCirujia);
                if (oldFkSuspencionOfCirujiaListCirujia != null) {
                    oldFkSuspencionOfCirujiaListCirujia.getCirujiaList().remove(cirujiaListCirujia);
                    oldFkSuspencionOfCirujiaListCirujia = em.merge(oldFkSuspencionOfCirujiaListCirujia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSuspenciones(suspenciones.getIdSuspenciones()) != null) {
                throw new PreexistingEntityException("Suspenciones " + suspenciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Suspenciones suspenciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suspenciones persistentSuspenciones = em.find(Suspenciones.class, suspenciones.getIdSuspenciones());
            List<Cirujia> cirujiaListOld = persistentSuspenciones.getCirujiaList();
            List<Cirujia> cirujiaListNew = suspenciones.getCirujiaList();
            List<Cirujia> attachedCirujiaListNew = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListNewCirujiaToAttach : cirujiaListNew) {
                cirujiaListNewCirujiaToAttach = em.getReference(cirujiaListNewCirujiaToAttach.getClass(), cirujiaListNewCirujiaToAttach.getIdCirujia());
                attachedCirujiaListNew.add(cirujiaListNewCirujiaToAttach);
            }
            cirujiaListNew = attachedCirujiaListNew;
            suspenciones.setCirujiaList(cirujiaListNew);
            suspenciones = em.merge(suspenciones);
            for (Cirujia cirujiaListOldCirujia : cirujiaListOld) {
                if (!cirujiaListNew.contains(cirujiaListOldCirujia)) {
                    cirujiaListOldCirujia.setFkSuspencion(null);
                    cirujiaListOldCirujia = em.merge(cirujiaListOldCirujia);
                }
            }
            for (Cirujia cirujiaListNewCirujia : cirujiaListNew) {
                if (!cirujiaListOld.contains(cirujiaListNewCirujia)) {
                    Suspenciones oldFkSuspencionOfCirujiaListNewCirujia = cirujiaListNewCirujia.getFkSuspencion();
                    cirujiaListNewCirujia.setFkSuspencion(suspenciones);
                    cirujiaListNewCirujia = em.merge(cirujiaListNewCirujia);
                    if (oldFkSuspencionOfCirujiaListNewCirujia != null && !oldFkSuspencionOfCirujiaListNewCirujia.equals(suspenciones)) {
                        oldFkSuspencionOfCirujiaListNewCirujia.getCirujiaList().remove(cirujiaListNewCirujia);
                        oldFkSuspencionOfCirujiaListNewCirujia = em.merge(oldFkSuspencionOfCirujiaListNewCirujia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = suspenciones.getIdSuspenciones();
                if (findSuspenciones(id) == null) {
                    throw new NonexistentEntityException("The suspenciones with id " + id + " no longer exists.");
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
            Suspenciones suspenciones;
            try {
                suspenciones = em.getReference(Suspenciones.class, id);
                suspenciones.getIdSuspenciones();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suspenciones with id " + id + " no longer exists.", enfe);
            }
            List<Cirujia> cirujiaList = suspenciones.getCirujiaList();
            for (Cirujia cirujiaListCirujia : cirujiaList) {
                cirujiaListCirujia.setFkSuspencion(null);
                cirujiaListCirujia = em.merge(cirujiaListCirujia);
            }
            em.remove(suspenciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Suspenciones> findSuspencionesEntities() {
        return findSuspencionesEntities(true, -1, -1);
    }

    public List<Suspenciones> findSuspencionesEntities(int maxResults, int firstResult) {
        return findSuspencionesEntities(false, maxResults, firstResult);
    }

    private List<Suspenciones> findSuspencionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Suspenciones.class));
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

    public Suspenciones findSuspenciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suspenciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuspencionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Suspenciones> rt = cq.from(Suspenciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
