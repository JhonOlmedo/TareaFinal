package com.olme_gue.gestion.educativa.controllers.contabilidad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olme_gue.gestion.educativa.models.entity.contabilidad.MovimientoContable;
import com.olme_gue.gestion.educativa.repositorios.contabilidad.MovimientoContableR;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movimientos-contables")
@Tag(name = "Movimientos Contables", description = "API para la gestión de movimientos contables")
public class MovimientoContableC {

    @Autowired
    private MovimientoContableR movimientoContableRepository;

    @Operation(summary = "Obtener todos los movimientos contables",
            description = "Retorna una lista de todos los movimientos contables registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de movimientos contables recuperada exitosamente")
    @GetMapping
    public List<MovimientoContable> getAllMovimientosContables() {
        return movimientoContableRepository.findAll();
    }

    @Operation(summary = "Obtener movimiento contable por ID",
            description = "Retorna un movimiento contable específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento contable encontrado"),
            @ApiResponse(responseCode = "404", description = "Movimiento contable no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoContable> getMovimientoContableById(@PathVariable Integer id) {
        Optional<MovimientoContable> movimientoContable = movimientoContableRepository.findById(id);
        return movimientoContable.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo movimiento contable",
            description = "Registra un nuevo movimiento contable en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento contable creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del movimiento contable inválidos")
    })
    @PostMapping
    public MovimientoContable createMovimientoContable(@Valid @RequestBody MovimientoContable movimientoContable) {
        return movimientoContableRepository.save(movimientoContable);
    }

    @Operation(summary = "Actualizar movimiento contable",
            description = "Actualiza la información de un movimiento contable existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento contable actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento contable no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del movimiento contable inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoContable> updateMovimientoContable(@PathVariable Integer id, @Valid @RequestBody MovimientoContable movimientoContableDetails) {
        Optional<MovimientoContable> existingMovimientoContable = movimientoContableRepository.findById(id);
        return existingMovimientoContable.map(movimiento -> {
            movimiento.setValor(movimientoContableDetails.getValor());
            movimiento.setTipo(movimientoContableDetails.getTipo());
            movimiento.setReferencia(movimientoContableDetails.getReferencia());
            movimiento.setAsientoContable(movimientoContableDetails.getAsientoContable());
            return ResponseEntity.ok(movimientoContableRepository.save(movimiento));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar movimiento contable",
            description = "Elimina un movimiento contable del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimiento contable eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Movimiento contable no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovimientoContable(@PathVariable Integer id) {
        Optional<MovimientoContable> movimientoContable = movimientoContableRepository.findById(id);
        return movimientoContable.map(movimiento -> {
            movimientoContableRepository.delete(movimiento);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
