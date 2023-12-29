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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.management.entity.Patient;
import com.nursery.management.service.PatientService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}

	@PostMapping("/createPatient")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		Patient createdPatient = patientService.createPatient(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
	}

	@GetMapping("/byNursery/{nurseryId}")
	public ResponseEntity<List<Patient>> getPatientsByNursery(@PathVariable String nurseryId) {
		List<Patient> patients = patientService.getPatientsByNurseryId(nurseryId);
		return ResponseEntity.ok(patients);
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long patientId) {
		Patient patient = patientService.getPatientById(patientId);

		if (patient != null) {
			return ResponseEntity.ok(patient);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/{patientId}/rateCaretaker/{caretakerId}")
	public ResponseEntity<?> rateCaretaker(@PathVariable Long patientId, @PathVariable Long caretakerId,
			@RequestBody int newRating) {
		try {
			patientService.addRating(patientId, caretakerId, newRating);
			return ResponseEntity.ok("Rating added successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{patientId}")
	public ResponseEntity<Void> deleteCaretaker(@PathVariable Long patientId) {
		Patient patient = patientService.getPatientById(patientId);

		if (patient != null) {
			patientService.deletePatient(patientId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
