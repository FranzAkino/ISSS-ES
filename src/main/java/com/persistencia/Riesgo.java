package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

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
public class Riesgo {
    //region Comentado
    /*private Integer idRiesgo;
    private String nombre;

    @Id
    @Column(name = "idRiesgo", nullable = false, insertable = true, updatable = true)
    public Integer getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    @Basic
    @Column(name = "Nombre", nullable = false, insertable = true, updatable = true, length = 45)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Riesgo riesgo = (Riesgo) o;

        if (idRiesgo != null ? !idRiesgo.equals(riesgo.idRiesgo) : riesgo.idRiesgo != null) return false;
        if (nombre != null ? !nombre.equals(riesgo.nombre) : riesgo.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRiesgo != null ? idRiesgo.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }*/
    //endregion

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idRiesgo")
    private Integer idRiesgo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;

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
        return "huuu.Riesgo[ idRiesgo=" + idRiesgo + " ]";
    }

}
