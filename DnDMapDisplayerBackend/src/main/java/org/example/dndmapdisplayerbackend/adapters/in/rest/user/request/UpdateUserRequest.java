package org.example.dndmapdisplayerbackend.adapters.in.rest.user.request;

public record UpdateUserRequest(
        String email,
        String name,
        String password
) {
}
