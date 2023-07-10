package net.stivka.psp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.stivka.psp.service.ApiKeyService;
import net.stivka.psp.service.MerchantService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private MerchantService merchantService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new ApiKeyAuthFilter("X-API-Key", apiKeyService, merchantService),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/api/payments", HttpMethod.POST.name()))
                        .authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/api/payments/**", HttpMethod.GET.name()))
                        .authenticated()
                        .anyRequest().authenticated());

        return http.build();
    }
}
