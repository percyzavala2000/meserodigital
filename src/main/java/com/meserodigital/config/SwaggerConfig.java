package com.meserodigital.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI meseroDigitalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mesero Digital API")
                        .description("Documentación interactiva de la API para Mesero Digital")
                        .version("1.0"));
    }
}