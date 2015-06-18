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
public class HorarioJpaController implements Serializable {

    public HorarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) {
        if (horario.getCirujanoList() == null) {
            horario.setCirujanoList(new ArrayList<Cirujano>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cirujano> attachedCirujanoList = new ArrayList<Cirujano>();
            for (Cirujano cirujanoListCirujanoToAttach : horario.getCirujanoList()) {
                cirujanoListCirujanoToAttach = em.getReference(cirujanoListCirujanoToAttach.getClass(), cirujanoListCirujanoToAttach.getCirujanoPK());
                attachedCirujanoList.add(cirujanoListCirujanoToAttach);
            }
            horario.setCirujanoList(attachedCirujanoList);
            em.persist(horario);
            for (Cirujano cirujanoListCirujano : horario.getCirujanoList()) {
                Horario oldFkHorariosOfCirujanoListCirujano = cirujanoListCirujano.getFkHorarios();
                cirujanoListCirujano.setFkHorarios(horario);
                cirujanoListCirujano = em.merge(cirujanoListCirujano);
                if (oldFkHorariosOfCirujanoListCirujano != null) {
                    oldFkHorariosOfCirujanoListCirujano.getCirujanoList().remove(cirujanoListCirujano);
                    oldFkHorariosOfCirujanoListCirujano = em.merge(oldFkHorariosOfCirujanoListCirujano);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Horario horario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario persistentHorario = em.find(Horario.class, horario.getIdHorario());
            List<Cirujano> cirujanoListOld = persistentHorario.getCirujanoList();
            List<Cirujano> cirujanoListNew = horario.getCirujanoList();
            List<String> illegalOrphanMessages = null;
            for (Cirujano cirujanoListOldCirujano : cirujanoListOld) {
                if (!cirujanoListNew.contains(cirujanoListOldCirujano)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cirujano " + cirujanoListOldCirujano + " since its fkHorarios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cirujano> attachedCirujanoListNew = new ArrayList<Cirujano>();
            for (Cirujano cirujanoListNewCirujanoToAttach : cirujanoListNew) {
                cirujanoListNewCirujanoToAttach = em.getReference(cirujanoListNewCirujanoToAttach.getClass(), cirujanoListNewCirujanoToAttach.getCirujanoPK());
                attachedCirujanoListNew.add(cirujanoListNewCirujanoToAttach);
            }
            cirujanoListNew = attachedCirujanoListNew;
            horario.setCirujanoList(cirujanoListNew);
            horario = em.merge(horario);
            for (Cirujano cirujanoListNewCirujano : cirujanoListNew) {
                if (!cirujanoListOld.contains(cirujanoListNewCirujano)) {
                    Horario oldFkHorariosOfCirujanoListNewCirujano = cirujanoListNewCirujano.getFkHorarios();
                    cirujanoListNewCirujano.setFkHorarios(horario);
                    cirujanoListNewCirujano = em.merge(cirujanoListNewCirujano);
                    if (oldFkHorariosOfCirujanoListNewCirujano != null && !oldFkHorariosOfCirujanoListNewCirujano.equals(horario)) {
                        oldFkHorariosOfCirujanoListNewCirujano.getCirujanoList().remove(cirujanoListNewCirujano);
                        oldFkHorariosOfCirujanoListNewCirujano = em.merge(oldFkHorariosOfCirujanoListNewCirujano);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = horario.getIdHorario();
                if (findHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getIdHorario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cirujano> cirujanoListOrphanCheck = horario.getCirujanoList();
            for (Cirujano cirujanoListOrphanCheckCirujano : cirujanoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Cirujano " + cirujanoListOrphanCheckCirujano + " in its cirujanoList field has a non-nullable fkHorarios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Horario> findHorarioEntities() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Horario.class));
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

    public Horario findHorario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Horario> rt = cq.from(Horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
