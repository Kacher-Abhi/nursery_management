package com.nursery.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Patient;

public interface CareTakerRepository extends JpaRepository<CareTaker, String> {
	List<CareTaker> findByNursery(Nursery nursery);

	Optional<CareTaker> findByEmail(String email);

	@Query("SELECT COUNT(c) > 0 FROM CareTaker c WHERE c.email = :email AND c.nursery.nurseryId = :nurseryId")
	boolean existsByEmailAndNurseryId(@Param("email") String email, @Param("nurseryId") String nurseryId);

	@Query("SELECT a FROM CareTaker a WHERE a.email = :email AND a.nursery.nurseryId = :nurseryId")
	CareTaker existsByEmailAndNursery(@Param("email") String email, @Param("nurseryId") String nurseryId);

}
