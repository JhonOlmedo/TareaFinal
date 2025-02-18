package com.olme_gue.gestion.educativa.repositorios.administracion;

import com.olme_gue.gestion.educativa.models.entity.administracion.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioR extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    Optional<Usuario> findByUsername(String username);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
}
