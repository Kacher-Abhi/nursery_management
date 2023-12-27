package com.nursery.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserInfoController {
	
	@GetMapping("userinfo/{userid}")
	public  ResponseEntity<?> getUserInfo(@PathVariable String userid){
		
		return ResponseEntity.ok("Returned Successfully");
	}

}
