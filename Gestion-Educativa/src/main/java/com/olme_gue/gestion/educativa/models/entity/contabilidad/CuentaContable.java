package com.olme_gue.gestion.educativa.models.entity.contabilidad;

import jakarta.persistence.*;

@Entity
@Table(name = "CUENTA_CONTABLE")
public class CuentaContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private int idCuenta;

    @Column(name = "codigo", nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "naturaleza", nullable = false, length = 50)
    private String naturaleza;

    public CuentaContable() {
    }

    public CuentaContable(int idCuenta, String codigo, String nombre, String tipo, String naturaleza) {
        this.idCuenta = idCuenta;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.naturaleza = naturaleza;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }
    
    
}
