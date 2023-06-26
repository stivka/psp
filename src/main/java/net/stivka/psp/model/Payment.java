package net.stivka.psp.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Payment {

    @Id
    private Long id;
    private BigDecimal amount;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Merchant merchant;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
