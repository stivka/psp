package net.stivka.psp.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping()
    public ResponseEntity<Merchant> getMerchantById(@RequestParam Long id) {
        Optional<Merchant> merchant = merchantService.getMerchant(id);
        return merchant.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
