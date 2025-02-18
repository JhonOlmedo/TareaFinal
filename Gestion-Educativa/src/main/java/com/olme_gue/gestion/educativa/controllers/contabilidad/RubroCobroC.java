package com.olme_gue.gestion.educativa.controllers.contabilidad;

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

import com.olme_gue.gestion.educativa.models.entity.contabilidad.RubroCobro;
import com.olme_gue.gestion.educativa.repositorios.contabilidad.RubroCobroR;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rubros-cobro")
@Tag(name = "Rubros de Cobro", description = "API para la gestión de rubros de cobro")
public class RubroCobroC {

    @Autowired
    private RubroCobroR rubroCobroRepository;

    @Operation(summary = "Obtener todos los rubros de cobro",
            description = "Retorna una lista de todos los rubros de cobro registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de rubros de cobro recuperada exitosamente")
    @GetMapping
    public List<RubroCobro> getAllRubrosCobro() {
        return rubroCobroRepository.findAll();
    }

    @Operation(summary = "Obtener rubro de cobro por ID",
            description = "Retorna un rubro de cobro específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rubro de cobro encontrado"),
            @ApiResponse(responseCode = "404", description = "Rubro de cobro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RubroCobro> getRubroCobroById(@PathVariable Integer id) {
        Optional<RubroCobro> rubroCobro = rubroCobroRepository.findById(id);
        return rubroCobro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo rubro de cobro",
            description = "Registra un nuevo rubro de cobro en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rubro de cobro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del rubro de cobro inválidos")
    })
    @PostMapping
    public RubroCobro createRubroCobro(@Valid @RequestBody RubroCobro rubroCobro) {
        return rubroCobroRepository.save(rubroCobro);
    }

    @Operation(summary = "Actualizar rubro de cobro",
            description = "Actualiza la información de un rubro de cobro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rubro de cobro actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Rubro de cobro no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del rubro de cobro inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RubroCobro> updateRubroCobro(@PathVariable Integer id, @Valid @RequestBody RubroCobro rubroCobroDetails) {
        Optional<RubroCobro> existingRubroCobro = rubroCobroRepository.findById(id);
        return existingRubroCobro.map(rubro -> {
            rubro.setNombre(rubroCobroDetails.getNombre());
            rubro.setValor(rubroCobroDetails.getValor());
            rubro.setTipo(rubroCobroDetails.getTipo());
            rubro.setPeriodo(rubroCobroDetails.getPeriodo());
            return ResponseEntity.ok(rubroCobroRepository.save(rubro));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar rubro de cobro",
            description = "Elimina un rubro de cobro del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rubro de cobro eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Rubro de cobro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRubroCobro(@PathVariable Integer id) {
        Optional<RubroCobro> rubroCobro = rubroCobroRepository.findById(id);
        return rubroCobro.map(rubro -> {
            rubroCobroRepository.delete(rubro);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
