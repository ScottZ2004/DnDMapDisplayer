package org.example.dndmapdisplayerbackend.adapters.in.rest;

public record UserResponse(
        Long id,
        String name,
        String email
) {}