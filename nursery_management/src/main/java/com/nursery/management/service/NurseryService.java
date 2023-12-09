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
		if (nursery.getNurseryId().length() == 6) {
			return nurseryRepository.save(nursery);
		}
		return nursery;
	}

	public Nursery updateNursery(String nurseryId, Nursery updatedNursery) {
		Nursery existingNursery = getNurseryById(nurseryId);

		// Update the fields you want to allow updating
		existingNursery.setNurseryName(updatedNursery.getNurseryName());
		existingNursery.setPrimaryColor(updatedNursery.getPrimaryColor());
		existingNursery.setSecondaryColor(updatedNursery.getSecondaryColor());

		// Save the updated nursery
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
