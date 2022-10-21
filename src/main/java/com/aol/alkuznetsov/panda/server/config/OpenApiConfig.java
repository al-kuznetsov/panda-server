package com.aol.alkuznetsov.panda.server.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info =
        @Info(
            title = "${vars.api.info.title}",
            description = "${vars.api.info.description}",
            version = "${vars.api.version-url}"))
@Configuration
public class OpenApiConfig {}
