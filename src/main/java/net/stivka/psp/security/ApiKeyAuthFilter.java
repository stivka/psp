package net.stivka.psp.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.stivka.psp.service.ApiKeyService;

public class ApiKeyAuthFilter extends GenericFilterBean {

    private final String headerName;
    private final ApiKeyService apiKeyService;

    public ApiKeyAuthFilter(String headerName, ApiKeyService apiKeyService) {
        this.headerName = headerName;
        this.apiKeyService = apiKeyService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String apiKey = httpRequest.getHeader(headerName);

        Optional<ApiKey> optionalApiKey = apiKeyService.getApiKey(apiKey);
        if (!optionalApiKey.isPresent()) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key");
            return;
        }

        ApiKeyAuthentication authentication = new ApiKeyAuthentication(optionalApiKey.get());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
