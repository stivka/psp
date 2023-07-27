package net.stivka.psp.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import java.time.Instant;

// indicates that this class should not be mapped to a database table, but its fields should be mapped to the database tables of its subclasses
@MappedSuperclass
// to automatically populate the fields
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    protected Instant creationDate;

    @LastModifiedBy
    protected U lastModifiedBy;

    @LastModifiedDate
    protected Instant lastModifiedDate;

}
