package com.olme_gue.gestion.educativa.controllers.academico;

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

import com.olme_gue.gestion.educativa.models.entity.academico.Docente;
import com.olme_gue.gestion.educativa.repositorios.academico.DocenteR;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/docentes")
@Tag(name = "Docentes", description = "API para la gestión de docentes")
public class DocenteC {

    @Autowired
    private DocenteR docenteRepository;

    @Operation(summary = "Obtener todos los docentes",
            description = "Retorna una lista de todos los docentes registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de docentes recuperada exitosamente")
    @GetMapping
    public List<Docente> getAllDocentes() {
        return docenteRepository.findAll();
    }

    @Operation(summary = "Obtener docente por ID",
            description = "Retorna un docente específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docente encontrado"),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        return docente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo docente",
            description = "Registra un nuevo docente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del docente inválidos")
    })
    @PostMapping
    public Docente createDocente(@Valid @RequestBody Docente docente) {
        return docenteRepository.save(docente);
    }

    @Operation(summary = "Actualizar docente",
            description = "Actualiza la información de un docente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del docente inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Docente> updateDocente(@PathVariable Integer id, @Valid @RequestBody Docente docenteDetails) {
        Optional<Docente> existingDocente = docenteRepository.findById(id);
        return existingDocente.map(docente -> {
            docente.setCedula(docenteDetails.getCedula());
            docente.setNombres(docenteDetails.getNombres());
            docente.setApellidos(docenteDetails.getApellidos());
            docente.setEspecialidad(docenteDetails.getEspecialidad());
            docente.setTelefono(docenteDetails.getTelefono());
            docente.setFechaContratacion(docenteDetails.getFechaContratacion());
            docente.setAsignaturas(docenteDetails.getAsignaturas());
            return ResponseEntity.ok(docenteRepository.save(docente));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar docente",
            description = "Elimina un docente del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Docente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Docente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDocente(@PathVariable Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        return docente.map(d -> {
            docenteRepository.delete(d);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
