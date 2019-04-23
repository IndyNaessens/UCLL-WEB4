package org.ucll.web4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ucll.web4.entity.UserEntity;
import org.ucll.web4.repository.friendlist.UserFriendListRepository;
import org.ucll.web4.repository.user.UserRepository;

import java.util.UUID;


@SpringBootApplication
public class Web4Application {

    public static void main(String[] args) {
        SpringApplication.run(Web4Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(@Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder, @Autowired UserFriendListRepository userFriendListRepository) {
        return (args) -> {
            UserEntity user1 = new UserEntity.Builder()
                    .withId(UUID.fromString("14ed4726-243c-4dc6-a352-15d658f2ce31"))
                    .withFirstName("Indy")
                    .withLastName("Naessens")
                    .withPassword(passwordEncoder.encode("123indy"))
                    .withEmail("indy@naessens.net")
                    .withDefaultStatus()
                    .isMale()
                    .hasAge(21)
                    .build();

            UserEntity user2 = new UserEntity.Builder()
                    .withId(UUID.fromString("408e3eab-dcfd-4620-8c6a-e252a19bbe1c"))
                    .withFirstName("Wesly")
                    .withLastName("Naessens")
                    .withPassword(passwordEncoder.encode("123wesly"))
                    .withEmail("wesly@naessens.net")
                    .withDefaultStatus()
                    .isMale()
                    .hasAge(20)
                    .build();

            UserEntity user3 = new UserEntity.Builder()
                    .withRandomId()
                    .withFirstName("Keany")
                    .withLastName("Naessens")
                    .withPassword(passwordEncoder.encode("123keany"))
                    .withEmail("keany@naessens.net")
                    .withDefaultStatus()
                    .isMale()
                    .hasAge(18)
                    .build();

            UserEntity user4 = new UserEntity.Builder()
                    .withRandomId()
                    .withFirstName("Hanne")
                    .withLastName("Boonen")
                    .withPassword(passwordEncoder.encode("123hanne"))
                    .withEmail("hanne@boonen.net")
                    .withDefaultStatus()
                    .isFemale()
                    .hasAge(21)
                    .build();

            userRepository.create(user1, user1.getUserId());
            userRepository.create(user2, user2.getUserId());
            userRepository.create(user3, user3.getUserId());
            userRepository.create(user4, user4.getUserId());
        };
    }
}
