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
@Table(name = "CIE9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cie9.findAll", query = "SELECT c FROM Cie9 c"),
    @NamedQuery(name = "Cie9.findByIdProcedimiento", query = "SELECT c FROM Cie9 c WHERE c.idProcedimiento = :idProcedimiento"),
    @NamedQuery(name = "Cie9.findByNombre", query = "SELECT c FROM Cie9 c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cie9.findByDescripcion", query = "SELECT c FROM Cie9 c WHERE c.descripcion = :descripcion")})
public class Cie9 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @Column(name = "idProcedimiento")
    private Integer idProcedimiento;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCie9")
    private List<Cirujia> cirujiaList;

    public Cie9() {
    }

    public Cie9(Integer idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public Cie9(Integer idProcedimiento, String nombre) {
        this.idProcedimiento = idProcedimiento;
        this.nombre = nombre;
    }

    public Integer getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Integer idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idProcedimiento != null ? idProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cie9)) {
            return false;
        }
        Cie9 other = (Cie9) object;
        if ((this.idProcedimiento == null && other.idProcedimiento != null) || (this.idProcedimiento != null && !this.idProcedimiento.equals(other.idProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Cie9[ idProcedimiento=" + idProcedimiento + " ]";
    }
    
}
