package org.example.dndmapdisplayerbackend.adapters.in.rest.user;

public record CreateUserRequest(
        String name,
        String email,
        String password
) {}