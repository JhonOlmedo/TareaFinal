package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.PlanEstudio;
import com.olme_gue.gestion.educativa.repositorios.academico.PlanEstudioR;
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
@RequestMapping("/api/planes-estudio")
@Tag(name = "Planes de Estudio", description = "API para la gestión de planes de estudio en el sistema educativo")
public class PlanEstudioC {

    @Autowired
    private PlanEstudioR planEstudioRepository;

    @Operation(summary = "Obtener todos los planes de estudio",
            description = "Retorna una lista de todos los planes de estudio registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de planes de estudio recuperada exitosamente")
    @GetMapping
    public List<PlanEstudio> getAllPlanesEstudio() {
        return planEstudioRepository.findAll();
    }

    @Operation(summary = "Obtener plan de estudio por ID",
            description = "Retorna un plan de estudio específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan de estudio encontrado"),
            @ApiResponse(responseCode = "404", description = "Plan de estudio no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlanEstudio> getPlanEstudioById(@PathVariable Integer id) {
        Optional<PlanEstudio> planEstudio = planEstudioRepository.findById(id);
        return planEstudio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo plan de estudio",
            description = "Registra un nuevo plan de estudio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan de estudio creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del plan de estudio inválidos")
    })
    @PostMapping
    public PlanEstudio createPlanEstudio(@RequestBody PlanEstudio planEstudio) {
        return planEstudioRepository.save(planEstudio);
    }

    @Operation(summary = "Actualizar plan de estudio",
            description = "Actualiza la información de un plan de estudio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan de estudio actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Plan de estudio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del plan de estudio inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlanEstudio> updatePlanEstudio(@PathVariable Integer id, @RequestBody PlanEstudio planEstudioDetails) {
        Optional<PlanEstudio> existingPlanEstudio = planEstudioRepository.findById(id);
        return existingPlanEstudio.map(planEstudio -> {
            planEstudio.setNombre(planEstudioDetails.getNombre());
            planEstudio.setDescripcion(planEstudioDetails.getDescripcion());
            planEstudio.setFechaInicio(planEstudioDetails.getFechaInicio());
            planEstudio.setFechaFin(planEstudioDetails.getFechaFin());
            planEstudio.setEstado(planEstudioDetails.getEstado());
            return ResponseEntity.ok(planEstudioRepository.save(planEstudio));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar plan de estudio",
            description = "Elimina un plan de estudio del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Plan de estudio eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Plan de estudio no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlanEstudio(@PathVariable Integer id) {
        Optional<PlanEstudio> planEstudio = planEstudioRepository.findById(id);
        return planEstudio.map(p -> {
            planEstudioRepository.delete(p);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
