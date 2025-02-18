package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Calificacion;
import com.olme_gue.gestion.educativa.repositorios.academico.CalificacionR;
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
@RequestMapping("/api/calificaciones")
@Tag(name = "Calificaciones", description = "API para la gestión de calificaciones en el sistema educativo")
public class CalificacionC {

    @Autowired
    private CalificacionR calificacionRepository;

    @Operation(summary = "Obtener todas las calificaciones",
            description = "Retorna una lista de todas las calificaciones registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de calificaciones recuperada exitosamente")
    @GetMapping
    public List<Calificacion> getAllCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Operation(summary = "Obtener calificación por ID",
            description = "Retorna una calificación específica basada en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calificación encontrada"),
            @ApiResponse(responseCode = "404", description = "Calificación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getCalificacionById(@PathVariable Integer id) {
        Optional<Calificacion> calificacion = calificacionRepository.findById(id);
        return calificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva calificación",
            description = "Registra una nueva calificación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calificación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la calificación inválidos")
    })
    @PostMapping
    public Calificacion createCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Operation(summary = "Actualizar calificación",
            description = "Actualiza la información de una calificación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calificación actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Calificación no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de la calificación inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> updateCalificacion(@PathVariable Integer id, @RequestBody Calificacion calificacionDetails) {
        Optional<Calificacion> existingCalificacion = calificacionRepository.findById(id);
        return existingCalificacion.map(calificacion -> {
            calificacion.setNota(calificacionDetails.getNota());
            calificacion.setTipoEvaluacion(calificacionDetails.getTipoEvaluacion());
            calificacion.setFechaRegistro(calificacionDetails.getFechaRegistro());
            calificacion.setObservaciones(calificacionDetails.getObservaciones());
            calificacion.setEstudiante(calificacionDetails.getEstudiante());
            calificacion.setGrupo(calificacionDetails.getGrupo());
            return ResponseEntity.ok(calificacionRepository.save(calificacion));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar calificación",
            description = "Elimina una calificación del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Calificación eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Calificación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCalificacion(@PathVariable Integer id) {
        Optional<Calificacion> calificacion = calificacionRepository.findById(id);
        return calificacion.map(c -> {
            calificacionRepository.delete(c);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
