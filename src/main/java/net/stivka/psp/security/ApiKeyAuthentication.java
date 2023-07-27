package net.stivka.psp.security;

import java.util.ArrayList;
import java.util.Collection;
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
        // The API key is the "name" in this case
        return apiKey.getKey();
    }

    @Override
    public Object getCredentials() {
        // The API key is the "credential" in this case
        return apiKey.getKey();
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        // The apiKey object is the "principal" in this case
        return apiKey;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // As roles are not being used, return an empty list of GrantedAuthority
        return new ArrayList<GrantedAuthority>();
    }
}
