package com.ashokit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityProvider {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails normalUser = User
								.withUsername("himanshi")
								.password("normal")
								.roles("NORMAL")
								.build();
		UserDetails admin = User
								.withUsername("himanshi")
								.password("himanshi")
								.roles("ADMIN")
								.build();
//		UserDetails public_user = User
//							.withUsername("himanshi")
//							.password(passwordEncoder().encode())
//							.roles("NORMAL")
//							.build();
		return new InMemoryUserDetailsManager(normalUser,admin);
		
	}
	@Bean
	public SecurityFilterChain filerChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
					.authorizeHttpRequests()
					.requestMatchers("/user/public")
					.permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.formLogin();
		
		return httpSecurity.build();
	}
}
