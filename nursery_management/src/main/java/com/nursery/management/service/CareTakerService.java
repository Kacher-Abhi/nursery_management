package com.nursery.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Rating;
import com.nursery.management.entity.Role;
import com.nursery.management.repository.CareTakerRepository;
import com.nursery.management.repository.NurseryRepository;
import com.nursery.management.repository.RatingRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class CareTakerService {

	@Autowired
	private CareTakerRepository careTakerRepository;

	@Autowired
	private NurseryRepository nurseryRepository;

	@Autowired
	private RatingRepository ratingRepository;

	public CareTaker createCaretaker(CareTaker caretaker) {
		if (careTakerRepository.existsByEmailAndNurseryId(caretaker.getEmail(), caretaker.getNurseryId())) {
			throw new RuntimeException("Email is already in use");
		}
		String nurseryId = caretaker.getNurseryId();
		System.out.println(nurseryId);
		Nursery nursery = nurseryRepository.findById(caretaker.getNurseryId())
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		if (nurseryId != null) {
			caretaker.setNursery(nursery);
			caretaker.setAuthorities(Role.ROLE_CARETAKER.name());
		} else {
			throw new RuntimeException("Nursery Id should not be null");
		}
		caretaker.setPassword(BCrypt.hashpw(caretaker.getPassword(), BCrypt.gensalt(4)));

		return careTakerRepository.save(caretaker);
	}

	public List<CareTaker> getCaretakersByNurseryId(String nurseryId) {
		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		return careTakerRepository.findByNursery(nursery);
	}

	public CareTaker getCaretakerById(String caretakerId) {
		return careTakerRepository.findById(caretakerId).orElse(null);
	}

	public CareTaker updateCaretaker(String caretakerId, CareTaker updatedCaretaker) {
		// Perform any additional validation or business logic if needed
		CareTaker existingCaretaker = getCaretakerById(caretakerId);

		existingCaretaker.setName(updatedCaretaker.getName());
		existingCaretaker.setAge(updatedCaretaker.getAge());
		existingCaretaker.setPhoneNumber(updatedCaretaker.getPhoneNumber());
		existingCaretaker.setEmail(updatedCaretaker.getEmail());
//		existingCaretaker.setPassword(updatedCaretaker.getPassword());
		existingCaretaker.setSex(updatedCaretaker.getSex());
//		existingCaretaker.setNurseryId(updatedCaretaker.getNursery().getNurseryId());
		existingCaretaker.setYearsOfExperience(updatedCaretaker.getYearsOfExperience());
		existingCaretaker.setDesignation(updatedCaretaker.getDesignation());
//		existingCaretaker.setAuthorities(existingCaretaker.getAuthorities());
		return careTakerRepository.save(existingCaretaker);
	}

	public void deleteCaretaker(String caretakerId) {
		careTakerRepository.deleteById(caretakerId);
	}

	public List<CareTaker> getAllCaretakers() {
		return careTakerRepository.findAll();
	}

	public CareTaker updateAverageRating(String caretakerId, String nurseryId) {
		CareTaker caretaker = careTakerRepository.findById(caretakerId)
				.orElseThrow(() -> new EntityNotFoundException("Caretaker not found with id: " + caretakerId));

		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + caretakerId));

		List<Rating> ratings = ratingRepository.findByCaretakerId(caretakerId, nurseryId);

		if (!ratings.isEmpty()) {
			double averageRating = ratings.stream().mapToDouble(Rating::getRating).average().orElse(0.0);
			caretaker.setAverageRating(averageRating);

		}
		return careTakerRepository.save(caretaker);
	}

}
