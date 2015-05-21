package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by akino on 05-15-15.
 */
@Entity
@Table(name = "Metas")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Metas.findAll", query = "SELECT m FROM Metas m"),
        @NamedQuery(name = "Metas.findByIdMetas", query = "SELECT m FROM Metas m WHERE m.idMetas = :idMetas"),
        @NamedQuery(name = "Metas.findByMes", query = "SELECT m FROM Metas m WHERE m.mes = :mes"),
        @NamedQuery(name = "Metas.findByMeta", query = "SELECT m FROM Metas m WHERE m.meta = :meta")})
public class Metas {
    //region Comentado
    /*private Integer idMetas;
    private Integer mes;
    private Integer meta;
    private Integer fkCirujano;
    private Cirujano cirujanoByFkCirujano;

    @Id
    @Column(name = "idMetas", nullable = false, insertable = true, updatable = true)
    public Integer getIdMetas() {
        return idMetas;
    }

    public void setIdMetas(Integer idMetas) {
        this.idMetas = idMetas;
    }

    @Basic
    @Column(name = "Mes", nullable = false, insertable = true, updatable = true)
    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    @Basic
    @Column(name = "Meta", nullable = false, insertable = true, updatable = true)
    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    @Basic
    @Column(name = "fk_Cirujano", nullable = false, insertable = true, updatable = true)
    public Integer getFkCirujano() {
        return fkCirujano;
    }

    public void setFkCirujano(Integer fkCirujano) {
        this.fkCirujano = fkCirujano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metas metas = (Metas) o;

        if (idMetas != null ? !idMetas.equals(metas.idMetas) : metas.idMetas != null) return false;
        if (mes != null ? !mes.equals(metas.mes) : metas.mes != null) return false;
        if (meta != null ? !meta.equals(metas.meta) : metas.meta != null) return false;
        if (fkCirujano != null ? !fkCirujano.equals(metas.fkCirujano) : metas.fkCirujano != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMetas != null ? idMetas.hashCode() : 0;
        result = 31 * result + (mes != null ? mes.hashCode() : 0);
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        result = 31 * result + (fkCirujano != null ? fkCirujano.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "fk_Cirujano", referencedColumnName = "idCirujano", nullable = false)
    public Cirujano getCirujanoByFkCirujano() {
        return cirujanoByFkCirujano;
    }

    public void setCirujanoByFkCirujano(Cirujano cirujanoByFkCirujano) {
        this.cirujanoByFkCirujano = cirujanoByFkCirujano;
    }*/
    //endregion

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idMetas")
    private Integer idMetas;
    @Basic(optional = false)
    @Column(name = "Mes")
    private int mes;
    @Basic(optional = false)
    @Column(name = "Meta")
    private int meta;
    @JoinColumn(name = "fk_Cirujano", referencedColumnName = "idCirujano")
    @ManyToOne(optional = false)
    private Cirujano fkCirujano;

    public Metas() {
    }

    public Metas(Integer idMetas) {
        this.idMetas = idMetas;
    }

    public Metas(Integer idMetas, int mes, int meta) {
        this.idMetas = idMetas;
        this.mes = mes;
        this.meta = meta;
    }

    public Integer getIdMetas() {
        return idMetas;
    }

    public void setIdMetas(Integer idMetas) {
        this.idMetas = idMetas;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public Cirujano getFkCirujano() {
        return fkCirujano;
    }

    public void setFkCirujano(Cirujano fkCirujano) {
        this.fkCirujano = fkCirujano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetas != null ? idMetas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metas)) {
            return false;
        }
        Metas other = (Metas) object;
        if ((this.idMetas == null && other.idMetas != null) || (this.idMetas != null && !this.idMetas.equals(other.idMetas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "huuu.Metas[ idMetas=" + idMetas + " ]";
    }


}
