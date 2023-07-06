package net.stivka.psp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.stivka.psp.model.Merchant;
import net.stivka.psp.security.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findByKey(String key);

    boolean existsByKeyAndMerchant(String key, Merchant merchant);

}
