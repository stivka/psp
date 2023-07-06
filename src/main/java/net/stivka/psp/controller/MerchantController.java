package net.stivka.psp.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.stivka.psp.model.Merchant;
import net.stivka.psp.service.MerchantService;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Long id) {
        Optional<Merchant> merchant = merchantService.findById(id);
        return merchant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    // @PostMapping
    // public ResponseEntity<Merchant> saveMerchant() {
    //     Merchant savedMerchant = merchantService.saveMerchant();
    // }
}
