package com.olme_gue.gestion.educativa.models.entity.administracion;

import jakarta.persistence.*;

@Entity
@Table(name = "PARAMETRO_GENERAL")
public class ParametroGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parametro")
    private int idParametro;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "valor", nullable = false, length = 100)
    private String valor;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    public ParametroGeneral() {
    }

    public ParametroGeneral(int idParametro, String codigo, String nombre, String valor, String descripcion) {
        this.idParametro = idParametro;
        this.codigo = codigo;
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
