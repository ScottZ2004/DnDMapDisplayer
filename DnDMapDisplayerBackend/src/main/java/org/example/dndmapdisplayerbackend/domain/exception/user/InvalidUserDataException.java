package org.example.dndmapdisplayerbackend.domain.exception.user;

public class InvalidUserDataException extends RuntimeException {
    public InvalidUserDataException(String message) {
        super(message);
    }
}
