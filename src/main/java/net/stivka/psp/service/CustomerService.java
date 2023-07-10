package net.stivka.psp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import net.stivka.psp.model.Customer;
import net.stivka.psp.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
}
