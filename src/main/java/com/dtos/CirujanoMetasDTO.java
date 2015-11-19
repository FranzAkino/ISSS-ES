package com.dtos;


public class CirujanoMetasDTO {
    String nombres;
    String apellidos;

    String tipoReporte;

    int anio;
    int mes;
    int suspendidas;
    int realizadas;
    int idEspecialidad;
    String especialidad;
    int meta;
    int metaTotal;
    int realizadasTotal;

    int programadas;
    double rendimiento;

    public int getProgramadas() {
        return programadas;
    }

    public void setProgramadas(int programadas) {
        this.programadas = programadas;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(double rendimiento) {
        this.rendimiento = rendimiento;
    }

    public int getMetaTotal() {
        return metaTotal;
    }

    public void setMetaTotal(int metaTotal) {
        this.metaTotal = metaTotal;
    }

    public int getRealizadasTotal() {
        return realizadasTotal;
    }

    public void setRealizadasTotal(int realizadasTotal) {
        this.realizadasTotal = realizadasTotal;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getSuspendidas() {
        return suspendidas;
    }

    public void setSuspendidas(int suspendidas) {
        this.suspendidas = suspendidas;
    }

    public int getRealizadas() {
        return realizadas;
    }

    public void setRealizadas(int realizadas) {
        this.realizadas = realizadas;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
}
