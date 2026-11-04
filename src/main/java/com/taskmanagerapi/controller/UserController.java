package com.taskmanagerapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.taskmanagerapi.service.UserService;
import com.taskmanagerapi.entity.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.taskmanagerapi.exception.ResourceNotFoundException;

import java.util.List;


@RestController 
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable Long id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
