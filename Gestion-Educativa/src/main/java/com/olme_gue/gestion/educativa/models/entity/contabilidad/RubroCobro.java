package com.olme_gue.gestion.educativa.models.entity.contabilidad;

import jakarta.persistence.*;

@Entity
@Table(name = "RUBRO_COBRO")
public class RubroCobro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubro")
    private int idRubro;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "periodo", length = 50)
    private String periodo;

    public RubroCobro() {
    }

    public RubroCobro(int idRubro, String nombre, float valor, String tipo, String periodo) {
        this.idRubro = idRubro;
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
        this.periodo = periodo;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    
}
