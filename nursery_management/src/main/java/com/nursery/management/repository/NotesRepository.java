package com.nursery.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nursery.management.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {

	@Query("SELECT t FROM Notes t WHERE t.patient.patientId = :patientId AND t.nursery.nurseryId = :nurseryId")
	List<Notes> findByPatientIdAndNurseryId(@Param("patientId") String patientId, @Param("nurseryId") String nurseryId);

	@Query("SELECT e FROM Notes e WHERE e.patient.patientId = :patientId " + "AND e.nursery.nurseryId = :nurseryId "
			+ "AND e.month = :month " + "AND e.year = :year")
	List<Notes> findByPatientIdAndNurseryIdAndMonthAndYear(@Param("patientId") String patientId,
			@Param("nurseryId") String nurseryId, @Param("month") int month, @Param("year") int year);

	@Query("SELECT e FROM Notes e WHERE e.patient.patientId = :patientId " + "AND e.nursery.nurseryId = :nurseryId "
			+ "AND e.year = :year")
	List<Notes> findByPatientIdAndNurseryIdAndYear(@Param("patientId") String patientId,
			@Param("nurseryId") String nurseryId, @Param("year") int year);

	@Query("SELECT n FROM Notes n WHERE n.nursery.nurseryId = :nurseryId AND n.patient.patientId = :patientId ORDER BY n.notesId ASC")
	List<Notes> getFirst50RecordsForNurseryAndPatient(@Param("patientId") String patientId,
			@Param("nurseryId") String nurseryId);
}