package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.util.Password;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner runner(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));


			userService.saveUser(new User(null, "John Doe", "johndoe", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jane Doe", "janedoe", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Will Doe", "willdoe", "1234", new ArrayList<>()));

			userService.addRoleToUser("johndoe","ROLE_USER");
			userService.addRoleToUser("janedoe","ROLE_USER");
			userService.addRoleToUser("willdoe","ROLE_USER");
			userService.addRoleToUser("willdoe","ROLE_ADMIN");
		};
	}
}
