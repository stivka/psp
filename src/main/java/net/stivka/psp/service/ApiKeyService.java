package net.stivka.psp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import net.stivka.psp.repository.ApiKeyRepository;
import net.stivka.psp.security.ApiKey;

@Service
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public Optional<ApiKey> getApiKey(String apiKey) {
        return apiKeyRepository.findByKey(apiKey);
    }
}
