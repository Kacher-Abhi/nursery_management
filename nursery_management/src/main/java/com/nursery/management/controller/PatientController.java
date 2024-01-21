package com.nursery.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Patient;
import com.nursery.management.service.CareTakerService;
import com.nursery.management.service.PatientService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private CareTakerService careTakerService;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}

	@PostMapping("/createPatient")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
		Patient createdPatient = patientService.createPatient(patient);
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
					&& !nursery_id.equals(patient.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
	}
	
//	@PostMapping("/createPatient")
////	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
//	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
//		try {
//			Patient createdPatient = patientService.createPatient(patient);
//			return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//
//		}
//	}

	@GetMapping("/byNursery/{nurseryId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> getPatientsByNursery(@PathVariable String nurseryId) {
		List<Patient> patients = patientService.getPatientsByNurseryId(nurseryId);
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
		if (patients != null) {
			return ResponseEntity.ok(patients);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/{patientId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_PATIENT')or hasRole('ROLE_CARETAKER')")
	public ResponseEntity<?> getPatientById(@PathVariable String patientId) {
		Patient patient = patientService.getPatientById(patientId);

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
					&& !nursery_id.equals(patient.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}

		if (patient != null) {
			return ResponseEntity.ok(patient);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("byCaretaker/{caretakerId}")
	public ResponseEntity<CareTaker> getPatientByCaretakerId(@PathVariable String caretakerId) {
		CareTaker caretaker = careTakerService.getCaretakerById(caretakerId);

		if (caretaker != null) {
			return ResponseEntity.ok(caretaker);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/{patientId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_PATIENT')")
	public ResponseEntity<?> updatePatient(@PathVariable String patientId, @RequestBody Patient updatedCaretaker) {
		Patient patient = patientService.getPatientById(patientId);
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
					&& !nursery_id.equals(patient.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		if (patient != null) {
			updatedCaretaker.setPatientId(patientId);
			Patient savedPatient = patientService.updatePatient(patientId, updatedCaretaker);
			return ResponseEntity.ok(savedPatient);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{patientId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> deleteCaretaker(@PathVariable String patientId) {
		Patient patient = patientService.getPatientById(patientId);
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
					&& !nursery_id.equals(patient.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		} 

		if (patient != null) {
			patientService.deletePatient(patientId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
