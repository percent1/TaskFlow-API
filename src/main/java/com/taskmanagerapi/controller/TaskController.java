
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/tasks")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody Task task) {
        return taskService.createTask(userDetails.getUsername(), task);
    }

    @GetMapping
    public List<Task> getAllTasks(
            @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getAllTasks(userDetails.getUsername());
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId) {
        return taskService.getTaskById(userDetails.getUsername(), taskId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId,
            @Valid @RequestBody Task task) {
        return taskService.updateTask(userDetails.getUsername(), taskId, task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId) {
        taskService.deleteTask(userDetails.getUsername(), taskId);
    }
}
