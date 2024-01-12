package com.nursery.management.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nursery.management.entity.CurrentUser;
import com.nursery.management.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private TokenService tokenService;

	public JwtAuthenticationFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		String authorizationHeader = httpServletRequest.getHeader("Authorization");

		if (authorizationHeaderIsInvalid(authorizationHeader)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		String requestURI = httpServletRequest.getRequestURI();
		String[] pathSegments = requestURI.split("/");

		String nurseryId = pathSegments[pathSegments.length - 1];
		UsernamePasswordAuthenticationToken token = createToken(authorizationHeader, nurseryId);

		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
		return authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
	}

	private UsernamePasswordAuthenticationToken createToken(String authorizationHeader, String nurseryId) {
		String token = authorizationHeader.replace("Bearer ", "");
		CurrentUser userPrincipal = tokenService.parseToken(token, nurseryId);

		return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
	}
}
