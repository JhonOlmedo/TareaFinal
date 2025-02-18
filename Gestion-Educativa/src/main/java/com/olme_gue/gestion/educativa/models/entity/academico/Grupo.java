package com.olme_gue.gestion.educativa.models.entity.academico;

import jakarta.persistence.*;

@Entity
@Table(name = "GRUPO")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private int idGrupo;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "modalidad", length = 50)
    private String modalidad;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", nullable = false)
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;

    public Grupo() {
    }

    public Grupo(int idGrupo, String codigo, int capacidad, String estado, String modalidad, Asignatura asignatura, Docente docente) {
        this.idGrupo = idGrupo;
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.estado = estado;
        this.modalidad = modalidad;
        this.asignatura = asignatura;
        this.docente = docente;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    
}
