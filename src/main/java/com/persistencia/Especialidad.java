package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by akino on 05-15-15.
 */
@Entity
@Table(name = "Especialidad")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e"),
        @NamedQuery(name = "Especialidad.findByIdEspecialidad", query = "SELECT e FROM Especialidad e WHERE e.idEspecialidad = :idEspecialidad"),
        @NamedQuery(name = "Especialidad.findByEspecialidad", query = "SELECT e FROM Especialidad e WHERE e.especialidad = :especialidad")})
public class Especialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idEspecialidad")
    private Integer idEspecialidad;
    @Column(name = "Especialidad")
    private String especialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidad")
    private List<CirujanoEspecialidad> cirujanoEspecialidadList;

    public Especialidad() {
    }

    public Especialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @XmlTransient
    public List<CirujanoEspecialidad> getCirujanoEspecialidadList() {
        return cirujanoEspecialidadList;
    }

    public void setCirujanoEspecialidadList(List<CirujanoEspecialidad> cirujanoEspecialidadList) {
        this.cirujanoEspecialidadList = cirujanoEspecialidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecialidad != null ? idEspecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.idEspecialidad == null && other.idEspecialidad != null) || (this.idEspecialidad != null && !this.idEspecialidad.equals(other.idEspecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Especialidad[ idEspecialidad=" + idEspecialidad + " ]";
    }
}
