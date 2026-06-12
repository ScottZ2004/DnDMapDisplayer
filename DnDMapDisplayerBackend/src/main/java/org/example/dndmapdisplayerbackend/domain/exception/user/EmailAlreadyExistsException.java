package org.example.dndmapdisplayerbackend.domain.exception.user;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Account with this email already exists.");
    }
}
