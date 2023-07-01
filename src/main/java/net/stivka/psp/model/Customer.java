package net.stivka.psp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Customer extends Auditable<String> {
    @Id
    private Long id;
    private String name;
    private String email;

    // You might want to encrypt the password in a real application
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;
}
