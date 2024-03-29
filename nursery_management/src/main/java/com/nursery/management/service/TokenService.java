package com.nursery.management.service;

import java.io.IOException;
import java.security.Key;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nursery.management.config.JwtSecretGenerator;
import com.nursery.management.entity.CurrentUser;
import com.nursery.management.entity.JwtResponse;
import com.nursery.management.entity.JwtSec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

	@Autowired
	private JwtSecService jwtSecService;

	private String jwtSecret;

	public JwtResponse generateToken(CurrentUser user, String tenantId) {
		Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
		Date expirationDate = Date.from(expirationTime);
		JwtSec secretKey = jwtSecService.getExisting(tenantId);
		this.jwtSecret = secretKey.getSecretKey();

		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

		String compactTokenString = Jwts.builder().claim("sub", user.getUsername()).claim("role", user.getUserRole())
				.claim("tenant", user.getTenant()).setExpiration(expirationDate).signWith(key, SignatureAlgorithm.HS256)
				.compact();
		JwtResponse reponse = new JwtResponse();
		reponse.setExpires(expirationDate);
		reponse.setRole(user.getUserRole());
		reponse.setToken(compactTokenString);

		return reponse;
	}

	public JwtResponse generatePasswordToken(CurrentUser user, String tenantId) {
		Instant expirationTime = Instant.now().plus(10, ChronoUnit.MINUTES); // Set expiration time to 10 minutes
		Date expirationDate = Date.from(expirationTime);
		JwtSec secretKey = jwtSecService.getExisting(tenantId);
		String jwtSecret = secretKey.getSecretKey();

		Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

		String compactTokenString = Jwts.builder().claim("sub", user.getUsername()).claim("role", user.getUserRole())
				.claim("tenant", user.getTenant()).setExpiration(expirationDate).signWith(key, SignatureAlgorithm.HS256)
				.compact();

		JwtResponse response = new JwtResponse();
		response.setExpires(expirationDate);
		response.setRole(user.getUserRole());
		response.setToken(compactTokenString);

		return response;
	}

	public CurrentUser parseToken(String token, String nurseryId) throws IOException {
		JwtSec jwtSecret = jwtSecService.getExisting(nurseryId);
		byte[] secretBytes = jwtSecret.getSecretKey().getBytes();

		Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(secretBytes).build().parseClaimsJws(token);

		String username = jwsClaims.getBody().getSubject();
		String role = jwsClaims.getBody().get("role", String.class);
		String tenant = jwsClaims.getBody().get("tenant", String.class);

		if (!tenant.equals(nurseryId))
			throw new UsernameNotFoundException("You dont have permission to view this");

		return new CurrentUser(username, role, tenant);
	}

}
