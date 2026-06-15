package org.example.dndmapdisplayerbackend.application.service;

import org.example.dndmapdisplayerbackend.config.DomainService;
import org.example.dndmapdisplayerbackend.domain.exception.InvalidDataException;
import org.example.dndmapdisplayerbackend.domain.exception.file.FileNotFoundException;
import org.example.dndmapdisplayerbackend.domain.exception.user.InvalidUserDataException;
import org.example.dndmapdisplayerbackend.domain.exception.user.UnauthorizedException;
import org.example.dndmapdisplayerbackend.domain.exception.user.UserNotFoundException;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.file.GetFileUseCase;
import org.example.dndmapdisplayerbackend.domain.port.in.file.UploadFileUseCase;
import org.example.dndmapdisplayerbackend.domain.port.out.file.FileStoragePort;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.user.UserRepositoryPort;

@DomainService
public class FileService implements GetFileUseCase, UploadFileUseCase {

    FileStoragePort fileStoragePort;
    UserRepositoryPort userRepositoryPort;

    public FileService(FileStoragePort fileStoragePort, UserRepositoryPort userRepositoryPort) {
        this.fileStoragePort = fileStoragePort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public byte[] getFile(Long userId, String filename, String requestingUserEmail) {
        User user = userRepositoryPort.findById(userId).orElseThrow(UserNotFoundException::new);

        if(!user.getEmail().equals(requestingUserEmail)) {
            throw new UnauthorizedException("You can only get files for your own account");
        }

        if (!fileStoragePort.exists(userId, filename)) {
            throw new FileNotFoundException(filename);
        }

        return fileStoragePort.retrieve(userId, filename);
    }

    @Override
    public String uploadFile(Long userId, String requestingUserEmail, byte[] data, String fileName, String contentType) {
        User user = userRepositoryPort.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equals(requestingUserEmail)) {
            throw new UnauthorizedException("You can only upload files for your own account");
        }

        if (data == null || data.length == 0) {
            throw new InvalidDataException("File cannot be empty");
        }

        return fileStoragePort.store(userId, data, fileName, contentType);
    }
}
