package org.example.dndmapdisplayerbackend.domain.port.out.file;

public interface FileStoragePort {
    String store(Long userId, byte[] data, String fileName, String contentType);
    byte[] retrieve(Long userId, String filename);
    boolean exists(Long userId, String filename);
    void delete(Long userId, String filename);
}
