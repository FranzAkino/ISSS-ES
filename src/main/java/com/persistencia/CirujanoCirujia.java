/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author akino
 */
@Entity
@Table(name = "CirujanoCirujia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CirujanoCirujia.findAll", query = "SELECT c FROM CirujanoCirujia c"),
    @NamedQuery(name = "CirujanoCirujia.findByIdCirujanoCirujia", query = "SELECT c FROM CirujanoCirujia c WHERE c.idCirujanoCirujia = :idCirujanoCirujia"),
    @NamedQuery(name = "CirujanoCirujia.findByTitular", query = "SELECT c FROM CirujanoCirujia c WHERE c.titular = :titular")})
public class CirujanoCirujia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idCirujanoCirujia")
    private Integer idCirujanoCirujia;
    @Basic(optional = false)
    @Column(name = "Titular")
    private int titular;
    @JoinColumn(name = "fk_idCirujia", referencedColumnName = "idCirujia")
    @ManyToOne(optional = false)
    private Cirujia fkidCirujia;
    @JoinColumn(name = "fk_idCirujano", referencedColumnName = "idCirujano")
    @ManyToOne(optional = false)
    private Cirujano fkidCirujano;

    public CirujanoCirujia() {
    }

    public CirujanoCirujia(Integer idCirujanoCirujia) {
        this.idCirujanoCirujia = idCirujanoCirujia;
    }

    public CirujanoCirujia(Integer idCirujanoCirujia, int titular) {
        this.idCirujanoCirujia = idCirujanoCirujia;
        this.titular = titular;
    }

    public CirujanoCirujia(Cirujia cirujia,Cirujano cirujano, int titular) {
        this.fkidCirujia=cirujia;
        this.fkidCirujano=cirujano;
        this.titular = titular;
    }

    public Integer getIdCirujanoCirujia() {
        return idCirujanoCirujia;
    }

    public void setIdCirujanoCirujia(Integer idCirujanoCirujia) {
        this.idCirujanoCirujia = idCirujanoCirujia;
    }

    public int getTitular() {
        return titular;
    }

    public void setTitular(int titular) {
        this.titular = titular;
    }

    public Cirujia getFkidCirujia() {
        return fkidCirujia;
    }

    public void setFkidCirujia(Cirujia fkidCirujia) {
        this.fkidCirujia = fkidCirujia;
    }

    public Cirujano getFkidCirujano() {
        return fkidCirujano;
    }

    public void setFkidCirujano(Cirujano fkidCirujano) {
        this.fkidCirujano = fkidCirujano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCirujanoCirujia != null ? idCirujanoCirujia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CirujanoCirujia)) {
            return false;
        }
        CirujanoCirujia other = (CirujanoCirujia) object;
        if ((this.idCirujanoCirujia == null && other.idCirujanoCirujia != null) || (this.idCirujanoCirujia != null && !this.idCirujanoCirujia.equals(other.idCirujanoCirujia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.CirujanoCirujia[ idCirujanoCirujia=" + idCirujanoCirujia + " ]";
    }
    
}
