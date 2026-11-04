package com.taskmanagerapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanagerapi.entity.Task;
import com.taskmanagerapi.entity.User;
import com.taskmanagerapi.exception.ResourceNotFoundException;
import com.taskmanagerapi.repository.TaskRepository;
import com.taskmanagerapi.repository.UserRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository taskRepository,
            UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Find the database user using their authenticated email.
    private User getAuthenticatedUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    // Create a task for the authenticated user.
    public Task createTask(String email, Task task) {

        User user = getAuthenticatedUser(email);

        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);

        return taskRepository.save(task);
    }

    // Retrieve all tasks belonging to the authenticated user.
    public List<Task> getAllTasks(String email) {

        User user = getAuthenticatedUser(email);

        return taskRepository.findByUser_Id(user.getId());
    }

    // Retrieve a task only if it belongs to the authenticated user.
    public Task getTaskById(String email, Long taskId) {

        User user = getAuthenticatedUser(email);

        return taskRepository.findByIdAndUser_Id(taskId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));
    }

    // Update a task only if it belongs to the authenticated user.
    public Task updateTask(String email, Long taskId, Task updatedTask) {

        Task existingTask = getTaskById(email, taskId);

        existingTask.setTittle(updatedTask.getTittle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.isCompleted());
        existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    // Delete a task only if it belongs to the authenticated user.
    public void deleteTask(String email, Long taskId) {

        Task task = getTaskById(email, taskId);

        taskRepository.delete(task);
    }
}