package com.taskApp.taskApp.config;

import com.taskApp.taskApp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
    private final CorsConfigurationSource corsConfigurationSource;
    
    public SecurityConfig(CorsConfigurationSource corsConfigurationSource){
        this.corsConfigurationSource = corsConfigurationSource;
        
    }
    
    //authentication
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService();
    }
    
   //passwordEncoder
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    //bean for AuthenticationProvider
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
    
    //authorization bean
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrfCustomizer -> csrfCustomizer.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/index").permitAll();
                    auth.requestMatchers("/api/users/**").permitAll();
                    auth.requestMatchers("/api/tasks/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> {
                    form
                        .permitAll()
                        .defaultSuccessUrl("/index", true)  // Redirigir a /index después de un inicio de sesión exitoso
                ;})
                .sessionManagement(management -> {
                    management
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .sessionFixation(sessionFixation -> sessionFixation.newSession())
                        .invalidSessionUrl("/login")
                        .maximumSessions(1);
                })
                .logout(logout -> logout
                    .permitAll()
                );
        return httpSecurity.build();
    }
}
