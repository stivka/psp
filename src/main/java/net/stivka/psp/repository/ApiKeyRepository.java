package net.stivka.psp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.stivka.psp.security.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    boolean existsByApiKey(String apiKey);
}
