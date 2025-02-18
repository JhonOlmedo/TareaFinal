package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Asignatura;
import com.olme_gue.gestion.educativa.repositorios.academico.AsignaturaR;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturas")
@Tag(name = "Asignaturas", description = "API para la gestión de asignaturas en el sistema educativo")
public class AsignaturaC {
    @Autowired
    private AsignaturaR  asignaturaRepository;

    @Operation(summary = "Obtener todas las asignaturas",
            description = "Retorna una lista de todas las asignaturas registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de asignaturas recuperada exitosamente")
    @GetMapping
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @Operation(summary = "Obtener asignatura por ID",
            description = "Retorna una asignatura específica basada en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignatura encontrada"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> getAsignaturaById(@PathVariable Integer id) {
        Optional<Asignatura> asignatura = asignaturaRepository.findById(id);
        return asignatura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva asignatura",
            description = "Registra una nueva asignatura en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignatura creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la asignatura inválidos")
    })
    @PostMapping
    public Asignatura createAsignatura(@RequestBody Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    @Operation(summary = "Actualizar asignatura",
            description = "Actualiza la información de una asignatura existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asignatura actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de la asignatura inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable Integer id, @RequestBody Asignatura asignaturaDetails) {
        Optional<Asignatura> existingAsignatura = asignaturaRepository.findById(id);
        return existingAsignatura.map(asignatura -> {
            asignatura.setNombre(asignaturaDetails.getNombre());
            asignatura.setCodigo(asignaturaDetails.getCodigo());
            asignatura.setCreditos(asignaturaDetails.getCreditos());
            asignatura.setNivel(asignaturaDetails.getNivel());
            asignatura.setDescripcion(asignaturaDetails.getDescripcion());
            asignatura.setModalidad(asignaturaDetails.getModalidad());
            asignatura.setPrerequisitos(asignaturaDetails.getPrerequisitos());
            return ResponseEntity.ok(asignaturaRepository.save(asignatura));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar asignatura",
            description = "Elimina una asignatura del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Asignatura eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAsignatura(@PathVariable Integer id) {
        Optional<Asignatura> asignatura = asignaturaRepository.findById(id);
        return asignatura.map(a -> {
            asignaturaRepository.delete(a);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}