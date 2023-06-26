package net.stivka.psp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.stivka.psp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
//You would have to separate nested fields with underscore?
    List<Payment> findByCustomerId(Long id);

    List<Payment> findByMerchantId(Long id);
}
