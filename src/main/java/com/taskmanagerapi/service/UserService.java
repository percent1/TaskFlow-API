package com.taskmanagerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanagerapi.dto.ChangePasswordRequest;
import com.taskmanagerapi.dto.UpdateNameRequest;
import com.taskmanagerapi.entity.User;
import com.taskmanagerapi.exception.ResourceNotFoundException;
import com.taskmanagerapi.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user with an encrypted password.
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Retrieve a user using their ID.
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Retrieve all users.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve the currently authenticated user.
    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    // Update the currently authenticated user's name.
    public User updateName(String email, UpdateNameRequest request) {

        User user = getCurrentUser(email);

        user.setName(request.name().trim());

        return userRepository.save(user);
    }

    // Change the currently authenticated user's password.
    public void changePassword(
            String email,
            ChangePasswordRequest request) {

        User user = getCurrentUser(email);

        // Verify the existing password before changing it.
        if (!passwordEncoder.matches(
                request.currentPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "Current password is incorrect");
        }

        // Prevent the user from reusing their current password.
        if (passwordEncoder.matches(
                request.newPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "New password must be different from your current password");
        }

        // Encrypt and save the new password.
        user.setPassword(passwordEncoder.encode(request.newPassword()));

        userRepository.save(user);
    }
}
