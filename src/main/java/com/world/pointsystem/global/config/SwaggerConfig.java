package com.world.pointsystem.global.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("v1")
        .packagesToScan("com.world")
        .build();
  }

  @Bean
  public OpenAPI springOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Point System").description("Point System API document")
            .version("v0.0.1"));
  }
}