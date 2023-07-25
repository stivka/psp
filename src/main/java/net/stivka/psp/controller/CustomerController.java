package net.stivka.psp.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.stivka.psp.model.Customer;
import net.stivka.psp.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<Customer> getCustomerById(@RequestParam Long customerId) {
        Optional<Customer> customer = customerService.getCustomer(customerId);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}