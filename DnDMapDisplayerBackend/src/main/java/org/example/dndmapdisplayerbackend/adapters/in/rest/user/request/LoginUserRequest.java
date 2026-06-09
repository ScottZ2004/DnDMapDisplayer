package org.example.dndmapdisplayerbackend.adapters.in.rest.user.request;

public record LoginUserRequest (
    String email,
    String password
) {}
