package com.persistencia;

/**
 * Created by akino on 06-09-15.
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "Suspenciones")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Suspenciones.findAll", query = "SELECT s FROM Suspenciones s"),
        @NamedQuery(name = "Suspenciones.findByIdSuspenciones", query = "SELECT s FROM Suspenciones s WHERE s.idSuspenciones = :idSuspenciones"),
        @NamedQuery(name = "Suspenciones.findByCausa", query = "SELECT s FROM Suspenciones s WHERE s.causa = :causa"),
        @NamedQuery(name = "Suspenciones.findByTipo", query = "SELECT s FROM Suspenciones s WHERE s.tipo = :tipo")})
public class Suspenciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @Column(name = "idSuspenciones")
    private Integer idSuspenciones;
    @Basic(optional = false)
    @Column(name = "Causa")
    private String causa;
    @Basic(optional = false)
    @Column(name = "Tipo")
    private String tipo;
    @OneToMany(mappedBy = "fkSuspencion")
    private List<Cirujia> cirujiaList;

    public Suspenciones() {
    }

    public Suspenciones(Integer idSuspenciones) {
        this.idSuspenciones = idSuspenciones;
    }

    public Suspenciones(Integer idSuspenciones, String causa, String tipo) {
        this.idSuspenciones = idSuspenciones;
        this.causa = causa;
        this.tipo = tipo;
    }

    public Integer getIdSuspenciones() {
        return idSuspenciones;
    }

    public void setIdSuspenciones(Integer idSuspenciones) {
        this.idSuspenciones = idSuspenciones;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (idSuspenciones != null ? idSuspenciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suspenciones)) {
            return false;
        }
        Suspenciones other = (Suspenciones) object;
        if ((this.idSuspenciones == null && other.idSuspenciones != null) || (this.idSuspenciones != null && !this.idSuspenciones.equals(other.idSuspenciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Suspenciones[ idSuspenciones=" + idSuspenciones + " ]";
    }

}
