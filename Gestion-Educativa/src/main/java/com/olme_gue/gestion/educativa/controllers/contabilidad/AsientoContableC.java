package com.olme_gue.gestion.educativa.controllers.contabilidad;

import com.olme_gue.gestion.educativa.models.entity.contabilidad.AsientoContable;
import com.olme_gue.gestion.educativa.repositorios.contabilidad.AsientoContableR;
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
@RequestMapping("/api/asientos-contables")
@Tag(name = "Asientos Contables", description = "API para la gestión de asientos contables")
public class AsientoContableC {

    @Autowired
    private AsientoContableR asientoContableRepository;

    @Operation(summary = "Obtener todos los asientos contables",
            description = "Retorna una lista de todos los asientos contables registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de asientos contables recuperada exitosamente")
    @GetMapping
    public List<AsientoContable> getAllAsientosContables() {
        return asientoContableRepository.findAll();
    }

    @Operation(summary = "Obtener asiento contable por ID",
            description = "Retorna un asiento contable específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asiento contable encontrado"),
            @ApiResponse(responseCode = "404", description = "Asiento contable no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AsientoContable> getAsientoContableById(@PathVariable Integer id) {
        Optional<AsientoContable> asientoContable = asientoContableRepository.findById(id);
        return asientoContable.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo asiento contable",
            description = "Registra un nuevo asiento contable en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asiento contable creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del asiento contable inválidos")
    })
    @PostMapping
    public AsientoContable createAsientoContable(@Valid @RequestBody AsientoContable asientoContable) {
        return asientoContableRepository.save(asientoContable);
    }

    @Operation(summary = "Actualizar asiento contable",
            description = "Actualiza la información de un asiento contable existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asiento contable actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Asiento contable no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del asiento contable inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AsientoContable> updateAsientoContable(@PathVariable Integer id, @Valid @RequestBody AsientoContable asientoContableDetails) {
        Optional<AsientoContable> existingAsientoContable = asientoContableRepository.findById(id);
        return existingAsientoContable.map(asiento -> {
            asiento.setFecha(asientoContableDetails.getFecha());
            asiento.setDescripcion(asientoContableDetails.getDescripcion());
            asiento.setTipo(asientoContableDetails.getTipo());
            asiento.setTotalDebe(asientoContableDetails.getTotalDebe());
            asiento.setTotalHaber(asientoContableDetails.getTotalHaber());
            asiento.setMovimientosContables(asientoContableDetails.getMovimientosContables());
            return ResponseEntity.ok(asientoContableRepository.save(asiento));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar asiento contable",
            description = "Elimina un asiento contable del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Asiento contable eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Asiento contable no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAsientoContable(@PathVariable Integer id) {
        Optional<AsientoContable> asientoContable = asientoContableRepository.findById(id);
        return asientoContable.map(asiento -> {
            asientoContableRepository.delete(asiento);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
