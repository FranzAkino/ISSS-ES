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
@Table(name = "CIE9")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cie9.findAll", query = "SELECT c FROM Cie9 c"),
        @NamedQuery(name = "Cie9.findByIdProcedimiento", query = "SELECT c FROM Cie9 c WHERE c.idProcedimiento = :idProcedimiento"),
        @NamedQuery(name = "Cie9.findByNombre", query = "SELECT c FROM Cie9 c WHERE c.nombre = :nombre"),
        @NamedQuery(name = "Cie9.findByDescripcion", query = "SELECT c FROM Cie9 c WHERE c.descripcion = :descripcion")})
public class Cie9 {

    //region Comentado
    /*
    private Integer idProcedimiento;
    private String nombre;
    private String descripcion;
    private Collection<Cirujia> cirujiasByIdProcedimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cie9ByFkCie9")
    private List<Cirujia> cirujiaList;

    @Id
    @Column(name = "idProcedimiento", nullable = false, insertable = true, updatable = true)
    public Integer getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Integer idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    @Basic
    @Column(name = "Nombre", nullable = false, insertable = true, updatable = true, length = 45)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

        Cie9 cie9 = (Cie9) o;

        if (idProcedimiento != null ? !idProcedimiento.equals(cie9.idProcedimiento) : cie9.idProcedimiento != null)
            return false;
        if (nombre != null ? !nombre.equals(cie9.nombre) : cie9.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(cie9.descripcion) : cie9.descripcion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProcedimiento != null ? idProcedimiento.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    @XmlTransient
    public List<Cirujia> getCirujiaList() {
        return cirujiaList;
    }

    public void setCirujiaList(List<Cirujia> cirujiaList) {
        this.cirujiaList = cirujiaList;
    }

    @OneToMany(mappedBy = "cie9ByFkCie9")
    public Collection<Cirujia> getCirujiasByIdProcedimiento() {
        return cirujiasByIdProcedimiento;
    }

    public void setCirujiasByIdProcedimiento(Collection<Cirujia> cirujiasByIdProcedimiento) {
        this.cirujiasByIdProcedimiento = cirujiasByIdProcedimiento;
    }*/
    //endregion

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
        return "huuu.Cie9[ idProcedimiento=" + idProcedimiento + " ]";
    }


}
