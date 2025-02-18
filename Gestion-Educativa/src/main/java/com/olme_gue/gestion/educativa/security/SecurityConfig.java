package com.olme_gue.gestion.educativa.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.olme_gue.gestion.educativa.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {
        "/auth/login",
        "/swagger-ui/**",
        "/api-docs/**",
        "/swagger-ui/**",
        "/api-docs/**",
        "/swagger-ui.html",
        "/api/usuarios/**",
        "/api/controlador/**",
        "/api/docentes/**",
        "/api/asignaturas/**",
        "/api/calificaciones/**",
        "/api/estudiantes/**",
        "/api/grupos/**",
        "/api/horarios/**",
        "/api/matriculas/**",
        "/api/periodos-lectivos/**",
        "/api/planes-estudio/**",
        "/api/espacios-fisicos/**",
        "/api/parametros-generales/**",
        "/api/perfiles-acceso/**",
        "/api/usuarios/**",
        "/api/asientos-contables/**",
        "/api/comprobantes-venta/**",
        "/api/cuentas-contables/**",
        "/api/movimientos-contables/**",
        "/api/rubros-cobro/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider, UsuarioService usuarioService) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Habilitar CORS
            .csrf(csrf -> csrf.disable())  // Desactivar CSRF
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Stateless
            .authorizeRequests(auth -> auth
                .requestMatchers(PUBLIC_URLS).permitAll()  // Permitir acceso a URLs públicas
                .anyRequest().authenticated()  // Requiere autenticación para otras solicitudes
            )
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, usuarioService), UsernamePasswordAuthenticationFilter.class);  // Filtro JWT

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));  // Permitir todos los orígenes
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));  // Métodos permitidos
        configuration.setAllowedHeaders(Arrays.asList("*"));  // Permitir todos los headers
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        configuration.setAllowCredentials(true);  // Habilitar cookies de autenticación
        configuration.setMaxAge(3600L);  // Tiempo máximo de caché para CORS

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Aplicar CORS a todas las rutas
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usar BCrypt para codificar las contraseñas
    }
}

