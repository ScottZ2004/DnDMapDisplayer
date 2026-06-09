package org.example.dndmapdisplayerbackend.adapters.in.rest.user;

public record LoginUserRequest (
    String email,
    String password
) {}
