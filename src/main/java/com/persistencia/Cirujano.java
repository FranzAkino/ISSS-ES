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
        @NamedQuery(name = "Cirujano.findByMeta", query = "SELECT c FROM Cirujano c WHERE c.meta = :meta"),
        @NamedQuery(name = "Cirujano.findByRealizadas", query = "SELECT c FROM Cirujano c WHERE c.realizadas = :realizadas"),
        @NamedQuery(name = "Cirujano.findBySuspendidas", query = "SELECT c FROM Cirujano c WHERE c.suspendidas = :suspendidas"),
        @NamedQuery(name = "Cirujano.findByActivo", query = "SELECT c FROM Cirujano c WHERE c.activo = :activo")})
public class Cirujano {
    //region Comentado
    /*private Integer idCirujano;
    private String nombres;
    private String apellidos;
    private Integer fkEspeciadlidades;
    private Integer fkHorarios;
    private Integer meta;
    private Integer realizadas;
    private Integer suspendidas;
    private Integer activo;
    private Especialidad especialidadByFkEspeciadlidades;
    private Horario horarioByFkHorarios;
    private Collection<Metas> metasesByIdCirujano;

    @Id
    @Column(name = "idCirujano", nullable = false, insertable = true, updatable = true)
    public Integer getIdCirujano() {
        return idCirujano;
    }

    public void setIdCirujano(Integer idCirujano) {
        this.idCirujano = idCirujano;
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
    @Column(name = "fk_Especiadlidades", nullable = false, insertable = true, updatable = true)
    public Integer getFkEspeciadlidades() {
        return fkEspeciadlidades;
    }

    public void setFkEspeciadlidades(Integer fkEspeciadlidades) {
        this.fkEspeciadlidades = fkEspeciadlidades;
    }

    @Basic
    @Column(name = "fk_Horarios", nullable = false, insertable = true, updatable = true)
    public Integer getFkHorarios() {
        return fkHorarios;
    }

    public void setFkHorarios(Integer fkHorarios) {
        this.fkHorarios = fkHorarios;
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
    @Column(name = "Realizadas", nullable = false, insertable = true, updatable = true)
    public Integer getRealizadas() {
        return realizadas;
    }

    public void setRealizadas(Integer realizadas) {
        this.realizadas = realizadas;
    }

    @Basic
    @Column(name = "Suspendidas", nullable = false, insertable = true, updatable = true)
    public Integer getSuspendidas() {
        return suspendidas;
    }

    public void setSuspendidas(Integer suspendidas) {
        this.suspendidas = suspendidas;
    }

    @Basic
    @Column(name = "Activo", nullable = false, insertable = true, updatable = true)
    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cirujano cirujano = (Cirujano) o;

        if (idCirujano != null ? !idCirujano.equals(cirujano.idCirujano) : cirujano.idCirujano != null) return false;
        if (nombres != null ? !nombres.equals(cirujano.nombres) : cirujano.nombres != null) return false;
        if (apellidos != null ? !apellidos.equals(cirujano.apellidos) : cirujano.apellidos != null) return false;
        if (fkEspeciadlidades != null ? !fkEspeciadlidades.equals(cirujano.fkEspeciadlidades) : cirujano.fkEspeciadlidades != null)
            return false;
        if (fkHorarios != null ? !fkHorarios.equals(cirujano.fkHorarios) : cirujano.fkHorarios != null) return false;
        if (meta != null ? !meta.equals(cirujano.meta) : cirujano.meta != null) return false;
        if (realizadas != null ? !realizadas.equals(cirujano.realizadas) : cirujano.realizadas != null) return false;
        if (suspendidas != null ? !suspendidas.equals(cirujano.suspendidas) : cirujano.suspendidas != null)
            return false;
        if (activo != null ? !activo.equals(cirujano.activo) : cirujano.activo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCirujano != null ? idCirujano.hashCode() : 0;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (fkEspeciadlidades != null ? fkEspeciadlidades.hashCode() : 0);
        result = 31 * result + (fkHorarios != null ? fkHorarios.hashCode() : 0);
        result = 31 * result + (meta != null ? meta.hashCode() : 0);
        result = 31 * result + (realizadas != null ? realizadas.hashCode() : 0);
        result = 31 * result + (suspendidas != null ? suspendidas.hashCode() : 0);
        result = 31 * result + (activo != null ? activo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "fk_Especiadlidades", referencedColumnName = "idEspecialidad", nullable = false)
    public Especialidad getEspecialidadByFkEspeciadlidades() {
        return especialidadByFkEspeciadlidades;
    }

    public void setEspecialidadByFkEspeciadlidades(Especialidad especialidadByFkEspeciadlidades) {
        this.especialidadByFkEspeciadlidades = especialidadByFkEspeciadlidades;
    }

    @ManyToOne
    @JoinColumn(name = "fk_Horarios", referencedColumnName = "idHorario", nullable = false)
    public Horario getHorarioByFkHorarios() {
        return horarioByFkHorarios;
    }

    public void setHorarioByFkHorarios(Horario horarioByFkHorarios) {
        this.horarioByFkHorarios = horarioByFkHorarios;
    }

    @OneToMany(mappedBy = "cirujanoByFkCirujano")
    public Collection<Metas> getMetasesByIdCirujano() {
        return metasesByIdCirujano;
    }

    public void setMetasesByIdCirujano(Collection<Metas> metasesByIdCirujano) {
        this.metasesByIdCirujano = metasesByIdCirujano;
    }*/
    //endregion
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
    @Column(name = "Meta")
    private int meta;
    @Basic(optional = false)
    @Column(name = "Realizadas")
    private int realizadas;
    @Basic(optional = false)
    @Column(name = "Suspendidas")
    private int suspendidas;
    @Basic(optional = false)
    @Column(name = "Activo")
    private int activo;
    @JoinColumn(name = "fk_Horarios", referencedColumnName = "idHorario")
    @ManyToOne(optional = false)
    private Horario fkHorarios;
    @JoinColumn(name = "fk_Especiadlidades", referencedColumnName = "idEspecialidad")
    @ManyToOne(optional = false)
    private Especialidad fkEspeciadlidades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCirujano")
    private List<Metas> metasList;

    public Cirujano() {
    }

    public Cirujano(Integer idCirujano) {
        this.idCirujano = idCirujano;
    }

    public Cirujano(Integer idCirujano, String nombres, String apellidos, int meta, int realizadas, int suspendidas, int activo) {
        this.idCirujano = idCirujano;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.meta = meta;
        this.realizadas = realizadas;
        this.suspendidas = suspendidas;
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

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public int getRealizadas() {
        return realizadas;
    }

    public void setRealizadas(int realizadas) {
        this.realizadas = realizadas;
    }

    public int getSuspendidas() {
        return suspendidas;
    }

    public void setSuspendidas(int suspendidas) {
        this.suspendidas = suspendidas;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Horario getFkHorarios() {
        return fkHorarios;
    }

    public void setFkHorarios(Horario fkHorarios) {
        this.fkHorarios = fkHorarios;
    }

    public Especialidad getFkEspeciadlidades() {
        return fkEspeciadlidades;
    }

    public void setFkEspeciadlidades(Especialidad fkEspeciadlidades) {
        this.fkEspeciadlidades = fkEspeciadlidades;
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
        return "huuu.Cirujano[ idCirujano=" + idCirujano + " ]";
    }

}
