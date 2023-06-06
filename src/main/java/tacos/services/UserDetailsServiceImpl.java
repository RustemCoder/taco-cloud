package tacos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tacos.entities.User;
import tacos.repositories.UserRepository;

import java.util.*;
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepo;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepo) {
	    this.userRepo = userRepo;
	}
	
	@Bean
	public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
		 User user = userRepo.findByUsername(username);
		    if (user != null) {
		      return user;
		    }
		    throw new UsernameNotFoundException(
		                    "User '" + username + "' not found");
		  }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
		  .authorizeRequests()
		  .requestMatchers("/design", "/orders").hasRole("USER")
		  .requestMatchers("/", "/**").permitAll()
		  .and()
		  .formLogin()
		  .loginPage("/login")
		  .and()
		  .build();
	}
}
	


