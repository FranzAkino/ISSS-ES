package com.persistencia;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by akino on 06-09-15.
 */
@Embeddable
public class CirujanoEspecialidadPK implements Serializable{
    @Basic(optional = false)
    @Column(name = "idCirujanoEspecialidad")
    private int idCirujanoEspecialidad;
    @Basic(optional = false)
    @Column(name = "idCirujano")
    private int idCirujano;
    @Basic(optional = false)
    @Column(name = "idEspecialidad")
    private int idEspecialidad;

    public CirujanoEspecialidadPK() {
    }

    public CirujanoEspecialidadPK(int idCirujanoEspecialidad, int idCirujano, int idEspecialidad) {
        this.idCirujanoEspecialidad = idCirujanoEspecialidad;
        this.idCirujano = idCirujano;
        this.idEspecialidad = idEspecialidad;
    }

    public int getIdCirujanoEspecialidad() {
        return idCirujanoEspecialidad;
    }

    public void setIdCirujanoEspecialidad(int idCirujanoEspecialidad) {
        this.idCirujanoEspecialidad = idCirujanoEspecialidad;
    }

    public int getIdCirujano() {
        return idCirujano;
    }

    public void setIdCirujano(int idCirujano) {
        this.idCirujano = idCirujano;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCirujanoEspecialidad;
        hash += (int) idCirujano;
        hash += (int) idEspecialidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CirujanoEspecialidadPK)) {
            return false;
        }
        CirujanoEspecialidadPK other = (CirujanoEspecialidadPK) object;
        if (this.idCirujanoEspecialidad != other.idCirujanoEspecialidad) {
            return false;
        }
        if (this.idCirujano != other.idCirujano) {
            return false;
        }
        if (this.idEspecialidad != other.idEspecialidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.CirujanoEspecialidadPK[ idCirujanoEspecialidad=" + idCirujanoEspecialidad + ", idCirujano=" + idCirujano + ", idEspecialidad=" + idEspecialidad + " ]";
    }
}
