package org.ucll.web4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ucll.web4.entity.UserEntity;
import org.ucll.web4.repository.user.UserRepository;

import java.util.UUID;


@SpringBootApplication
public class Web4Application {

    public static void main(String[] args) {
        SpringApplication.run(Web4Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(@Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        return (args) -> {
            UserEntity user1 = new UserEntity.Builder()
                    .withId(UUID.fromString("14ed4726-243c-4dc6-a352-15d658f2ce31"))
                    .withFirstName("Indy")
                    .withLastName("Naessens")
                    .withPassword(passwordEncoder.encode("123indy"))
                    .withEmail("indy.naessens@pm.me")
                    .withDefaultStatus()
                    .build();

            UserEntity user2 = new UserEntity.Builder()
                    .withId(UUID.fromString("408e3eab-dcfd-4620-8c6a-e252a19bbe1c"))
                    .withFirstName("Wesly")
                    .withLastName("Naessens")
                    .withPassword(passwordEncoder.encode("123wesly"))
                    .withEmail("wesly@naessens.net")
                    .withDefaultStatus()
                    .build();

            UserEntity user3 = new UserEntity.Builder()
                    .withRandomId()
                    .withFirstName("Keany")
                    .withLastName("Naessens")
                    .withPassword(passwordEncoder.encode("123keany"))
                    .withEmail("keany@naessens.net")
                    .withDefaultStatus()
                    .build();

            userRepository.create(user1, user1.getUserId());
            userRepository.create(user2, user2.getUserId());
            userRepository.create(user3, user3.getUserId());
        };
    }
}
