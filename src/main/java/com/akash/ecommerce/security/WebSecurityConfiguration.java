package com.akash.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/v1/products").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/v1/products/*").hasRole("ADMIN") 
		.antMatchers(HttpMethod.PUT, "/api/v1/products/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/v1/categories/*").hasRole("ADMIN") 
		.antMatchers(HttpMethod.PUT, "/api/v1/categories/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/api/v1/suppliers").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/v1/suppliers/*").hasRole("ADMIN") 
		.antMatchers(HttpMethod.PUT, "/api/v1/suppliers/*").hasRole("ADMIN")
		.and()
		.httpBasic()
		.and()
		.logout();
		http.cors();
		http.csrf().disable();
	}
}
