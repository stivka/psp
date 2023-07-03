package net.stivka.psp.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
// use this to allow Jackson to handle circular references by serializing the first occurrence of the entity normally, 
// and serializing subsequent occurrences as references to the first occurrence. 
// This breaks the circular reference and prevents the infinite recursion.
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
