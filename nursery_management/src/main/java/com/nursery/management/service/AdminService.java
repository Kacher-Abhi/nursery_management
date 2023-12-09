package com.nursery.management.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.Admin;
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

	public List<Admin> getAdminsByNursery(Nursery nursery) {
		return adminRepository.findByNursery(nursery);
	}

	public Admin createAdmin(Admin admin) {
		String nurseryId = admin.getNurseryId();
		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		if (nurseryId != null) {
			admin.setNursery(nursery);
		}
		
		Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        admin.setRoles(roles);

		return adminRepository.save(admin);
	}
	
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

	public Admin updateAdmin(Long adminId, Admin updatedAdmin) {
		Admin existingAdmin = getAdminById(adminId);

		// Update the fields you want to allow updating
		existingAdmin.setName(updatedAdmin.getName());
		existingAdmin.setEmail(updatedAdmin.getEmail());
		existingAdmin.setPhone_number(updatedAdmin.getPhone_number());
		existingAdmin.setPassword(updatedAdmin.getPassword());
		existingAdmin.setNursery(updatedAdmin.getNursery()); // Update nursery if needed
		existingAdmin.setSuperAdmin(updatedAdmin.isSuperAdmin());

		// Save the updated admin
		return adminRepository.save(existingAdmin);
	}

	public void deleteAdmin(Long adminId) {
		Admin admin = getAdminById(adminId);
		adminRepository.delete(admin);
	}

	private Admin getAdminById(Long adminId) {
		return adminRepository.findById(adminId)
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + adminId));
	}
}
