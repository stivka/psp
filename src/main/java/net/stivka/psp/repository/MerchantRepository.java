package net.stivka.psp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.stivka.psp.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    
}
