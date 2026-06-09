package org.example.dndmapdisplayerbackend.application.service.user;

import org.example.dndmapdisplayerbackend.config.DomainService;
import org.example.dndmapdisplayerbackend.domain.exception.EmailAlreadyExistsException;
import org.example.dndmapdisplayerbackend.domain.exception.InvalidCredentialsException;
import org.example.dndmapdisplayerbackend.domain.exception.InvalidUserDataException;
import org.example.dndmapdisplayerbackend.domain.exception.UserNotFoundException;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.user.CreateUserUseCase;
import org.example.dndmapdisplayerbackend.domain.port.in.user.GetUserUseCase;
import org.example.dndmapdisplayerbackend.domain.port.in.user.LoginUseCase;
import org.example.dndmapdisplayerbackend.domain.port.out.bcrypt.PasswordEncoderPort;
import org.example.dndmapdisplayerbackend.domain.port.out.jwt.TokenProviderPort;
import org.example.dndmapdisplayerbackend.domain.port.out.presistance.user.UserRepositoryPort;

@DomainService
public class UserService implements CreateUserUseCase, GetUserUseCase, LoginUseCase {

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

                .orElseThrow(() ->

                        new RuntimeException("User not found"));

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
}
