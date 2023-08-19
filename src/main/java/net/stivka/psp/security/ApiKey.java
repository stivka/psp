package net.stivka.psp.security;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_key_seq_generator")
    @SequenceGenerator(name = "api_key_seq_generator", sequenceName = "api_key_seq", allocationSize = 1)
    private Long id;

    @Column(name = "key_value", nullable = false)
    private String key;

    // we don't want api keys to me modified after creation, so lets use only creation fields
    @CreatedBy
    protected String createdBy;

    @CreatedDate
    protected Instant creationDate;
}
