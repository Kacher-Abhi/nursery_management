package com.nursery.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.repository.CareTakerRepository;
import com.nursery.management.repository.NurseryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NurseryService {

	@Autowired
	private NurseryRepository nurseryRepository;

	@Autowired
	private CareTakerRepository careTakerRepository;

	public List<Nursery> getAllNurseries() {
		return nurseryRepository.findAll();
	}

	public Nursery getNurseryById(String nurseryId) {
		return nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));
	}

	public Nursery createNursery(Nursery nursery) {
		// You may want to perform some validation or business logic before saving
		if (nurseryRepository.existsById(nursery.getNurseryId())
				|| nurseryRepository.existsByNurseryName(nursery.getNurseryName())) {
			throw new RuntimeException("Nursery with id : " + nursery.getNurseryId() + " or Nursery with name : "
					+ nursery.getNurseryName() + " is already present in the database ");
		} else {

			if (nursery.getNurseryId().length() == 6) {
				return nurseryRepository.save(nursery);
			} else {
				throw new RuntimeException(
						"Error creating Nursery \nReason : Expected Nursery ID lenth : 6, But entered Nursery ID length: "
								+ nursery.getNurseryId().length());
			}
		}
	}

	public Nursery updateNursery(String nurseryId, Nursery updatedNursery) {
		Nursery existingNursery = getNurseryById(nurseryId);

		// Update the fields you want to allow updating
		existingNursery.setNurseryName(updatedNursery.getNurseryName());
		existingNursery.setPrimaryColor(updatedNursery.getPrimaryColor());
		existingNursery.setSecondaryColor(updatedNursery.getSecondaryColor());
		existingNursery.setEmail(updatedNursery.getEmail());
		existingNursery.setPhoneNumber(updatedNursery.getPhoneNumber());
		
		return nurseryRepository.save(existingNursery);
	}

	public void deleteNursery(String nurseryId) {
		Nursery nursery = getNurseryById(nurseryId);
		nurseryRepository.delete(nursery);
	}

	public List<CareTaker> getCaretakersByNurseryId(String nurseryId) {
		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));
		return careTakerRepository.findByNursery(nursery);
	}

}
