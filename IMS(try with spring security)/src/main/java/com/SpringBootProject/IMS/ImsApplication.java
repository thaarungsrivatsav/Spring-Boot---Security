package com.SpringBootProject.IMS;

import com.SpringBootProject.IMS.entity.RoleTable;
import com.SpringBootProject.IMS.entity.UserProfileTable;
import com.SpringBootProject.IMS.repository.UserRepository;
import jdk.internal.dynalink.support.NameCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class ImsApplication {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//			}
//		};
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(ImsApplication.class, args);
		System.out.println("Hello World IMS project using Spring Boot");
	}
	@PostConstruct
	public void createUser() {
//		this.createUser("admin@inventoryapp.com", "admin_pwd", 1L);
		this.createUser("vendor@inventoryapp.com", "vendor_pwd", 2L , "vendor1");
	}

	public void createUser(String email, String password, Long roleId , String name) {
		UserProfileTable userProfileTable =  this.userRepository.findByUserEmail(email);
		if (userProfileTable != null) {
			return;
		}
		userProfileTable = new UserProfileTable();
		final RoleTable roleTable = new RoleTable();
		roleTable.setRoleId(roleId);
		userProfileTable.setUserEmail(email);
		userProfileTable.setUserName(name);
		userProfileTable.setUserPassword(this.passwordEncoder.encode(password));
		userProfileTable.setRoleTable(roleTable);
		userProfileTable.setCreatedAt(LocalDateTime.now());
		this.userRepository.save(userProfileTable);
	}

}
