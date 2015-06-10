package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by akino on 05-15-15.
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
        return "persistencia.Riesgo[ idRiesgo=" + idRiesgo + " ]";
    }

}
