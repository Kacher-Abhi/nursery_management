package com.nursery.management.config;

import java.security.Key;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nursery.management.entity.Role;
import com.nursery.management.service.CurrentUserService;
import com.nursery.management.service.TokenService;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig {
	
	@Autowired
	private TokenService TokenService;
	
	@Autowired
	private CurrentUserService userDetailsService;
	
	

	   
	private final TokenService tokenService;


	public SecurityConfig(TokenService tokenService) {
	        this.tokenService = tokenService;
	    }

	public JwtAuthenticationFilter jwtAuthenticationFilter() {
			TokenService tokenService = new TokenService();
	        return new JwtAuthenticationFilter(tokenService);
	    }

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
    public UserDetailsService userDetailsService() { 
        return new CurrentUserService(); 
    }

	@Bean
	public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService()); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider;
	}
	@Bean
	public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
		http.
		sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.requestMatchers("/auth", "/auth/token").permitAll()
		.requestMatchers("/admins/**").hasRole("ADMIN")
		.and()
		.addFilterBefore(new JwtAuthenticationFilter(TokenService)
		,UsernamePasswordAuthenticationFilter.class);
		http.cors().and().csrf().disable();
		return http.build();

	}

}