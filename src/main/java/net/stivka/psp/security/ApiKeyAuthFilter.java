package net.stivka.psp.security;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.stivka.psp.service.UserService;

public class ApiKeyAuthFilter extends GenericFilterBean {

    private static final String USER_ID = "user_id";
    private final String headerName;
    private final UserService userService;

    public ApiKeyAuthFilter(String headerName, UserService userService) {
        this.headerName = headerName;
        this.userService = userService;
    }

    /*
     * should also check that the API key is associated with an active user,
     * and that the user's account hasn't been locked, disabled, or expired.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        System.out.println("Filter executed for path: " + httpRequest.getRequestURI());

        // Bypass the filter for Swagger UI endpoint
        String path = httpRequest.getRequestURI();
        // If the path doesn't start with /api/, just continue the chain and bypass the
        // filter logic
        if (!path.startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }

        // perhaps add a message to the response, if the header is missing
        // the api key or user id
        String apiKey = httpRequest.getHeader(headerName);
        Long userId = Long.valueOf(httpRequest.getHeader(USER_ID));

        boolean isApiKeyValid = userService.checkApiKey(userId, apiKey);

        if (isApiKeyValid) {
            ApiKey apiKeyObject = userService.getUser(userId).get().getApiKey();
            ApiKeyAuthentication authentication = new ApiKeyAuthentication(apiKeyObject);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API key");
            return;
        }

        chain.doFilter(request, response);
    }
}
