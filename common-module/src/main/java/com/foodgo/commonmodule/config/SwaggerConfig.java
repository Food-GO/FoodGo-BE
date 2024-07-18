package com.foodgo.commonmodule.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "HANEUM FOODGO API 명세서",
                description = "HANEUM FOODGO BE API 명세서입니다.",
                version = "v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi stOpenApi() {

        return GroupedOpenApi.builder()
                .group("HANEUM FOODGO API v1")
                .pathsToMatch("/**")
                .build();
    }
}

