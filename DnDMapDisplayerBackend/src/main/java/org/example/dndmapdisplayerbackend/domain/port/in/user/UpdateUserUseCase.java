package org.example.dndmapdisplayerbackend.domain.port.in.user;

import org.example.dndmapdisplayerbackend.domain.model.User;

public interface UpdateUserUseCase {
    User updateUser(Long id, String name, String email, String password, String requestingUserEmail);
}
