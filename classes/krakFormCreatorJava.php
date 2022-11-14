<?php
 class krakFormCreatorJava{
	public $pn;
	public $db;
	public $table;
	public $outpath;
	public $dbhost;
	public $dbh;
	public $dbu;
	public $dbp;
	public $base_url;
	
	public function __construct(){
		$this->auteur="{'name':'Armand Kouassi', 'email':'krak225@gmail.com', 'telephone':'+225 04 78 36 89'}";
		$this->pn='com.krak.quickapp';//package name
		
		$this->outpath=$this->db.'/src/main/java/'.str_replace('.','/',$this->pn);
		// $this->outpath=$this->db.'/';
		$this->dbhost='localhost';
		$this->dbu='root';
		$this->dbp='';
		$this->base_url='';
	}
	
 	public function connectDB($db,$h='localhost', $u='root',$p=''){
		$this->db=$db;
		$this->dbhost=$h;
		$this->dbu=$u;
		$this->dbp=$p;
		
		$pdo = new PDO('mysql:host='.$this->dbhost.';dbname='.$db, $this->dbu, $this->dbp);
		$pdo->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_WARNING);
		
		$this->dbh = $pdo;
		
	}

 	public function setDB($db){
		// $this->db=$db;
		$this->connectDB();
	}

	public function setPath($outpath){
		$this->outpath=$outpath.'/src/main/java/'.str_replace('.','/',$this->pn);
	}
	
	public function setPackageName($pn){
		$this->pn = $pn;
	}
	
	public function createAllForms(){
		
		$this->createCoreFiles();
		
		$db=$this->db;$cpt=0;$err=0;
		
		
		$dbreq= 'SHOW TABLES';
		$stm = $this->dbh->query($dbreq);
        $tables = $stm->fetchAll(PDO::FETCH_COLUMN);
		
		// debug($tables);
		
		foreach($tables as $tablename){
			
			$outpath=$this->outpath;
			if($this->createFormulaire($tablename,$this->outpath)){
				$cpt++;
			}else{$err++;}

		}
		

		// print 'wcwc'.$cpt;
		if($err==0){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	private function createCoreFiles(){
		
		$tab = explode('.', $this->pn);
		
		$groupId = $tab[0].'.'.$tab[1];
		$artifactId = $tab[2];
		
		$base_java = $artifactId.'/src/main/java';
		
		@mkdir($artifactId);
		@mkdir($artifactId.'/src');
		
		@mkdir($artifactId.'/src/main');
		@mkdir($artifactId.'/src/main/java');
		@mkdir($artifactId.'/src/main/resources');
		
		@mkdir($artifactId.'/src/main/java/'.$tab[0]);
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1]);
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2]);
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/filters');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/controllers');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/entities');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/repositories');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/services');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/playload');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/playload/requests');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/playload/responses');
		@mkdir($artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2].'/utils');
		
		// die();
		
		$base_url = $artifactId.'/src/main/java/'.$tab[0].'/'.$tab[1].'/'.$tab[2];
		$this->base_url = $base_url;
		
		//
		$pom = '<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>'.$groupId.'</groupId>
	<artifactId>'.$artifactId.'</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>'.ucfirst($artifactId).'</name>
	<description>Java Application by quickApp</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
		    <groupId>com.auth0</groupId>
		    <artifactId>java-jwt</artifactId>
		    <version>4.2.1</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
';
		
		$file_pom=$artifactId.'/pom.xml';
		
		$fp=fopen($file_pom, 'w+');
		fputs($fp, $pom);
		fclose($fp);
		
		
		//
		$application_properties = 'spring.datasource.url=jdbc:mysql://'.$this->dbhost.':3306/'.$this->db.'
spring.datasource.username='.$this->dbu.'
spring.datasource.password='.$this->dbp.'
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true
server.port=7000';

		$file_application_properties=$artifactId.'/src/main/resources/application.properties';
		
		$fp=fopen($file_application_properties, 'w+');
		fputs($fp, $application_properties);
		fclose($fp);
		
		
		//
		$QuickAppJavaApplication = 'package '.$this->pn.';

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import '.$this->pn.'.entities.Role;
import '.$this->pn.'.entities.User;
import '.$this->pn.'.services.AccountService;

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
			
			//créer un utilisateur admin qui pourra se connecter au lancement de l\'application
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
';
		
		$file_QuickAppJavaApplication=$base_url.'/QuickAppJavaApplication.java';
		
		$fp=fopen($file_QuickAppJavaApplication, 'w+');
		fputs($fp, $QuickAppJavaApplication);
		fclose($fp);
		
		
		//
		$SecurityConfig = "package ".$this->pn.";\n\n";

		$SecurityConfig.='import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import '.$this->pn.'.filters.JwtAuthenticationFilter;
import '.$this->pn.'.filters.JwtAutorizationFilter;
import '.$this->pn.'.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsServiceImpl userDetailsService;
	
	
	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		//http.formLogin();
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.authorizeRequests().antMatchers("/login/**", "/api/auth/refreshToken/**").permitAll();
	    http.authorizeRequests().antMatchers("/api/users/add/**", "/api/addroletouser/**","/api/roles/add/**").permitAll();
	    //http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/add/**").hasAuthority("ADMIN");
	    http.authorizeRequests().anyRequest().authenticated();
	    
	    http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
	    http.addFilterBefore(new JwtAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
}
';
		
		$file_SecurityConfig=$base_url.'/SecurityConfig.java';
		
		$fp=fopen($file_SecurityConfig, 'w+');
		fputs($fp, $SecurityConfig);
		fclose($fp);
		
		
		//
		$JwtUtils = 'package '.$this->pn.';

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import '.$this->pn.'.filters.JwtAuthenticationFilter;
import '.$this->pn.'.filters.JwtAutorizationFilter;
import '.$this->pn.'.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsServiceImpl userDetailsService;
	
	
	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		//http.formLogin();
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.authorizeRequests().antMatchers("/login/**", "/api/auth/refreshToken/**").permitAll();
	    http.authorizeRequests().antMatchers("/api/users/add/**", "/api/addroletouser/**","/api/roles/add/**").permitAll();
	    //http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/add/**").hasAuthority("ADMIN");
	    http.authorizeRequests().anyRequest().authenticated();
	    
	    http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
	    http.addFilterBefore(new JwtAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
}';
		
		$file_JwtUtils=$base_url.'/utils/JwtUtils.java';
		
		$fp=fopen($file_JwtUtils, 'w+');
		fputs($fp, $JwtUtils);
		fclose($fp);
		
		
		
		//
		$AccountService='package '.$this->pn.'.services;

import java.util.List;
import java.util.Optional;

import '.$this->pn.'.entities.Role;
import '.$this->pn.'.entities.User;


public interface AccountService{
	User AddUser(User user);
	Role AddRole(Role role);
	void AddRoleToUser(String username, String roleName);
	User loadUserByUsername(String username);
	List<User> listUsers();
	List<Role> listRoles();
	Optional<User> getUser(Long id);
	
}

';
		
		$file_AccountService=$base_url.'/services/AccountService.java';
		
		$fp=fopen($file_AccountService, 'w+');
		fputs($fp, $AccountService);
		fclose($fp);
		
		
		//
		$AccountServiceImpl='package '.$this->pn.'.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import '.$this->pn.'.entities.Role;
import '.$this->pn.'.entities.User;
import '.$this->pn.'.repositories.RoleRepository;
import '.$this->pn.'.repositories.UserRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User AddUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	@Override
	public Role AddRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void AddRoleToUser(String username, String roleName) {
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByRoleName(roleName);
		
		user.getRoles().add(role);
	}

	@Override
	public User loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<Role> listRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	
}
';
		
		$file_AccountServiceImpl=$base_url.'/services/AccountServiceImpl.java';
		
		$fp=fopen($file_AccountServiceImpl, 'w+');
		fputs($fp, $AccountServiceImpl);
		fclose($fp);
		
		
		//
		$UserDetailsServiceImpl='package '.$this->pn.'.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import '.$this->pn.'.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private AccountService accountService;
	
	
	public UserDetailsServiceImpl(AccountService accountService) {
		super();
		this.accountService = accountService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = accountService.loadUserByUsername(username);
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		System.out.println(authorities.toString());
		
		//appel direct vu que pas d\'alias d\'import en java et que notre Entity User a été déjà importé
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		
	}

}
';
		
		$file_UserDetailsServiceImpl=$base_url.'/services/UserDetailsServiceImpl.java';
		
		$fp=fopen($file_UserDetailsServiceImpl, 'w+');
		fputs($fp, $UserDetailsServiceImpl);
		fclose($fp);
		
		
		
		
		//
		$UserRepository='package '.$this->pn.'.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import '.$this->pn.'.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
';
		
		$file_UserRepository=$base_url.'/repositories/UserRepository.java';
		
		$fp=fopen($file_UserRepository, 'w+');
		fputs($fp, $UserRepository);
		fclose($fp);
		
		
		
		
		//
		$RoleRepository='package '.$this->pn.'.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import '.$this->pn.'.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
';
		
		$file_RoleRepository=$base_url.'/repositories/RoleRepository.java';
		
		$fp=fopen($file_RoleRepository, 'w+');
		fputs($fp, $RoleRepository);
		fclose($fp);
		
		
		
		
		//
		$RoleUserForm='package '.$this->pn.'.playload.requests;

import lombok.Data;

@Data
public class RoleUserForm {
	public String username;
	public String roleName;
}
';
		
		$file_RoleUserForm=$base_url.'/playload/requests/RoleUserForm.java';
		
		$fp=fopen($file_RoleUserForm, 'w+');
		fputs($fp, $RoleUserForm);
		fclose($fp);
		
		
		
		//
		$JwtAuthenticationFilter='package '.$this->pn.'.filters;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import '.$this->pn.'.utils.JwtUtils;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;

	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authenticationToken);
		
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		System.out.println("successfulAuthentication");
		
		User user = (User) authResult.getPrincipal();
		
		Date AccessTokenExpireAt = new Date(System.currentTimeMillis() + JwtUtils.EXPIRE_ACCESS_TOKEN);
		Date RefreshTokenExpireAt = new Date(System.currentTimeMillis() + JwtUtils.EXPIRE_REFRESH_TOKEN);
		
		String jwtAccessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(AccessTokenExpireAt)
				.withClaim("roles", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
				.withIssuer(request.getRequestURL().toString())
				.sign(JwtUtils.ALGORITHM)
				;

		String jwtRefreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(RefreshTokenExpireAt)
				.withIssuer(request.getRequestURL().toString())
				.sign(JwtUtils.ALGORITHM)
				;
		
		Map<String, String> idTokens = new HashMap<>();
		idTokens.put("refresh-token", jwtRefreshToken);
		idTokens.put("access-token", jwtAccessToken);
		idTokens.put("access-token-expireat", AccessTokenExpireAt.toString());
		idTokens.put("refresh-token-expireat", RefreshTokenExpireAt.toString());
		
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), idTokens);
		
		
	}
	
	
}';
		
		$file_JwtAuthenticationFilter=$base_url.'/filters/JwtAuthenticationFilter.java';
		
		$fp=fopen($file_JwtAuthenticationFilter, 'w+');
		fputs($fp, $JwtAuthenticationFilter);
		fclose($fp);
		
		
		
		
		//
		$JwtAutorizationFilter='package '.$this->pn.'.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;

import '.$this->pn.'.utils.JwtUtils;

public class JwtAutorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("JwtAutorizationFilter::doFilterInternal");
		
		if(request.getServletPath().equals("/api/auth/refreshToken")) {
			
			filterChain.doFilter(request, response);
			
		}else {
			
			String authorizationToken = request.getHeader(JwtUtils.AUTH_HEADER);

			if(authorizationToken != null && authorizationToken.startsWith(JwtUtils.PREFIX)) {
				
				try {
					String jwt = authorizationToken.substring(JwtUtils.PREFIX.length());
					JWTVerifier jwtVerifier = JWT.require(JwtUtils.ALGORITHM).build();
					
					DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
					String username = decodedJWT.getSubject();
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					Collection<GrantedAuthority> authorities = new ArrayList<>();
					for(String r:roles) {
						authorities.add(new SimpleGrantedAuthority(r));
					}
					
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
					
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					
					filterChain.doFilter(request, response);
					
				}catch(Exception e) {
					
					response.setHeader("error-message", e.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				}
				
			}else {
				filterChain.doFilter(request, response);
			}
			
		}
		
	}
	
	
}';
		
		$file_JwtAutorizationFilter=$base_url.'/filters/JwtAutorizationFilter.java';
		
		$fp=fopen($file_JwtAutorizationFilter, 'w+');
		fputs($fp, $JwtAutorizationFilter);
		fclose($fp);
		
		
		
		//
		$User='package '.$this->pn.'.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class User {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String username;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> Roles = new ArrayList<>();
	
}
';
		
		$file_User=$base_url.'/entities/User.java';
		
		$fp=fopen($file_User, 'w+');
		fputs($fp, $User);
		fclose($fp);
		
		
		
		
		//
		$Role='package '.$this->pn.'.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Role {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String roleName;
	
}';
		
		$file_Role=$base_url.'/entities/Role.java';
		
		$fp=fopen($file_Role, 'w+');
		fputs($fp, $Role);
		fclose($fp);
		
		
		
		//
		$AccountController='package '.$this->pn.'.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import '.$this->pn.'.entities.Role;
import '.$this->pn.'.entities.User;
import '.$this->pn.'.playload.requests.RoleUserForm;
import '.$this->pn.'.services.AccountService;


@RestController
@RequestMapping(path="/api/")
public class AccountController {
		
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	private AccountService accountService;

	@GetMapping(path="/users")
	public List<User> users(){
		
		return accountService.listUsers();
		
	}
	
	@GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
		
        return accountService.getUser(id);
        
    }

	@PostMapping(path="/users/add")
	@PostAuthorize("hasAuthority(\'ADMIN\')")
	public User saveUser(@RequestBody User user){
		
		return accountService.AddUser(user);
	}


	@GetMapping(path="/roles")
	public List<Role> roles(){
		
		return accountService.listRoles();
		
	}
	
	@PostMapping(path="/roles/add")
	public Role saveRole(@RequestBody Role role){
		
		return accountService.AddRole(role);
		
	}

	@PostMapping(path="/addroletouser")
	public void AddRoleToUser(@RequestBody RoleUserForm roleUserForm){
		
		accountService.AddRoleToUser(roleUserForm.username, roleUserForm.roleName);
		
	}

	@GetMapping(path="/profile")
	public User profile(Principal principal){
		
		return accountService.loadUserByUsername(principal.getName());
		
	}
	
}


';
		
		$file_AccountController=$base_url.'/controllers/AccountController.java';
		
		$fp=fopen($file_AccountController, 'w+');
		fputs($fp, $AccountController);
		fclose($fp);
		
		
		//
		$AuthController='package '.$this->pn.'.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import '.$this->pn.'.entities.User;
import '.$this->pn.'.services.AccountService;
import '.$this->pn.'.utils.JwtUtils;


@RestController
@RequestMapping(path="/api/auth/")
public class AuthController {
		
	public AuthController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	private AccountService accountService;

	
	@GetMapping(path="/refreshToken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String authorizationToken = request.getHeader(JwtUtils.AUTH_HEADER);
		
		if(authorizationToken != null && authorizationToken.startsWith(JwtUtils.PREFIX)) {
			try {
				String jwt = authorizationToken.substring(JwtUtils.PREFIX.length());
				JWTVerifier jwtVerifier = JWT.require(JwtUtils.ALGORITHM).build();
				
				DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
				String username = decodedJWT.getSubject();
				
				User user = accountService.loadUserByUsername(username);
				
				Date AccessTokenExpireAt = new Date(System.currentTimeMillis() + JwtUtils.EXPIRE_ACCESS_TOKEN);
				
				String jwtAccessToken = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(AccessTokenExpireAt)
						.withClaim("roles", user.getRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
						.withIssuer(request.getRequestURL().toString())
						.sign(JwtUtils.ALGORITHM)
						;

				
				Map<String, String> idTokens = new HashMap<>();
				idTokens.put("refresh-token", jwt);
				idTokens.put("access-token", jwtAccessToken);
				idTokens.put("access-token-expireat", AccessTokenExpireAt.toString());
				
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), idTokens);
				
				
			}catch(Exception e) {
				
				System.out.println("Can\'t refresh token: " + e.getMessage());
				
				throw e;
			}
		}else {
			throw new RuntimeException ("Refresh token is required");			
		}
		
	}
	
	
}
';
		
		$file_AuthController=$base_url.'/controllers/AuthController.java';
		
		$fp=fopen($file_AuthController, 'w+');
		fputs($fp, $AuthController);
		fclose($fp);
		
		
		
		//
		$QuickAppJavaApplicationTests='package '.$this->pn.';

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuickAppJavaApplicationTests {

	@Test
	void contextLoads() {
	}

}
';
		
		$file_QuickAppJavaApplicationTests=$base_url.'/test/QuickAppJavaApplicationTests.java';
		
		// $fp=fopen($file_QuickAppJavaApplicationTests, 'w+');
		// fputs($fp, $QuickAppJavaApplicationTests);
		// fclose($fp);
		
		
		
		
		
		
		
	}
	
	private function createFormulaire($table,$outpath){
		
		$sql='DESCRIBE '.$table;
		$stm = $this->dbh->query($sql);
        $table_fields = $stm->fetchAll(PDO::FETCH_COLUMN);
		
		// debug($table_fields);
		
		
	////
	//le model
	$model="package ".$this->pn.".entities;\n\n";
	
	$model.="import javax.persistence.Entity\n";
	$model.="import javax.persistence.GeneratedValue\n";
	$model.="import javax.persistence.GenerationType\n";
	$model.="import javax.persistence.Id\n";
	$model.="import lombok.AllArgsConstructor\n";
	$model.="import lombok.Data\n";
	$model.="import lombok.NoArgsConstructor\n\n";
	
	$model.="@Entity\n";
	$model.="@Data\n";
	$model.="@NoArgsConstructor\n";
	$model.="@AllArgsConstructor\n";
	$model.="public class ".ucfirst($table)." {\n\n";
	
	$model.="\t@Id\n";
	$model.="\t@GeneratedValue(strategy=GenerationType.IDENTITY)\n";
	$model.="\tprivate Long id;\n";
	
	$i=0;
	foreach($table_fields as $field_name){

		if($i>0 and (strtolower($field_name)!=$table."_statut")){//PERMET DE NE PAS PRENDRE EN COMPTE L'ID DE LA TABLE
			//test s'il s'agit du clé parent qui a migré alors utilisation d'un select
			$tab=explode('_',$field_name);
			$x=sizeof($tab);
			//si c'est un table parent qui a migré
			if($tab[$x-1]=='id'){
				$table_parent=str_replace('_id',null,$field_name);
				$model.= "\t".'@OneToMany(mappedBy='.$table_parent.')'."\n";
				$model.= "\t".'private String '.$table_parent.'"'."\n";
				
			}else{//si pas clé parente
				$field_name=str_replace($table.'_',null,$field_name);
				// if($d->type=='int'){
					// $model.= "\t".'private int '.$field_name.';'."\n";
				// }else{
					$model.= "\t".'private String '.$field_name.';'."\n";
				// }
			}
			
		}
		$i++;
	}
	
	$model.='}'."\n";
	//fin de la page model
	
	
	//le repository
	$repository="package ".$this->pn.".repositories;\n\n";
	
	$repository.="import org.springframework.data.jpa.repository.JpaRepository;\n";
	$repository.="import ".$this->pn.".entities.".ucfirst($table).";\n\n";
	
	$repository.="public interface ".ucfirst($table)."Repository extends JpaRepository<".ucfirst($table).", Long> {\n\n";
	$repository.="}\n";
	//fin de la page repository
	
	
	
	//le service
	$service="package ".$this->pn.".services;\n\n";
	$service.="import java.util.List;\n";
	$service.="import java.util.Optional;\n\n";
	$service.="import ".$this->pn.".entities.".ucfirst($table).";\n\n";
	
	$service.="public interface ".ucfirst($table)."Service {\n\n";
	
	$service.="\tList<".ucfirst($table)."> get".ucfirst($table)."s();\n";
	$service.="\tOptional ".ucfirst($table)." get".ucfirst($table)."(Long id);\n";
	
	$service.="\t".ucfirst($table)." Add".ucfirst($table)."(".ucfirst($table)." ".strtolower($table).");\n";
	
	$service.="\t".ucfirst($table)." Edit".ucfirst($table)."(Long id, ".ucfirst($table)." ".strtolower($table).");\n";
	
	$service.="\tvoid ".ucfirst($table)." delete".ucfirst($table)."(Long id);\n\n";
	
	$service.="}\n";
	//fin de la page service
	
	
	//le serviceImpl
	$serviceImpl="package ".$this->pn.".services;\n\n";
	
	$serviceImpl.="import javax.transaction.Transactional;\n";
	$serviceImpl.="import org.springframework.stereotype.Service;\n\n";

	$serviceImpl.="import java.util.List;\n";
	$serviceImpl.="import java.util.Optional;\n\n";
	
	$serviceImpl.="import ".$this->pn.".entities.".ucfirst($table).";\n\n";
	$serviceImpl.="import ".$this->pn.".entities.".ucfirst($table).";\n\n";
	$serviceImpl.="import ".$this->pn.".repositories.".ucfirst($table)."Repository;\n\n";
	

	$serviceImpl.="@Service\n";
	$serviceImpl.="@Transactional\n";
	$serviceImpl.="public class ".ucfirst($table)."ServiceImpl implements ".ucfirst($table)."Service {\n\n";
	
	$repositoryName = strtolower($table).'Repository';
	
	$serviceImpl.="private ".ucfirst($table)."Repository ".$repositoryName.";\n\n";
	
	$serviceImpl.="public ".ucfirst($table)."ServiceImpl(".ucfirst($table)."Repository ".$repositoryName.") {\n";
	$serviceImpl.="\tthis.".$repositoryName." = ".$repositoryName.";\n";
	$serviceImpl.="}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public List<'.ucfirst($table).'> get'.ucfirst($table)."s(){
	return $repositoryName.findAll();\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public Optionnal<'.ucfirst($table).'> get'.ucfirst($table)."(Long id){
	return $repositoryName.findById(id);\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public '.ucfirst($table).' Add'.ucfirst($table).'('.ucfirst($table).' '.strtolower($table)."){
	return $repositoryName.save(".strtolower($table).");\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public '.ucfirst($table).' Edit'.ucfirst($table).'(Long id, '.ucfirst($table).' '.strtolower($table)."){
	return $repositoryName.save(".strtolower($table).");\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public '.ucfirst($table).' delete'.ucfirst($table)."(Long id){
	return $repositoryName.delete(id);\n}\n\n";
	
	$serviceImpl.='}'."\n";
	//fin de la page serviceImpl
	
	
	
	//le controller
	$controller="package ".$this->pn.".controllers;\n\n";
	$controller.="import java.security.Principal;\n";
	$controller.="import java.util.List;\n";
	$controller.="import java.util.Optional;\n\n";

	$controller.="import org.springframework.security.access.prepost.PostAuthorize;\n";
	$controller.="import org.springframework.web.bind.annotation.GetMapping;\n";
	$controller.="import org.springframework.web.bind.annotation.PathVariable;\n";
	$controller.="import org.springframework.web.bind.annotation.PostMapping;\n";
	$controller.="import org.springframework.web.bind.annotation.RequestBody;\n";
	$controller.="import org.springframework.web.bind.annotation.RequestMapping;\n";
	$controller.="import org.springframework.web.bind.annotation.RestController;\n\n";

	$controller.="import ".$this->pn.".entities.".ucfirst($table).";\n";
	$controller.="import ".$this->pn.".services.".ucfirst($table)."Service;\n\n";
	
	$controller.="@RestController\n";
	$controller.="@RequestMapping(path=\"/api/\")\n";
	$controller.="public class ".ucfirst($table)."Controller {\n\n";
	
	$serviceName = strtolower($table).'Service';
	
	//$controller.="@Autowired;\n";
	$controller.="public ".ucfirst($table)."Service ".$serviceName.";\n\n";
	
	$controller.="public ".ucfirst($table)."Controller(".ucfirst($table)."Service ".$serviceName.") {\n";
	$controller.="\tthis.".$serviceName." = ".$serviceName.";\n";
	$controller.="}\n\n";
	
	$controller.='@GetMapping(path="/'.strtolower($table).'s")'."\n";
	$controller.='public List<'.ucfirst($table).'> get'.ucfirst($table)."s(){
	return $serviceName.findAll();\n}\n\n";
	
	$controller.='@GetMapping(path="/'.strtolower($table).'/{id}")'."\n";
	$controller.='public Optionnal<'.ucfirst($table).'> get'.ucfirst($table)."(@PathVariable Long id){
	return $serviceName.findById(id);\n}\n\n";
	
	$controller.='@PostMapping(path="/'.strtolower($table).'")'."\n";
	$controller.='public '.ucfirst($table).' Add'.ucfirst($table).'('.ucfirst($table).' '.strtolower($table)."){
	return $serviceName.save(".strtolower($table).");\n}\n\n";
	
	$controller.='@PutMapping(path="/'.strtolower($table).'/{id}")'."\n";
	$controller.='public '.ucfirst($table).' Edit'.ucfirst($table).'(@PathVariable Long id, '.ucfirst($table).' '.strtolower($table)."){
	return $serviceName.save(".strtolower($table).");\n}\n\n";
	
	$controller.='@DeleteMapping(path="/'.strtolower($table).'/{id}")'."\n";
	$controller.='public '.ucfirst($table).' Delete'.ucfirst($table)."(@PathVariable Long id){
	return $serviceName.delete(id);\n}\n\n";
	
	$controller.="}\n";
	//fin de la page controller
	
	
	/*
	$i=0;
	foreach($table_fields as $field_name){

		if($i>0 and (strtolower($field_name)!=$table."_statut")){//PERMET DE NE PAS PRENDRE EN COMPTE L'ID DE LA TABLE
			//test s'il s'agit du clé parent qui a migré alors utilisation d'un select
			$tab=explode('_',$field_name);
			$x=sizeof($tab);
			//si c'est un table parent qui a migré
			if($tab[$x-1]=='id'){
				$table_parent=str_replace('_id',null,$field_name);
				$controller.= "\t".'@OneToMany(mappedBy='.$table_parent.')'."\n";
				$controller.= "\t".'private String '.$table_parent.'"'."\n";
				
			}else{//si pas clé parente
				$field_name=str_replace($table.'_',null,$field_name);
				// if($d->type=='int'){
					// $form.= "\t".'private int '.$field_name.';'."\n";
				// }else{
					$controller.= "\t".'private String '.$field_name.';'."\n";
				// }
			}
			
		}
		$i++;
	}
	*/
	

		//création des fichers
		
		$file_model			= $this->base_url.'/entities/'.ucfirst($table).'.java';
		$file_repository	= $this->base_url.'/repositories/'.ucfirst($table).'Repository.java';
		$file_service		= $this->base_url.'/services/'.ucfirst($table).'Service.java';
		$file_serviceImpl	= $this->base_url.'/services/'.ucfirst($table).'ServiceImpl.java';
		$file_controller	= $this->base_url.'/controllers/'.ucfirst($table).'Controller.java';
		
		
		$fp_m=fopen($file_model,'w+');
		$fp_r=fopen($file_repository,'w+');
		$fp_s=fopen($file_service,'w+');
		$fp_si=fopen($file_serviceImpl,'w+');
		$fp_c=fopen($file_controller,'w+');
		
		if(fputs($fp_m, $model) && fputs($fp_r, $repository) && fputs($fp_s, $service) && fputs($fp_si, $serviceImpl) && fputs($fp_c, $controller)){
			
			fclose($fp_m);
			fclose($fp_r);
			fclose($fp_s);
			fclose($fp_si);
			fclose($fp_c);
			
			return true;
			
		}else{
			
			fclose($fp_m);
			fclose($fp_r);
			fclose($fp_s);
			fclose($fp_si);
			fclose($fp_c);
			
			return false;
			
		}
		
		
		
	}
	
	
	function haveImage($table){
		
		
		
		/*
		$tab=array();
		$req=mysql_query('select * from '.$table);
		while($d=mysql_fetch_field($req)){
			$lib=strtolower(str_replace($table.'_',null,$d->name));
			$tab[]=$lib;
		}
		if(in_array('image',$tab)){
			return true;
		}elseif(in_array('logo',$tab)){
			return true;
		}elseif(in_array('photo',$tab)){
			return true;
		}elseif(in_array('picture',$tab)){
			return true;
		}else{
			return false;
		}
		*/
		
	}
	
	function getImageField($table){
		$tab=array();
		$req=mysql_query('select * from '.$table);
		while($d=mysql_fetch_field($req)){
			$lib=strtolower(str_replace($table.'_',null,$d->name));
			$tab[]=$lib;
		}
		if(in_array('image',$tab)){
			return 'image';
		}elseif(in_array('logo',$tab)){
			return 'logo';
		}elseif(in_array('photo',$tab)){
			return 'photo';
		}elseif(in_array('picture',$tab)){
			return 'picture';
		}else{
			return '';
		}
	}

 
	function copyFiles($dir2copy,$dir_paste){
	  // On vérifie si $dir2copy est un dossier
	  if (is_dir($dir2copy)) {
	 
		// Si oui, on l'ouvre
		if ($dh = opendir($dir2copy)) {     
		  // On liste les dossiers et fichiers de $dir2copy
		  while (($file = readdir($dh)) !== false) {
			// Si le dossier dans lequel on veut coller n'existe pas, on le créé
			if (!is_dir($dir_paste)) mkdir ($dir_paste, 0777);
	 
			  // S'il s'agit d'un dossier, on relance la fonction rÃ©cursive
			  if(is_dir($dir2copy.$file) && $file != '..'  && $file != '.') copyFiles( $dir2copy.$file.'/' , $dir_paste.$file.'/' );     
				// S'il sagit d'un fichier, on le copie simplement
				elseif($file != '..'  && $file != '.') copy ( $dir2copy.$file , $dir_paste.$file );                                       
			 }
	 
		  // On ferme $dir2copy
		  closedir($dh);
			return true;
		}
	 
	  }
	}

	public function creerFichier($filename,$content){
		$fp=fopen($filename,'a+');
		fputs($fp,$content);
		fclose($fp);
	}
	
 }
 
?>