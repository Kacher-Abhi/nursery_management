package com.nursery.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.management.entity.CurrentUser;
import com.nursery.management.entity.JwtResponse;
import com.nursery.management.service.CurrentUserService;
import com.nursery.management.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private CurrentUserService currentUserService;
	
	@PostMapping("/token")
	public JwtResponse postMethodName(@RequestParam("nurseryId") String nurseryId, @RequestParam("username") String username, @RequestParam("password") String password)
   {
		//TODO: process POST request
		UserDetails user = currentUserService.loadUserByUsername(username);
		
		JwtResponse response = tokenService.generateToken((CurrentUser)user, nurseryId);
		
		return response;
	}
	
}
