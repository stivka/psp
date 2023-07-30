package net.stivka.psp.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.stivka.psp.model.PaymentMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
}
