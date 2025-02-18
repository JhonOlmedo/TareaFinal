package com.olme_gue.gestion.educativa.models.entity.academico;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "DOCENTE")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private int idDocente;

    @Column(name = "cedula", nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_contratacion", nullable = false)
    private Date fechaContratacion;

    @ManyToMany
    @JoinTable(
            name = "asignatura_docente",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "docente_id")
    )
    private Set<Asignatura> asignaturas;

    public Docente() {
    }

    public Docente(int idDocente, String cedula, String nombres, String apellidos, String especialidad, String telefono, Date fechaContratacion, Set<Asignatura> asignaturas) {
        this.idDocente = idDocente;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.asignaturas = asignaturas;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

}
