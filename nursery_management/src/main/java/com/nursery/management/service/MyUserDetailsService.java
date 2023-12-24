package com.nursery.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.Patient;
import com.nursery.management.repository.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(username);
		if (admin != null) {
			System.out.println("Admin found with email " + username);
			return admin;
		}

		Patient patient = patientRepository.findByEmail(username);
		if (patient != null) {
			return patient;
		}

		throw new UsernameNotFoundException("User not found with username: " + username);
	}
}
