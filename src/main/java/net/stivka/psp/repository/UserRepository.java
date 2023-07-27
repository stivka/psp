package net.stivka.psp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.stivka.psp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
