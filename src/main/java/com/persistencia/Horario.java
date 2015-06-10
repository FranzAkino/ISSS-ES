package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.List;

/**
 * Created by akino on 05-15-15.
 */
@Entity
@Table(name = "Horario")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
        @NamedQuery(name = "Horario.findByIdHorario", query = "SELECT h FROM Horario h WHERE h.idHorario = :idHorario"),
        @NamedQuery(name = "Horario.findByEntrada", query = "SELECT h FROM Horario h WHERE h.entrada = :entrada"),
        @NamedQuery(name = "Horario.findBySalida", query = "SELECT h FROM Horario h WHERE h.salida = :salida")})
public class Horario {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idHorario")
    private Integer idHorario;
    @Basic(optional = false)
    @Column(name = "Entrada")
    @Temporal(TemporalType.TIME)
    private Date entrada;
    @Basic(optional = false)
    @Column(name = "Salida")
    @Temporal(TemporalType.TIME)
    private Date salida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkHorarios")
    private List<Cirujano> cirujanoList;

    public Horario() {
    }

    public Horario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Horario(Integer idHorario, Date entrada, Date salida) {
        this.idHorario = idHorario;
        this.entrada = entrada;
        this.salida = salida;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    @XmlTransient
    public List<Cirujano> getCirujanoList() {
        return cirujanoList;
    }

    public void setCirujanoList(List<Cirujano> cirujanoList) {
        this.cirujanoList = cirujanoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "huuu.Horario[ idHorario=" + idHorario + " ]";
    }

}
