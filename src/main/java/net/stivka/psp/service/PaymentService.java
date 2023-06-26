package net.stivka.psp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.stivka.psp.model.Payment;
import net.stivka.psp.model.Customer;
import net.stivka.psp.model.Merchant;
import net.stivka.psp.repository.CustomerRepository;
import net.stivka.psp.repository.MerchantRepository;
import net.stivka.psp.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final MerchantRepository merchantRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, 
                          CustomerRepository customerRepository, 
                          MerchantRepository merchantRepository) {
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.merchantRepository = merchantRepository;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment savePayment(Long customerId, Long merchantId, Payment payment) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) {
            throw new RuntimeException("Customer not found");
        }
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);
        if (!merchantOptional.isPresent()) {
            throw new RuntimeException("Merchant not found");
        }
        payment.setCustomer(customerOptional.get());
        payment.setMerchant(merchantOptional.get());
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> getCustomerPayments(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    public List<Payment> getMerchantPayments(Long merchantId) {
        return paymentRepository.findByMerchantId(merchantId);
    }
}
