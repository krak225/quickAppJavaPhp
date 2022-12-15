package net.krak.grh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.krak.grh.entities.Role;
import net.krak.grh.entities.User;
import net.krak.grh.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class QuickAppJavaApplication{
	
	@Autowired
	private Environment env;
	
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
			
			
			System.out.println("Server listening on port: " + env.getProperty("server.port"));
		
		};		
		
	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
