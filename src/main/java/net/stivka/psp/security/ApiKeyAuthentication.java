package net.stivka.psp.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyAuthentication implements Authentication {

    private ApiKey apiKey;
    private boolean authenticated;

    public ApiKeyAuthentication(ApiKey apiKey) {
        this.apiKey = apiKey;
        this.authenticated = apiKey != null;
    }

    @Override
    public String getName() {
        return apiKey.getMerchant().getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // or return authorities based on the API key, if you have any

    }

    @Override
    public Object getCredentials() {
        return apiKey.getKey(); // the API key is the "credential" in this case

    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey.getMerchant();
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }
}
