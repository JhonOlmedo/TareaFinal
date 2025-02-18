package com.olme_gue.gestion.educativa.service;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olme_gue.gestion.educativa.models.entity.administracion.Usuario;
import com.olme_gue.gestion.educativa.repositorios.administracion.UsuarioR;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioR usuarioR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String autenticarUsuario(String username, String password) throws Exception {
        Usuario usuario = usuarioR.findByUsername(username)
                .orElseThrow(() -> new Exception("No se ha encontrado el usuario"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new Exception("La contraseña es incorrecta");
        }

        // Generar el token JWT
        return generarToken(usuario);
    }

    private String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expiración de 1 día
                .signWith(SECRET_KEY)
                .compact();
    }

    public Optional<Usuario> obtenerUsuarioPorNombre(String username) {
        return usuarioR.findByUsername(username);
    }

    public Usuario crearUsuario(Usuario usuario) throws Exception {
        if (usuarioR.existsByUsername(usuario.getUsername())) {
            throw new Exception("El username ya se encuentra en uso");
        }
        if (usuarioR.existsByEmail(usuario.getEmail())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        return usuarioR.save(usuario);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
