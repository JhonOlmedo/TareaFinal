package com.olme_gue.gestion.educativa.repositorios.contabilidad;

import com.olme_gue.gestion.educativa.models.entity.contabilidad.MovimientoContable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoContableR extends JpaRepository<MovimientoContable, Integer> {
}
