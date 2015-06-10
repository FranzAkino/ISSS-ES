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
        if (cirujano.getCirujiaList() == null) {
            cirujano.setCirujiaList(new ArrayList<Cirujia>());
        }
        if (cirujano.getCirujanoEspecialidadList() == null) {
            cirujano.setCirujanoEspecialidadList(new ArrayList<CirujanoEspecialidad>());
        }
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
            List<Cirujia> attachedCirujiaList = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListCirujiaToAttach : cirujano.getCirujiaList()) {
                cirujiaListCirujiaToAttach = em.getReference(cirujiaListCirujiaToAttach.getClass(), cirujiaListCirujiaToAttach.getIdCirujia());
                attachedCirujiaList.add(cirujiaListCirujiaToAttach);
            }
            cirujano.setCirujiaList(attachedCirujiaList);
            List<CirujanoEspecialidad> attachedCirujanoEspecialidadList = new ArrayList<CirujanoEspecialidad>();
            for (CirujanoEspecialidad cirujanoEspecialidadListCirujanoEspecialidadToAttach : cirujano.getCirujanoEspecialidadList()) {
                cirujanoEspecialidadListCirujanoEspecialidadToAttach = em.getReference(cirujanoEspecialidadListCirujanoEspecialidadToAttach.getClass(), cirujanoEspecialidadListCirujanoEspecialidadToAttach.getCirujanoEspecialidadPK());
                attachedCirujanoEspecialidadList.add(cirujanoEspecialidadListCirujanoEspecialidadToAttach);
            }
            cirujano.setCirujanoEspecialidadList(attachedCirujanoEspecialidadList);
            List<Metas> attachedMetasList = new ArrayList<Metas>();
            for (Metas metasListMetasToAttach : cirujano.getMetasList()) {
                metasListMetasToAttach = em.getReference(metasListMetasToAttach.getClass(), metasListMetasToAttach.getMetasPK());
                attachedMetasList.add(metasListMetasToAttach);
            }
            cirujano.setMetasList(attachedMetasList);
            em.persist(cirujano);
            if (fkHorarios != null) {
                fkHorarios.getCirujanoList().add(cirujano);
                fkHorarios = em.merge(fkHorarios);
            }
            for (Cirujia cirujiaListCirujia : cirujano.getCirujiaList()) {
                Cirujano oldFkCirujanoOfCirujiaListCirujia = cirujiaListCirujia.getFkCirujano();
                cirujiaListCirujia.setFkCirujano(cirujano);
                cirujiaListCirujia = em.merge(cirujiaListCirujia);
                if (oldFkCirujanoOfCirujiaListCirujia != null) {
                    oldFkCirujanoOfCirujiaListCirujia.getCirujiaList().remove(cirujiaListCirujia);
                    oldFkCirujanoOfCirujiaListCirujia = em.merge(oldFkCirujanoOfCirujiaListCirujia);
                }
            }
            for (CirujanoEspecialidad cirujanoEspecialidadListCirujanoEspecialidad : cirujano.getCirujanoEspecialidadList()) {
                Cirujano oldCirujanoOfCirujanoEspecialidadListCirujanoEspecialidad = cirujanoEspecialidadListCirujanoEspecialidad.getCirujano();
                cirujanoEspecialidadListCirujanoEspecialidad.setCirujano(cirujano);
                cirujanoEspecialidadListCirujanoEspecialidad = em.merge(cirujanoEspecialidadListCirujanoEspecialidad);
                if (oldCirujanoOfCirujanoEspecialidadListCirujanoEspecialidad != null) {
                    oldCirujanoOfCirujanoEspecialidadListCirujanoEspecialidad.getCirujanoEspecialidadList().remove(cirujanoEspecialidadListCirujanoEspecialidad);
                    oldCirujanoOfCirujanoEspecialidadListCirujanoEspecialidad = em.merge(oldCirujanoOfCirujanoEspecialidadListCirujanoEspecialidad);
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
            List<Cirujia> cirujiaListOld = persistentCirujano.getCirujiaList();
            List<Cirujia> cirujiaListNew = cirujano.getCirujiaList();
            List<CirujanoEspecialidad> cirujanoEspecialidadListOld = persistentCirujano.getCirujanoEspecialidadList();
            List<CirujanoEspecialidad> cirujanoEspecialidadListNew = cirujano.getCirujanoEspecialidadList();
            List<Metas> metasListOld = persistentCirujano.getMetasList();
            List<Metas> metasListNew = cirujano.getMetasList();
            List<String> illegalOrphanMessages = null;
            for (Cirujia cirujiaListOldCirujia : cirujiaListOld) {
                if (!cirujiaListNew.contains(cirujiaListOldCirujia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cirujia " + cirujiaListOldCirujia + " since its fkCirujano field is not nullable.");
                }
            }
            for (CirujanoEspecialidad cirujanoEspecialidadListOldCirujanoEspecialidad : cirujanoEspecialidadListOld) {
                if (!cirujanoEspecialidadListNew.contains(cirujanoEspecialidadListOldCirujanoEspecialidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CirujanoEspecialidad " + cirujanoEspecialidadListOldCirujanoEspecialidad + " since its cirujano field is not nullable.");
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
            if (fkHorariosNew != null) {
                fkHorariosNew = em.getReference(fkHorariosNew.getClass(), fkHorariosNew.getIdHorario());
                cirujano.setFkHorarios(fkHorariosNew);
            }
            List<Cirujia> attachedCirujiaListNew = new ArrayList<Cirujia>();
            for (Cirujia cirujiaListNewCirujiaToAttach : cirujiaListNew) {
                cirujiaListNewCirujiaToAttach = em.getReference(cirujiaListNewCirujiaToAttach.getClass(), cirujiaListNewCirujiaToAttach.getIdCirujia());
                attachedCirujiaListNew.add(cirujiaListNewCirujiaToAttach);
            }
            cirujiaListNew = attachedCirujiaListNew;
            cirujano.setCirujiaList(cirujiaListNew);
            List<CirujanoEspecialidad> attachedCirujanoEspecialidadListNew = new ArrayList<CirujanoEspecialidad>();
            for (CirujanoEspecialidad cirujanoEspecialidadListNewCirujanoEspecialidadToAttach : cirujanoEspecialidadListNew) {
                cirujanoEspecialidadListNewCirujanoEspecialidadToAttach = em.getReference(cirujanoEspecialidadListNewCirujanoEspecialidadToAttach.getClass(), cirujanoEspecialidadListNewCirujanoEspecialidadToAttach.getCirujanoEspecialidadPK());
                attachedCirujanoEspecialidadListNew.add(cirujanoEspecialidadListNewCirujanoEspecialidadToAttach);
            }
            cirujanoEspecialidadListNew = attachedCirujanoEspecialidadListNew;
            cirujano.setCirujanoEspecialidadList(cirujanoEspecialidadListNew);
            List<Metas> attachedMetasListNew = new ArrayList<Metas>();
            for (Metas metasListNewMetasToAttach : metasListNew) {
                metasListNewMetasToAttach = em.getReference(metasListNewMetasToAttach.getClass(), metasListNewMetasToAttach.getMetasPK());
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
            for (Cirujia cirujiaListNewCirujia : cirujiaListNew) {
                if (!cirujiaListOld.contains(cirujiaListNewCirujia)) {
                    Cirujano oldFkCirujanoOfCirujiaListNewCirujia = cirujiaListNewCirujia.getFkCirujano();
                    cirujiaListNewCirujia.setFkCirujano(cirujano);
                    cirujiaListNewCirujia = em.merge(cirujiaListNewCirujia);
                    if (oldFkCirujanoOfCirujiaListNewCirujia != null && !oldFkCirujanoOfCirujiaListNewCirujia.equals(cirujano)) {
                        oldFkCirujanoOfCirujiaListNewCirujia.getCirujiaList().remove(cirujiaListNewCirujia);
                        oldFkCirujanoOfCirujiaListNewCirujia = em.merge(oldFkCirujanoOfCirujiaListNewCirujia);
                    }
                }
            }
            for (CirujanoEspecialidad cirujanoEspecialidadListNewCirujanoEspecialidad : cirujanoEspecialidadListNew) {
                if (!cirujanoEspecialidadListOld.contains(cirujanoEspecialidadListNewCirujanoEspecialidad)) {
                    Cirujano oldCirujanoOfCirujanoEspecialidadListNewCirujanoEspecialidad = cirujanoEspecialidadListNewCirujanoEspecialidad.getCirujano();
                    cirujanoEspecialidadListNewCirujanoEspecialidad.setCirujano(cirujano);
                    cirujanoEspecialidadListNewCirujanoEspecialidad = em.merge(cirujanoEspecialidadListNewCirujanoEspecialidad);
                    if (oldCirujanoOfCirujanoEspecialidadListNewCirujanoEspecialidad != null && !oldCirujanoOfCirujanoEspecialidadListNewCirujanoEspecialidad.equals(cirujano)) {
                        oldCirujanoOfCirujanoEspecialidadListNewCirujanoEspecialidad.getCirujanoEspecialidadList().remove(cirujanoEspecialidadListNewCirujanoEspecialidad);
                        oldCirujanoOfCirujanoEspecialidadListNewCirujanoEspecialidad = em.merge(oldCirujanoOfCirujanoEspecialidadListNewCirujanoEspecialidad);
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
            List<Cirujia> cirujiaListOrphanCheck = cirujano.getCirujiaList();
            for (Cirujia cirujiaListOrphanCheckCirujia : cirujiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cirujano (" + cirujano + ") cannot be destroyed since the Cirujia " + cirujiaListOrphanCheckCirujia + " in its cirujiaList field has a non-nullable fkCirujano field.");
            }
            List<CirujanoEspecialidad> cirujanoEspecialidadListOrphanCheck = cirujano.getCirujanoEspecialidadList();
            for (CirujanoEspecialidad cirujanoEspecialidadListOrphanCheckCirujanoEspecialidad : cirujanoEspecialidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cirujano (" + cirujano + ") cannot be destroyed since the CirujanoEspecialidad " + cirujanoEspecialidadListOrphanCheckCirujanoEspecialidad + " in its cirujanoEspecialidadList field has a non-nullable cirujano field.");
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
