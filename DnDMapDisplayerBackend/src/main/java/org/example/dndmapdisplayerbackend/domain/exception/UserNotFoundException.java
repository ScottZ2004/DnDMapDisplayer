package org.example.dndmapdisplayerbackend.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found with");
    }
}
