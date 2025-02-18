package com.olme_gue.gestion.educativa.repositorios.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaR extends JpaRepository<Matricula, Integer> {
}
