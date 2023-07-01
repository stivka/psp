package net.stivka.psp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.stivka.psp.model.Customer;
import net.stivka.psp.model.Merchant;
import net.stivka.psp.model.Payment;
import net.stivka.psp.repository.CustomerRepository;
import net.stivka.psp.repository.MerchantRepository;
import net.stivka.psp.repository.PaymentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentApiEndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    // needs authorized API access first
    // @Test
    // public void testCreatePayment() {
    //     // Given
    //     Long customerId = (long) 1;
    //     Long merchantId = (long) 1;
    //     Customer customer = customerRepository.findById(customerId).orElseThrow();
    //     Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(); 

    //     Payment newPayment = new Payment();
    //     newPayment.setAmount(new BigDecimal("100.00"));
        

    //     // When
    //     ResponseEntity<Payment> response = restTemplate.postForEntity(
    //         "/api/payments/{customerId}/{merchantId}",
    //         newPayment,
    //         Payment.class,
    //         customer.getId(),
    //         merchant.getId()
    //     );

    //     // Then
    //     assertEquals(HttpStatus.CREATED, response.getStatusCode());
    //     assertEquals(newPayment.getAmount(), response.getBody().getAmount());
    // }
}
