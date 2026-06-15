package org.example.dndmapdisplayerbackend.domain.port.in.file;

public interface GetFileUseCase {
    byte[] getFile(Long userId, String filename, String requestingUserEmail);
}
