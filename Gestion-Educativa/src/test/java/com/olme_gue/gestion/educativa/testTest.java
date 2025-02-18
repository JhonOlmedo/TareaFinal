package com.olme_gue.gestion.educativa;

import com.olme_gue.gestion.educativa.controllers.academico.DocenteC;
import com.olme_gue.gestion.educativa.models.entity.academico.Docente;
import com.olme_gue.gestion.educativa.repositorios.academico.DocenteR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class TestTest {

    @Mock
    private DocenteR docenteRepository;

    @InjectMocks
    private DocenteC docenteController;

    private Docente docente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks
        docente = new Docente(1, "12345678", "Juan", "Pérez", "Matemáticas", "1234567890", null, null);  // Cambié Long a int
    }

    @Test
    void shouldReturnDocenteWhenFoundById() {
        // Arrange
        when(docenteRepository.findById(1)).thenReturn(Optional.of(docente));

        // Act
        ResponseEntity<Docente> response = docenteController.getDocenteById(1);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getIdDocente()).isEqualTo(1);
    }

    @Test
    void shouldReturnNotFoundWhenDocenteDoesNotExist() {
        // Arrange
        when(docenteRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Docente> response = docenteController.getDocenteById(1);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    void shouldCreateDocente() {
        // Arrange
        when(docenteRepository.save(docente)).thenReturn(docente);

        // Act
        Docente createdDocente = docenteController.createDocente(docente);

        // Assert
        assertThat(createdDocente).isNotNull();
        assertThat(createdDocente.getCedula()).isEqualTo("12345678");
    }

    @Test
    void shouldUpdateDocente() {
        // Arrange
        when(docenteRepository.findById(1)).thenReturn(Optional.of(docente));
        Docente updatedDocente = new Docente(1, "87654321", "Pedro", "López", "Ciencias", "0987654321", null, null);
        when(docenteRepository.save(any(Docente.class))).thenReturn(updatedDocente);

        // Act
        ResponseEntity<Docente> response = docenteController.updateDocente(1, updatedDocente);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getCedula()).isEqualTo("87654321");
    }

    @Test
    void shouldDeleteDocente() {
        // Arrange
        when(docenteRepository.findById(1)).thenReturn(Optional.of(docente));

        // Act
        ResponseEntity<Object> response = docenteController.deleteDocente(1);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
    }

}
