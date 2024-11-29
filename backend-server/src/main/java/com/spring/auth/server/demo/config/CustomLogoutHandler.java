package com.spring.auth.server.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutHandler {

	private final OAuth2AuthorizationService authorizationService;

	public CustomLogoutHandler(OAuth2AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	public CustomLogoutHandler() {
		this.authorizationService = null;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String token = request.getHeader("Authorization");
		if (token != null && ((String) token).startsWith("Bearer ")) {
			token = ((String) token).substring(7);
			OAuth2Authorization authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
			if (authorization != null) {
				authorizationService.remove(authorization); // Invalidate token
			}
		}
	}
}
