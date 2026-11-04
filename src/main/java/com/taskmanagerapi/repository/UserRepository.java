package com.taskmanagerapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagerapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a registered user using their email address
    Optional<User> findByEmail(String email);
}