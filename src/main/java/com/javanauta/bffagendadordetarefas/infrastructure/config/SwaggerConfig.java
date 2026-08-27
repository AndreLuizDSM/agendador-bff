package com.javanauta.bffagendadordetarefas.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.url}")
    private String serverUrl;

    @Value("${swagger.server.description}")
    private String serverDescription;

    @Bean
    public OpenAPI configOpenApi(){
        return new OpenAPI()
                .addServersItem(new Server().url(serverUrl).description(serverDescription));
    }
}
