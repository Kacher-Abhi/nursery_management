package com.nursery.management.config;

import java.security.Key;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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

import com.nursery.management.service.TokenService;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig {

	private TokenService TokenService;
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
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

//	@SuppressWarnings("removal")
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		http.authorizeRequests()
//		.requestMatchers("/caretakers/**").hasRole("ADMIN")
//		.requestMatchers("/admins/**").permitAll()
//		.requestMatchers(HttpMethod.POST,"/admins").permitAll()
//		.requestMatchers(HttpMethod.POST,"/caretakers").hasAnyRole("ADMIN","SUPERADMIN")
//
//
////		.requestMatchers("/expenses/**").hasAnyRole("OWNER","CASHIER")
////		.requestMatchers("/billing/**").hasAnyRole("OWNER","CASHIER")
////		.requestMatchers("/item/**").hasAnyRole("ADMIN","OWNER")// for viewing reports and editing menu items 
////		.requestMatchers(HttpMethod.POST,"/items").hasAnyRole("ADMIN","OWNER")
////		.requestMatchers("/admin/**").hasAnyRole("ADMIN","OWNER")// for adding new employees
////		.requestMatchers(HttpMethod.POST,"/users").hasAnyRole("ADMIN","OWNER")
////		.requestMatchers("/orders/**").hasAnyRole("CHEF","OWNER","CASHIER")//for viewing order Status
////		.requestMatchers(HttpMethod.POST,"/orders/**").hasAnyRole("CHEF","OWNER","CASHIER")
////		.requestMatchers(HttpMethod.PUT,"/orders/**").hasAnyRole("CHEF","OWNER","CASHIER")
////		.requestMatchers("/customer/**").hasAnyRole("OWNER","CASHIER")// for adding new customers
////		.requestMatchers(HttpMethod.POST,"/cust").hasAnyRole("OWNER","CASHIER")
//		.requestMatchers("/").permitAll()
//		.and().formLogin();
//		http.csrf().disable();  
//	}

	@Bean
	public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception{
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers("/auth", "/auth/token").permitAll()
                
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(TokenService),
                        UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        return http.build();
    }
	
	
}
