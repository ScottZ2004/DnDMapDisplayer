package org.example.dndmapdisplayerbackend.domain.model;

public class User {
    private final Long id;

    private final String name;

    private final String email;

    private final String password;

    private final Role role;

    private final boolean emailVerified;

    public User(Long id, String name, String email, String password) {

        this.id = id;

        this.name = name;

        this.email = email;

        this.password = password;

        this.role = Role.USER;
        this.emailVerified = false;
    }

    public User(Long id, String name, String email, String password, Role role, boolean emailVerified) {

        this.id = id;

        this.name = name;

        this.email = email;

        this.password = password;

        this.role = role;

        this.emailVerified = emailVerified;
    }


    public Long getId() {

        return id;

    }

    public String getName() {

        return name;

    }

    public String getEmail() {

        return email;

    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }
}
