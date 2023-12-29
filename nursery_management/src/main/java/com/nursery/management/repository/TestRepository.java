package com.nursery.management.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;
import com.nursery.management.entity.Test;

public interface TestRepository extends JpaRepository<Test, Long>{
	
	
	List<Test> findByNursery(Nursery nursery);
	
	List<Test> findByPatient(Patient patient);
	
//	List<Test> findByCaretaker(CareTaker careTaker);
	@Query("SELECT '*' FROM Test p WHERE p.patientEmail = :patientEmail AND p.nursery.id = :nurseryId")
	List<Test> getTestByPateintEmail(@Param("patientEmail") String patientEmail, @Param("nurseryId") String nurseryId);


}
