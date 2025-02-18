package com.olme_gue.gestion.educativa.controllers.administracion;

import com.olme_gue.gestion.educativa.models.entity.administracion.EspacioFisico;
import com.olme_gue.gestion.educativa.repositorios.administracion.EspacioFisicoR;
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
@RequestMapping("/api/espacios-fisicos")
@Tag(name = "Espacios Físicos", description = "API para la gestión de espacios físicos")
public class EspacioFisicoC {

    @Autowired
    private EspacioFisicoR espacioFisicoRepository;

    @Operation(summary = "Obtener todos los espacios físicos",
            description = "Retorna una lista de todos los espacios físicos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de espacios físicos recuperada exitosamente")
    @GetMapping
    public List<EspacioFisico> getAllEspaciosFisicos() {
        return espacioFisicoRepository.findAll();
    }

    @Operation(summary = "Obtener espacio físico por ID",
            description = "Retorna un espacio físico específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espacio físico encontrado"),
            @ApiResponse(responseCode = "404", description = "Espacio físico no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EspacioFisico> getEspacioFisicoById(@PathVariable Integer id) {
        Optional<EspacioFisico> espacioFisico = espacioFisicoRepository.findById(id);
        return espacioFisico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo espacio físico",
            description = "Registra un nuevo espacio físico en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espacio físico creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del espacio físico inválidos")
    })
    @PostMapping
    public EspacioFisico createEspacioFisico(@RequestBody EspacioFisico espacioFisico) {
        return espacioFisicoRepository.save(espacioFisico);
    }

    @Operation(summary = "Actualizar espacio físico",
            description = "Actualiza la información de un espacio físico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espacio físico actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Espacio físico no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del espacio físico inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EspacioFisico> updateEspacioFisico(@PathVariable Integer id, @RequestBody EspacioFisico espacioFisicoDetails) {
        Optional<EspacioFisico> existingEspacioFisico = espacioFisicoRepository.findById(id);
        return existingEspacioFisico.map(espacioFisico -> {
            espacioFisico.setCodigo(espacioFisicoDetails.getCodigo());
            espacioFisico.setNombre(espacioFisicoDetails.getNombre());
            espacioFisico.setCapacidad(espacioFisicoDetails.getCapacidad());
            espacioFisico.setTipo(espacioFisicoDetails.getTipo());
            espacioFisico.setUbicacion(espacioFisicoDetails.getUbicacion());
            return ResponseEntity.ok(espacioFisicoRepository.save(espacioFisico));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar espacio físico",
            description = "Elimina un espacio físico del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Espacio físico eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Espacio físico no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEspacioFisico(@PathVariable Integer id) {
        Optional<EspacioFisico> espacioFisico = espacioFisicoRepository.findById(id);
        return espacioFisico.map(e -> {
            espacioFisicoRepository.delete(e);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
