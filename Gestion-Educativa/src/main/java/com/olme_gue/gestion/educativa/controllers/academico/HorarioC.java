package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Horario;
import com.olme_gue.gestion.educativa.repositorios.academico.HorarioR;
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
@RequestMapping("/api/horarios")
@Tag(name = "Horarios", description = "API para la gestión de horarios en el sistema educativo")
public class HorarioC {

    @Autowired
    private HorarioR horarioRepository;

    @Operation(summary = "Obtener todos los horarios",
            description = "Retorna una lista de todos los horarios registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de horarios recuperada exitosamente")
    @GetMapping
    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    @Operation(summary = "Obtener horario por ID",
            description = "Retorna un horario específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario encontrado"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable Integer id) {
        Optional<Horario> horario = horarioRepository.findById(id);
        return horario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo horario",
            description = "Registra un nuevo horario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos del horario inválidos")
    })
    @PostMapping
    public Horario createHorario(@RequestBody Horario horario) {
        return horarioRepository.save(horario);
    }

    @Operation(summary = "Actualizar horario",
            description = "Actualiza la información de un horario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Horario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del horario inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Integer id, @RequestBody Horario horarioDetails) {
        Optional<Horario> existingHorario = horarioRepository.findById(id);
        return existingHorario.map(horario -> {
            horario.setDia(horarioDetails.getDia());
            horario.setHoraInicio(horarioDetails.getHoraInicio());
            horario.setHoraFin(horarioDetails.getHoraFin());
            return ResponseEntity.ok(horarioRepository.save(horario));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar horario",
            description = "Elimina un horario del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Horario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Horario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHorario(@PathVariable Integer id) {
        Optional<Horario> horario = horarioRepository.findById(id);
        return horario.map(h -> {
            horarioRepository.delete(h);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
