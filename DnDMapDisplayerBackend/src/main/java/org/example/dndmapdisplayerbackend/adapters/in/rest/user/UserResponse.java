package org.example.dndmapdisplayerbackend.adapters.in.rest.user;

public record UserResponse(
        Long id,
        String name,
        String email
) {}