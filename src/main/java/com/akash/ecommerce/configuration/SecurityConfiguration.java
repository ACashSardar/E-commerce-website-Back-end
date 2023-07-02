package com.akash.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService());
		return authenticationProvider;
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf()
			.disable()
			.authorizeRequests()
			.requestMatchers(HttpMethod.POST, "/api/v1/products").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/api/v1/products/*").hasRole("ADMIN") 
			.requestMatchers(HttpMethod.PUT, "/api/v1/products/*").hasRole("ADMIN")
			.requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/api/v1/categories/*").hasRole("ADMIN") 
			.requestMatchers(HttpMethod.PUT, "/api/v1/categories/*").hasRole("ADMIN")
			.requestMatchers(HttpMethod.POST, "/api/v1/suppliers").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/api/v1/suppliers/*").hasRole("ADMIN") 
			.requestMatchers(HttpMethod.PUT, "/api/v1/suppliers/*").hasRole("ADMIN")
			.and()
			.httpBasic()
			.and()
			.logout();
		
		return http.build();
	}
}
