package com.olme_gue.gestion.educativa.models.entity.academico;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CALIFICACION")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private int idCalificacion;

    @Column(name = "nota", nullable = false)
    private float nota;

    @Column(name = "tipo_evaluacion", nullable = false, length = 50)
    private String tipoEvaluacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;

    public Calificacion() {
    }

    public Calificacion(int idCalificacion, float nota, String tipoEvaluacion, Date fechaRegistro, String observaciones, Estudiante estudiante, Grupo grupo) {
        this.idCalificacion = idCalificacion;
        this.nota = nota;
        this.tipoEvaluacion = tipoEvaluacion;
        this.fechaRegistro = fechaRegistro;
        this.observaciones = observaciones;
        this.estudiante = estudiante;
        this.grupo = grupo;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
}
