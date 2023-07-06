package net.stivka.psp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import net.stivka.psp.model.Merchant;
import net.stivka.psp.repository.MerchantRepository;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Optional<Merchant> findById(Long id) {
        return merchantRepository.findById(id);
    }
}
