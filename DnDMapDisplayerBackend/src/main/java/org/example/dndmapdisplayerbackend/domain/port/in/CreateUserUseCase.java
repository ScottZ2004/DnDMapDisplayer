package org.example.dndmapdisplayerbackend.domain.port.in;

import org.example.dndmapdisplayerbackend.domain.model.User;

public interface CreateUserUseCase {
    User createUser(String name, String email);
}
