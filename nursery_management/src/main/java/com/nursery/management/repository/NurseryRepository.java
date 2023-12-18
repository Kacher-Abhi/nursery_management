package com.nursery.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nursery.management.entity.Nursery;

public interface NurseryRepository extends JpaRepository<Nursery, String>{
	boolean existsByNurseryName(String name);
}
