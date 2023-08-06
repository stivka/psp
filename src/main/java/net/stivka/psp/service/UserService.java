package net.stivka.psp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.stivka.psp.model.User;
import net.stivka.psp.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // this allows apiKey to be lazily loaded, because the transaction will be left open
    @Transactional(readOnly = true)
    public boolean checkApiKey(Long userId, String apiKey) {
        Optional<User> userOptional = getUser(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOptional.get().getApiKey().getKey().equals(apiKey);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

}
