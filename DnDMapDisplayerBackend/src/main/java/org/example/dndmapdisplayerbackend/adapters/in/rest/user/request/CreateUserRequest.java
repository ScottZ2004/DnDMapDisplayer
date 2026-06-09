package org.example.dndmapdisplayerbackend.adapters.in.rest.user.request;

public record CreateUserRequest(
        String name,
        String email,
        String password
) {}