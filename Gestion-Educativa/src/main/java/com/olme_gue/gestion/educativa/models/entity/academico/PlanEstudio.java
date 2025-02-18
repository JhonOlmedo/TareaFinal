package com.olme_gue.gestion.educativa.models.entity.academico;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLAN_ESTUDIO")
public class PlanEstudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private int idPlan;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "fecha_inicio")
    private java.util.Date fechaInicio;

    @Column(name = "fecha_fin")
    private java.util.Date fechaFin;

    @Column(name = "estado", length = 50)
    private String estado;

    public PlanEstudio() {
    }
    

    public PlanEstudio(int idPlan, String nombre, String descripcion, Date fechaInicio, Date fechaFin, String estado) {
        this.idPlan = idPlan;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
