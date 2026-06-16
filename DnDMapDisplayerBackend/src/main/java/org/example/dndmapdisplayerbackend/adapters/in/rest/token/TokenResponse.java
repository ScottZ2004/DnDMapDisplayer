package org.example.dndmapdisplayerbackend.adapters.in.rest.token;

import org.example.dndmapdisplayerbackend.adapters.in.rest.file.ImageResponse;
import org.example.dndmapdisplayerbackend.adapters.out.persistence.image.ImageEntity;
import org.example.dndmapdisplayerbackend.domain.model.Size;

public record TokenResponse(
        Long id,
        String name,
        ImageResponse image,
        Size size,
        float gridRow,
        float gridColumn
) {
}
