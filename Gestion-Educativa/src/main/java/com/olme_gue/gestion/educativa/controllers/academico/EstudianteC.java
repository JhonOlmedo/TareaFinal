package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Estudiante;
import com.olme_gue.gestion.educativa.repositorios.academico.EstudianteR;
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
@RequestMapping("/api/estudiantes")
@Tag(name = "Estudiantes", description = "API para la gestión de estudiantes en el sistema educativo")
public class EstudianteC {
    @Autowired
    private EstudianteR estudianteRepository;

    @Operation(summary = "Obtener todos los estudiantes",
            description = "Retorna una lista de todos los estudiantes registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de estudiantes recuperada exitosamente")
    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Operation(summary = "Obtener estudiante por ID",
            description = "Retorna un estudiante específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Integer id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        return estudiante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo estudiante",
            description = "Registra un nuevo estudiante en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos")
    })
    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Operation(summary = "Actualizar estudiante",
            description = "Actualiza la información de un estudiante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del estudiante inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Integer id, @RequestBody Estudiante estudianteDetails) {
        Optional<Estudiante> existingEstudiante = estudianteRepository.findById(id);
        return existingEstudiante.map(estudiante -> {
            estudiante.setCedula(estudianteDetails.getCedula());
            estudiante.setNombres(estudianteDetails.getNombres());
            estudiante.setApellidos(estudianteDetails.getApellidos());
            estudiante.setFechaNacimiento(estudianteDetails.getFechaNacimiento());
            estudiante.setDireccion(estudianteDetails.getDireccion());
            estudiante.setTelefono(estudianteDetails.getTelefono());
            estudiante.setEmail(estudianteDetails.getEmail());
            estudiante.setEstado(estudianteDetails.getEstado());
            return ResponseEntity.ok(estudianteRepository.save(estudiante));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar estudiante",
            description = "Elimina un estudiante del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstudiante(@PathVariable Integer id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        return estudiante.map(e -> {
            estudianteRepository.delete(e);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
