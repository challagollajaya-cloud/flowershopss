package org.example.flowershopss.service;

import org.example.flowershopss.model.User;
import org.example.flowershopss.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register new user
    public String registerUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "success";
    }

    // Find user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }
}