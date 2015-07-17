/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author akino
 */
@Entity
@Table(name = "Cirujia")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cirujia.findAll", query = "SELECT c FROM Cirujia c"),
        @NamedQuery(name = "Cirujia.findByIdCirujia", query = "SELECT c FROM Cirujia c WHERE c.idCirujia = :idCirujia"),
        @NamedQuery(name = "Cirujia.findByFecha", query = "SELECT c FROM Cirujia c WHERE c.fecha = :fecha"),
        @NamedQuery(name = "Cirujia.findByDiagnosticoPostoperatorio", query = "SELECT c FROM Cirujia c WHERE c.diagnosticoPostoperatorio = :diagnosticoPostoperatorio"),
        @NamedQuery(name = "Cirujia.findByRegion", query = "SELECT c FROM Cirujia c WHERE c.region = :region"),
        @NamedQuery(name = "Cirujia.findByTipoAnestecia", query = "SELECT c FROM Cirujia c WHERE c.tipoAnestecia = :tipoAnestecia"),
        @NamedQuery(name = "Cirujia.findByAnestesista", query = "SELECT c FROM Cirujia c WHERE c.anestesista = :anestesista"),
        @NamedQuery(name = "Cirujia.findByInstrumentista", query = "SELECT c FROM Cirujia c WHERE c.instrumentista = :instrumentista"),
        @NamedQuery(name = "Cirujia.findByCircular", query = "SELECT c FROM Cirujia c WHERE c.circular = :circular"),
        @NamedQuery(name = "Cirujia.findByEmergencia", query = "SELECT c FROM Cirujia c WHERE c.emergencia = :emergencia"),
        @NamedQuery(name = "Cirujia.findByRealizada", query = "SELECT c FROM Cirujia c WHERE c.realizada = :realizada"),
        @NamedQuery(name = "Cirujia.findByAnestesiologo", query = "SELECT c FROM Cirujia c WHERE c.anestesiologo = :anestesiologo"),
        @NamedQuery(name = "Cirujia.findByInicio", query = "SELECT c FROM Cirujia c WHERE c.inicio = :inicio"),
        @NamedQuery(name = "Cirujia.findByFinalizacion", query = "SELECT c FROM Cirujia c WHERE c.finalizacion = :finalizacion")})
public class Cirujia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "idCirujia")
    private Integer idCirujia;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "DiagnosticoPostoperatorio")
    private String diagnosticoPostoperatorio;
    @Basic(optional = false)
    @Column(name = "Region")
    private String region;
    @Basic(optional = false)
    @Column(name = "TipoAnestecia")
    private String tipoAnestecia;
    @Basic(optional = false)
    @Column(name = "Anestesista")
    private String anestesista;
    @Basic(optional = false)
    @Column(name = "Instrumentista")
    private String instrumentista;
    @Basic(optional = false)
    @Column(name = "Circular")
    private String circular;
    @Basic(optional = false)
    @Column(name = "Emergencia")
    private int emergencia;
    @Basic(optional = false)
    @Column(name = "Realizada")
    private int realizada;
    @Column(name = "Anestesiologo")
    private String anestesiologo;
    @Column(name = "Inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Column(name = "Finalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalizacion;
    @JoinColumn(name = "fk_Suspencion", referencedColumnName = "idSuspenciones")
    @ManyToOne
    private Suspenciones fkSuspencion;
    @JoinColumn(name = "fk_Riesgo", referencedColumnName = "idRiesgo")
    @ManyToOne(optional = false)
    private Riesgo fkRiesgo;
    @JoinColumn(name = "fk_Quirofano", referencedColumnName = "idQuirofano")
    @ManyToOne(optional = false)
    private Quirofano fkQuirofano;
    @JoinColumn(name = "fk_Paciente", referencedColumnName = "idPaciente")
    @ManyToOne(optional = false)
    private Paciente fkPaciente;
    @JoinColumn(name = "fk_Cie9", referencedColumnName = "idProcedimiento")
    @ManyToOne(optional = false)
    private Cie9 fkCie9;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidCirujia")
    private List<CirujanoCirujia> cirujanoCirujiaList;

    public Cirujia() {
    }

    public Cirujia(Integer idCirujia) {
        this.idCirujia = idCirujia;
    }

    public Cirujia(Integer idCirujia, Date fecha, String diagnosticoPostoperatorio, String region, String tipoAnestecia, String anestesista, String instrumentista, String circular, int emergencia, int realizada) {
        this.idCirujia = idCirujia;
        this.fecha = fecha;
        this.diagnosticoPostoperatorio = diagnosticoPostoperatorio;
        this.region = region;
        this.tipoAnestecia = tipoAnestecia;
        this.anestesista = anestesista;
        this.instrumentista = instrumentista;
        this.circular = circular;
        this.emergencia = emergencia;
        this.realizada = realizada;
    }

    public Integer getIdCirujia() {
        return idCirujia;
    }

    public void setIdCirujia(Integer idCirujia) {
        this.idCirujia = idCirujia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDiagnosticoPostoperatorio() {
        return diagnosticoPostoperatorio;
    }

    public void setDiagnosticoPostoperatorio(String diagnosticoPostoperatorio) {
        this.diagnosticoPostoperatorio = diagnosticoPostoperatorio;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTipoAnestecia() {
        return tipoAnestecia;
    }

    public void setTipoAnestecia(String tipoAnestecia) {
        this.tipoAnestecia = tipoAnestecia;
    }

    public String getAnestesista() {
        return anestesista;
    }

    public void setAnestesista(String anestesista) {
        this.anestesista = anestesista;
    }

    public String getInstrumentista() {
        return instrumentista;
    }

    public void setInstrumentista(String instrumentista) {
        this.instrumentista = instrumentista;
    }

    public String getCircular() {
        return circular;
    }

    public void setCircular(String circular) {
        this.circular = circular;
    }

    public int getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(int emergencia) {
        this.emergencia = emergencia;
    }

    public int getRealizada() {
        return realizada;
    }

    public void setRealizada(int realizada) {
        this.realizada = realizada;
    }

    public String getAnestesiologo() {
        return anestesiologo;
    }

    public void setAnestesiologo(String anestesiologo) {
        this.anestesiologo = anestesiologo;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFinalizacion() {
        return finalizacion;
    }

    public void setFinalizacion(Date finalizacion) {
        this.finalizacion = finalizacion;
    }

    public Suspenciones getFkSuspencion() {
        return fkSuspencion;
    }

    public void setFkSuspencion(Suspenciones fkSuspencion) {
        this.fkSuspencion = fkSuspencion;
    }

    public Riesgo getFkRiesgo() {
        return fkRiesgo;
    }

    public void setFkRiesgo(Riesgo fkRiesgo) {
        this.fkRiesgo = fkRiesgo;
    }

    public Quirofano getFkQuirofano() {
        return fkQuirofano;
    }

    public void setFkQuirofano(Quirofano fkQuirofano) {
        this.fkQuirofano = fkQuirofano;
    }

    public Paciente getFkPaciente() {
        return fkPaciente;
    }

    public void setFkPaciente(Paciente fkPaciente) {
        this.fkPaciente = fkPaciente;
    }

    public Cie9 getFkCie9() {
        return fkCie9;
    }

    public void setFkCie9(Cie9 fkCie9) {
        this.fkCie9 = fkCie9;
    }

    @XmlTransient
    public List<CirujanoCirujia> getCirujanoCirujiaList() {
        return cirujanoCirujiaList;
    }

    public void setCirujanoCirujiaList(List<CirujanoCirujia> cirujanoCirujiaList) {
        this.cirujanoCirujiaList = cirujanoCirujiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCirujia != null ? idCirujia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cirujia)) {
            return false;
        }
        Cirujia other = (Cirujia) object;
        if ((this.idCirujia == null && other.idCirujia != null) || (this.idCirujia != null && !this.idCirujia.equals(other.idCirujia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.persistencia.Cirujia[ idCirujia=" + idCirujia + " ]";
    }

}
