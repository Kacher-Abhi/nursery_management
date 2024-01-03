package com.nursery.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;
import com.nursery.management.entity.Rating;
import com.nursery.management.repository.CareTakerRepository;
import com.nursery.management.repository.NurseryRepository;
import com.nursery.management.repository.PatientRepository;
import com.nursery.management.repository.RatingRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private CareTakerRepository caretakerRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private NurseryRepository nurseryRepository;

	public List<Rating> getRatingsByPatient(String patientId, String nurseryId) {
		return ratingRepository.findByPatientId(patientId, nurseryId);
	}

	public List<Rating> getRatingsByCaretaker(String caretakerId, String nurseryId) {
		return ratingRepository.findByCaretakerId(caretakerId, nurseryId);
	}

	public void rateCaretaker(String patientId, String caretakerId, String nurseryId, double rating) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

		CareTaker caretaker = caretakerRepository.findById(caretakerId)
				.orElseThrow(() -> new EntityNotFoundException("Caretaker not found with id: " + caretakerId));

		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Caretaker not found with id: " + caretakerId));

		Rating newRating = new Rating();
		newRating.setPatient(patient);
		newRating.setCaretaker(caretaker);
		newRating.setRating(rating);
		newRating.setNursery(nursery);

		ratingRepository.save(newRating);
	}

	public double getAverageRatingByCaretaker(String caretakerId) {
		return ratingRepository.findAverageRatingByCaretakerId(caretakerId);
	}

	public double getAverageRatingByCaretakerIdAndNurseryId(String caretakerId, String nurseryId) {
		return ratingRepository.findByCaretakerIdAndNurseryId(caretakerId, nurseryId);
	}

	public List<Nursery> getByNurseryId(String nurseryId) {
		return ratingRepository.findByNurseryId(nurseryId);
	}
}
