package com.nursery.management.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;
import com.nursery.management.entity.Test;
import com.nursery.management.repository.CareTakerRepository;
import com.nursery.management.repository.NurseryRepository;
import com.nursery.management.repository.PatientRepository;
import com.nursery.management.repository.TestRepository;

import jakarta.persistence.*;

import java.util.List;

@Service
public class TestService {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private NurseryRepository nurseryRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private CareTakerRepository careTakerRepository;

	public Test getTestById(Long id) {
		return testRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Test not found with id: " + id));
	}

	public Test createTest(Test test) {
		// Add any validation or business logic before saving
		String nurseryId = test.getNurseryId();
		Long patientId = test.getPatientId();
//        validatePatientAndNursery(patientId, nurseryId);
		Long careTakerId = test.getCaretakerId();

		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

		CareTaker caretaker = careTakerRepository.findById(careTakerId)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + careTakerId));

		if (nurseryId != null && patientId != null && careTakerId != null) {
			test.setNursery(nursery);
			test.setPatient(patient);
			test.setCareTaker(caretaker);
			test.setCareTakerEmail(caretaker.getEmail());
			test.setPatientEmail(patient.getEmail());

		}

		return testRepository.save(test);

	}

	public Test updateTest(Long id, Test updatedTest) {
		Test existingTest = getTestById(id);
		// Update fields of the existing test
		existingTest.setTestName(updatedTest.getTestName());
		existingTest.setResult(updatedTest.getResult());
		// Update other fields as needed

		return testRepository.save(existingTest);
	}

	public void deleteTest(Long id) {
		Test existingTest = getTestById(id);
		testRepository.delete(existingTest);
	}

	public List<Test> getAllTests() {
		return testRepository.findAll();
	}

	public List<Test> getTestByPateintEmail(String patientEmail, String nurseryId) {
		// TODO Auto-generated method stub
		List<Test> findByPatientEmail = testRepository.getTestByPateintEmail(patientEmail, nurseryId);
		return findByPatientEmail;
	}

//	private void validatePatientAndNursery(Long patientId, String nurseryId) {
//        boolean isPatientInNursery = patientRepository.existsByIdAndNurseryId(patientId, nurseryId);
//        if (!isPatientInNursery) {
//            throw new IllegalArgumentException("Patient with ID " + patientId +
//                    " does not belong to Nursery with ID " + nurseryId);
//        }
//    }

}
