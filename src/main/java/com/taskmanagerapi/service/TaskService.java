
package com.taskmanagerapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanagerapi.entity.Task;
import com.taskmanagerapi.entity.User;
import com.taskmanagerapi.repository.TaskRepository;
import com.taskmanagerapi.repository.UserRepository;
import com.taskmanagerapi.exception.ResourceNotFoundException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Create a task for a specific user.
    public Task createTask(Long userId, Task task) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);

        return taskRepository.save(task);
    }

    // Retrieve all tasks belonging to a user.
    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUser_Id(userId);
    }

    // Retrieve a specific task belonging to a user.
    public Task getTaskById(Long userId, Long taskId) {

        return taskRepository.findByIdAndUser_Id(taskId, userId).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
                
    }

    // Update an existing task.
    public Task updateTask(Long userId, Long taskId, Task updatedTask) {

        Task existingTask = getTaskById(userId, taskId);

        existingTask.setTittle(updatedTask.getTittle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.isCompleted());
        existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    // Delete a task belonging to a user.
    public void deleteTask(Long userId, Long taskId) {

        Task task = getTaskById(userId, taskId);

        taskRepository.delete(task);
    }
}
