package org.example.dndmapdisplayerbackend.adapters.in.rest.user.response;

public record UserResponse(
        Long id,
        String name,
        String email
) {}