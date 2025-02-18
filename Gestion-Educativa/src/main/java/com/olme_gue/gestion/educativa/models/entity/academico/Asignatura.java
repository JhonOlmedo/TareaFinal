package com.olme_gue.gestion.educativa.models.entity.academico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ASIGNATURA")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private int idAsignatura;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "creditos", nullable = false)
    private int creditos;

    @Column(name = "nivel", length = 50)
    private String nivel;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "modalidad", length = 50)
    private String modalidad;

    @Column(name = "prerequisitos", length = 255)
    private String prerequisitos;

    @ManyToMany(mappedBy = "asignaturas")
    private Set<Docente> docentes;

    @ManyToMany(mappedBy = "asignaturas")
    @JsonBackReference
    private Set<Estudiante> estudiantes;

    public Asignatura() {
    }

    public Asignatura(int idAsignatura, String nombre, String codigo, int creditos, String nivel, String descripcion, String modalidad, String prerequisitos, Set<Docente> docentes, Set<Estudiante> estudiantes) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.modalidad = modalidad;
        this.prerequisitos = prerequisitos;
        this.docentes = docentes;
        this.estudiantes = estudiantes;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getPrerequisitos() {
        return prerequisitos;
    }

    public void setPrerequisitos(String prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    public Set<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

}
