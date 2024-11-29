package com.spring.auth.server.demo.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SessionInvalidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Check if this is a token revocation request
		if (request.getRequestURI().contains("/oauth2/revoke")) {
			System.err.println("/oauth2/revoke");
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			// If authenticated, invalidate the session
			if (authentication != null && request.getSession(false) != null) {
				request.getSession(false).invalidate();
				SecurityContextHolder.clearContext();
				System.out.println("User session invalidated during token revocation.");
			}
		}

		// Proceed with the rest of the filter chain
		filterChain.doFilter(request, response);
	}
}
