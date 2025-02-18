package com.olme_gue.gestion.educativa.models.entity.contabilidad;

import com.olme_gue.gestion.educativa.models.entity.academico.Matricula;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMPROBANTE_VENTA")
public class ComprobanteVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private int idComprobante;

    @Column(name = "numero", nullable = false, unique = true, length = 50)
    private String numero;

    @Column(name = "fecha_emision", nullable = false)
    private Date fechaEmision;

    @Column(name = "valor_total", nullable = false)
    private float valorTotal;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "tipo", length = 20)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    private Matricula matricula;

    @OneToOne
    @JoinColumn(name = "id_asiento_contable", referencedColumnName = "id_asiento")
    private AsientoContable asientoContable;

    public ComprobanteVenta() {
    }

    public ComprobanteVenta(int idComprobante, String numero, Date fechaEmision, float valorTotal, String estado, String tipo, Matricula matricula, AsientoContable asientoContable) {
        this.idComprobante = idComprobante;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.valorTotal = valorTotal;
        this.estado = estado;
        this.tipo = tipo;
        this.matricula = matricula;
        this.asientoContable = asientoContable;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public AsientoContable getAsientoContable() {
        return asientoContable;
    }

    public void setAsientoContable(AsientoContable asientoContable) {
        this.asientoContable = asientoContable;
    }
    
    
}
