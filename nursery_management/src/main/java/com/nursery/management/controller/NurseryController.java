package com.nursery.management.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.management.entity.Nursery;
import com.nursery.management.service.NurseryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/nurseries")
public class NurseryController {

	@Autowired
	private NurseryService nurseryService;

	@GetMapping
	public List<Nursery> getAllNurseries() {
		return nurseryService.getAllNurseries();
	}

	@GetMapping("/{nurseryId}")
	public ResponseEntity<?> getNurseryById(@PathVariable String nurseryId) {
		try {
			Nursery nursery = nurseryService.getNurseryById(nurseryId);
			return ResponseEntity.ok(nursery);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Value("${upoadDir}")
	private String uploadFolder;

	@PostMapping("/createNursery")
	public ResponseEntity<?> createNursery(@RequestParam("nurseryId") String nurseryId,
			@RequestParam("nurseryName") String nurseryName, @RequestParam("primaryColor") String primaryColor,
			@RequestParam("secondaryColor") String secondaryColor, final @RequestParam("image") MultipartFile file,
			@RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber,

			Model model, HttpServletRequest request) {
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName,
						HttpStatus.BAD_REQUEST);
			}
			Date createDate = new Date();
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Nursery createdNursery = new Nursery();
			createdNursery.setNurseryName(nurseryName);
			createdNursery.setNurseryId(nurseryId);
			createdNursery.setPrimaryColor(primaryColor);
			createdNursery.setSecondaryColor(secondaryColor);
			createdNursery.setImage(imageData);
			createdNursery.setEmail(email);
			createdNursery.setPhoneNumber(phoneNumber);
			nurseryService.createNursery(createdNursery);
			return ResponseEntity.status(HttpStatus.CREATED).body("Nursery Added Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{nurseryId}")
	public ResponseEntity<?> updateNursery(@PathVariable String nurseryId, @RequestBody Nursery updatedNursery) {
		try {
			Nursery nursery = nurseryService.updateNursery(nurseryId, updatedNursery);
			return ResponseEntity.ok(nursery);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{nurseryId}")
	public ResponseEntity<?> deleteNursery(@PathVariable String nurseryId) {
		try {
			nurseryService.deleteNursery(nurseryId);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
