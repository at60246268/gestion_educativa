package pe.edu.idat.gestion.app.ms.planillas.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{
    private static final String SCHEMA = "bearerAuth";

    @Bean
    public OpenAPI openAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Gestión de Planillas")
                        .version("1.0")
                        .description("Microservicio de Planillas - JWT y Roles")
                        .contact(new Contact()
                                .name("IDAT")
                                .email("fesolano@idat.pe")
                        )
                        .license( new License().name("2026-I")))
                .addSecurityItem(new SecurityRequirement().addList(SCHEMA))
                .components(new Components()
                        .addSecuritySchemes(SCHEMA, new SecurityScheme()
                                .name(SCHEMA)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT - ESTRUCTURA")));


    }
}
