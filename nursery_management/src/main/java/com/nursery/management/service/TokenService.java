package com.nursery.management.service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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

        String compactTokenString = Jwts.builder()
                .claim("sub", user.getUsername())
                .claim("role", user.getUserRole())
                .claim("tenant", user.getTenant())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        JwtResponse reponse = new JwtResponse();
        reponse.setExpires(expirationDate);
        reponse.setToken(compactTokenString);

        return reponse;
	}

	public CurrentUser parseToken(String token) {
		byte[] secretBytes = jwtSecret.getBytes();

		Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(secretBytes).build().parseClaimsJws(token);

		String username = jwsClaims.getBody().getSubject();
		String role = jwsClaims.getBody().get("role", String.class);
		String tenant = jwsClaims.getBody().get("tenant", String.class);

		return new CurrentUser(username, role, tenant);
	}
	
}
