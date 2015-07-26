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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Riesgo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Riesgo.findAll", query = "SELECT r FROM Riesgo r"),
    @NamedQuery(name = "Riesgo.findByIdRiesgo", query = "SELECT r FROM Riesgo r WHERE r.idRiesgo = :idRiesgo"),
    @NamedQuery(name = "Riesgo.findByNombre", query = "SELECT r FROM Riesgo r WHERE r.nombre = :nombre")})
public class Riesgo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idRiesgo")
    private Integer idRiesgo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRiesgo")
    private List<Cirujia> cirujiaList;

    public Riesgo() {
    }

    public Riesgo(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public Riesgo(Integer idRiesgo, String nombre) {
        this.idRiesgo = idRiesgo;
        this.nombre = nombre;
    }

    public Integer getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idRiesgo != null ? idRiesgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Riesgo)) {
            return false;
        }
        Riesgo other = (Riesgo) object;
        if ((this.idRiesgo == null && other.idRiesgo != null) || (this.idRiesgo != null && !this.idRiesgo.equals(other.idRiesgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Riesgo[ idRiesgo=" + idRiesgo + " ]";
    }
    
}
