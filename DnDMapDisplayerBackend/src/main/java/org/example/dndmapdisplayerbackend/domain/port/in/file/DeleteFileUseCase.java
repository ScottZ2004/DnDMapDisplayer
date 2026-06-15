package org.example.dndmapdisplayerbackend.domain.port.in.file;

public interface DeleteFileUseCase {
    void deleteFile(Long userId, String filename, String requestingUserEmail);
}
