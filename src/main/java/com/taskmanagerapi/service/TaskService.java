package com.taskmanagerapi.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

import com.taskmanagerapi.entity.Task;
import com.taskmanagerapi.entity.User;
import com.taskmanagerapi.repository.TaskRepository;
import com.taskmanagerapi.repository.UserRepository;

@Service 
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepositor){
        this.taskRepository = taskRepository;
        this.userRepository = userRepositor;
    }

    public Task createTask(Task task, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
