package com.taskmanagerapi.dto;

public record LoginResponse(
        String token,
        String tokenType,
        String name
) {}