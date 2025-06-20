package com.AWS.Figma.config;


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
                        .requestMatchers("/signup","/login","/api/visitors","/api/visitors/search","/api/items","/{id}","/out-of-stock/all","/out-of-stock/top5","/dashboard/summary","/nearing-stock/top5","/nearing-stock/all","/api/sales","/api/sales/date-range","/api/sales/item/{itemId}","/api/sales/dashboard/summary/current","/api/sales/dashboard/daily/current","/api/sales/dashboard/daily/{year}/{month}","api/category/add","api/category/{id}","api/category/edit/{id}").permitAll() // 👈 Allow public access
                        .anyRequest().authenticated() // 👈 All other endpoints require login
                )
                .httpBasic(Customizer.withDefaults()); // Optional: enable basic auth for testing

        return http.build();
    }
}

