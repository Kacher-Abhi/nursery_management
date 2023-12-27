package com.nursery.management.config;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfig {

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
	@SuppressWarnings("removal")
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .requestMatchers("/caretakers/**").hasAnyRole("ADMIN")
                .requestMatchers("/admins/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/admins/**").hasAnyRole("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/caretakers").hasAnyRole("ROLE_ADMIN"));;
		http.csrf().disable();
		http.cors().disable();
		http.formLogin();
		return http.build();
	}
}
