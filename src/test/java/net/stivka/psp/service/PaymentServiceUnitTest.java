package net.stivka.psp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import net.stivka.psp.model.Payment;
import net.stivka.psp.repository.CustomerRepository;
import net.stivka.psp.repository.MerchantRepository;
import net.stivka.psp.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentServiceUnitTest {

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private MerchantRepository merchantRepository;

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testGetPayment() {
        Long id = (long) 1;
        Payment mockPayment = new Payment();
        mockPayment.setId(id);
        mockPayment.setAmount(new BigDecimal("100.00"));
        when(paymentRepository.findById(id)).thenReturn(Optional.of(mockPayment));
        Optional<Payment> payment = paymentService.getPayment(id);
        verify(paymentRepository).findById(id);
        assertTrue(payment.isPresent());
        assertEquals(mockPayment.getAmount(), payment.get().getAmount());
    }
}
