package com.github.vkravchenk0.demo.repositoryImpl;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

public class CustomClientRegistrationRepositoryImpl implements ClientRegistrationRepository {

	@Override
	public ClientRegistration findByRegistrationId(String registrationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
