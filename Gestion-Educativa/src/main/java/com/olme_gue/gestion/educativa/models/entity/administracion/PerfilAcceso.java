package com.olme_gue.gestion.educativa.models.entity.administracion;

import jakarta.persistence.*;

@Entity
@Table(name = "PERFIL_ACCESO")
public class PerfilAcceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private int idPerfil;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "permisos", length = 255)
    private String permisos;

    public PerfilAcceso() {
    }

    public PerfilAcceso(int idPerfil, String nombre, String descripcion, String permisos) {
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.permisos = permisos;
    }
    
    

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
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

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    
    
}
