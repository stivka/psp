package net.stivka.psp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.stivka.psp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
