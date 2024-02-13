package com.nursery.management.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.management.entity.Notes;
import com.nursery.management.entity.Test;
import com.nursery.management.service.NotesService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/notes")
public class NotesController {

	@Autowired
	private NotesService notesService;

	@Value("${upoadDir}")
	private String uploadFolder;

	@PostMapping("/createNotes")
//	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<?> createNotes(@RequestParam("notes") String notes,
			@RequestParam("nurseryId") String nurseryId, @RequestParam("patientId") String patientId,
			@RequestParam("caretakerId") String caretakerId, @RequestParam("date") LocalDate date,
			@RequestParam("time") LocalTime time, final @RequestParam("image") MultipartFile file,

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

			Notes createdNotes = new Notes();
			
			createdNotes.setNotes(notes);
			
			createdNotes.setNurseryId(nurseryId);
			createdNotes.setPatientId(patientId);
			createdNotes.setCaretakerId(caretakerId);

			createdNotes.setDate(date);
			createdNotes.setTime(time);
			
			createdNotes.setImage(imageData);
			
			notesService.createNotes(createdNotes);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdNotes);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

		}
	}

	@GetMapping
	public ResponseEntity<List<Notes>> getAllNotes() {
		List<Notes> notes = notesService.getAllNotes();
		return ResponseEntity.ok(notes);
	}

//	By Default(when we don't send Month or year) then we need the first 50 records,
//	When month is present, year =0, then send me all the notes for the given month in current year,
//	When year is present, month = 0, then send all the notes related to that year
//	When year and month is present, then filter based on both and send me the values
	@GetMapping("/{patientId}/{nurseryId}")
	public List<Notes> getDetailsByMonthAndYear(@PathVariable String patientId,
	                                            @PathVariable String nurseryId,
	                                            @RequestParam(value = "month", defaultValue = "0") int month,
	                                            @RequestParam(value = "year", defaultValue = "0") int year) {
	    if (month == 0 && year == 0) {
	        // Case 1: Default behavior - return the first 50 records
	        return notesService.getFirst50RecordsForNurseryAndPatient(patientId, nurseryId);
	    } else 
		if (month > 0 && year == 0) {
	        // Case 2: When month is present and year = 0, return all notes for the given month in the current year
	        int currentYear = Year.now().getValue();
	        return notesService.getDetailsByPatientIdAndNurseryIdAndMonthAndYear(patientId, nurseryId, month, currentYear);
	    } else if (year > 0 && month == 0) {
	        // Case 3: When year is present and month = 0, return all notes related to that year
	        return notesService.getDetailsByPatientIdAndNurseryIdAndYear(patientId, nurseryId, year);
	    } else {
	        // Case 4: When both year and month are present, filter based on both and return the values
	        return notesService.getDetailsByPatientIdAndNurseryIdAndMonthAndYear(patientId, nurseryId, month, year);
	    }
	}

	

//	@GetMapping("/{patientId}/{nurseryId}")
//	public List<Notes> getDetailsByPatientIdAndNurseryId(@PathVariable String patientId,
//			@PathVariable String nurseryId) {
//		List<Notes> notes = notesService.getNotesForPatientInNursery(patientId, nurseryId);
//		return notes;
//	}

}
