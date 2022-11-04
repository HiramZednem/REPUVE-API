package com.escuelita.demo.core.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:5173/", "Agrega la url que te da vite al correr el programa")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(-1);
    }
}
