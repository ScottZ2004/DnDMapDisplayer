package org.example.dndmapdisplayerbackend.domain.port.in.user;

import org.example.dndmapdisplayerbackend.domain.model.Role;
import org.example.dndmapdisplayerbackend.domain.model.User;

public interface CreateUserUseCase {
    User createUser(String name, String email, String password);
}
