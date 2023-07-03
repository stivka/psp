package net.stivka.psp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.stivka.psp.model.Payment;
import net.stivka.psp.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPayment(id);
        return payment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{customerId}/{merchantId}")
    public ResponseEntity<Payment> savePayment(@PathVariable Long customerId, @PathVariable Long merchantId,
            @RequestBody Payment payment) {
        Payment savedPayment = paymentService.savePayment(customerId, merchantId, payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<Payment>> getPaymentsByCustomerId(@PathVariable Long customerId) {
        List<Payment> payments = paymentService.getCustomerPayments(customerId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/merchants/{merchantId}")
    public ResponseEntity<List<Payment>> getPaymentsByMerchantId(@PathVariable Long merchantId) {
        List<Payment> payments = paymentService.getMerchantPayments(merchantId);
        return ResponseEntity.ok(payments);
    }
}
