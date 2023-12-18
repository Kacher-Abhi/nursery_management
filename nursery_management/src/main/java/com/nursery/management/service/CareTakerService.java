package com.nursery.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.repository.CareTakerRepository;
import com.nursery.management.repository.NurseryRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class CareTakerService {

	@Autowired
	private CareTakerRepository careTakerRepository;

	@Autowired
	private NurseryRepository nurseryRepository;

	public CareTaker createCaretaker(CareTaker caretaker) {
		String nurseryId = caretaker.getNurseryId();
		System.out.println(nurseryId);
		Nursery nursery = nurseryRepository.findById(caretaker.getNurseryId())
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		if (nurseryId != null) {
			caretaker.setNursery(nursery);
		} else {
			throw new RuntimeException("Nursery Id should not be null");
		}

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

	public CareTaker updateCaretaker(CareTaker updatedCaretaker) {
		// Perform any additional validation or business logic if needed
		return careTakerRepository.save(updatedCaretaker);
	}

	public void deleteCaretaker(String caretakerId) {
		careTakerRepository.deleteById(caretakerId);
	}

	public List<CareTaker> getAllCaretakers() {
		return careTakerRepository.findAll();
	}

}
