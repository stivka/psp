package net.stivka.psp.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.stivka.psp.repository.CustomerRepository;
import net.stivka.psp.repository.MerchantRepository;
import net.stivka.psp.repository.PaymentRepository;

/* in the integration test, service and repositories are autowired, so they'll be 
actual spring managed beans not mocks. This helps to detect issues when 
components don't work together.
*/
@SpringBootTest
public class PaymentServiceIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Test
    public void contextLoads() {
        assertNotNull(paymentService);
    }
}
