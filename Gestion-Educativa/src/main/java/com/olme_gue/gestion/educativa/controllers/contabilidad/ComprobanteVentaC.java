package com.olme_gue.gestion.educativa.controllers.contabilidad;

import com.olme_gue.gestion.educativa.models.entity.contabilidad.ComprobanteVenta;
import com.olme_gue.gestion.educativa.repositorios.contabilidad.ComprobanteVentaR;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comprobantes-venta")
@Tag(name = "Comprobantes de Venta", description = "API para la gestión de comprobantes de venta")
public class ComprobanteVentaC {

    @Autowired
    private ComprobanteVentaR comprobanteVentaRepository;

    @Operation(summary = "Obtener todos los comprobantes de venta",
            description = "Retorna una lista de todos los comprobantes de venta registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de comprobantes de venta recuperada exitosamente")
    @GetMapping
    public List<ComprobanteVenta> getAllComprobantesVenta() {
        return comprobanteVentaRepository.findAll();
    }

    @Operation(summary = "Obtener comprobante de venta por ID",
            description = "Retorna un comprobante de venta específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comprobante de venta encontrado"),
            @ApiResponse(responseCode = "404", description = "Comprobante de venta no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteVenta> getComprobanteVentaById(@PathVariable Integer id) {
        Optional<ComprobanteVenta> comprobanteVenta = comprobanteVentaRepository.findById(id);
        return comprobanteVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo comprobante de venta",
            description = "Registra un nuevo comprobante de venta en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comprobante de venta creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del comprobante de venta inválidos")
    })
    @PostMapping
    public ComprobanteVenta createComprobanteVenta(@Valid @RequestBody ComprobanteVenta comprobanteVenta) {
        return comprobanteVentaRepository.save(comprobanteVenta);
    }

    @Operation(summary = "Actualizar comprobante de venta",
            description = "Actualiza la información de un comprobante de venta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comprobante de venta actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comprobante de venta no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del comprobante de venta inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ComprobanteVenta> updateComprobanteVenta(@PathVariable Integer id, @Valid @RequestBody ComprobanteVenta comprobanteVentaDetails) {
        Optional<ComprobanteVenta> existingComprobanteVenta = comprobanteVentaRepository.findById(id);
        return existingComprobanteVenta.map(comprobante -> {
            comprobante.setNumero(comprobanteVentaDetails.getNumero());
            comprobante.setFechaEmision(comprobanteVentaDetails.getFechaEmision());
            comprobante.setValorTotal(comprobanteVentaDetails.getValorTotal());
            comprobante.setEstado(comprobanteVentaDetails.getEstado());
            comprobante.setTipo(comprobanteVentaDetails.getTipo());
            comprobante.setMatricula(comprobanteVentaDetails.getMatricula());
            comprobante.setAsientoContable(comprobanteVentaDetails.getAsientoContable());
            return ResponseEntity.ok(comprobanteVentaRepository.save(comprobante));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar comprobante de venta",
            description = "Elimina un comprobante de venta del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comprobante de venta eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Comprobante de venta no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComprobanteVenta(@PathVariable Integer id) {
        Optional<ComprobanteVenta> comprobanteVenta = comprobanteVentaRepository.findById(id);
        return comprobanteVenta.map(comprobante -> {
            comprobanteVentaRepository.delete(comprobante);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
