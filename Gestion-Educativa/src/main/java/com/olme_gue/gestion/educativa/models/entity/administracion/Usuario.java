package com.olme_gue.gestion.educativa.models.entity.administracion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.olme_gue.gestion.educativa.models.entity.academico.Docente;
import com.olme_gue.gestion.educativa.models.entity.academico.Estudiante;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe proporcionar un email válido")
    private String email;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "ultimo_acceso")
    private java.time.LocalDateTime ultimoAcceso;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Docente docente;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "id_usuario")
    private Estudiante estudiante;

    @ElementCollection(fetch = FetchType.EAGER)  // Esto mapea una lista de roles como una colección
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "id_usuario"))
    @Column(name = "role")
    private Set<String> roles;  // Establecer roles del usuario

    public Usuario() {
    }

    public Usuario(int idUsuario, String email, String username, String password, String estado, LocalDateTime ultimoAcceso, Docente docente, Estudiante estudiante, Set<String> roles) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.ultimoAcceso = ultimoAcceso;
        this.docente = docente;
        this.estudiante = estudiante;
        this.roles = roles;
    }

    // Getters y setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Set<String> getRoles() {
        return roles; 
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
