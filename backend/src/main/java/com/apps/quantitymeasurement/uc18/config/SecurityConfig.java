package com.apps.quantitymeasurement.uc18.config;

import com.apps.quantitymeasurement.uc18.security.JwtService;

import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Value("${app.jwt.secret}")
    private String secret;

    @Autowired
    private JwtService jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                // CORS ENABLE
                .cors(cors -> {})

                // DISABLE CSRF
                .csrf(csrf -> csrf.disable())

                // URL AUTHORIZATION
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )

                // GOOGLE LOGIN — redirects to React with JWT token in URL
                .oauth2Login(oauth ->
                        oauth.successHandler((request, response, authentication) -> {
                            String email = authentication.getName();
                            String token = jwtService.generateToken(email);
                            response.sendRedirect(
                                    "http://localhost:5173?token=" + token
                            );
                        })
                )

                // JWT VALIDATION
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt()
                )

                // H2 CONSOLE
                .headers(headers ->
                        headers.frameOptions(
                                frame -> frame.disable()
                        )
                );

        return http.build();
    }

    // JWT DECODER
    @Bean
    public JwtDecoder jwtDecoder() {

        SecretKey key =
                Keys.hmacShaKeyFor(
                        secret.getBytes()
                );

        return NimbusJwtDecoder
                .withSecretKey(key)
                .build();
    }

    // CORS CONFIGURATION
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        // REACT FRONTEND URL
        configuration.setAllowedOrigins(
                List.of("http://localhost:5173")
        );

        // ALLOW ALL HTTP METHODS
        configuration.setAllowedMethods(
                List.of("*")
        );

        // ALLOW ALL HEADERS
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // REGISTER CORS
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}