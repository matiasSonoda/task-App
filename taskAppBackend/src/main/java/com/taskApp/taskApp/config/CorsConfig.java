package com.taskApp.taskApp.config;

import org.springframework.context.annotation.*;
import org.springframework.web.cors.*;

@Configuration
public class CorsConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");//Permitir todos los origenes
        configuration.addAllowedMethod("*"); //Permitir todos los metodos HTTP
        configuration.addAllowedHeader("*"); //Permitir todos los encabezados
    
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
}
