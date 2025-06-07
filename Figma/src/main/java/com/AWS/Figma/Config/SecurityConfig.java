package com.AWS.Figma.Config; // Update package name as needed

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup","/login","/addItem").permitAll() // 👈 Allow public access
                        .anyRequest().authenticated() // 👈 All other endpoints require login
                )
                .httpBasic(Customizer.withDefaults()); // Optional: enable basic auth for testing

        return http.build();
    }
}
