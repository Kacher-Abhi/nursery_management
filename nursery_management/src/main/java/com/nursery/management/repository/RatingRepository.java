package com.nursery.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nursery.management.entity.Nursery;
import com.nursery.management.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
//	List<Rating> findByPatientId(String patientId);

//	List<Rating> findByCaretakerId(String caretakerId);

	@Query("SELECT t FROM Rating t WHERE t.patient.patientId = :patientId AND t.nursery.nurseryId = :nurseryId")
	List<Rating> findByPatientId(@Param("patientId") String patientId, @Param("nurseryId") String nurseryId);

	@Query("SELECT t FROM Rating t WHERE t.caretaker.caretakerId = :caretakerId AND t.nursery.nurseryId = :nurseryId")
	List<Rating> findByCaretakerId(@Param("caretakerId") String caretakerId, @Param("nurseryId") String nurseryId);

	@Query("SELECT AVG(r.rating) FROM Rating r WHERE r.caretaker.caretakerId = :caretakerId")
	Double findAverageRatingByCaretakerId(@Param("caretakerId") String caretakerId);

	@Query("SELECT AVG(r.rating) FROM Rating r WHERE r.caretaker.caretakerId = :caretakerId AND r.nursery.nurseryId = :nurseryId")
	Double findByCaretakerIdAndNurseryId(@Param("caretakerId") String caretakerId,
			@Param("nurseryId") String nurseryId);

	@Query("SELECT AVG(r.rating) FROM Rating r WHERE r.nursery.nurseryId = :nurseryId")
	List<Nursery> findByNurseryId(@Param("nurseryId") String nurseryId);
}