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
public class MetasPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCirujano_fk")
    private int idCirujanofk;
    @Basic(optional = false)
    @Column(name = "Mes")
    private int mes;
    @Basic(optional = false)
    @Column(name = "Anio")
    private int anio;

    public MetasPK() {
    }

    public MetasPK(int idCirujanofk, int mes, int anio) {
        this.idCirujanofk = idCirujanofk;
        this.mes = mes;
        this.anio = anio;
    }

    public int getIdCirujanofk() {
        return idCirujanofk;
    }

    public void setIdCirujanofk(int idCirujanofk) {
        this.idCirujanofk = idCirujanofk;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCirujanofk;
        hash += (int) mes;
        hash += (int) anio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetasPK)) {
            return false;
        }
        MetasPK other = (MetasPK) object;
        if (this.idCirujanofk != other.idCirujanofk) {
            return false;
        }
        if (this.mes != other.mes) {
            return false;
        }
        if (this.anio != other.anio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.MetasPK[ idCirujanofk=" + idCirujanofk + ", mes=" + mes + ", anio=" + anio + " ]";
    }
    
}
