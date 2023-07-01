package net.stivka.psp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Merchant extends Auditable<String> {
    @Id
    private Long id;
    private String name;
    private String email;

    private String password;

    @OneToMany(mappedBy = "merchant")
    private List<Payment> payments;
}

