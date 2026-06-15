package org.example.dndmapdisplayerbackend.adapters.out.storage;

import org.example.dndmapdisplayerbackend.domain.exception.file.FileNotFoundException;
import org.example.dndmapdisplayerbackend.domain.port.out.file.FileStoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class LocalFileStorageAdapter implements FileStoragePort {

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public String store(Long userId, byte[] data, String fileName, String contentType) {
        try {
            Path userDir = Path.of(storagePath, userId.toString());
            Files.createDirectories(userDir);

            Path filePath = userDir.resolve(fileName);
            Files.write(filePath, data);

            return filePath.toString();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] retrieve(Long userId, String filename) {
        try {
            Path fileNamen = Path.of(storagePath, userId.toString(), filename);
            return Files.readAllBytes(fileNamen);
        }
        catch(IOException e) {
            throw new FileNotFoundException(filename);
        }
    }

    @Override
    public boolean exists(Long userId, String filename) {
        Path filePath = Path.of(storagePath, userId.toString(), filename);
        return Files.exists(filePath);
    }

    @Override
    public void delete(Long userId, String filename) {
//        try {
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
