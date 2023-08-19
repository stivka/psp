package net.stivka.psp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.stivka.psp.security.ApiKey;

@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq_generator")
    @SequenceGenerator(name = "app_user_seq_generator", sequenceName = "app_user_seq", allocationSize = 1)
    private Long id;
    private String fullName;
    private String email;

    @Column(name = "user_password")
    private String password;

    /*
     * Another option is to leave the fetch type as LAZY, and use JOIN FETCH in the
     * specific queries
     * where you need the ApiKey to be fetched alongside the User. This gives more
     * control
     * and can lead to better performance, as it only fetches the ApiKey when it's
     * actually needed.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ApiKey apiKey;

    // Lazy means that the customer won't be fetched,
    // before user.getCustomer is explicitly called
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Merchant merchant;

}
