package net.stivka.psp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import net.stivka.psp.model.Merchant;
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

    public boolean validateApiKeyForMerchant(String apiKey, Merchant merchant) {
        return apiKeyRepository.existsByKeyAndMerchant(apiKey, merchant);
    }

    public boolean isAdminApiKey(String apiKey) {
        Optional<ApiKey> optionalApiKey = apiKeyRepository.findByKey(apiKey);
        return optionalApiKey.isPresent() && optionalApiKey.get().getRole().equalsIgnoreCase("ADMIN");
    }

    public boolean isMerchantApiKey(String apiKey) {
        Optional<ApiKey> optionalApiKey = apiKeyRepository.findByKey(apiKey);
        return optionalApiKey.isPresent() && optionalApiKey.get().getRole().equalsIgnoreCase("MERCHANT");
    }
}
