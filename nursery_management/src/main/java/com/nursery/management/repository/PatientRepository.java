package com.nursery.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	List<Patient> findByNursery(Nursery nursery);
}
