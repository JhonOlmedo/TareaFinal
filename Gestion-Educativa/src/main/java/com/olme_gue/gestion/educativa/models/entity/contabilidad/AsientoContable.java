package com.olme_gue.gestion.educativa.models.entity.contabilidad;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ASIENTO_CONTABLE")
public class AsientoContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento")
    private int idAsiento;

    @Column(name = "fecha", nullable = false)
    private java.util.Date fecha;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "total_debe", nullable = false)
    private float totalDebe;

    @Column(name = "total_haber", nullable = false)
    private float totalHaber;

    @OneToMany(mappedBy = "asientoContable", cascade = CascadeType.ALL)
    private List<MovimientoContable> movimientosContables;

    public AsientoContable() {
    }

    public AsientoContable(int idAsiento, Date fecha, String descripcion, String tipo, float totalDebe, float totalHaber, List<MovimientoContable> movimientosContables) {
        this.idAsiento = idAsiento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.totalDebe = totalDebe;
        this.totalHaber = totalHaber;
        this.movimientosContables = movimientosContables;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTotalDebe() {
        return totalDebe;
    }

    public void setTotalDebe(float totalDebe) {
        this.totalDebe = totalDebe;
    }

    public float getTotalHaber() {
        return totalHaber;
    }

    public void setTotalHaber(float totalHaber) {
        this.totalHaber = totalHaber;
    }

    public List<MovimientoContable> getMovimientosContables() {
        return movimientosContables;
    }

    public void setMovimientosContables(List<MovimientoContable> movimientosContables) {
        this.movimientosContables = movimientosContables;
    }
    
    

}
