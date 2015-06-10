package com.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.persistencia.exceptions.NonexistentEntityException;
import com.persistencia.exceptions.PreexistingEntityException;

/**
 * Created by akino on 06-09-15.
 */
public class CirujanoEspecialidadJpaController implements Serializable {

    public CirujanoEspecialidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CirujanoEspecialidad cirujanoEspecialidad) throws PreexistingEntityException, Exception {
        if (cirujanoEspecialidad.getCirujanoEspecialidadPK() == null) {
            cirujanoEspecialidad.setCirujanoEspecialidadPK(new CirujanoEspecialidadPK());
        }
        cirujanoEspecialidad.getCirujanoEspecialidadPK().setIdEspecialidad(cirujanoEspecialidad.getEspecialidad().getIdEspecialidad());
        cirujanoEspecialidad.getCirujanoEspecialidadPK().setIdCirujano(cirujanoEspecialidad.getCirujano().getIdCirujano());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidad especialidad = cirujanoEspecialidad.getEspecialidad();
            if (especialidad != null) {
                especialidad = em.getReference(especialidad.getClass(), especialidad.getIdEspecialidad());
                cirujanoEspecialidad.setEspecialidad(especialidad);
            }
            Cirujano cirujano = cirujanoEspecialidad.getCirujano();
            if (cirujano != null) {
                cirujano = em.getReference(cirujano.getClass(), cirujano.getIdCirujano());
                cirujanoEspecialidad.setCirujano(cirujano);
            }
            em.persist(cirujanoEspecialidad);
            if (especialidad != null) {
                especialidad.getCirujanoEspecialidadList().add(cirujanoEspecialidad);
                especialidad = em.merge(especialidad);
            }
            if (cirujano != null) {
                cirujano.getCirujanoEspecialidadList().add(cirujanoEspecialidad);
                cirujano = em.merge(cirujano);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCirujanoEspecialidad(cirujanoEspecialidad.getCirujanoEspecialidadPK()) != null) {
                throw new PreexistingEntityException("CirujanoEspecialidad " + cirujanoEspecialidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CirujanoEspecialidad cirujanoEspecialidad) throws NonexistentEntityException, Exception {
        cirujanoEspecialidad.getCirujanoEspecialidadPK().setIdEspecialidad(cirujanoEspecialidad.getEspecialidad().getIdEspecialidad());
        cirujanoEspecialidad.getCirujanoEspecialidadPK().setIdCirujano(cirujanoEspecialidad.getCirujano().getIdCirujano());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CirujanoEspecialidad persistentCirujanoEspecialidad = em.find(CirujanoEspecialidad.class, cirujanoEspecialidad.getCirujanoEspecialidadPK());
            Especialidad especialidadOld = persistentCirujanoEspecialidad.getEspecialidad();
            Especialidad especialidadNew = cirujanoEspecialidad.getEspecialidad();
            Cirujano cirujanoOld = persistentCirujanoEspecialidad.getCirujano();
            Cirujano cirujanoNew = cirujanoEspecialidad.getCirujano();
            if (especialidadNew != null) {
                especialidadNew = em.getReference(especialidadNew.getClass(), especialidadNew.getIdEspecialidad());
                cirujanoEspecialidad.setEspecialidad(especialidadNew);
            }
            if (cirujanoNew != null) {
                cirujanoNew = em.getReference(cirujanoNew.getClass(), cirujanoNew.getIdCirujano());
                cirujanoEspecialidad.setCirujano(cirujanoNew);
            }
            cirujanoEspecialidad = em.merge(cirujanoEspecialidad);
            if (especialidadOld != null && !especialidadOld.equals(especialidadNew)) {
                especialidadOld.getCirujanoEspecialidadList().remove(cirujanoEspecialidad);
                especialidadOld = em.merge(especialidadOld);
            }
            if (especialidadNew != null && !especialidadNew.equals(especialidadOld)) {
                especialidadNew.getCirujanoEspecialidadList().add(cirujanoEspecialidad);
                especialidadNew = em.merge(especialidadNew);
            }
            if (cirujanoOld != null && !cirujanoOld.equals(cirujanoNew)) {
                cirujanoOld.getCirujanoEspecialidadList().remove(cirujanoEspecialidad);
                cirujanoOld = em.merge(cirujanoOld);
            }
            if (cirujanoNew != null && !cirujanoNew.equals(cirujanoOld)) {
                cirujanoNew.getCirujanoEspecialidadList().add(cirujanoEspecialidad);
                cirujanoNew = em.merge(cirujanoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CirujanoEspecialidadPK id = cirujanoEspecialidad.getCirujanoEspecialidadPK();
                if (findCirujanoEspecialidad(id) == null) {
                    throw new NonexistentEntityException("The cirujanoEspecialidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CirujanoEspecialidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CirujanoEspecialidad cirujanoEspecialidad;
            try {
                cirujanoEspecialidad = em.getReference(CirujanoEspecialidad.class, id);
                cirujanoEspecialidad.getCirujanoEspecialidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cirujanoEspecialidad with id " + id + " no longer exists.", enfe);
            }
            Especialidad especialidad = cirujanoEspecialidad.getEspecialidad();
            if (especialidad != null) {
                especialidad.getCirujanoEspecialidadList().remove(cirujanoEspecialidad);
                especialidad = em.merge(especialidad);
            }
            Cirujano cirujano = cirujanoEspecialidad.getCirujano();
            if (cirujano != null) {
                cirujano.getCirujanoEspecialidadList().remove(cirujanoEspecialidad);
                cirujano = em.merge(cirujano);
            }
            em.remove(cirujanoEspecialidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CirujanoEspecialidad> findCirujanoEspecialidadEntities() {
        return findCirujanoEspecialidadEntities(true, -1, -1);
    }

    public List<CirujanoEspecialidad> findCirujanoEspecialidadEntities(int maxResults, int firstResult) {
        return findCirujanoEspecialidadEntities(false, maxResults, firstResult);
    }

    private List<CirujanoEspecialidad> findCirujanoEspecialidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CirujanoEspecialidad.class));
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

    public CirujanoEspecialidad findCirujanoEspecialidad(CirujanoEspecialidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CirujanoEspecialidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCirujanoEspecialidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CirujanoEspecialidad> rt = cq.from(CirujanoEspecialidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
