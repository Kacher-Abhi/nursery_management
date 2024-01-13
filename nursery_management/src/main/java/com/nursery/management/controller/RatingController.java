package com.nursery.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Rating;
import com.nursery.management.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@GetMapping("/patient/{nurseryId}/{patientId}")
	public List<Rating> getRatingsByPatient(@PathVariable String nurseryId, @PathVariable String patientId) {
		return ratingService.getRatingsByPatient(patientId, nurseryId);
	}

	@GetMapping("/caretaker/{nurseryId}/{caretakerId}")
	public List<Rating> getRatingsByCaretaker(@PathVariable String caretakerId, @PathVariable String nurseryId) {
		return ratingService.getRatingsByCaretaker(caretakerId, nurseryId);
	}

	@PostMapping("/rate/{nurseryId}/{patientId}/{caretakerId}/{rating}")
	public ResponseEntity<?> rateCaretaker(@PathVariable String nurseryId, @PathVariable String patientId, @PathVariable String caretakerId,
			@PathVariable double rating) {
		return ratingService.rateCaretaker(patientId, caretakerId, nurseryId, rating);
	}

	@GetMapping("/average/{caretakerId}")
	public double getAverageRatingByCaretaker(@PathVariable String caretakerId) {
		return ratingService.getAverageRatingByCaretaker(caretakerId);
	}
	
	@GetMapping("/average/{nurseryId}/{caretakerId}")
	public double getAverageRatingByCaretakerIdAndNurseryId(@PathVariable String nurseryId, @PathVariable String caretakerId) {
		return ratingService.getAverageRatingByCaretakerIdAndNurseryId(caretakerId, nurseryId);
	}
	
}
