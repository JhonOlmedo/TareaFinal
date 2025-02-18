package com.olme_gue.gestion.educativa.controllers.administracion;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olme_gue.gestion.educativa.models.entity.administracion.LoginRequest;
import com.olme_gue.gestion.educativa.models.entity.administracion.LoginResponse;
import com.olme_gue.gestion.educativa.models.entity.administracion.Usuario;
import com.olme_gue.gestion.educativa.repositorios.administracion.UsuarioR;
import com.olme_gue.gestion.educativa.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador para manejar las solicitudes relacionadas con Usuario.
 */
@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario", description = "API para la gestión de usuarios del sistema educativo")
public class UsuarioC {

    @Autowired
    private UsuarioR UsuarioR;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtener todos los usuarios registrados.
     */
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return UsuarioR.findAll();
    }

    /**
     * Obtener usuario por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        return UsuarioR.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo usuario.
     */
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        if (UsuarioR.existsByUsername(usuario.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (UsuarioR.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            usuario.setPassword(usuarioService.encodePassword(usuario.getPassword())); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


        Usuario savedUsuario = UsuarioR.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }
    
    /**
     * Actualizar un usuario existente.
     */
    /*
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        return UsuarioR.findById(id)
                .map(usuario -> {
                    usuario.setUsername(usuarioDetails.getUsername());
                    usuario.setNombre(usuarioDetails.getNombre());
                    usuario.setApellido(usuarioDetails.getApellido());
                    usuario.setCedula(usuarioDetails.getCedula());
                    usuario.setTelefono(usuarioDetails.getTelefono());
                    usuario.setEmail(usuarioDetails.getEmail());
                    usuario.setPassword(usuarioDetails.getPassword());
                    usuario.setEstado(usuarioDetails.getEstado());
                    return ResponseEntity.ok(usuarioRepository.save(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }*/

    /**
     * Eliminar un usuario.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Integer id) {
        return UsuarioR.findById(id)
                .map(usuario -> {
                    UsuarioR.delete(usuario);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Buscar usuario por email.
     */
    @GetMapping("/buscar")
    public ResponseEntity<Usuario> getUsuarioByEmail(@RequestParam String email) {
        Usuario usuario = UsuarioR.findByEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Login de usuario.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = usuarioService.autenticarUsuario(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new LoginResponse("Login exitoso", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(e.getMessage(), null));
        }
    }
}
