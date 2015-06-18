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
public class CirujanoCirujiaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCirujanoCirujia")
    private int idCirujanoCirujia;
    @Basic(optional = false)
    @Column(name = "fk_idCirujia")
    private int fkidCirujia;
    @Basic(optional = false)
    @Column(name = "fk_idCirujano")
    private int fkidCirujano;

    public CirujanoCirujiaPK() {
    }

    public CirujanoCirujiaPK(int idCirujanoCirujia, int fkidCirujia, int fkidCirujano) {
        this.idCirujanoCirujia = idCirujanoCirujia;
        this.fkidCirujia = fkidCirujia;
        this.fkidCirujano = fkidCirujano;
    }

    public int getIdCirujanoCirujia() {
        return idCirujanoCirujia;
    }

    public void setIdCirujanoCirujia(int idCirujanoCirujia) {
        this.idCirujanoCirujia = idCirujanoCirujia;
    }

    public int getFkidCirujia() {
        return fkidCirujia;
    }

    public void setFkidCirujia(int fkidCirujia) {
        this.fkidCirujia = fkidCirujia;
    }

    public int getFkidCirujano() {
        return fkidCirujano;
    }

    public void setFkidCirujano(int fkidCirujano) {
        this.fkidCirujano = fkidCirujano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCirujanoCirujia;
        hash += (int) fkidCirujia;
        hash += (int) fkidCirujano;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CirujanoCirujiaPK)) {
            return false;
        }
        CirujanoCirujiaPK other = (CirujanoCirujiaPK) object;
        if (this.idCirujanoCirujia != other.idCirujanoCirujia) {
            return false;
        }
        if (this.fkidCirujia != other.fkidCirujia) {
            return false;
        }
        if (this.fkidCirujano != other.fkidCirujano) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.CirujanoCirujiaPK[ idCirujanoCirujia=" + idCirujanoCirujia + ", fkidCirujia=" + fkidCirujia + ", fkidCirujano=" + fkidCirujano + " ]";
    }

}
