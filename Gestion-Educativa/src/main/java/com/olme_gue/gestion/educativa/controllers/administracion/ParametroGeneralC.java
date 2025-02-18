package com.olme_gue.gestion.educativa.controllers.administracion;

import com.olme_gue.gestion.educativa.models.entity.administracion.ParametroGeneral;
import com.olme_gue.gestion.educativa.repositorios.administracion.ParametroGeneralR;
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
@RequestMapping("/api/parametros-generales")
@Tag(name = "Parámetros Generales", description = "API para la gestión de parámetros generales")
public class ParametroGeneralC {

    @Autowired
    private ParametroGeneralR parametroGeneralRepository;

    @Operation(summary = "Obtener todos los parámetros generales",
            description = "Retorna una lista de todos los parámetros generales registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de parámetros generales recuperada exitosamente")
    @GetMapping
    public List<ParametroGeneral> getAllParametrosGenerales() {
        return parametroGeneralRepository.findAll();
    }

    @Operation(summary = "Obtener parámetro general por ID",
            description = "Retorna un parámetro general específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parámetro general encontrado"),
            @ApiResponse(responseCode = "404", description = "Parámetro general no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ParametroGeneral> getParametroGeneralById(@PathVariable Integer id) {
        Optional<ParametroGeneral> parametroGeneral = parametroGeneralRepository.findById(id);
        return parametroGeneral.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo parámetro general",
            description = "Registra un nuevo parámetro general en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parámetro general creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del parámetro general inválidos")
    })
    @PostMapping
    public ParametroGeneral createParametroGeneral(@RequestBody ParametroGeneral parametroGeneral) {
        return parametroGeneralRepository.save(parametroGeneral);
    }

    @Operation(summary = "Actualizar parámetro general",
            description = "Actualiza la información de un parámetro general existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parámetro general actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro general no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del parámetro general inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ParametroGeneral> updateParametroGeneral(@PathVariable Integer id, @RequestBody ParametroGeneral parametroGeneralDetails) {
        Optional<ParametroGeneral> existingParametroGeneral = parametroGeneralRepository.findById(id);
        return existingParametroGeneral.map(parametroGeneral -> {
            parametroGeneral.setCodigo(parametroGeneralDetails.getCodigo());
            parametroGeneral.setNombre(parametroGeneralDetails.getNombre());
            parametroGeneral.setValor(parametroGeneralDetails.getValor());
            parametroGeneral.setDescripcion(parametroGeneralDetails.getDescripcion());
            return ResponseEntity.ok(parametroGeneralRepository.save(parametroGeneral));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar parámetro general",
            description = "Elimina un parámetro general del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Parámetro general eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Parámetro general no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParametroGeneral(@PathVariable Integer id) {
        Optional<ParametroGeneral> parametroGeneral = parametroGeneralRepository.findById(id);
        return parametroGeneral.map(p -> {
            parametroGeneralRepository.delete(p);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
