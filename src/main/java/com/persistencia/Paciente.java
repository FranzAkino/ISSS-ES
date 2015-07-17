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
import javax.persistence.Id;
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
@Table(name = "Paciente")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
        @NamedQuery(name = "Paciente.findByIdPaciente", query = "SELECT p FROM Paciente p WHERE p.idPaciente = :idPaciente"),
        @NamedQuery(name = "Paciente.findByNombres", query = "SELECT p FROM Paciente p WHERE p.nombres = :nombres"),
        @NamedQuery(name = "Paciente.findByApellidos", query = "SELECT p FROM Paciente p WHERE p.apellidos = :apellidos"),
        @NamedQuery(name = "Paciente.findByEdad", query = "SELECT p FROM Paciente p WHERE p.edad = :edad"),
        @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
        @NamedQuery(name = "Paciente.findByEstadoCivil", query = "SELECT p FROM Paciente p WHERE p.estadoCivil = :estadoCivil"),
        @NamedQuery(name = "Paciente.findByCalidadAsegurado", query = "SELECT p FROM Paciente p WHERE p.calidadAsegurado = :calidadAsegurado")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @Column(name = "idPaciente")
    private Integer idPaciente;
    @Basic(optional = false)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "Edad")
    private int edad;
    @Basic(optional = false)
    @Column(name = "Sexo")
    private Character sexo;
    @Basic(optional = false)
    @Column(name = "Estado Civil")
    private Character estadoCivil;
    @Basic(optional = false)
    @Column(name = "Calidad Asegurado")
    private String calidadAsegurado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPaciente")
    private List<Cirujia> cirujiaList;

    public Paciente() {
    }

    public Paciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente(Integer idPaciente, String nombres, String apellidos, int edad, Character sexo, Character estadoCivil, String calidadAsegurado) {
        this.idPaciente = idPaciente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.calidadAsegurado = calidadAsegurado;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCalidadAsegurado() {
        return calidadAsegurado;
    }

    public void setCalidadAsegurado(String calidadAsegurado) {
        this.calidadAsegurado = calidadAsegurado;
    }

    @XmlTransient
    public List<Cirujia> getCirujiaList() {
        return cirujiaList;
    }

    public void setCirujiaList(List<Cirujia> cirujiaList) {
        this.cirujiaList = cirujiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Paciente[ idPaciente=" + idPaciente + " ]";
    }

}
