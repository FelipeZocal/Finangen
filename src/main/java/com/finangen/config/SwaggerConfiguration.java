package com.finangen.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("finangen")
                .pathsToMatch("/**")
                .packagesToScan("com.finangen.resources")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("Finangen 2025")
                .description("Projeto Finangen 2025")
                .version("1.0")
                .contact(new Contact().name("Finangen Spring")
                        .url("https://github.com/Arthur-Zocal")
                        .email("arthurzocal2005@gmail.com")));
    }
}
