package com.nursery.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nursery.management.entity.CareTaker;
import com.nursery.management.entity.Nursery;

public interface CareTakerRepository extends JpaRepository<CareTaker, String> {
    List<CareTaker> findByNursery(Nursery nursery);
    Optional<CareTaker> findByEmail(String email);
}
