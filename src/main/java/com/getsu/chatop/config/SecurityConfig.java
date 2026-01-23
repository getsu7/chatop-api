package com.getsu.chatop.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private String jwtKey = "UILrLj2Wfc8ZIQnFv8qlluoZJMdO3A5l/jbLHSSu4Ow=";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        try {
            httpSecurity
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                            .anyRequest().authenticated()
                    );

            return httpSecurity.build();
        } catch (Exception e) {
            throw new RuntimeException("Erreur de configuration de sécurité", e);
        }
    }

//    @Bean
//    public JwtEncoder jwtEncoder() {
//        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
//    }

    @Bean
    public JwtEncoder jwtEncoder() {
        SecretKey key = new SecretKeySpec(
                this.jwtKey.getBytes(),
                "HmacSHA256"
        );

        return new NimbusJwtEncoder(new ImmutableSecret<>(key));
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length, "HS256");
//        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
//    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey key = new SecretKeySpec(
                this.jwtKey.getBytes(),
                "HmacSHA256"
        );

        return NimbusJwtDecoder
                .withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
