package org.example.dndmapdisplayerbackend.domain.exception.user;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid email or password");
    }
}
