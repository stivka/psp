package net.stivka.psp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.stivka.psp.dto.PaymentRequest;
import net.stivka.psp.model.Payment;
import net.stivka.psp.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@Validated
@Tag(name = "Payment Management", description = "APIs related to payment operations")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get all payments")
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getPayments();
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Get a payment by its ID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPayment(id);
        return payment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a payment by its ID")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all payments of a specific customer")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Payment>> getPaymentsByCustomerId(@PathVariable Long customerId) {
        List<Payment> payments = paymentService.getCustomerPayments(customerId);
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Get all payments of a specific merchant")
    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<List<Payment>> getPaymentsByMerchantId(@PathVariable Long merchantId) {
        List<Payment> payments = paymentService.getMerchantPayments(merchantId);
        return ResponseEntity.ok(payments);
    }

    @Operation(summary = "Save a new payment")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Payment created successfully"),
    })
    @PostMapping
    public ResponseEntity<Payment> savePayment(@RequestBody @Validated PaymentRequest paymentRequest) {
        Payment savedPayment = paymentService.savePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }
}
