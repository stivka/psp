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

        String path = httpRequest.getRequestURI();
        String merchantId = path.split("/")[path.split("/").length - 1];
        Optional<Merchant> merchant = merchantService.findById(Long.parseLong(merchantId));        

        if (!optionalApiKey.isPresent() || !apiKeyService.validateApiKeyForMerchant(apiKey, merchant.get())) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key");
            return;
        }

        ApiKeyAuthentication authentication = new ApiKeyAuthentication(optionalApiKey.get());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
