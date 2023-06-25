package net.stivka.psp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Use a different table name, because user is a reserved keyword
@Data
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String fullName;
    private String email;

    // You might want to encrypt the password in a real application
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;
}
