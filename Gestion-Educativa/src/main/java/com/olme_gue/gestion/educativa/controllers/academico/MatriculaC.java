package com.olme_gue.gestion.educativa.controllers.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Matricula;
import com.olme_gue.gestion.educativa.repositorios.academico.MatriculaR;
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
@RequestMapping("/api/matriculas")
@Tag(name = "Matrículas", description = "API para la gestión de matrículas en el sistema educativo")
public class MatriculaC {

    @Autowired
    private MatriculaR matriculaRepository;

    @Operation(summary = "Obtener todas las matrículas",
            description = "Retorna una lista de todas las matrículas registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de matrículas recuperada exitosamente")
    @GetMapping
    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    @Operation(summary = "Obtener matrícula por ID",
            description = "Retorna una matrícula específica basada en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula encontrada"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable Integer id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        return matricula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nueva matrícula",
            description = "Registra una nueva matrícula en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la matrícula inválidos")
    })
    @PostMapping
    public Matricula createMatricula(@RequestBody Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Operation(summary = "Actualizar matrícula",
            description = "Actualiza la información de una matrícula existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de la matrícula inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> updateMatricula(@PathVariable Integer id, @RequestBody Matricula matriculaDetails) {
        Optional<Matricula> existingMatricula = matriculaRepository.findById(id);
        return existingMatricula.map(matricula -> {
            matricula.setFechaMatricula(matriculaDetails.getFechaMatricula());
            matricula.setEstado(matriculaDetails.getEstado());
            matricula.setValorTotal(matriculaDetails.getValorTotal());
            matricula.setEstudiante(matriculaDetails.getEstudiante());
            matricula.setPeriodoLectivo(matriculaDetails.getPeriodoLectivo());
            return ResponseEntity.ok(matriculaRepository.save(matricula));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar matrícula",
            description = "Elimina una matrícula del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Matrícula eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Matrícula no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMatricula(@PathVariable Integer id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        return matricula.map(m -> {
            matriculaRepository.delete(m);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
