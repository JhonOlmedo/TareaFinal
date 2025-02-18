package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Grupo;
import com.olme_gue.gestion.educativa.repositorios.academico.GrupoR;
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
@RequestMapping("/api/grupos")
@Tag(name = "Grupos", description = "API para la gestión de grupos en el sistema educativo")
public class GrupoC {

    @Autowired
    private GrupoR grupoRepository;

    @Operation(summary = "Obtener todos los grupos",
            description = "Retorna una lista de todos los grupos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de grupos recuperada exitosamente")
    @GetMapping
    public List<Grupo> getAllGrupos() {
        return grupoRepository.findAll();
    }

    @Operation(summary = "Obtener grupo por ID",
            description = "Retorna un grupo específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo encontrado"),
            @ApiResponse(responseCode = "404", description = "Grupo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Grupo> getGrupoById(@PathVariable Integer id) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        return grupo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo grupo",
            description = "Registra un nuevo grupo en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del grupo inválidos")
    })
    @PostMapping
    public Grupo createGrupo(@RequestBody Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Operation(summary = "Actualizar grupo",
            description = "Actualiza la información de un grupo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grupo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Grupo no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del grupo inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Grupo> updateGrupo(@PathVariable Integer id, @RequestBody Grupo grupoDetails) {
        Optional<Grupo> existingGrupo = grupoRepository.findById(id);
        return existingGrupo.map(grupo -> {
            grupo.setCodigo(grupoDetails.getCodigo());
            grupo.setCapacidad(grupoDetails.getCapacidad());
            grupo.setEstado(grupoDetails.getEstado());
            grupo.setModalidad(grupoDetails.getModalidad());
            grupo.setAsignatura(grupoDetails.getAsignatura());
            grupo.setDocente(grupoDetails.getDocente());
            return ResponseEntity.ok(grupoRepository.save(grupo));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar grupo",
            description = "Elimina un grupo del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Grupo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Grupo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGrupo(@PathVariable Integer id) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        return grupo.map(g -> {
            grupoRepository.delete(g);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
