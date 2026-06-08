package org.example.dndmapdisplayerbackend.domain.port.in;

import org.example.dndmapdisplayerbackend.domain.model.User;

public interface GetUserUseCase {
    User getUser(Long id);

}
