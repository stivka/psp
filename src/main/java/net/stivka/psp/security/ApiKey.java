package net.stivka.psp.security;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import net.stivka.psp.model.Merchant;

@Data
@Entity
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key_value", nullable = false)
    private String key;

    @OneToOne
    @JoinColumn(name = "merchant_id", nullable = true)
    private Merchant merchant;

    @Column(name = "key_role", nullable = false)
    private String role; // "ROLE_ADMIN" or "ROLE_MERCHANT"

    // we don't want api keys to me modified after creation, so lets add only creation fields
    @CreatedBy
    protected String createdBy;

    @CreatedDate
    protected Instant creationDate;
}
