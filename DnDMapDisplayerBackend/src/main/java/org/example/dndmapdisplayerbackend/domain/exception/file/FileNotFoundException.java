package org.example.dndmapdisplayerbackend.domain.exception.file;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String filename) {
        super("File not found: " + filename);
    }
}