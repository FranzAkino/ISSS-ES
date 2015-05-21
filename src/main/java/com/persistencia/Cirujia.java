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
        @NamedQuery(name = "Cirujia.findByFkCirujano", query = "SELECT c FROM Cirujia c WHERE c.fkCirujano = :fkCirujano"),
        @NamedQuery(name = "Cirujia.findByEmergencia", query = "SELECT c FROM Cirujia c WHERE c.emergencia = :emergencia"),
        @NamedQuery(name = "Cirujia.findByFkRiesgo", query = "SELECT c FROM Cirujia c WHERE c.fkRiesgo = :fkRiesgo")})
public class Cirujia {

    //region Comentado
    /*private Integer idCirujia;
    private Timestamp fecha;
    private Integer fkPaciente;
    private Integer fkCie9;
    private String diagnosticoPostoperatorio;
    private String region;
    private String tipoAnestecia;
    private String anestesista;
    private Integer fkQuirofano;
    private Integer fkCirujano;
    private Integer emergencia;
    private Integer fkRiesgo;
    private Cie9 cie9ByFkCie9;
    private Paciente pacienteByFkPaciente;
    private Quirofano quirofanoByFkQuirofano;


    @Id
    @Column(name = "idCirujia", nullable = false, insertable = true, updatable = true)
    public Integer getIdCirujia() {
        return idCirujia;
    }

    public void setIdCirujia(Integer idCirujia) {
        this.idCirujia = idCirujia;
    }

    @Basic
    @Column(name = "Fecha", nullable = false, insertable = true, updatable = true)
    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "fk_Paciente", nullable = false, insertable = true, updatable = true)
    public Integer getFkPaciente() {
        return fkPaciente;
    }

    public void setFkPaciente(Integer fkPaciente) {
        this.fkPaciente = fkPaciente;
    }

    @Basic
    @Column(name = "fk_Cie9", nullable = false, insertable = true, updatable = true)
    public Integer getFkCie9() {
        return fkCie9;
    }

    public void setFkCie9(Integer fkCie9) {
        this.fkCie9 = fkCie9;
    }

    @Basic
    @Column(name = "DiagnosticoPostoperatorio", nullable = false, insertable = true, updatable = true, length = 100)
    public String getDiagnosticoPostoperatorio() {
        return diagnosticoPostoperatorio;
    }

    public void setDiagnosticoPostoperatorio(String diagnosticoPostoperatorio) {
        this.diagnosticoPostoperatorio = diagnosticoPostoperatorio;
    }

    @Basic
    @Column(name = "Region", nullable = false, insertable = true, updatable = true, length = 45)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "TipoAnestecia", nullable = false, insertable = true, updatable = true, length = 45)
    public String getTipoAnestecia() {
        return tipoAnestecia;
    }

    public void setTipoAnestecia(String tipoAnestecia) {
        this.tipoAnestecia = tipoAnestecia;
    }

    @Basic
    @Column(name = "Anestesista", nullable = false, insertable = true, updatable = true, length = 100)
    public String getAnestesista() {
        return anestesista;
    }

    public void setAnestesista(String anestesista) {
        this.anestesista = anestesista;
    }

    @Basic
    @Column(name = "fk_Quirofano", nullable = false, insertable = true, updatable = true)
    public Integer getFkQuirofano() {
        return fkQuirofano;
    }

    public void setFkQuirofano(Integer fkQuirofano) {
        this.fkQuirofano = fkQuirofano;
    }

    @Basic
    @Column(name = "fk_Cirujano", nullable = false, insertable = true, updatable = true)
    public Integer getFkCirujano() {
        return fkCirujano;
    }

    public void setFkCirujano(Integer fkCirujano) {
        this.fkCirujano = fkCirujano;
    }

    @Basic
    @Column(name = "Emergencia", nullable = false, insertable = true, updatable = true)
    public Integer getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(Integer emergencia) {
        this.emergencia = emergencia;
    }

    @Basic
    @Column(name = "fk_Riesgo", nullable = false, insertable = true, updatable = true)
    public Integer getFkRiesgo() {
        return fkRiesgo;
    }

    public void setFkRiesgo(Integer fkRiesgo) {
        this.fkRiesgo = fkRiesgo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cirujia cirujia = (Cirujia) o;

        if (idCirujia != null ? !idCirujia.equals(cirujia.idCirujia) : cirujia.idCirujia != null) return false;
        if (fecha != null ? !fecha.equals(cirujia.fecha) : cirujia.fecha != null) return false;
        if (fkPaciente != null ? !fkPaciente.equals(cirujia.fkPaciente) : cirujia.fkPaciente != null) return false;
        if (fkCie9 != null ? !fkCie9.equals(cirujia.fkCie9) : cirujia.fkCie9 != null) return false;
        if (diagnosticoPostoperatorio != null ? !diagnosticoPostoperatorio.equals(cirujia.diagnosticoPostoperatorio) : cirujia.diagnosticoPostoperatorio != null)
            return false;
        if (region != null ? !region.equals(cirujia.region) : cirujia.region != null) return false;
        if (tipoAnestecia != null ? !tipoAnestecia.equals(cirujia.tipoAnestecia) : cirujia.tipoAnestecia != null)
            return false;
        if (anestesista != null ? !anestesista.equals(cirujia.anestesista) : cirujia.anestesista != null) return false;
        if (fkQuirofano != null ? !fkQuirofano.equals(cirujia.fkQuirofano) : cirujia.fkQuirofano != null) return false;
        if (fkCirujano != null ? !fkCirujano.equals(cirujia.fkCirujano) : cirujia.fkCirujano != null) return false;
        if (emergencia != null ? !emergencia.equals(cirujia.emergencia) : cirujia.emergencia != null) return false;
        if (fkRiesgo != null ? !fkRiesgo.equals(cirujia.fkRiesgo) : cirujia.fkRiesgo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCirujia != null ? idCirujia.hashCode() : 0;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (fkPaciente != null ? fkPaciente.hashCode() : 0);
        result = 31 * result + (fkCie9 != null ? fkCie9.hashCode() : 0);
        result = 31 * result + (diagnosticoPostoperatorio != null ? diagnosticoPostoperatorio.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (tipoAnestecia != null ? tipoAnestecia.hashCode() : 0);
        result = 31 * result + (anestesista != null ? anestesista.hashCode() : 0);
        result = 31 * result + (fkQuirofano != null ? fkQuirofano.hashCode() : 0);
        result = 31 * result + (fkCirujano != null ? fkCirujano.hashCode() : 0);
        result = 31 * result + (emergencia != null ? emergencia.hashCode() : 0);
        result = 31 * result + (fkRiesgo != null ? fkRiesgo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "fk_Cie9", referencedColumnName = "idProcedimiento", nullable = false)
    public Cie9 getCie9ByFkCie9() {
        return cie9ByFkCie9;
    }

    public void setCie9ByFkCie9(Cie9 cie9ByFkCie9) {
        this.cie9ByFkCie9 = cie9ByFkCie9;
    }

    @ManyToOne
    @JoinColumn(name = "fk_Paciente", referencedColumnName = "idPaciente", nullable = false)
    public Paciente getPacienteByFkPaciente() {
        return pacienteByFkPaciente;
    }

    public void setPacienteByFkPaciente(Paciente pacienteByFkPaciente) {
        this.pacienteByFkPaciente = pacienteByFkPaciente;
    }

    @ManyToOne
    @JoinColumn(name = "fk_Quirofano", referencedColumnName = "idQuirofano", nullable = false)
    public Quirofano getQuirofanoByFkQuirofano() {
        return quirofanoByFkQuirofano;
    }

    public void setQuirofanoByFkQuirofano(Quirofano quirofanoByFkQuirofano) {
        this.quirofanoByFkQuirofano = quirofanoByFkQuirofano;
    }*/
    //endregion
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
    @Column(name = "fk_Cirujano")
    private int fkCirujano;
    @Basic(optional = false)
    @Column(name = "Emergencia")
    private int emergencia;
    @Basic(optional = false)
    @Column(name = "fk_Riesgo")
    private int fkRiesgo;
    @JoinColumn(name = "fk_Quirofano", referencedColumnName = "idQuirofano")
    @ManyToOne(optional = false)
    private Quirofano fkQuirofano;
    @JoinColumn(name = "fk_Paciente", referencedColumnName = "idPaciente")
    @ManyToOne(optional = false)
    private Paciente fkPaciente;
    @JoinColumn(name = "fk_Cie9", referencedColumnName = "idProcedimiento")
    @ManyToOne(optional = false)
    private Cie9 fkCie9;

    public Cirujia() {
    }

    public Cirujia(Integer idCirujia) {
        this.idCirujia = idCirujia;
    }

    public Cirujia(Integer idCirujia, Date fecha, String diagnosticoPostoperatorio, String region, String tipoAnestecia, String anestesista, int fkCirujano, int emergencia, int fkRiesgo) {
        this.idCirujia = idCirujia;
        this.fecha = fecha;
        this.diagnosticoPostoperatorio = diagnosticoPostoperatorio;
        this.region = region;
        this.tipoAnestecia = tipoAnestecia;
        this.anestesista = anestesista;
        this.fkCirujano = fkCirujano;
        this.emergencia = emergencia;
        this.fkRiesgo = fkRiesgo;
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

    public int getFkCirujano() {
        return fkCirujano;
    }

    public void setFkCirujano(int fkCirujano) {
        this.fkCirujano = fkCirujano;
    }

    public int getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(int emergencia) {
        this.emergencia = emergencia;
    }

    public int getFkRiesgo() {
        return fkRiesgo;
    }

    public void setFkRiesgo(int fkRiesgo) {
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
        return "huuu.Cirujia[ idCirujia=" + idCirujia + " ]";
    }
}
