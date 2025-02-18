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

import com.olme_gue.gestion.educativa.models.entity.contabilidad.CuentaContable;
import com.olme_gue.gestion.educativa.repositorios.contabilidad.CuentaContableR;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cuentas-contables")
@Tag(name = "Cuentas Contables", description = "API para la gestión de cuentas contables")
public class CuentaContableC {

    @Autowired
    private CuentaContableR cuentaContableRepository;

    @Operation(summary = "Obtener todas las cuentas contables",
            description = "Retorna una lista de todas las cuentas contables registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de cuentas contables recuperada exitosamente")
    @GetMapping
    public List<CuentaContable> getAllCuentasContables() {
        return cuentaContableRepository.findAll();
    }

    @Operation(summary = "Obtener cuenta contable por ID",
            description = "Retorna una cuenta contable específica basada en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta contable encontrada"),
            @ApiResponse(responseCode = "404", description = "Cuenta contable no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuentaContable> getCuentaContableById(@PathVariable Integer id) {
        Optional<CuentaContable> cuentaContable = cuentaContableRepository.findById(id);
        return cuentaContable.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva cuenta contable",
            description = "Registra una nueva cuenta contable en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta contable creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la cuenta contable inválidos")
    })
    @PostMapping
    public CuentaContable createCuentaContable(@Valid @RequestBody CuentaContable cuentaContable) {
        return cuentaContableRepository.save(cuentaContable);
    }

    @Operation(summary = "Actualizar cuenta contable",
            description = "Actualiza la información de una cuenta contable existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta contable actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta contable no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de la cuenta contable inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuentaContable> updateCuentaContable(@PathVariable Integer id, @Valid @RequestBody CuentaContable cuentaContableDetails) {
        Optional<CuentaContable> existingCuentaContable = cuentaContableRepository.findById(id);
        return existingCuentaContable.map(cuenta -> {
            cuenta.setCodigo(cuentaContableDetails.getCodigo());
            cuenta.setNombre(cuentaContableDetails.getNombre());
            cuenta.setTipo(cuentaContableDetails.getTipo());
            cuenta.setNaturaleza(cuentaContableDetails.getNaturaleza());
            return ResponseEntity.ok(cuentaContableRepository.save(cuenta));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar cuenta contable",
            description = "Elimina una cuenta contable del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta contable eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cuenta contable no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCuentaContable(@PathVariable Integer id) {
        Optional<CuentaContable> cuentaContable = cuentaContableRepository.findById(id);
        return cuentaContable.map(cuenta -> {
            cuentaContableRepository.delete(cuenta);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
