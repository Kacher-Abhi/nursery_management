package com.nursery.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.service.CareTakerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/caretakers")
public class CareTakerController {

	@Autowired
	private CareTakerService caretakerService;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public List<CareTaker> getAllCaretakers() {
		return caretakerService.getAllCaretakers();
	}

	@GetMapping("/byNursery/{nurseryId}")
//	PreAuthorize()
	// Super_admin, nursery_admin
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> getCaretakersByNursery(@PathVariable String nurseryId) {
		List<CareTaker> caretakers = caretakerService.getCaretakersByNurseryId(nurseryId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(nurseryId)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		if (caretakers != null) {
			return ResponseEntity.ok(caretakers);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/{caretakerId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_PATIENT')or hasRole('ROLE_CARETAKER')")
	public ResponseEntity<?> getCaretakerById(@PathVariable String caretakerId) {
		CareTaker caretaker = caretakerService.getCaretakerById(caretakerId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(caretaker.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		if (caretaker != null) {
			return ResponseEntity.ok(caretaker);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping("/createCaretaker")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> createCareTaker(@RequestBody CareTaker careTaker) {
		try {
			CareTaker createCareTaker = caretakerService.createCaretaker(careTaker);
			return ResponseEntity.status(HttpStatus.CREATED).body(createCareTaker);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

	@PutMapping("/{caretakerId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_CARETAKER')")
	public ResponseEntity<?> updateCaretaker(@PathVariable String caretakerId,
			@RequestBody CareTaker updatedCaretaker) {
		CareTaker caretaker = caretakerService.getCaretakerById(caretakerId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(caretaker.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}
		if (caretaker != null) {
			updatedCaretaker.setCaretakerId(caretakerId);
			CareTaker savedCaretaker = caretakerService.updateCaretaker(caretakerId, updatedCaretaker);
			return ResponseEntity.ok(savedCaretaker);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/rate/{caretakerId}/{nurseryId}")
	public ResponseEntity<CareTaker> updateAverage(@PathVariable String caretakerId, @PathVariable String nurseryId) {
		CareTaker caretaker = caretakerService.getCaretakerById(caretakerId);

		if (caretaker != null) {
			CareTaker savedCaretaker = caretakerService.updateAverageRating(caretakerId, nurseryId);
			return ResponseEntity.ok(savedCaretaker);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{caretakerId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> deleteCaretaker(@PathVariable String caretakerId) {
		CareTaker caretaker = caretakerService.getCaretakerById(caretakerId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			String nursery_id = "";
			String role = "";
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				nursery_id = userDetails.getUsername().split(":")[1];
				role = userDetails.getAuthorities().toArray()[0].toString();
			}
			if ((!role.equals("ROLE_SUPER_ADMIN")) && !nursery_id.isBlank() && !nursery_id.isEmpty()
					&& !nursery_id.equals(caretaker.getNursery().getNurseryId())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You dont have access to this!");
			}
		}

		if (caretaker != null) {
			caretakerService.deleteCaretaker(caretakerId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
