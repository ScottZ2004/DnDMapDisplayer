package org.example.dndmapdisplayerbackend.domain.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
