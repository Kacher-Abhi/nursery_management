package com.nursery.management.service;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;

public class TwoFactorAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String username = request.getParameter(getUsernameParameter());
		String nurseryId = request.getParameter("nurseryId");
		String combinedUsername = username + ":" + nurseryId;
		return combinedUsername;
	}
}