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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup","/login","/addItem","/editItem/{id}","/item/{id}","/addQuantity/{id}","/search","/generateRfidTags/{id}","/createCategory","/createBrand","/createType","/editCategory/{id}","/editbrand","/editBrand/{categoryId}","/total items","/nearing stock count","/out of stock count","/InventorySummary","/top5 nearing stock","/nearing stock/all","/top5 out of stock","/out of stock/all","/Salessummary").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}