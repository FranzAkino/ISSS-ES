package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
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
public class Especialidad {
    //region Comentado
    /*private Integer idEspecialidad;
    private String especialidad;
    private Collection<Cirujano> cirujanosByIdEspecialidad;

    @Id
    @Column(name = "idEspecialidad", nullable = false, insertable = true, updatable = true)
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Basic
    @Column(name = "Especialidad", nullable = true, insertable = true, updatable = true, length = 45)
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Especialidad that = (Especialidad) o;

        if (idEspecialidad != null ? !idEspecialidad.equals(that.idEspecialidad) : that.idEspecialidad != null)
            return false;
        if (especialidad != null ? !especialidad.equals(that.especialidad) : that.especialidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEspecialidad != null ? idEspecialidad.hashCode() : 0;
        result = 31 * result + (especialidad != null ? especialidad.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "especialidadByFkEspeciadlidades")
    public Collection<Cirujano> getCirujanosByIdEspecialidad() {
        return cirujanosByIdEspecialidad;
    }

    public void setCirujanosByIdEspecialidad(Collection<Cirujano> cirujanosByIdEspecialidad) {
        this.cirujanosByIdEspecialidad = cirujanosByIdEspecialidad;
    }*/
    //endregion
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idEspecialidad")
    private Integer idEspecialidad;
    @Column(name = "Especialidad")
    private String especialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEspeciadlidades")
    private List<Cirujano> cirujanoList;

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
    public List<Cirujano> getCirujanoList() {
        return cirujanoList;
    }

    public void setCirujanoList(List<Cirujano> cirujanoList) {
        this.cirujanoList = cirujanoList;
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
        return "huuu.Especialidad[ idEspecialidad=" + idEspecialidad + " ]";
    }

}
