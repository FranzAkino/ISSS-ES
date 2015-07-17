/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author akino
 */
@Entity
@Table(name = "Quirofano")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Quirofano.findAll", query = "SELECT q FROM Quirofano q"),
        @NamedQuery(name = "Quirofano.findByIdQuirofano", query = "SELECT q FROM Quirofano q WHERE q.idQuirofano = :idQuirofano"),
        @NamedQuery(name = "Quirofano.findByDescripcion", query = "SELECT q FROM Quirofano q WHERE q.descripcion = :descripcion")})
public class Quirofano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @Column(name = "idQuirofano")
    private Integer idQuirofano;
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkQuirofano")
    private List<Cirujia> cirujiaList;

    public Quirofano() {
    }

    public Quirofano(Integer idQuirofano) {
        this.idQuirofano = idQuirofano;
    }

    public Integer getIdQuirofano() {
        return idQuirofano;
    }

    public void setIdQuirofano(Integer idQuirofano) {
        this.idQuirofano = idQuirofano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Cirujia> getCirujiaList() {
        return cirujiaList;
    }

    public void setCirujiaList(List<Cirujia> cirujiaList) {
        this.cirujiaList = cirujiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuirofano != null ? idQuirofano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quirofano)) {
            return false;
        }
        Quirofano other = (Quirofano) object;
        if ((this.idQuirofano == null && other.idQuirofano != null) || (this.idQuirofano != null && !this.idQuirofano.equals(other.idQuirofano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Quirofano[ idQuirofano=" + idQuirofano + " ]";
    }

}
