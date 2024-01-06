package com.nursery.management.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.Nursery;

public interface AdminRepository extends JpaRepository<Admin, String> {
	List<Admin> findByNursery(Nursery nursery);

	@Query("SELECT a FROM Admin a WHERE a.email = :email AND a.nursery.nurseryId = :nurseryId")
	Admin existsByEmailAndNursery(@Param("email") String email, @Param("nurseryId") String nurseryId);

	@Query("SELECT COUNT(a) > 0 FROM Admin a WHERE a.email = :email AND a.nursery.nurseryId = :nurseryId")
	boolean existsByEmailAndNurseryId(@Param("email") String email, @Param("nurseryId") String nurseryId);

}
