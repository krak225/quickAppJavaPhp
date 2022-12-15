package net.krak.grh.controllers;

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

import net.krak.grh.entities.User;
import net.krak.grh.services.AccountService;
import net.krak.grh.utils.JwtUtils;


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
				
				System.out.println("Can't refresh token: " + e.getMessage());
				
				throw e;
			}
		}else {
			throw new RuntimeException ("Refresh token is required");			
		}
		
	}
	
	
}
