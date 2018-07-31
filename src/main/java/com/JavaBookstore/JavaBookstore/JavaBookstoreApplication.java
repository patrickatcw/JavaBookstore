package com.JavaBookstore.JavaBookstore;

import com.JavaBookstore.JavaBookstore.domain.User;
import com.JavaBookstore.JavaBookstore.domain.security.Role;
import com.JavaBookstore.JavaBookstore.domain.security.UserRole;
import com.JavaBookstore.JavaBookstore.service.UserService;
import com.JavaBookstore.JavaBookstore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JavaBookstoreApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(JavaBookstoreApplication.class, args);
	}

	//regular user for testing, will see in database
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("James");
		user1.setLastName("Turner");
		user1.setUsername("j");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("t"));
		user1.setEmail("JTurner@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));

		userService.createUser(user1, userRoles);
	}
}


/*
ran from here then went to browser to localhost8080
and display was bookstore navbar example, so success
 */