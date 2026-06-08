package org.example.dndmapdisplayerbackend.adapters.in.rest;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.CreateUserUseCase;
import org.example.dndmapdisplayerbackend.domain.port.in.GetUserUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            GetUserUseCase getUserUseCase) {

        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @PostMapping
    public UserResponse create(
            @RequestBody CreateUserRequest request) {

        User user = createUserUseCase.createUser(
                request.name(),
                request.email());

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {

        User user = getUserUseCase.getUser(id);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }
}