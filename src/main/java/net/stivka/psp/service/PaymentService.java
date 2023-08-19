package net.stivka.psp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.stivka.psp.model.Payment;
import net.stivka.psp.model.User;
import net.stivka.psp.dto.PaymentRequest;
import net.stivka.psp.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;

    public PaymentService(PaymentRepository paymentRepository, UserService userService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }
    
    @Transactional
    public Payment savePayment(PaymentRequest paymentRequest) {
        Optional<User> senderOptional = userService.getUser(paymentRequest.getSenderId());
        Optional<User> receiverOptional = userService.getUser(paymentRequest.getReceiverId());
    
        if (!senderOptional.isPresent() || !receiverOptional.isPresent()) {
            throw new RuntimeException("Invalid sender or receiver");
        }
    
        User sender = senderOptional.get();
        User receiver = receiverOptional.get();
    
        Payment payment = new Payment();
    
        // If sender's customer is not null, then it's a customer
        if (sender.getCustomer() != null) {
            payment.setCustomer(sender.getCustomer());
            payment.setMerchant(receiver.getMerchant());
        } else { // if sender's customer is null, it must be a merchant
            payment.setCustomer(receiver.getCustomer());
            payment.setMerchant(sender.getMerchant());
        }
    
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
    
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
