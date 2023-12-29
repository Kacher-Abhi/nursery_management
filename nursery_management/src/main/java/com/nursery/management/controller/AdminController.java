package com.nursery.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private NurseryService nurseryService;

	@GetMapping

	public List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/byNursery/{nurseryId}")
	public List<Admin> getAdminsByNursery(@PathVariable String nurseryId) {
		Nursery nursery = nurseryService.getNurseryById(nurseryId);
		return adminService.getAdminsByNursery(nursery);
	}

	@PostMapping("/createAdmin")

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
	public ResponseEntity<?> updateAdmin(@PathVariable Long adminId, @RequestBody Admin updatedAdmin) {
		try {
			Admin admin = adminService.updateAdmin(adminId, updatedAdmin);
			return ResponseEntity.ok(admin);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{adminId}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
		try {
			adminService.deleteAdmin(adminId);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
