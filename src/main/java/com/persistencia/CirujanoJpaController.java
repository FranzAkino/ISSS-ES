/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class CirujanoJpaController implements Serializable {

    public CirujanoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cirujano cirujano) throws PreexistingEntityException, Exception {
        if (cirujano.getCirujanoPK() == null) {
            cirujano.setCirujanoPK(new CirujanoPK());
        }
        if (cirujano.getCirujanoCirujiaList() == null) {
            cirujano.setCirujanoCirujiaList(new ArrayList<CirujanoCirujia>());
        }
        if (cirujano.getMetasList() == null) {
            cirujano.setMetasList(new ArrayList<Metas>());
        }
        cirujano.getCirujanoPK().setFkidEspecialidad(cirujano.getEspecialidad().getIdEspecialidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidad especialidad = cirujano.getEspecialidad();
            if (especialidad != null) {
                especialidad = em.getReference(especialidad.getClass(), especialidad.getIdEspecialidad());
                cirujano.setEspecialidad(especialidad);
            }
            Horario fkHorarios = cirujano.getFkHorarios();
            if (fkHorarios != null) {
                fkHorarios = em.getReference(fkHorarios.getClass(), fkHorarios.getIdHorario());
                cirujano.setFkHorarios(fkHorarios);
            }
            List<CirujanoCirujia> attachedCirujanoCirujiaList = new ArrayList<CirujanoCirujia>();
            for (CirujanoCirujia cirujanoCirujiaListCirujanoCirujiaToAttach : cirujano.getCirujanoCirujiaList()) {
                cirujanoCirujiaListCirujanoCirujiaToAttach = em.getReference(cirujanoCirujiaListCirujanoCirujiaToAttach.getClass(), cirujanoCirujiaListCirujanoCirujiaToAttach.getCirujanoCirujiaPK());
                attachedCirujanoCirujiaList.add(cirujanoCirujiaListCirujanoCirujiaToAttach);
            }
            cirujano.setCirujanoCirujiaList(attachedCirujanoCirujiaList);
            List<Metas> attachedMetasList = new ArrayList<Metas>();
            for (Metas metasListMetasToAttach : cirujano.getMetasList()) {
                metasListMetasToAttach = em.getReference(metasListMetasToAttach.getClass(), metasListMetasToAttach.getMetasPK());
                attachedMetasList.add(metasListMetasToAttach);
            }
            cirujano.setMetasList(attachedMetasList);
            em.persist(cirujano);
            if (especialidad != null) {
                especialidad.getCirujanoList().add(cirujano);
                especialidad = em.merge(especialidad);
            }
            if (fkHorarios != null) {
                fkHorarios.getCirujanoList().add(cirujano);
                fkHorarios = em.merge(fkHorarios);
            }
            for (CirujanoCirujia cirujanoCirujiaListCirujanoCirujia : cirujano.getCirujanoCirujiaList()) {
                Cirujano oldCirujanoOfCirujanoCirujiaListCirujanoCirujia = cirujanoCirujiaListCirujanoCirujia.getCirujano();
                cirujanoCirujiaListCirujanoCirujia.setCirujano(cirujano);
                cirujanoCirujiaListCirujanoCirujia = em.merge(cirujanoCirujiaListCirujanoCirujia);
                if (oldCirujanoOfCirujanoCirujiaListCirujanoCirujia != null) {
                    oldCirujanoOfCirujanoCirujiaListCirujanoCirujia.getCirujanoCirujiaList().remove(cirujanoCirujiaListCirujanoCirujia);
                    oldCirujanoOfCirujanoCirujiaListCirujanoCirujia = em.merge(oldCirujanoOfCirujanoCirujiaListCirujanoCirujia);
                }
            }
            for (Metas metasListMetas : cirujano.getMetasList()) {
                Cirujano oldCirujanoOfMetasListMetas = metasListMetas.getCirujano();
                metasListMetas.setCirujano(cirujano);
                metasListMetas = em.merge(metasListMetas);
                if (oldCirujanoOfMetasListMetas != null) {
                    oldCirujanoOfMetasListMetas.getMetasList().remove(metasListMetas);
                    oldCirujanoOfMetasListMetas = em.merge(oldCirujanoOfMetasListMetas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCirujano(cirujano.getCirujanoPK()) != null) {
                throw new PreexistingEntityException("Cirujano " + cirujano + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cirujano cirujano) throws IllegalOrphanException, NonexistentEntityException, Exception {
        cirujano.getCirujanoPK().setFkidEspecialidad(cirujano.getEspecialidad().getIdEspecialidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujano persistentCirujano = em.find(Cirujano.class, cirujano.getCirujanoPK());
            Especialidad especialidadOld = persistentCirujano.getEspecialidad();
            Especialidad especialidadNew = cirujano.getEspecialidad();
            Horario fkHorariosOld = persistentCirujano.getFkHorarios();
            Horario fkHorariosNew = cirujano.getFkHorarios();
            List<CirujanoCirujia> cirujanoCirujiaListOld = persistentCirujano.getCirujanoCirujiaList();
            List<CirujanoCirujia> cirujanoCirujiaListNew = cirujano.getCirujanoCirujiaList();
            List<Metas> metasListOld = persistentCirujano.getMetasList();
            List<Metas> metasListNew = cirujano.getMetasList();
            List<String> illegalOrphanMessages = null;
            for (CirujanoCirujia cirujanoCirujiaListOldCirujanoCirujia : cirujanoCirujiaListOld) {
                if (!cirujanoCirujiaListNew.contains(cirujanoCirujiaListOldCirujanoCirujia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CirujanoCirujia " + cirujanoCirujiaListOldCirujanoCirujia + " since its cirujano field is not nullable.");
                }
            }
            for (Metas metasListOldMetas : metasListOld) {
                if (!metasListNew.contains(metasListOldMetas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Metas " + metasListOldMetas + " since its cirujano field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (especialidadNew != null) {
                especialidadNew = em.getReference(especialidadNew.getClass(), especialidadNew.getIdEspecialidad());
                cirujano.setEspecialidad(especialidadNew);
            }
            if (fkHorariosNew != null) {
                fkHorariosNew = em.getReference(fkHorariosNew.getClass(), fkHorariosNew.getIdHorario());
                cirujano.setFkHorarios(fkHorariosNew);
            }
            List<CirujanoCirujia> attachedCirujanoCirujiaListNew = new ArrayList<CirujanoCirujia>();
            for (CirujanoCirujia cirujanoCirujiaListNewCirujanoCirujiaToAttach : cirujanoCirujiaListNew) {
                cirujanoCirujiaListNewCirujanoCirujiaToAttach = em.getReference(cirujanoCirujiaListNewCirujanoCirujiaToAttach.getClass(), cirujanoCirujiaListNewCirujanoCirujiaToAttach.getCirujanoCirujiaPK());
                attachedCirujanoCirujiaListNew.add(cirujanoCirujiaListNewCirujanoCirujiaToAttach);
            }
            cirujanoCirujiaListNew = attachedCirujanoCirujiaListNew;
            cirujano.setCirujanoCirujiaList(cirujanoCirujiaListNew);
            List<Metas> attachedMetasListNew = new ArrayList<Metas>();
            for (Metas metasListNewMetasToAttach : metasListNew) {
                metasListNewMetasToAttach = em.getReference(metasListNewMetasToAttach.getClass(), metasListNewMetasToAttach.getMetasPK());
                attachedMetasListNew.add(metasListNewMetasToAttach);
            }
            metasListNew = attachedMetasListNew;
            cirujano.setMetasList(metasListNew);
            cirujano = em.merge(cirujano);
            if (especialidadOld != null && !especialidadOld.equals(especialidadNew)) {
                especialidadOld.getCirujanoList().remove(cirujano);
                especialidadOld = em.merge(especialidadOld);
            }
            if (especialidadNew != null && !especialidadNew.equals(especialidadOld)) {
                especialidadNew.getCirujanoList().add(cirujano);
                especialidadNew = em.merge(especialidadNew);
            }
            if (fkHorariosOld != null && !fkHorariosOld.equals(fkHorariosNew)) {
                fkHorariosOld.getCirujanoList().remove(cirujano);
                fkHorariosOld = em.merge(fkHorariosOld);
            }
            if (fkHorariosNew != null && !fkHorariosNew.equals(fkHorariosOld)) {
                fkHorariosNew.getCirujanoList().add(cirujano);
                fkHorariosNew = em.merge(fkHorariosNew);
            }
            for (CirujanoCirujia cirujanoCirujiaListNewCirujanoCirujia : cirujanoCirujiaListNew) {
                if (!cirujanoCirujiaListOld.contains(cirujanoCirujiaListNewCirujanoCirujia)) {
                    Cirujano oldCirujanoOfCirujanoCirujiaListNewCirujanoCirujia = cirujanoCirujiaListNewCirujanoCirujia.getCirujano();
                    cirujanoCirujiaListNewCirujanoCirujia.setCirujano(cirujano);
                    cirujanoCirujiaListNewCirujanoCirujia = em.merge(cirujanoCirujiaListNewCirujanoCirujia);
                    if (oldCirujanoOfCirujanoCirujiaListNewCirujanoCirujia != null && !oldCirujanoOfCirujanoCirujiaListNewCirujanoCirujia.equals(cirujano)) {
                        oldCirujanoOfCirujanoCirujiaListNewCirujanoCirujia.getCirujanoCirujiaList().remove(cirujanoCirujiaListNewCirujanoCirujia);
                        oldCirujanoOfCirujanoCirujiaListNewCirujanoCirujia = em.merge(oldCirujanoOfCirujanoCirujiaListNewCirujanoCirujia);
                    }
                }
            }
            for (Metas metasListNewMetas : metasListNew) {
                if (!metasListOld.contains(metasListNewMetas)) {
                    Cirujano oldCirujanoOfMetasListNewMetas = metasListNewMetas.getCirujano();
                    metasListNewMetas.setCirujano(cirujano);
                    metasListNewMetas = em.merge(metasListNewMetas);
                    if (oldCirujanoOfMetasListNewMetas != null && !oldCirujanoOfMetasListNewMetas.equals(cirujano)) {
                        oldCirujanoOfMetasListNewMetas.getMetasList().remove(metasListNewMetas);
                        oldCirujanoOfMetasListNewMetas = em.merge(oldCirujanoOfMetasListNewMetas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CirujanoPK id = cirujano.getCirujanoPK();
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

    public void destroy(CirujanoPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cirujano cirujano;
            try {
                cirujano = em.getReference(Cirujano.class, id);
                cirujano.getCirujanoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cirujano with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CirujanoCirujia> cirujanoCirujiaListOrphanCheck = cirujano.getCirujanoCirujiaList();
            for (CirujanoCirujia cirujanoCirujiaListOrphanCheckCirujanoCirujia : cirujanoCirujiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cirujano (" + cirujano + ") cannot be destroyed since the CirujanoCirujia " + cirujanoCirujiaListOrphanCheckCirujanoCirujia + " in its cirujanoCirujiaList field has a non-nullable cirujano field.");
            }
            List<Metas> metasListOrphanCheck = cirujano.getMetasList();
            for (Metas metasListOrphanCheckMetas : metasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cirujano (" + cirujano + ") cannot be destroyed since the Metas " + metasListOrphanCheckMetas + " in its metasList field has a non-nullable cirujano field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Especialidad especialidad = cirujano.getEspecialidad();
            if (especialidad != null) {
                especialidad.getCirujanoList().remove(cirujano);
                especialidad = em.merge(especialidad);
            }
            Horario fkHorarios = cirujano.getFkHorarios();
            if (fkHorarios != null) {
                fkHorarios.getCirujanoList().remove(cirujano);
                fkHorarios = em.merge(fkHorarios);
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

    public Cirujano findCirujano(CirujanoPK id) {
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
