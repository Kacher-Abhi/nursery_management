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

import com.nursery.management.entity.Nursery;
import com.nursery.management.service.NurseryService;

import jakarta.persistence.EntityNotFoundException;

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
    public ResponseEntity<Nursery> getNurseryById(@PathVariable String nurseryId) {
        try {
            Nursery nursery = nurseryService.getNurseryById(nurseryId);
            return ResponseEntity.ok(nursery);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Nursery> createNursery(@RequestBody Nursery nursery) {
        Nursery createdNursery = nurseryService.createNursery(nursery);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNursery);
    }

    @PutMapping("/{nurseryId}")
    public ResponseEntity<Nursery> updateNursery(@PathVariable String nurseryId, @RequestBody Nursery updatedNursery) {
        try {
            Nursery nursery = nurseryService.updateNursery(nurseryId, updatedNursery);
            return ResponseEntity.ok(nursery);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{nurseryId}")
    public ResponseEntity<Void> deleteNursery(@PathVariable String nurseryId) {
        try {
            nurseryService.deleteNursery(nurseryId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

