package com.persistencia;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
        @NamedQuery(name = "CirujanoCirujia.findByIdCirujanoCirujia", query = "SELECT c FROM CirujanoCirujia c WHERE c.cirujanoCirujiaPK.idCirujanoCirujia = :idCirujanoCirujia"),
        @NamedQuery(name = "CirujanoCirujia.findByFkidCirujia", query = "SELECT c FROM CirujanoCirujia c WHERE c.cirujanoCirujiaPK.fkidCirujia = :fkidCirujia"),
        @NamedQuery(name = "CirujanoCirujia.findByFkidCirujano", query = "SELECT c FROM CirujanoCirujia c WHERE c.cirujanoCirujiaPK.fkidCirujano = :fkidCirujano"),
        @NamedQuery(name = "CirujanoCirujia.findByTitular", query = "SELECT c FROM CirujanoCirujia c WHERE c.titular = :titular")})
public class CirujanoCirujia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CirujanoCirujiaPK cirujanoCirujiaPK;
//    @Basic(optional = false)
    @Column(name = "Titular")
    private int titular;
    @JoinColumn(name = "fk_idCirujia", referencedColumnName = "idCirujia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cirujia cirujia;
    @JoinColumn(name = "fk_idCirujano", referencedColumnName = "idCirujano", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cirujano cirujano;

    public CirujanoCirujia() {
    }

    public CirujanoCirujia(CirujanoCirujiaPK cirujanoCirujiaPK) {
        this.cirujanoCirujiaPK = cirujanoCirujiaPK;
    }

    public CirujanoCirujia(CirujanoCirujiaPK cirujanoCirujiaPK, int titular) {
        this.cirujanoCirujiaPK = cirujanoCirujiaPK;
        this.titular = titular;
    }

    public CirujanoCirujia(int idCirujanoCirujia, int fkidCirujia, int fkidCirujano) {
        this.cirujanoCirujiaPK = new CirujanoCirujiaPK(idCirujanoCirujia, fkidCirujia, fkidCirujano);
    }

    public CirujanoCirujiaPK getCirujanoCirujiaPK() {
        return cirujanoCirujiaPK;
    }

    public void setCirujanoCirujiaPK(CirujanoCirujiaPK cirujanoCirujiaPK) {
        this.cirujanoCirujiaPK = cirujanoCirujiaPK;
    }

    public int getTitular() {
        return titular;
    }

    public void setTitular(int titular) {
        this.titular = titular;
    }

    public Cirujia getCirujia() {
        return cirujia;
    }

    public void setCirujia(Cirujia cirujia) {
        this.cirujia = cirujia;
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
        hash += (cirujanoCirujiaPK != null ? cirujanoCirujiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CirujanoCirujia)) {
            return false;
        }
        CirujanoCirujia other = (CirujanoCirujia) object;
        if ((this.cirujanoCirujiaPK == null && other.cirujanoCirujiaPK != null) || (this.cirujanoCirujiaPK != null && !this.cirujanoCirujiaPK.equals(other.cirujanoCirujiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.CirujanoCirujia[ cirujanoCirujiaPK=" + cirujanoCirujiaPK + " ]";
    }

}
