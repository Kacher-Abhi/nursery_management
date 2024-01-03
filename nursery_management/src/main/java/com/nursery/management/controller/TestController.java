package com.nursery.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Test;
import com.nursery.management.service.TestService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping("/{id}")
	public ResponseEntity<Test> getTestById(@PathVariable Long id) {
		Test test = testService.getTestById(id);
		return ResponseEntity.ok(test);
	}

	@Value("${upoadDir}")
	private String uploadFolder;

	@PostMapping("/createTest")
	public ResponseEntity<?> createNursery(@RequestParam("testName") String testName,
			@RequestParam("result") String result, @RequestParam("nurseryId") String nurseryId,
			@RequestParam("patientId") String patientId, @RequestParam("caretakerId") String caretakerId,
			@RequestParam("testTakenDate") LocalDate testTakenDate, @RequestParam("testTakenTime") LocalTime testTakenTime,

			final @RequestParam("testResult") MultipartFile file, final @RequestParam("prescription") MultipartFile prescription,

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
				
				BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream1.write(prescription.getBytes());
				stream1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			byte[] prescriptionData = prescription.getBytes();

			Test createdtest = new Test();
			createdtest.setTestName(testName);
			createdtest.setResult(result);

			createdtest.setNurseryId(nurseryId);
			createdtest.setPatientId(patientId);
			createdtest.setCaretakerId(caretakerId);
			
			createdtest.setTestTakenDate(testTakenDate);
			createdtest.setTestTakenTime(testTakenTime);


			createdtest.setImage(imageData);
			createdtest.setPrescription(prescriptionData);

			testService.createTest(createdtest);
			return ResponseEntity.status(HttpStatus.CREATED).body("test created");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody Test updatedTest) {
		Test updated = testService.updateTest(id, updatedTest);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
		testService.deleteTest(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Test>> getAllTests() {
		List<Test> tests = testService.getAllTests();
		return ResponseEntity.ok(tests);
	}

	@GetMapping("/byPatientId/{nurseryId}/{patientId}")
	public ResponseEntity<List<Test>> getTestsByPatientEmail(@PathVariable String nurseryId,
			@PathVariable String patientId) {
		List<Test> tests = testService.getTestsForPatientInNursery(patientId, nurseryId);
		return new ResponseEntity<>(tests, HttpStatus.OK);
	}

	@GetMapping("/byCareTakerId/{nurseryId}/{caretakerId}")
	public ResponseEntity<List<Test>> getTestsByCareTakerEmail(@PathVariable String nurseryId,
			@PathVariable String caretakerId) {
		List<Test> tests = testService.getTestsForCareTakerInNursery(caretakerId, nurseryId);
		return new ResponseEntity<>(tests, HttpStatus.OK);
	}

	@GetMapping("/{nurseryId}/{caretakerId}/{patientId}")
	public ResponseEntity<List<Test>> getTestsByPatientAndCaretaker(@PathVariable String nurseryId,
			@PathVariable String caretakerId, @PathVariable String patientId) {
		List<Test> tests = testService.getTestsForCareTakerAndPatient(caretakerId, nurseryId, patientId);
		return new ResponseEntity<>(tests, HttpStatus.OK);
	}
}
