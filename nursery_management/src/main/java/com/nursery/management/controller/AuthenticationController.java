package com.nursery.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.management.entity.CurrentUser;
import com.nursery.management.entity.JwtResponse;
import com.nursery.management.service.CurrentUserService;
import com.nursery.management.service.TokenService;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private CurrentUserService currentUserService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<?> postMethodName(@RequestParam("nurseryId") String nurseryId,
			@RequestParam("username") String username, @RequestParam("password") String password) throws AuthenticationException {
		
		try {
	        String combinedUsername = username + ":" + nurseryId;
	        UserDetails user = currentUserService.loadUserByUsername(combinedUsername);

	        if (user == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User credentials doesn't match with our records");
			}
	        // Authenticate the user
	        Authentication authenticationToken =
	                new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
            authenticationToken = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	        if(!authenticationToken.isAuthenticated()) {
	        	throw new AuthenticationException("User credentials are not correct");
	        }

	        // If no exception is thrown, the authentication was successful
	        // Generate and return the JWT token
	        JwtResponse response = tokenService.generateToken((CurrentUser) user, nurseryId);
	        return ResponseEntity.status(HttpStatus.OK).body(response);

	    } catch (AuthenticationException e) {
	        // Handle authentication failure
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
}
}
