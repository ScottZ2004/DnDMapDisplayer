package org.example.dndmapdisplayerbackend.adapters.out.persistence;


import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.out.UserRepositoryPort;

import java.util.Optional;

public class UserRepositoryAdapter
        implements UserRepositoryPort {

    private final UserRepository repository;

    public UserRepositoryAdapter(
            UserRepository repository) {

        this.repository = repository;
    }

    @Override
    public User save(User user) {

        UserEntity entity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail());

        UserEntity saved = repository.save(entity);

        return new User(
                saved.getId(),
                saved.getName(),
                saved.getEmail());
    }

    @Override
    public Optional<User> findById(Long id) {

        return repository.findById(id)
                .map(entity -> new User(
                        entity.getId(),
                        entity.getName(),
                        entity.getEmail()));
    }
}