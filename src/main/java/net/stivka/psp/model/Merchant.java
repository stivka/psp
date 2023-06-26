package net.stivka.psp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
public class Merchant {
    @Id
    private Long id;
    private String name;
    private String email;

    private String password;

    @OneToMany(mappedBy = "merchant")
    private List<Payment> payments;
}

