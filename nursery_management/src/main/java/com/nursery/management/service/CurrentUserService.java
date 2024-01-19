package com.nursery.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.CurrentUser;
import com.nursery.management.entity.Patient;
import com.nursery.management.repository.*;

@Service
public class CurrentUserService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private CareTakerRepository careTakerRepository;

	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

		String[] split = input.split(":");
	    String username = split[0];
	    String nurseryId = split[1];

	    Admin admin = adminRepository.existsByEmailAndNursery(username, nurseryId);
	    if (admin != null) {
	        System.out.println("Admin found with email " + username);
	        return new CurrentUser(admin);
	    }

	    Patient patient = patientRepository.existsByEmailAndNursery(username, nurseryId);
	    if (patient != null) {
	        System.out.println("Patient found with email " + username);
	        return new CurrentUser(patient);
	    }

	    CareTaker careTaker = careTakerRepository.existsByEmailAndNursery(username, nurseryId);

	    // Return null if the user is not found
	    return (careTaker != null) ? new CurrentUser(careTaker) : null;
	}
}
