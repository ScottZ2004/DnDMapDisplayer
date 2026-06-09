package org.example.dndmapdisplayerbackend.domain.port.out.jwt;

import org.example.dndmapdisplayerbackend.domain.model.User;

public interface TokenProviderPort {
    String generateToken(User user);
    String extractEmail(String token);
    boolean isTokenValid(String token);
}
