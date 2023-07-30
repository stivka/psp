package net.stivka.psp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.stivka.psp.model.Payment;
import net.stivka.psp.model.User;
import net.stivka.psp.dto.PaymentRequest;
import net.stivka.psp.model.Customer;
import net.stivka.psp.model.Merchant;
import net.stivka.psp.repository.PaymentRepository;
import net.stivka.psp.repository.UserRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment savePayment(PaymentRequest paymentRequest) {
        // Hibernate will return an instance of the subclass of Customer or Merchant
        Optional<User> senderOptional = userRepository.findById(paymentRequest.getSenderId());
        Optional<User> receiverOptional = userRepository.findById(paymentRequest.getReceiverId());
    
        if (!senderOptional.isPresent() || !receiverOptional.isPresent()) {
            throw new RuntimeException("Invalid sender or receiver");
        }
    
        User sender = senderOptional.get();
        User receiver = receiverOptional.get();
    
        Payment payment = new Payment();
        
        // Hibernate's JOINED inheritance strategy will create the Merchant or Customer object
        if (sender instanceof Customer) {
            payment.setCustomer((Customer) sender);
            payment.setMerchant((Merchant) receiver);
        } else {
            payment.setCustomer((Customer) receiver);
            payment.setMerchant((Merchant) sender);
        }
    
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
    
        // Save the new payment
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
