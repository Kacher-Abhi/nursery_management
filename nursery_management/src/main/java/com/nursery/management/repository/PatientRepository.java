package com.nursery.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

	List<Patient> findByNursery(Nursery nursery);

	Patient findByEmail(String email);

	@Query("SELECT COUNT(p) > 0 FROM Patient p WHERE p.id = :patientId AND p.nursery.id = :nurseryId")
	boolean existsByIdAndNurseryId(@Param("patientId") String patientId, @Param("nurseryId") String nurseryId);
}
