package net.olea.santemaghreb.filters;

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

import net.olea.santemaghreb.utils.JwtUtils;

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
	
	
}