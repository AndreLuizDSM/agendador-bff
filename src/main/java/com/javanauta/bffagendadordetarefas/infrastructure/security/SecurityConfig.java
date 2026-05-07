package com.javanauta.bffagendadordetarefas.infrastructure.security;

import com.javanauta.bffagendadordetarefas.infrastructure.exceptions.UnauthorizedException;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = SecurityConfig.SECURITY_SCHEME, type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT", scheme = "Bearer")
public class SecurityConfig {

    private SecurityConfig(){
        throw new UnauthorizedException("Utility Class!");
    }

    public static final String SECURITY_SCHEME = "bearerAuth";
}
