package com.itemsharing.userservice;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.service.UserService;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class UserserviceApplication implements CommandLineRunner{
	@Autowired
	private UserService userService;
	
	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}
	
	public static void main (String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Prafulla");
		user1.setLastName("Raut");
		user1.setUsername("prafulla");
		user1.setPassword("password");
		user1.setEmail("prafulla@gmail.com");
		
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1);
	}
}
