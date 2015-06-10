package com.persistencia;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by akino on 06-09-15.
 */
@Entity
@Table(name = "CirujanoEspecialidad")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CirujanoEspecialidad.findAll", query = "SELECT c FROM CirujanoEspecialidad c"),
        @NamedQuery(name = "CirujanoEspecialidad.findByIdCirujanoEspecialidad", query = "SELECT c FROM CirujanoEspecialidad c WHERE c.cirujanoEspecialidadPK.idCirujanoEspecialidad = :idCirujanoEspecialidad"),
        @NamedQuery(name = "CirujanoEspecialidad.findByIdCirujano", query = "SELECT c FROM CirujanoEspecialidad c WHERE c.cirujanoEspecialidadPK.idCirujano = :idCirujano"),
        @NamedQuery(name = "CirujanoEspecialidad.findByIdEspecialidad", query = "SELECT c FROM CirujanoEspecialidad c WHERE c.cirujanoEspecialidadPK.idEspecialidad = :idEspecialidad")})
public class CirujanoEspecialidad implements Serializable{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CirujanoEspecialidadPK cirujanoEspecialidadPK;
    @JoinColumn(name = "idEspecialidad", referencedColumnName = "idEspecialidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Especialidad especialidad;
    @JoinColumn(name = "idCirujano", referencedColumnName = "idCirujano", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cirujano cirujano;

    public CirujanoEspecialidad() {
    }

    public CirujanoEspecialidad(CirujanoEspecialidadPK cirujanoEspecialidadPK) {
        this.cirujanoEspecialidadPK = cirujanoEspecialidadPK;
    }

    public CirujanoEspecialidad(int idCirujanoEspecialidad, int idCirujano, int idEspecialidad) {
        this.cirujanoEspecialidadPK = new CirujanoEspecialidadPK(idCirujanoEspecialidad, idCirujano, idEspecialidad);
    }

    public CirujanoEspecialidadPK getCirujanoEspecialidadPK() {
        return cirujanoEspecialidadPK;
    }

    public void setCirujanoEspecialidadPK(CirujanoEspecialidadPK cirujanoEspecialidadPK) {
        this.cirujanoEspecialidadPK = cirujanoEspecialidadPK;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Cirujano getCirujano() {
        return cirujano;
    }

    public void setCirujano(Cirujano cirujano) {
        this.cirujano = cirujano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cirujanoEspecialidadPK != null ? cirujanoEspecialidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CirujanoEspecialidad)) {
            return false;
        }
        CirujanoEspecialidad other = (CirujanoEspecialidad) object;
        if ((this.cirujanoEspecialidadPK == null && other.cirujanoEspecialidadPK != null) || (this.cirujanoEspecialidadPK != null && !this.cirujanoEspecialidadPK.equals(other.cirujanoEspecialidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.CirujanoEspecialidad[ cirujanoEspecialidadPK=" + cirujanoEspecialidadPK + " ]";
    }
}
