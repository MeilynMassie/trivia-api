package com.mjm.api.trivia.config;

import com.mjm.api.trivia.service.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final AdminService adminService;

    public SecurityConfig(AdminService adminService) {
        this.adminService = adminService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .userDetailsService(adminService)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/*/admin/**").authenticated()
                    .anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}