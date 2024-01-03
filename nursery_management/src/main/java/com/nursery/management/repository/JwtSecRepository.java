package com.nursery.management.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nursery.management.entity.Admin;
import com.nursery.management.entity.JwtSec;

public interface JwtSecRepository extends JpaRepository<JwtSec, Long> {

	Optional<JwtSec> findByNursery(String nursery);
}
