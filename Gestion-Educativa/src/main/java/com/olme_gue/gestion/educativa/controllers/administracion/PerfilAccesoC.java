package com.olme_gue.gestion.educativa.controllers.administracion;

import com.olme_gue.gestion.educativa.models.entity.administracion.PerfilAcceso;
import com.olme_gue.gestion.educativa.repositorios.administracion.PerfilAccesoR;
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
@RequestMapping("/api/perfiles-acceso")
@Tag(name = "Perfiles de Acceso", description = "API para la gestión de perfiles de acceso")
public class PerfilAccesoC {

    @Autowired
    private PerfilAccesoR perfilAccesoRepository;

    @Operation(summary = "Obtener todos los perfiles de acceso",
            description = "Retorna una lista de todos los perfiles de acceso registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de perfiles de acceso recuperada exitosamente")
    @GetMapping
    public List<PerfilAcceso> getAllPerfilAccesos() {
        return perfilAccesoRepository.findAll();
    }

    @Operation(summary = "Obtener perfil de acceso por ID",
            description = "Retorna un perfil de acceso específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de acceso encontrado"),
            @ApiResponse(responseCode = "404", description = "Perfil de acceso no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PerfilAcceso> getPerfilAccesoById(@PathVariable Integer id) {
        Optional<PerfilAcceso> perfilAcceso = perfilAccesoRepository.findById(id);
        return perfilAcceso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo perfil de acceso",
            description = "Registra un nuevo perfil de acceso en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de acceso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del perfil de acceso inválidos")
    })
    @PostMapping
    public PerfilAcceso createPerfilAcceso(@RequestBody PerfilAcceso perfilAcceso) {
        return perfilAccesoRepository.save(perfilAcceso);
    }

    @Operation(summary = "Actualizar perfil de acceso",
            description = "Actualiza la información de un perfil de acceso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de acceso actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Perfil de acceso no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del perfil de acceso inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PerfilAcceso> updatePerfilAcceso(@PathVariable Integer id, @RequestBody PerfilAcceso perfilAccesoDetails) {
        Optional<PerfilAcceso> existingPerfilAcceso = perfilAccesoRepository.findById(id);
        return existingPerfilAcceso.map(perfilAcceso -> {
            perfilAcceso.setNombre(perfilAccesoDetails.getNombre());
            perfilAcceso.setDescripcion(perfilAccesoDetails.getDescripcion());
            perfilAcceso.setPermisos(perfilAccesoDetails.getPermisos());
            return ResponseEntity.ok(perfilAccesoRepository.save(perfilAcceso));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar perfil de acceso",
            description = "Elimina un perfil de acceso del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Perfil de acceso eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Perfil de acceso no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerfilAcceso(@PathVariable Integer id) {
        Optional<PerfilAcceso> perfilAcceso = perfilAccesoRepository.findById(id);
        return perfilAcceso.map(p -> {
            perfilAccesoRepository.delete(p);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
