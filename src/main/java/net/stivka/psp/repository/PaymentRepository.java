package net.stivka.psp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.stivka.psp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
