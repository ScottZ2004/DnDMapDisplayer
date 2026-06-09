package org.example.dndmapdisplayerbackend.domain.port.out.presistance.user;

import org.example.dndmapdisplayerbackend.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Boolean existsByEmail(String email);

    User save(User user);

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);

}
