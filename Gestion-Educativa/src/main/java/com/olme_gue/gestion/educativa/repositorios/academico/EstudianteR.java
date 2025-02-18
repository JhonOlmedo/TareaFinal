package com.olme_gue.gestion.educativa.repositorios.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteR extends JpaRepository<Estudiante, Integer> {
}
