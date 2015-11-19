/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author akino
 */
@Entity
@Table(name = "Metas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metas.findAll", query = "SELECT m FROM Metas m"),
    @NamedQuery(name = "Metas.findByIdCirujanofk", query = "SELECT m FROM Metas m WHERE m.metasPK.idCirujanofk = :idCirujanofk"),
    @NamedQuery(name = "Metas.findByMes", query = "SELECT m FROM Metas m WHERE m.metasPK.mes = :mes"),
    @NamedQuery(name = "Metas.findByAnio", query = "SELECT m FROM Metas m WHERE m.metasPK.anio = :anio"),
    @NamedQuery(name = "Metas.findByMeta", query = "SELECT m FROM Metas m WHERE m.meta = :meta"),
    @NamedQuery(name = "Metas.findByRealizadas", query = "SELECT m FROM Metas m WHERE m.realizadas = :realizadas"),
    @NamedQuery(name = "Metas.findBySuspendidas", query = "SELECT m FROM Metas m WHERE m.suspendidas = :suspendidas")})
public class Metas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MetasPK metasPK;

    @Basic(optional = false)
    @Column(name = "Meta")
    private int meta;

    @Column(name = "Realizadas")
    private Integer realizadas;

    @Column(name = "Suspendidas")
    private Integer suspendidas;

    @JoinColumn(name = "idCirujano_fk", referencedColumnName = "idCirujano", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cirujano cirujano;

    public Metas() {
    }

    public Metas(MetasPK metasPK) {
        this.metasPK = metasPK;
    }

    public Metas(MetasPK metasPK, int meta) {
        this.metasPK = metasPK;
        this.meta = meta;
    }

    public Metas(int idCirujanofk, int mes, int anio) {
        this.metasPK = new MetasPK(idCirujanofk, mes, anio);
    }



    public MetasPK getMetasPK() {
        return metasPK;
    }

    public void setMetasPK(MetasPK metasPK) {
        this.metasPK = metasPK;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public Integer getRealizadas() {
        return realizadas;
    }

    public void setRealizadas(Integer realizadas) {
        this.realizadas = realizadas;
    }

    public Integer getSuspendidas() {
        return suspendidas;
    }

    public void setSuspendidas(Integer suspendidas) {
        this.suspendidas = suspendidas;
    }

    public Cirujano getCirujano() {
        return cirujano;
    }

    public void setCirujano(Cirujano cirujano) {
        this.cirujano = cirujano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metasPK != null ? metasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metas)) {
            return false;
        }
        Metas other = (Metas) object;
        if ((this.metasPK == null && other.metasPK != null) || (this.metasPK != null && !this.metasPK.equals(other.metasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Metas[ metasPK=" + metasPK + " ]";
    }
    
}
