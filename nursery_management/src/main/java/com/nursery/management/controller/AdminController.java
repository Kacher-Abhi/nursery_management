package com.nursery.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.service.AdminService;
import com.nursery.management.service.NurseryService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/byId/{adminId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> getAdminById(@PathVariable String adminId) {
		Admin admin = adminService.getAdminById(adminId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(admin.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		if (admin != null) {
			return ResponseEntity.ok(admin);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/byNursery/{nurseryId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> getAdminsByNursery(@PathVariable String nurseryId) {

		List<Admin> admins = adminService.getAdminsByNurseryId(nurseryId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(nurseryId)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		if (admins != null) {
			return ResponseEntity.ok(admins);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@PostMapping("/createAdmin")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
		try {
			Admin createdAdmin = adminService.createAdmin(admin);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

	@PutMapping("/{adminId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> updateAdmin(@PathVariable String adminId, @RequestBody Admin updatedAdmin) {
		Admin admin = adminService.getAdminById(adminId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(admin.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		if (admin != null) {
			updatedAdmin.setAdminId(adminId);
			Admin savedAdmin = adminService.updateAdmin(adminId, updatedAdmin);
			return ResponseEntity.ok(savedAdmin);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{adminId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> deleteAdmin(@PathVariable String adminId) {
		Admin admin = adminService.getAdminById(adminId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(admin.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}

		if (admin != null) {
			adminService.deleteAdmin(adminId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
