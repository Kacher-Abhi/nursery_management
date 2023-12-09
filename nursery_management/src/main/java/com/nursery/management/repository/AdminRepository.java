package com.nursery.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.Nursery;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByNursery(Nursery nursery);

	Admin findByEmail(String email);
    
}

