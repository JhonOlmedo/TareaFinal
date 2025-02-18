package com.olme_gue.gestion.educativa.models.entity.academico;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.olme_gue.gestion.educativa.models.entity.administracion.Usuario;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;
//comentario
@Entity
@Table(name = "ESTUDIANTE")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private int idEstudiante;

    @Column(name = "cedula", nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "ESTUDIANTE_ASIGNATURA", 
        joinColumns = @JoinColumn(name = "id_estudiante"), 
        inverseJoinColumns = @JoinColumn(name = "id_asignatura")
    )
    private Set<Asignatura> asignaturas;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String cedula, String nombres, String apellidos, Date fechaNacimiento, String direccion, String telefono, String email, String estado, Set<Asignatura> asignaturas, Usuario usuario) {
        this.idEstudiante = idEstudiante;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.asignaturas = asignaturas;
        this.usuario = usuario;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
