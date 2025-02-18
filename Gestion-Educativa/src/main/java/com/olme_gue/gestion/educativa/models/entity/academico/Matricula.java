package com.olme_gue.gestion.educativa.models.entity.academico;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MATRICULA")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private int idMatricula;

    @Column(name = "fecha_matricula", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaMatricula;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "valor_total", nullable = false)
    private float valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_periodo", nullable = false)
    private PeriodoLectivo periodoLectivo;

    public Matricula() {
    }

    public Matricula(int idMatricula, Date fechaMatricula, String estado, float valorTotal, Estudiante estudiante, PeriodoLectivo periodoLectivo) {
        this.idMatricula = idMatricula;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
        this.valorTotal = valorTotal;
        this.estudiante = estudiante;
        this.periodoLectivo = periodoLectivo;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public PeriodoLectivo getPeriodoLectivo() {
        return periodoLectivo;
    }

    public void setPeriodoLectivo(PeriodoLectivo periodoLectivo) {
        this.periodoLectivo = periodoLectivo;
    }
    
    
}
