package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.PeriodoLectivo;
import com.olme_gue.gestion.educativa.repositorios.academico.PeriodoLectivoR;
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
@RequestMapping("/api/periodos-lectivos")
@Tag(name = "Períodos Lectivos", description = "API para la gestión de períodos lectivos en el sistema educativo")
public class PeriodoLectivoC {

    @Autowired
    private PeriodoLectivoR periodoLectivoRepository;

    @Operation(summary = "Obtener todos los períodos lectivos",
            description = "Retorna una lista de todos los períodos lectivos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de períodos lectivos recuperada exitosamente")
    @GetMapping
    public List<PeriodoLectivo> getAllPeriodosLectivos() {
        return periodoLectivoRepository.findAll();
    }

    @Operation(summary = "Obtener período lectivo por ID",
            description = "Retorna un período lectivo específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período lectivo encontrado"),
            @ApiResponse(responseCode = "404", description = "Período lectivo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PeriodoLectivo> getPeriodoLectivoById(@PathVariable Integer id) {
        Optional<PeriodoLectivo> periodoLectivo = periodoLectivoRepository.findById(id);
        return periodoLectivo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo período lectivo",
            description = "Registra un nuevo período lectivo en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período lectivo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del período lectivo inválidos")
    })
    @PostMapping
    public PeriodoLectivo createPeriodoLectivo(@RequestBody PeriodoLectivo periodoLectivo) {
        return periodoLectivoRepository.save(periodoLectivo);
    }

    @Operation(summary = "Actualizar período lectivo",
            description = "Actualiza la información de un período lectivo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Período lectivo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Período lectivo no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del período lectivo inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PeriodoLectivo> updatePeriodoLectivo(@PathVariable Integer id, @RequestBody PeriodoLectivo periodoLectivoDetails) {
        Optional<PeriodoLectivo> existingPeriodoLectivo = periodoLectivoRepository.findById(id);
        return existingPeriodoLectivo.map(periodoLectivo -> {
            periodoLectivo.setNombre(periodoLectivoDetails.getNombre());
            periodoLectivo.setFechaInicio(periodoLectivoDetails.getFechaInicio());
            periodoLectivo.setFechaFin(periodoLectivoDetails.getFechaFin());
            periodoLectivo.setEstado(periodoLectivoDetails.getEstado());
            return ResponseEntity.ok(periodoLectivoRepository.save(periodoLectivo));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar período lectivo",
            description = "Elimina un período lectivo del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Período lectivo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Período lectivo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePeriodoLectivo(@PathVariable Integer id) {
        Optional<PeriodoLectivo> periodoLectivo = periodoLectivoRepository.findById(id);
        return periodoLectivo.map(p -> {
            periodoLectivoRepository.delete(p);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
