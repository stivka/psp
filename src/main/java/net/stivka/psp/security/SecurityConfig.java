package net.stivka.psp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import net.stivka.psp.service.ApiKeyService;
import net.stivka.psp.service.MerchantService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private MerchantService merchantService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/payments/**").hasAnyRole("MERCHANT", "ADMIN")
                        .requestMatchers("/api/customers/**").hasAnyRole("MERCHANT", "ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(new ApiKeyAuthFilter("X-API-Key", apiKeyService, merchantService),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
