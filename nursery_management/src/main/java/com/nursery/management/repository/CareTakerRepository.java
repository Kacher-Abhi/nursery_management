package com.nursery.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;

public interface CareTakerRepository extends JpaRepository<CareTaker, String> {
	List<CareTaker> findByNursery(Nursery nursery);

	Optional<CareTaker> findByEmail(String email);

	@Query("SELECT COUNT(c) > 0 FROM CareTaker c WHERE c.email = :email AND c.nursery.nurseryId = :nurseryId")
	boolean existsByEmailAndNurseryId(@Param("email") String email, @Param("nurseryId") String nurseryId);

}
