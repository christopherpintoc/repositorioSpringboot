package com.plataforma.noticias.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements  WebMvcConfigurer{

    private String ORIGINS="http://localhost:4200";
    private String MAPPING="/**";
    private String[] METHODS={"POST", "GET", "PUT", "PATCH", "HEAD"};
    private String HEAD="*";

    @Override
    public void addCorsMappings(CorsRegistry CorsRegistry) {
        CorsRegistry.addMapping(MAPPING)
                .allowedOrigins(ORIGINS)
                .allowedMethods(METHODS)
                .allowedHeaders(HEAD)
                .maxAge(3600)
                .allowCredentials(false);
    }
}
