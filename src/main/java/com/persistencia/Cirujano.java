/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author akino
 */
@Entity
@Table(name = "Cirujano")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cirujano.findAll", query = "SELECT c FROM Cirujano c"),
        @NamedQuery(name = "Cirujano.findByIdCirujano", query = "SELECT c FROM Cirujano c WHERE c.idCirujano = :idCirujano"),
        @NamedQuery(name = "Cirujano.findByNombres", query = "SELECT c FROM Cirujano c WHERE c.nombres = :nombres"),
        @NamedQuery(name = "Cirujano.findByApellidos", query = "SELECT c FROM Cirujano c WHERE c.apellidos = :apellidos"),
        @NamedQuery(name = "Cirujano.findByActivo", query = "SELECT c FROM Cirujano c WHERE c.activo = :activo")})
public class Cirujano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idCirujano")
    private Integer idCirujano;
    @Basic(optional = false)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "Activo")
    private int activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidCirujano")
    private List<CirujanoCirujia> cirujanoCirujiaList;
    @JoinColumn(name = "fk_idEspecialidad", referencedColumnName = "idEspecialidad")
    @ManyToOne(optional = false)
    private Especialidad fkidEspecialidad;
    @JoinColumn(name = "fk_Horarios", referencedColumnName = "idHorario")
    @ManyToOne(optional = false)
    private Horario fkHorarios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cirujano")
    private List<Metas> metasList;

    public Cirujano() {
    }

    public Cirujano(Integer idCirujano) {
        this.idCirujano = idCirujano;
    }

    public Cirujano(Integer idCirujano, String nombres, String apellidos, int activo) {
        this.idCirujano = idCirujano;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.activo = activo;
    }

    public Integer getIdCirujano() {
        return idCirujano;
    }

    public void setIdCirujano(Integer idCirujano) {
        this.idCirujano = idCirujano;
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

    public Especialidad getFkidEspecialidad() {
        return fkidEspecialidad;
    }

    public void setFkidEspecialidad(Especialidad fkidEspecialidad) {
        this.fkidEspecialidad = fkidEspecialidad;
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
        hash += (idCirujano != null ? idCirujano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cirujano)) {
            return false;
        }
        Cirujano other = (Cirujano) object;
        if ((this.idCirujano == null && other.idCirujano != null) || (this.idCirujano != null && !this.idCirujano.equals(other.idCirujano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Cirujano[ idCirujano=" + idCirujano + " ]";
    }

}
