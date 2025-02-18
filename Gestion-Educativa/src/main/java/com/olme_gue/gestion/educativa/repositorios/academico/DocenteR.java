package com.olme_gue.gestion.educativa.repositorios.academico;

import com.olme_gue.gestion.educativa.models.entity.academico.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteR extends JpaRepository<Docente, Integer> {
}
