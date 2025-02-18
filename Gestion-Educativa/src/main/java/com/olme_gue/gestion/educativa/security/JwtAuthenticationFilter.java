package com.olme_gue.gestion.educativa.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.olme_gue.gestion.educativa.models.entity.administracion.Usuario;
import com.olme_gue.gestion.educativa.service.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioService usuarioService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UsuarioService usuarioService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJwtFromRequest(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            logger.info("Token válido para el usuario: {}", username);

            Optional<Usuario> usuarioOptional = usuarioService.obtenerUsuarioPorNombre(username);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                logger.info("Usuario encontrado: {}", username);

                // Aquí puedes agregar roles si es necesario
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for (String role : usuario.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establecer la autenticación en el SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("Usuario no encontrado para el nombre: {}", username);
            }
        } else {
            logger.info("Token no encontrado o no válido");
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Extraer token sin "Bearer "
        }
        return null;
    }
}
