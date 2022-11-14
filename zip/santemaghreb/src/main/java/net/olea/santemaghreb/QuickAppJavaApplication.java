package net.olea.santemaghreb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.olea.santemaghreb.entities.Role;
import net.olea.santemaghreb.entities.User;
import net.olea.santemaghreb.services.AccountService;

import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class QuickAppJavaApplication{

	public static void main(String[] args) {
		SpringApplication.run(QuickAppJavaApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner start(AccountService accountService) {
		
		return args -> {
			
			//créer un utilisateur admin qui pourra se connecter au lancement de l'application
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword("12345678");
			
			accountService.AddUser(admin);

			Role roleadmin = new Role();
			roleadmin.setRoleName("ADMIN");

			accountService.AddRole(roleadmin);
			
			accountService.AddRoleToUser(admin.getUsername(), roleadmin.getRoleName());
			
			
			System.out.println("Server listening on port: 7000");
		
		};		
		
	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
