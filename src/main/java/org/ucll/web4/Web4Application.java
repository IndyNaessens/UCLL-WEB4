package org.ucll.web4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.ucll.web4.user.UserEntity;
import org.ucll.web4.user.UserService;

import java.util.UUID;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Web4Application {

	public static void main(String[] args) {
		SpringApplication.run(Web4Application.class, args);
	}
}
