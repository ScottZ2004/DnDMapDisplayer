package org.example.dndmapdisplayerbackend.domain.port.out;

import org.example.dndmapdisplayerbackend.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);

    Optional<User> findById(Long id);
}
