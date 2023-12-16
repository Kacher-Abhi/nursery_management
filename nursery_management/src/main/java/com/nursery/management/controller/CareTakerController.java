package com.nursery.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nursery.management.entity.Admin;
import com.nursery.management.entity.CareTaker;
import com.nursery.management.service.AdminService;
import com.nursery.management.service.CareTakerService;

@RestController
@RequestMapping("/caretakers")
public class CareTakerController {

    @Autowired
    private CareTakerService caretakerService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/byNursery/{nurseryId}")
    public ResponseEntity<List<CareTaker>> getCaretakersByNursery(@PathVariable String nurseryId) {
        List<CareTaker> caretakers = caretakerService.getCaretakersByNurseryId(nurseryId);
        return ResponseEntity.ok(caretakers);
    }

    @GetMapping("/{caretakerId}")
    public ResponseEntity<CareTaker> getCaretakerById(@PathVariable Long caretakerId) {
    	CareTaker caretaker = caretakerService.getCaretakerById(caretakerId);

        if (caretaker != null) {
            return ResponseEntity.ok(caretaker);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{caretakerId}")
    public ResponseEntity<CareTaker> updateCaretaker(
            @PathVariable Long caretakerId,
            @RequestBody CareTaker updatedCaretaker
    ) {
    	CareTaker caretaker = caretakerService.getCaretakerById(caretakerId);

        if (caretaker != null) {
            updatedCaretaker.setCaretakerId(caretakerId);
            CareTaker savedCaretaker = caretakerService.updateCaretaker(updatedCaretaker);
            return ResponseEntity.ok(savedCaretaker);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{caretakerId}")
    public ResponseEntity<Void> deleteCaretaker(@PathVariable Long caretakerId) {
    	CareTaker caretaker = caretakerService.getCaretakerById(caretakerId);

        if (caretaker != null) {
            caretakerService.deleteCaretaker(caretakerId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
