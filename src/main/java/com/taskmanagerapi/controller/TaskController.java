package com.taskmanagerapi.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create a task for the authenticated user.
    @PostMapping
    public Task createTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody Task task) {

        return taskService.createTask(userDetails.getUsername(), task);
    }

    // Retrieve all tasks belonging to the authenticated user.
    @GetMapping
    public List<Task> getAllTasks(
            @AuthenticationPrincipal UserDetails userDetails) {

        return taskService.getAllTasks(userDetails.getUsername());
    }

    // Retrieve a specific task belonging to the authenticated user.
    @GetMapping("/{taskId}")
    public Task getTaskById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId) {

        return taskService.getTaskById(userDetails.getUsername(), taskId);
    }

    // Update a task belonging to the authenticated user.
    @PutMapping("/{taskId}")
    public Task updateTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId,
            @Valid @RequestBody Task task) {

        return taskService.updateTask(userDetails.getUsername(), taskId, task);
    }

    // Delete a task belonging to the authenticated user.
    @DeleteMapping("/{taskId}")
    public void deleteTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId) {

        taskService.deleteTask(userDetails.getUsername(), taskId);
    }
}