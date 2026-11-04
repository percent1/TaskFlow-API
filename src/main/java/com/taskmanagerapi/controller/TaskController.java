package com.taskmanagerapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagerapi.entity.Task;
import com.taskmanagerapi.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a task for a specific user.
    @PostMapping("/{userId}")
    public Task createTask(@PathVariable Long userId,
                           @RequestBody Task task) {
        return taskService.createTask(userId, task);
    }

    // Retrieve all tasks belonging to a user.
    @GetMapping("/user/{userId}")
    public List<Task> getAllTasks(@PathVariable Long userId) {
        return taskService.getAllTasks(userId);
    }

    // Retrieve a specific task belonging to a user.
    @GetMapping("/{userId}/{taskId}")
    public Task getTaskById(@PathVariable Long userId,
                            @PathVariable Long taskId) {
        return taskService.getTaskById(userId, taskId);
    }

    // Update an existing task.
    @PutMapping("/{userId}/{taskId}")
    public Task updateTask(@PathVariable Long userId,
                           @PathVariable Long taskId,
                           @RequestBody Task task) {
        return taskService.updateTask(userId, taskId, task);
    }

    // Delete a task belonging to a user.
    @DeleteMapping("/{userId}/{taskId}")
    public void deleteTask(@PathVariable Long userId,
                           @PathVariable Long taskId) {
        taskService.deleteTask(userId, taskId);
    }
}