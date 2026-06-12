package org.example.dndmapdisplayerbackend.adapters.in.rest.user;
import org.example.dndmapdisplayerbackend.adapters.in.rest.user.request.CreateUserRequest;
import org.example.dndmapdisplayerbackend.adapters.in.rest.user.request.LoginUserRequest;
import org.example.dndmapdisplayerbackend.adapters.in.rest.user.request.UpdateUserRequest;
import org.example.dndmapdisplayerbackend.adapters.in.rest.user.response.UserResponse;
import org.example.dndmapdisplayerbackend.domain.model.User;
import org.example.dndmapdisplayerbackend.domain.port.in.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final LoginUseCase loginUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            GetUserUseCase getUserUseCase, LoginUseCase loginUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase) {

        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.loginUseCase = loginUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping("/register")
    public UserResponse create(
            @RequestBody CreateUserRequest request) {

        User user = createUserUseCase.createUser(
                request.name(),
                request.email(),
                request.password());

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginUserRequest request) {
        String token = loginUseCase.Login(request.email(), request.password());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {

        User user = getUserUseCase.getUser(id);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id,
                               @RequestBody UpdateUserRequest request,
                               @AuthenticationPrincipal String email) {
        User updated = updateUserUseCase.updateUser(id, request.name(), request.email(), request.password(), email);
        return new UserResponse(
                updated.getId(),
                updated.getName(),
                updated.getEmail());
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@AuthenticationPrincipal String email) {
        deleteUserUseCase.deleteUser(email);
        return ResponseEntity.ok("User deleted successfully");
    }

}