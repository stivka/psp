package net.stivka.psp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.stivka.psp.model.Merchant;
import net.stivka.psp.service.ApiKeyService;
import net.stivka.psp.service.MerchantService;

public class ApiKeyAuthFilter extends GenericFilterBean {

    private final String headerName;
    private final ApiKeyService apiKeyService;
    private final MerchantService merchantService;

    public ApiKeyAuthFilter(String headerName, ApiKeyService apiKeyService, MerchantService merchantService) {
        this.headerName = headerName;
        this.apiKeyService = apiKeyService;
        this.merchantService = merchantService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String apiKey = httpRequest.getHeader(headerName);
        Optional<ApiKey> optionalApiKey = apiKeyService.getApiKey(apiKey);

        // api key should always be present, if the apiKey is not present, deny access.
        if (!optionalApiKey.isPresent()) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key");
            return;
        }

        ApiKey actualApiKey = optionalApiKey.get();
        String merchantId = httpRequest.getParameter("merchantId");
        boolean merchantPresent = merchantId != null && !merchantId.isEmpty();

        // If it's not admin, merchantId is required and should match the merchantId in
        // the apiKey
        if (!apiKeyService.isAdminApiKey(apiKey)) {
            if (!merchantPresent || (actualApiKey.getMerchant() != null &&
                    !actualApiKey.getMerchant().getId().toString().equals(merchantId))) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "Valid Merchant ID is required");
                return;
            }
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (apiKeyService.isAdminApiKey(apiKey)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (apiKeyService.isMerchantApiKey(apiKey)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_MERCHANT"));
        }

        ApiKeyAuthentication authentication = new ApiKeyAuthentication(actualApiKey, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
