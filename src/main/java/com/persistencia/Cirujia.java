package com.persistencia;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by akino on 05-15-15.
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
        @NamedQuery(name = "Cirujia.findByEmergencia", query = "SELECT c FROM Cirujia c WHERE c.emergencia = :emergencia"),
        @NamedQuery(name = "Cirujia.findByRealizada", query = "SELECT c FROM Cirujia c WHERE c.realizada = :realizada")})
public class Cirujia {
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
    @Column(name = "Emergencia")
    private int emergencia;
    @Basic(optional = false)
    @Column(name = "Realizada")
    private int realizada;
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
    @JoinColumn(name = "fk_Cirujano", referencedColumnName = "idCirujano")
    @ManyToOne(optional = false)
    private Cirujano fkCirujano;

    public Cirujia() {
    }

    public Cirujia(Integer idCirujia) {
        this.idCirujia = idCirujia;
    }

    public Cirujia(Integer idCirujia, Date fecha, String diagnosticoPostoperatorio, String region, String tipoAnestecia, String anestesista, int emergencia, int realizada) {
        this.idCirujia = idCirujia;
        this.fecha = fecha;
        this.diagnosticoPostoperatorio = diagnosticoPostoperatorio;
        this.region = region;
        this.tipoAnestecia = tipoAnestecia;
        this.anestesista = anestesista;
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

    public Cirujano getFkCirujano() {
        return fkCirujano;
    }

    public void setFkCirujano(Cirujano fkCirujano) {
        this.fkCirujano = fkCirujano;
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
        return "persistencia.Cirujia[ idCirujia=" + idCirujia + " ]";
    }
}
