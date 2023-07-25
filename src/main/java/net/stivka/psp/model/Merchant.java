package net.stivka.psp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.stivka.psp.security.ApiKey;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Merchant extends Auditable<String> {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "merchant")
    private List<Payment> payments;

    @OneToOne(mappedBy = "merchant", cascade = CascadeType.ALL)
    private ApiKey apiKey;
}
