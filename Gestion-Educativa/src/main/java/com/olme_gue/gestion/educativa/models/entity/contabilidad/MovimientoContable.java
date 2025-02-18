package com.olme_gue.gestion.educativa.models.entity.contabilidad;

import jakarta.persistence.*;

@Entity
@Table(name = "MOVIMIENTO_CONTABLE")
public class MovimientoContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private int idMovimiento;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "referencia", length = 100)
    private String referencia;

    // Relación con AsientoContable
    @ManyToOne
    @JoinColumn(name = "id_asiento_contable", referencedColumnName = "id_asiento")
    private AsientoContable asientoContable;

    public MovimientoContable() {
    }

    public MovimientoContable(int idMovimiento, float valor, String tipo, String referencia, AsientoContable asientoContable) {
        this.idMovimiento = idMovimiento;
        this.valor = valor;
        this.tipo = tipo;
        this.referencia = referencia;
        this.asientoContable = asientoContable;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public AsientoContable getAsientoContable() {
        return asientoContable;
    }

    public void setAsientoContable(AsientoContable asientoContable) {
        this.asientoContable = asientoContable;
    }
}
