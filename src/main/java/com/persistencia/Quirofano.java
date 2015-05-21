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
@Table(name = "Quirofano")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Quirofano.findAll", query = "SELECT q FROM Quirofano q"),
        @NamedQuery(name = "Quirofano.findByIdQuirofano", query = "SELECT q FROM Quirofano q WHERE q.idQuirofano = :idQuirofano"),
        @NamedQuery(name = "Quirofano.findByDescripcion", query = "SELECT q FROM Quirofano q WHERE q.descripcion = :descripcion")})
public class Quirofano {
    //region Comentado
    /*private Integer idQuirofano;
    private String descripcion;
    private Collection<Cirujia> cirujiasByIdQuirofano;

    @Id
    @Column(name = "idQuirofano", nullable = false, insertable = true, updatable = true)
    public Integer getIdQuirofano() {
        return idQuirofano;
    }

    public void setIdQuirofano(Integer idQuirofano) {
        this.idQuirofano = idQuirofano;
    }

    @Basic
    @Column(name = "Descripcion", nullable = true, insertable = true, updatable = true, length = 45)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quirofano quirofano = (Quirofano) o;

        if (idQuirofano != null ? !idQuirofano.equals(quirofano.idQuirofano) : quirofano.idQuirofano != null)
            return false;
        if (descripcion != null ? !descripcion.equals(quirofano.descripcion) : quirofano.descripcion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idQuirofano != null ? idQuirofano.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "quirofanoByFkQuirofano")
    public Collection<Cirujia> getCirujiasByIdQuirofano() {
        return cirujiasByIdQuirofano;
    }

    public void setCirujiasByIdQuirofano(Collection<Cirujia> cirujiasByIdQuirofano) {
        this.cirujiasByIdQuirofano = cirujiasByIdQuirofano;
    }*/
    //endregion

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
        return "huuu.Quirofano[ idQuirofano=" + idQuirofano + " ]";
    }

}
