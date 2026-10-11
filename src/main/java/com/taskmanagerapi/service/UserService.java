package com.taskmanagerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.taskmanagerapi.repository.UserRepository;
import com.taskmanagerapi.entity.*;;

@Service 
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
