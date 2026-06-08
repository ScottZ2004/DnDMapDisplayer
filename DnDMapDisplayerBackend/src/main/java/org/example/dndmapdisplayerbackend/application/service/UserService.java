package org.example.dndmapdisplayerbackend.application.service;

import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.CreateUserUseCase;
import org.example.dndmapdisplayerbackend.domain.port.in.GetUserUseCase;
import org.example.dndmapdisplayerbackend.domain.port.out.UserRepositoryPort;

public class UserService implements CreateUserUseCase, GetUserUseCase {

    private final UserRepositoryPort userRepository;

    public UserService(UserRepositoryPort userRepository) {

        this.userRepository = userRepository;

    }

    @Override

    public User createUser(String name, String email) {

        User user = new User(

                null,

                name,

                email

        );

        return userRepository.save(user);

    }

    @Override

    public User getUser(Long id) {

        return userRepository.findById(id)

                .orElseThrow(() ->

                        new RuntimeException("User not found"));

    }

}
