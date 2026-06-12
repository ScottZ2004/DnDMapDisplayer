package org.example.dndmapdisplayerbackend.application.service;

import org.example.dndmapdisplayerbackend.config.DomainService;
import org.example.dndmapdisplayerbackend.domain.exception.user.EmailAlreadyExistsException;
import org.example.dndmapdisplayerbackend.domain.exception.user.InvalidCredentialsException;
import org.example.dndmapdisplayerbackend.domain.exception.user.InvalidUserDataException;
import org.example.dndmapdisplayerbackend.domain.exception.user.UserNotFoundException;
import org.example.dndmapdisplayerbackend.domain.exception.user.UnauthorizedException;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.user.*;
import org.example.dndmapdisplayerbackend.domain.port.out.bcrypt.PasswordEncoderPort;
import org.example.dndmapdisplayerbackend.domain.port.out.jwt.TokenProviderPort;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.user.UserRepositoryPort;

@DomainService
public class UserService implements CreateUserUseCase, GetUserUseCase, LoginUseCase, UpdateUserUseCase, DeleteUserUseCase {

    private final PasswordEncoderPort passwordEncoder;
    private final UserRepositoryPort userRepository;
    private final TokenProviderPort tokenProvider;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public UserService(PasswordEncoderPort passwordEncoder, UserRepositoryPort userRepository, TokenProviderPort tokenProvider) {
        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;

        this.tokenProvider = tokenProvider;
    }

    @Override
    public User createUser(String name, String email, String password) {
        if (name == null || name.isBlank()) {
            throw new InvalidUserDataException("Name cannot be null or blank");
        }

        if (email == null || email.isBlank()) {
            throw new InvalidUserDataException("Name cannot be null or blank");
        }

        if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidUserDataException("Invalid email format");
        }

        if (password == null || password.isBlank()) {
            throw new InvalidUserDataException("Password cannot be null or blank");
        }

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(

                null,

                name,

                email,

                hashedPassword

        );

        return userRepository.save(user);

    }

    @Override

    public User getUser(Long id) {

        return userRepository.findById(id)

                .orElseThrow(UserNotFoundException::new);

    }

    @Override
    public String Login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        return tokenProvider.generateToken(user);
    }

    @Override
    public User updateUser(Long id, String name, String email, String password, String requestingUserEmail) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equals(requestingUserEmail)) {
            throw new UnauthorizedException("You can only update your own account");
        }

        if (name != null && !name.isBlank()) {
            user = new User(user.getId(), name, user.getEmail(), user.getPassword(), user.getRole(), user.isEmailVerified());
        }

        if (email != null && !email.isBlank()) {
            if (!email.matches(EMAIL_REGEX)) {
                throw new InvalidUserDataException("Invalid email format");
            }
            if (userRepository.existsByEmail(email)) {
                throw new EmailAlreadyExistsException();
            }
            user = new User(user.getId(), user.getName(), email, user.getPassword(), user.getRole(), user.isEmailVerified());
        }

        if (password != null && !password.isBlank()) {
            String hashed = passwordEncoder.encode(password);
            user = new User(user.getId(), user.getName(), user.getEmail(), hashed, user.getRole(), user.isEmailVerified());
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equals(email)) {
            throw new UnauthorizedException("You can only delete your own account");
        }

        userRepository.deleteById(user.getId());
    }
}
