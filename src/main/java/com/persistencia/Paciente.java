package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.List;

/**
 * Created by akino on 05-15-15.
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
public class Paciente {
    //region Comentado
/*    private Integer idPaciente;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String sexo;
    private String estadoCivil;
    private String calidadAsegurado;
    private Collection<Cirujia> cirujiasByIdPaciente;

    @Id
    @Column(name = "idPaciente", nullable = false, insertable = true, updatable = true)
    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Basic
    @Column(name = "Nombres", nullable = false, insertable = true, updatable = true, length = 45)
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "Apellidos", nullable = false, insertable = true, updatable = true, length = 45)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "Edad", nullable = false, insertable = true, updatable = true)
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Basic
    @Column(name = "Sexo", nullable = false, insertable = true, updatable = true, length = 1)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Basic
    @Column(name = "Estado Civil", nullable = false, insertable = true, updatable = true, length = 1)
    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Basic
    @Column(name = "Calidad Asegurado", nullable = false, insertable = true, updatable = true, length = 10)
    public String getCalidadAsegurado() {
        return calidadAsegurado;
    }

    public void setCalidadAsegurado(String calidadAsegurado) {
        this.calidadAsegurado = calidadAsegurado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paciente paciente = (Paciente) o;

        if (idPaciente != null ? !idPaciente.equals(paciente.idPaciente) : paciente.idPaciente != null) return false;
        if (nombres != null ? !nombres.equals(paciente.nombres) : paciente.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(paciente.apellidos) : paciente.apellidos != null) return false;
        if (edad != null ? !edad.equals(paciente.edad) : paciente.edad != null) return false;
        if (sexo != null ? !sexo.equals(paciente.sexo) : paciente.sexo != null) return false;
        if (estadoCivil != null ? !estadoCivil.equals(paciente.estadoCivil) : paciente.estadoCivil != null)
            return false;
        if (calidadAsegurado != null ? !calidadAsegurado.equals(paciente.calidadAsegurado) : paciente.calidadAsegurado != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPaciente != null ? idPaciente.hashCode() : 0;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (edad != null ? edad.hashCode() : 0);
        result = 31 * result + (sexo != null ? sexo.hashCode() : 0);
        result = 31 * result + (estadoCivil != null ? estadoCivil.hashCode() : 0);
        result = 31 * result + (calidadAsegurado != null ? calidadAsegurado.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pacienteByFkPaciente")
    public Collection<Cirujia> getCirujiasByIdPaciente() {
        return cirujiasByIdPaciente;
    }

    public void setCirujiasByIdPaciente(Collection<Cirujia> cirujiasByIdPaciente) {
        this.cirujiasByIdPaciente = cirujiasByIdPaciente;
    }*/
    //endregion

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
        return "huuu.Paciente[ idPaciente=" + idPaciente + " ]";
    }



}
