package com.spring.auth.server.demo.repositoryImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

public class CustomClientRegistrationRepositoryImpl implements ClientRegistrationRepository {

	private final Map<String, ClientRegistration> clientRegistrations = new HashMap<>();

	public CustomClientRegistrationRepositoryImpl() {
		clientRegistrations.put("oidc-client", googleClientRegistration());
	}

	@Override
	public ClientRegistration findByRegistrationId(String registrationId) {
		return clientRegistrations.get(registrationId);
	}

	private ClientRegistration googleClientRegistration() {
		return ClientRegistration.withRegistrationId("custom-client").clientId("oidc-client").clientSecret("password")
				.scope("openid", "profile", "email").authorizationUri("http://localhost:8080/oauth2/authorize")
				.tokenUri("http://localhost:8080/oauth2/token").userInfoUri("http://localhost:8080/userinfo")
				.jwkSetUri("http://localhost:8080/oauth2/jwks")
				.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}").clientName("Angular Client")
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).build();
	}

}
