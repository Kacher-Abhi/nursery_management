package com.nursery.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nursery.management.entity.Test;
import com.nursery.management.service.TestService;

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

	@PostMapping
	public ResponseEntity<Test> createTest(@RequestBody Test test) {
		Test createdTest = testService.createTest(test);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTest);
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

}
