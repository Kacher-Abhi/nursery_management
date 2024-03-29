package com.nursery.management.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Role;
import com.nursery.management.repository.AdminRepository;
import com.nursery.management.repository.NurseryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private NurseryRepository nurseryRepository;

	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	public List<Admin> getAdminsByNurseryId(String nurseryId) {
		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		return adminRepository.findByNursery(nursery);
	}

	public Admin createAdmin(Admin admin) {
		if (adminRepository.existsByEmailAndNurseryId(admin.getEmail(), admin.getNurseryId())) {
			throw new RuntimeException("Email is already in use for the given nursery");
		}
		String nurseryId = admin.getNurseryId();
		System.out.println(nurseryId);
		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));
		System.out.println(nursery.getNurseryId());
		
		if (nurseryId != null) {
			admin.setNursery(nursery);
		}
		if (admin.isSuperAdmin())
			admin.setRole(Role.ROLE_SUPER_ADMIN.name());
		else
			admin.setRole(Role.ROLE_ADMIN.name());
		admin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt(4)));

		return adminRepository.save(admin);
	}

//	public Admin getAdminByEmail(String email) {
//		return adminRepository.findByEmail(email);
//	}

	public Admin updateAdmin(String adminId, Admin updatedAdmin) {
		Admin existingAdmin = getAdminById(adminId);

		existingAdmin.setFirstName(updatedAdmin.getFirstName());
		existingAdmin.setLastName(updatedAdmin.getLastName());
		existingAdmin.setEmail(updatedAdmin.getEmail());
		existingAdmin.setPhone_number(updatedAdmin.getPhone_number());
		// Save the updated admin
		return adminRepository.save(existingAdmin);
	}

	public void deleteAdmin(String adminId) {
		Admin admin = getAdminById(adminId);
		adminRepository.delete(admin);
	}

	public Admin getAdminById(String adminId) {
		return adminRepository.findById(adminId)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));
	}

}
