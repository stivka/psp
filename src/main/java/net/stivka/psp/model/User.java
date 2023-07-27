package net.stivka.psp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.stivka.psp.security.ApiKey;

@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
// when querying a subclass, customer or merchant, a join to user table is made
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq_generator")
    @SequenceGenerator(name = "app_user_seq_generator", sequenceName = "app_user_seq", allocationSize = 1)
    private Long id;
    private String fullName;
    private String email;
    @Column(name = "user_password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private ApiKey apiKey;
}
