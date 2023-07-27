package net.stivka.psp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
// this specifies the primary key column that is used as a foreign key 
// to join another table. In other words, the primary key 'id' exists
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;
}
