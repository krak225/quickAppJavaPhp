package net.olea.santemaghreb.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.olea.santemaghreb.entities.User;

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
		
		//appel direct vu que pas d'alias d'import en java et que notre Entity User a été déjà importé
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
		
	}

}
