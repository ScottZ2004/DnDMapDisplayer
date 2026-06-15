package org.example.dndmapdisplayerbackend.domain.port.in.file;

public interface UploadFileUseCase {
    String uploadFile(Long userId, String requestingUserEmail, byte[] data, String fileName, String contentType);
}
