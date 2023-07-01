package net.stivka.psp.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Payment extends Auditable<String> {

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
