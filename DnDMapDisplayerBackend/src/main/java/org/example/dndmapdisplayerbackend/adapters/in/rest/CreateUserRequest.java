package org.example.dndmapdisplayerbackend.adapters.in.rest;

public record CreateUserRequest(
        String name,
        String email
) {}