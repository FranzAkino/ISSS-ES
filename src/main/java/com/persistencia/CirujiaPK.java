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
public class CirujiaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idCirujia")
    private int idCirujia;
    @Basic(optional = false)
    @Column(name = "fk_Paciente")
    private int fkPaciente;
    @Basic(optional = false)
    @Column(name = "fk_Cie9")
    private int fkCie9;
    @Basic(optional = false)
    @Column(name = "fk_Quirofano")
    private int fkQuirofano;

    public CirujiaPK() {
    }

    public CirujiaPK(int idCirujia, int fkPaciente, int fkCie9, int fkQuirofano) {
        this.idCirujia = idCirujia;
        this.fkPaciente = fkPaciente;
        this.fkCie9 = fkCie9;
        this.fkQuirofano = fkQuirofano;
    }

    public int getIdCirujia() {
        return idCirujia;
    }

    public void setIdCirujia(int idCirujia) {
        this.idCirujia = idCirujia;
    }

    public int getFkPaciente() {
        return fkPaciente;
    }

    public void setFkPaciente(int fkPaciente) {
        this.fkPaciente = fkPaciente;
    }

    public int getFkCie9() {
        return fkCie9;
    }

    public void setFkCie9(int fkCie9) {
        this.fkCie9 = fkCie9;
    }

    public int getFkQuirofano() {
        return fkQuirofano;
    }

    public void setFkQuirofano(int fkQuirofano) {
        this.fkQuirofano = fkQuirofano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCirujia;
        hash += (int) fkPaciente;
        hash += (int) fkCie9;
        hash += (int) fkQuirofano;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CirujiaPK)) {
            return false;
        }
        CirujiaPK other = (CirujiaPK) object;
        if (this.idCirujia != other.idCirujia) {
            return false;
        }
        if (this.fkPaciente != other.fkPaciente) {
            return false;
        }
        if (this.fkCie9 != other.fkCie9) {
            return false;
        }
        if (this.fkQuirofano != other.fkQuirofano) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.CirujiaPK[ idCirujia=" + idCirujia + ", fkPaciente=" + fkPaciente + ", fkCie9=" + fkCie9 + ", fkQuirofano=" + fkQuirofano + " ]";
    }

}
