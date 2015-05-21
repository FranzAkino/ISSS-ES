/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.persistencia.exceptions.IllegalOrphanException;
import com.persistencia.exceptions.NonexistentEntityException;

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
 *
 * @author akino
 */
public class CirujanoJpaController implements Serializable {

    public CirujanoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cirujano cirujano) {
        if (cirujano.getMetasList() == null) {
            cirujano.setMetasList(new ArrayList<Metas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario fkHorarios = cirujano.getFkHorarios();
            if (fkHorarios != null) {
                fkHorarios = em.getReference(fkHorarios.getClass(), fkHorarios.getIdHorario());
                cirujano.setFkHorarios(fkHorarios);
            }
            Especialidad fkEspeciadlidades = cirujano.getFkEspeciadlidades();
            if (fkEspeciadlidades != null) {
                fkEspeciadlidades = em.getReference(fkEspeciadlidades.getClass(), fkEspeciadlidades.getIdEspecialidad());
                cirujano.setFkEspeciadlidades(fkEspeciadlidades);
            }
            List<Metas> attachedMetasList = new ArrayList<Metas>();
            for (Metas metasListMetasToAttach : cirujano.getMetasList()) {
                metasListMetasToAttach = em.getReference(metasListMetasToAttach.getClass(), metasListMetasToAttach.getIdMetas());
                attachedMetasList.add(metasListMetasToAttach);
            }
            cirujano.setMetasList(attachedMetasList);
            em.persist(cirujano);
            if (fkHorarios != null) {
                fkHorarios.getCirujanoList().add(cirujano);
                fkHorarios = em.merge(fkHorarios);
            }
            if (fkEspeciadlidades != null) {
                fkEspeciadlidades.getCirujanoList().add(cirujano);
                fkEspeciadlidades = em.merge(fkEspeciadlidades);
            }
            for (Metas metasListMetas : cirujano.getMetasList()) {
                Cirujano oldFkCirujanoOfMetasListMetas = metasListMetas.getFkCirujano();
                metasListMetas.setFkCirujano(cirujano);
                metasListMetas = em.merge(metasListMetas);
                if (oldFkCirujanoOfMetasListMetas != null) {
                    oldFkCirujanoOfMetasListMetas.getMetasList().remove(metasListMetas);
                    oldFkCirujanoOfMetasListMetas = em.merge(oldFkCirujanoOfMetasListMetas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cirujano cirujano) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujano persistentCirujano = em.find(Cirujano.class, cirujano.getIdCirujano());
            Horario fkHorariosOld = persistentCirujano.getFkHorarios();
            Horario fkHorariosNew = cirujano.getFkHorarios();
            Especialidad fkEspeciadlidadesOld = persistentCirujano.getFkEspeciadlidades();
            Especialidad fkEspeciadlidadesNew = cirujano.getFkEspeciadlidades();
            List<Metas> metasListOld = persistentCirujano.getMetasList();
            List<Metas> metasListNew = cirujano.getMetasList();
            List<String> illegalOrphanMessages = null;
            for (Metas metasListOldMetas : metasListOld) {
                if (!metasListNew.contains(metasListOldMetas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Metas " + metasListOldMetas + " since its fkCirujano field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkHorariosNew != null) {
                fkHorariosNew = em.getReference(fkHorariosNew.getClass(), fkHorariosNew.getIdHorario());
                cirujano.setFkHorarios(fkHorariosNew);
            }
            if (fkEspeciadlidadesNew != null) {
                fkEspeciadlidadesNew = em.getReference(fkEspeciadlidadesNew.getClass(), fkEspeciadlidadesNew.getIdEspecialidad());
                cirujano.setFkEspeciadlidades(fkEspeciadlidadesNew);
            }
            List<Metas> attachedMetasListNew = new ArrayList<Metas>();
            for (Metas metasListNewMetasToAttach : metasListNew) {
                metasListNewMetasToAttach = em.getReference(metasListNewMetasToAttach.getClass(), metasListNewMetasToAttach.getIdMetas());
                attachedMetasListNew.add(metasListNewMetasToAttach);
            }
            metasListNew = attachedMetasListNew;
            cirujano.setMetasList(metasListNew);
            cirujano = em.merge(cirujano);
            if (fkHorariosOld != null && !fkHorariosOld.equals(fkHorariosNew)) {
                fkHorariosOld.getCirujanoList().remove(cirujano);
                fkHorariosOld = em.merge(fkHorariosOld);
            }
            if (fkHorariosNew != null && !fkHorariosNew.equals(fkHorariosOld)) {
                fkHorariosNew.getCirujanoList().add(cirujano);
                fkHorariosNew = em.merge(fkHorariosNew);
            }
            if (fkEspeciadlidadesOld != null && !fkEspeciadlidadesOld.equals(fkEspeciadlidadesNew)) {
                fkEspeciadlidadesOld.getCirujanoList().remove(cirujano);
                fkEspeciadlidadesOld = em.merge(fkEspeciadlidadesOld);
            }
            if (fkEspeciadlidadesNew != null && !fkEspeciadlidadesNew.equals(fkEspeciadlidadesOld)) {
                fkEspeciadlidadesNew.getCirujanoList().add(cirujano);
                fkEspeciadlidadesNew = em.merge(fkEspeciadlidadesNew);
            }
            for (Metas metasListNewMetas : metasListNew) {
                if (!metasListOld.contains(metasListNewMetas)) {
                    Cirujano oldFkCirujanoOfMetasListNewMetas = metasListNewMetas.getFkCirujano();
                    metasListNewMetas.setFkCirujano(cirujano);
                    metasListNewMetas = em.merge(metasListNewMetas);
                    if (oldFkCirujanoOfMetasListNewMetas != null && !oldFkCirujanoOfMetasListNewMetas.equals(cirujano)) {
                        oldFkCirujanoOfMetasListNewMetas.getMetasList().remove(metasListNewMetas);
                        oldFkCirujanoOfMetasListNewMetas = em.merge(oldFkCirujanoOfMetasListNewMetas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cirujano.getIdCirujano();
                if (findCirujano(id) == null) {
                    throw new NonexistentEntityException("The cirujano with id " + id + " no longer exists.");
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
            Cirujano cirujano;
            try {
                cirujano = em.getReference(Cirujano.class, id);
                cirujano.getIdCirujano();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cirujano with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Metas> metasListOrphanCheck = cirujano.getMetasList();
            for (Metas metasListOrphanCheckMetas : metasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cirujano (" + cirujano + ") cannot be destroyed since the Metas " + metasListOrphanCheckMetas + " in its metasList field has a non-nullable fkCirujano field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Horario fkHorarios = cirujano.getFkHorarios();
            if (fkHorarios != null) {
                fkHorarios.getCirujanoList().remove(cirujano);
                fkHorarios = em.merge(fkHorarios);
            }
            Especialidad fkEspeciadlidades = cirujano.getFkEspeciadlidades();
            if (fkEspeciadlidades != null) {
                fkEspeciadlidades.getCirujanoList().remove(cirujano);
                fkEspeciadlidades = em.merge(fkEspeciadlidades);
            }
            em.remove(cirujano);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cirujano> findCirujanoEntities() {
        return findCirujanoEntities(true, -1, -1);
    }

    public List<Cirujano> findCirujanoEntities(int maxResults, int firstResult) {
        return findCirujanoEntities(false, maxResults, firstResult);
    }

    private List<Cirujano> findCirujanoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cirujano.class));
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

    public Cirujano findCirujano(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cirujano.class, id);
        } finally {
            em.close();
        }
    }

    public int getCirujanoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cirujano> rt = cq.from(Cirujano.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
