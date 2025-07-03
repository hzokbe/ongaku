package com.hzokbe.ongaku.config.security;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${rsa.key.public}")
    private RSAPublicKey rsaPublicKey;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity security) throws Exception {
        return
            security
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                r -> r
                    .requestMatchers(HttpMethod.POST, "/sign-up")
                    .permitAll()
                    .anyRequest()
                    .denyAll()
            )
            .build();
    }
}
