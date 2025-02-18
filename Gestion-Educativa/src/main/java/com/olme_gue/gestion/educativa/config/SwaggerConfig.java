package com.olme_gue.gestion.educativa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gestión Educativa API")
                        .version("1.0")
                        .description("API REST para la gestión de estudiantes, docentes, asignaturas, grupos y matrículas")
                        .termsOfService("http://olme-gue.edu.ec/terms/")
                        .contact(new Contact()
                                .name("Grupo de Desarrollo")
                                .email("soporte@olme-gue.edu.ec")
                                .url("https://github.com/olme-gue/gestion-educativa"))
                        .license(new License()
                                .name("Uso Académico")
                                .url("http://olme-gue.edu.ec/license")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desarrollo"),
                        new Server()
                                .url("https://gestion-educativa.olme-gue.edu.ec")
                                .description("Servidor de Producción")
                ));
    }

}
