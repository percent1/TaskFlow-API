
package com.taskmanagerapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.taskmanagerapi.dto.ChangePasswordRequest;
import com.taskmanagerapi.dto.UpdateNameRequest;
import com.taskmanagerapi.dto.UserResponse;
import com.taskmanagerapi.entity.User;
import com.taskmanagerapi.exception.ResourceNotFoundException;
import com.taskmanagerapi.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user.
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    // Retrieve all users.
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Retrieve a user by ID.
    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    // Retrieve the authenticated user's account details.
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(
            Authentication authentication) {

        User user = userService.getCurrentUser(authentication.getName());

        return ResponseEntity.ok(
                new UserResponse(user.getName(), user.getEmail()));
    }

    // Update the authenticated user's name.
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateName(
            Authentication authentication,
            @Valid @RequestBody UpdateNameRequest request) {

        User user = userService.updateName(
                authentication.getName(), request);

        return ResponseEntity.ok(
                new UserResponse(user.getName(), user.getEmail()));
    }

    // Change the authenticated user's password.
    @PutMapping("/me/password")
    public ResponseEntity<Void> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequest request) {

        userService.changePassword(authentication.getName(), request);

        return ResponseEntity.noContent().build();
    }
}
