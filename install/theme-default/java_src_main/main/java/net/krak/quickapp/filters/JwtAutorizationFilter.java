package net.krak.quickapp.filters;

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

import net.krak.quickapp.utils.JwtUtils;

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
	
	
}