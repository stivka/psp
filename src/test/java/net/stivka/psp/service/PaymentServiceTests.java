package net.stivka.psp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import net.stivka.psp.model.Payment;
import net.stivka.psp.repository.PaymentRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PaymentServiceTests {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void testGetPayments() {
        // Mock the repository to return sample payments
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        List<Payment> payments = Arrays.asList(payment1, payment2);
        when(paymentRepository.findAll()).thenReturn(payments);

        // Call the service method
        List<Payment> result = paymentService.getPayments();

        // Assert the result
        assertThat(result).isEqualTo(payments);
    }

    // Add more tests for other service methods
}
