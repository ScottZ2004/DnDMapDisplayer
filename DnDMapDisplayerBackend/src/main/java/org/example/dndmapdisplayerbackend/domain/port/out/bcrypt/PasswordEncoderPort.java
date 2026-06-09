package org.example.dndmapdisplayerbackend.domain.port.out.bcrypt;

public interface PasswordEncoderPort {
    String encode(String password);
    boolean matches(String password, String encodedPassword);
}
