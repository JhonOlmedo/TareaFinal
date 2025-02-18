package com.olme_gue.gestion.educativa.repositorios.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionR extends JpaRepository<Calificacion, Integer> {
}
