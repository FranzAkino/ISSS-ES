/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author akino
 */
@Embeddable
public class CirujanoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCirujano")
    private int idCirujano;
    @Basic(optional = false)
    @Column(name = "fk_idEspecialidad")
    private int fkidEspecialidad;

    public CirujanoPK() {
    }

    public CirujanoPK(int idCirujano, int fkidEspecialidad) {
        this.idCirujano = idCirujano;
        this.fkidEspecialidad = fkidEspecialidad;
    }

    public int getIdCirujano() {
        return idCirujano;
    }

    public void setIdCirujano(int idCirujano) {
        this.idCirujano = idCirujano;
    }

    public int getFkidEspecialidad() {
        return fkidEspecialidad;
    }

    public void setFkidEspecialidad(int fkidEspecialidad) {
        this.fkidEspecialidad = fkidEspecialidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCirujano;
        hash += (int) fkidEspecialidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CirujanoPK)) {
            return false;
        }
        CirujanoPK other = (CirujanoPK) object;
        if (this.idCirujano != other.idCirujano) {
            return false;
        }
        if (this.fkidEspecialidad != other.fkidEspecialidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.CirujanoPK[ idCirujano=" + idCirujano + ", fkidEspecialidad=" + fkidEspecialidad + " ]";
    }

}
