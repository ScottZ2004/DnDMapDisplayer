package org.example.dndmapdisplayerbackend.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Account with this email already exists.");
    }
}
