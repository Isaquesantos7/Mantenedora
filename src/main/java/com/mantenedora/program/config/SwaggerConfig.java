package com.mantenedora.program.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configOpenApi() {
        return  new OpenAPI().info(
                new Info().description("Definições das operações da api de Mantenedora.")
                        .version("1.0.0")
                        .title("Mantenedora RestFull API")
        );
    }
}
