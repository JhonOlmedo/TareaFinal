package com.olme_gue.gestion.educativa.models.entity.administracion;

import jakarta.persistence.*;

@Entity
@Table(name = "ESPACIO_FISICO")
public class EspacioFisico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_espacio")
    private int idEspacio;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "ubicacion", length = 255)
    private String ubicacion;

    public EspacioFisico() {
    }

    public EspacioFisico(int idEspacio, String codigo, String nombre, int capacidad, String tipo, String ubicacion) {
        this.idEspacio = idEspacio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
}