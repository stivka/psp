package net.stivka.psp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.stivka.psp.service.ApiKeyService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApiKeyService apiKeyService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new ApiKeyAuthFilter("X-API-Key", apiKeyService), UsernamePasswordAuthenticationFilter.class);
    }
}
