package com.nursery.management.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.management.entity.CurrentUser;

@RestController
@RequestMapping("heelo")
public class testingController {
	
	    public String hello() {
	        return "hello";
	    }

	    @GetMapping("/admin")
	    @PreAuthorize("hasRole('ADMIN')")
	    public String helloAdmin() {
	        return "hello admin";
	    }

	    @GetMapping("/user")
	    public String helloUser() {
	        CurrentUser userPrincipal =
	                (CurrentUser) SecurityContextHolder
	                        .getContext()
	                        .getAuthentication()
	                        .getPrincipal();

	        return "hello " + userPrincipal.getUsername();
	}
}
