package net.krak.grh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.krak.grh.filters.JwtAuthenticationFilter;
import net.krak.grh.filters.JwtAutorizationFilter;
import net.krak.grh.services.UserDetailsServiceImpl;


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
