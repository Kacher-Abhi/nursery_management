package com.nursery.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;
import com.nursery.management.entity.Role;
import com.nursery.management.repository.CareTakerRepository;
import com.nursery.management.repository.NurseryRepository;
import com.nursery.management.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private NurseryRepository nurseryRepository;

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public Patient createPatient(Patient patient) {
		String nurseryId = patient.getNurseryId();
		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		if (nurseryId != null) {
			patient.setNursery(nursery);
			patient.setRole(Role.ROLE_PATIENT.name());
		}

		return patientRepository.save(patient);
	}

	public List<Patient> getPatientsByNurseryId(String nurseryId) {
		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		return patientRepository.findByNursery(nursery);
	}

	public Patient getPatientById(String patientId) {
		return patientRepository.findById(patientId)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
	}

	public void deletePatient(String patientId) {
		Patient admin = getPatientById(patientId);
		patientRepository.delete(admin);
	}

}
