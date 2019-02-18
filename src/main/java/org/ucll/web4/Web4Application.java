package org.ucll.web4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.ucll.web4.chat_user.ChatUser;
import org.ucll.web4.chat_user.ChatUserService;

import java.util.UUID;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Web4Application {

	public static void main(String[] args) {
		SpringApplication.run(Web4Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(ChatUserService chatUserService) {
		return (args) -> {
			ChatUser user1 = new ChatUser.Builder()
					.withFirstName("Indy")
					.withLastName("Naessens")
					.withPassword("12345678")
					.withEmail("indy.naessens@pm.me")
					.withDefaultStatus()
					.build();

			user1.setUserId(UUID.randomUUID());

			chatUserService.register(user1);
		};
	}
}
