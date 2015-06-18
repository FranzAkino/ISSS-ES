package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author akino
 */
@Entity
@Table(name = "Cirujano")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cirujano.findAll", query = "SELECT c FROM Cirujano c"),
        @NamedQuery(name = "Cirujano.findByIdCirujano", query = "SELECT c FROM Cirujano c WHERE c.cirujanoPK.idCirujano = :idCirujano"),
        @NamedQuery(name = "Cirujano.findByNombres", query = "SELECT c FROM Cirujano c WHERE c.nombres = :nombres"),
        @NamedQuery(name = "Cirujano.findByApellidos", query = "SELECT c FROM Cirujano c WHERE c.apellidos = :apellidos"),
        @NamedQuery(name = "Cirujano.findByActivo", query = "SELECT c FROM Cirujano c WHERE c.activo = :activo"),
        @NamedQuery(name = "Cirujano.findByFkidEspecialidad", query = "SELECT c FROM Cirujano c WHERE c.cirujanoPK.fkidEspecialidad = :fkidEspecialidad")})
public class Cirujano implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CirujanoPK cirujanoPK;
//    @Basic(optional = false)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "Activo")
    private int activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cirujano")
    private List<CirujanoCirujia> cirujanoCirujiaList;
    @JoinColumn(name = "fk_idEspecialidad", referencedColumnName = "idEspecialidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Especialidad especialidad;
    @JoinColumn(name = "fk_Horarios", referencedColumnName = "idHorario")
    @ManyToOne(optional = false)
    private Horario fkHorarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cirujano")
    private List<Metas> metasList;

    public Cirujano() {
    }

    public Cirujano(CirujanoPK cirujanoPK) {
        this.cirujanoPK = cirujanoPK;
    }

    public Cirujano(CirujanoPK cirujanoPK, String nombres, String apellidos, int activo) {
        this.cirujanoPK = cirujanoPK;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    public Cirujano(int idCirujano, int fkidEspecialidad) {
        this.cirujanoPK = new CirujanoPK(idCirujano, fkidEspecialidad);
    }

    public CirujanoPK getCirujanoPK() {
        return cirujanoPK;
    }

    public void setCirujanoPK(CirujanoPK cirujanoPK) {
        this.cirujanoPK = cirujanoPK;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<CirujanoCirujia> getCirujanoCirujiaList() {
        return cirujanoCirujiaList;
    }

    public void setCirujanoCirujiaList(List<CirujanoCirujia> cirujanoCirujiaList) {
        this.cirujanoCirujiaList = cirujanoCirujiaList;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Horario getFkHorarios() {
        return fkHorarios;
    }

    public void setFkHorarios(Horario fkHorarios) {
        this.fkHorarios = fkHorarios;
    }

    @XmlTransient
    public List<Metas> getMetasList() {
        return metasList;
    }

    public void setMetasList(List<Metas> metasList) {
        this.metasList = metasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cirujanoPK != null ? cirujanoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cirujano)) {
            return false;
        }
        Cirujano other = (Cirujano) object;
        if ((this.cirujanoPK == null && other.cirujanoPK != null) || (this.cirujanoPK != null && !this.cirujanoPK.equals(other.cirujanoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Cirujano[ cirujanoPK=" + cirujanoPK + " ]";
    }

}
