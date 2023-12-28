package com.nursery.management.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.management.config.JwtSecretGenerator;
import com.nursery.management.entity.JwtSec;
import com.nursery.management.repository.JwtSecRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class JwtSecService {
	
	@Autowired
	private JwtSecRepository jwtSecRepo;
	
	public boolean addNew(String tenantId) {
		
		Optional<JwtSec> jwtSec = jwtSecRepo.findByNursery(tenantId);
		if(!jwtSec.isPresent()) {
			String secretKey = JwtSecretGenerator.createSecret();
			JwtSec jwtSec1 = new JwtSec(tenantId, secretKey);
			jwtSecRepo.save(jwtSec1);
			return true;
		}
		return false;
	}
	
	public JwtSec getExisting(String tenantId){
		JwtSec jwtSec = jwtSecRepo.findByNursery(tenantId).orElseThrow(() -> new EntityNotFoundException("Secret Key does not exist"));
		
		return jwtSec;
		
	}

}
