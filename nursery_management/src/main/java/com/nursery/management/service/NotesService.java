package com.nursery.management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Notes;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;
import com.nursery.management.entity.Test;
import com.nursery.management.repository.CareTakerRepository;
import com.nursery.management.repository.NotesRepository;
import com.nursery.management.repository.NurseryRepository;
import com.nursery.management.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NotesService {

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private NurseryRepository nurseryRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private CareTakerRepository careTakerRepository;

	public List<Notes> getAllNotes() {
		return notesRepository.findAll();
	}

	public Notes createNotes(Notes notes) {
		// Add any validation or business logic before saving
		String nurseryId = notes.getNurseryId();
		String patientId = notes.getPatientId();

		String careTakerId = notes.getCaretakerId();

		Nursery nursery = nurseryRepository.findById(nurseryId)
				.orElseThrow(() -> new EntityNotFoundException("Nursery not found with id: " + nurseryId));

		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

		CareTaker caretaker = careTakerRepository.findById(careTakerId)
				.orElseThrow(() -> new EntityNotFoundException("CareTaker not found with id: " + careTakerId));

		if (nurseryId != null && patientId != null && careTakerId != null) {
			notes.setNursery(nursery);
			notes.setPatient(patient);
			notes.setCaretaker(caretaker);
			notes.setMonth(notes.getMonth());
			notes.setYear(notes.get_Year());

		}

		return notesRepository.save(notes);

	}

	public List<Notes> getNotesForPatientInNursery(String patientId, String nurseryId) {
		return notesRepository.findByPatientIdAndNurseryId(patientId, nurseryId);
	}

	public List<Notes> getDetailsByPatientIdAndNurseryIdAndYear(String patientId, String nurseryId, int year) {
        return notesRepository.findByPatientIdAndNurseryIdAndYear(patientId, nurseryId, year);
    }

	public List<Notes> getDetailsByPatientIdAndNurseryIdAndMonthAndYear(String patientId, String nurseryId, int month, int year) {
		// Assuming the year is the current year
		List<Notes> notes = notesRepository.findByPatientIdAndNurseryIdAndMonthAndYear(patientId, nurseryId, month, year);

		return notes;
	}
	
    public List<Notes> getFirst50RecordsForNurseryAndPatient( String patientId, String nurseryId) {
        return notesRepository.getFirst50RecordsForNurseryAndPatient(patientId, nurseryId);
    }

}
