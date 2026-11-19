package com.enterprise.app.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        // Simulating user creation logic
        UserResponse response = new UserResponse(101L, request.name(), request.email());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Java Record used as an immutable DTO (Data Transfer Object)
    public record UserRequest(
        @NotBlank(message = "Name is required") String name,
        @Email(message = "Invalid email format") String email
    ) {}

    public record UserResponse(Long id, String name, String email) {}
}
