package org.example.dndmapdisplayerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DnDMapDisplayerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnDMapDisplayerBackendApplication.class, args);
    }

}
