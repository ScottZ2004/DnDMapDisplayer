package org.example.dndmapdisplayerbackend.adapters.out.persistence.user;


import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.user.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter
        implements UserRepositoryPort {

    private final UserRepository repository;

    public UserRepositoryAdapter(
            UserRepository repository) {

        this.repository = repository;
    }

    @Override
    public Boolean existsByEmail(String email) {
        UserEntity user = repository.findByEmail(email);
        return user != null;
    }

    @Override
    public User save(User user) {

        UserEntity entity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.isEmailVerified());

        UserEntity saved = repository.save(entity);

        return new User(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPassword(),
                saved.getRole(),
                saved.isEmailVerified());
    }

    @Override
    public Optional<User> findById(Long id) {

        return repository.findById(id)
                .map(entity -> new User(
                        entity.getId(),
                        entity.getName(),
                        entity.getEmail(),
                        entity.getPassword())
                ).stream().findFirst();
    }
}