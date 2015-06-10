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
@Table(name = "Cirujano")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cirujano.findAll", query = "SELECT c FROM Cirujano c"),
        @NamedQuery(name = "Cirujano.findByIdCirujano", query = "SELECT c FROM Cirujano c WHERE c.idCirujano = :idCirujano"),
        @NamedQuery(name = "Cirujano.findByNombres", query = "SELECT c FROM Cirujano c WHERE c.nombres = :nombres"),
        @NamedQuery(name = "Cirujano.findByApellidos", query = "SELECT c FROM Cirujano c WHERE c.apellidos = :apellidos"),
        @NamedQuery(name = "Cirujano.findByActivo", query = "SELECT c FROM Cirujano c WHERE c.activo = :activo")})
public class Cirujano {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCirujano")
    private List<Cirujia> cirujiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cirujano")
    private List<CirujanoEspecialidad> cirujanoEspecialidadList;
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
    public List<Cirujia> getCirujiaList() {
        return cirujiaList;
    }

    public void setCirujiaList(List<Cirujia> cirujiaList) {
        this.cirujiaList = cirujiaList;
    }

    @XmlTransient
    public List<CirujanoEspecialidad> getCirujanoEspecialidadList() {
        return cirujanoEspecialidadList;
    }

    public void setCirujanoEspecialidadList(List<CirujanoEspecialidad> cirujanoEspecialidadList) {
        this.cirujanoEspecialidadList = cirujanoEspecialidadList;
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
        return "persistencia.Cirujano[ idCirujano=" + idCirujano + " ]";
    }

}
