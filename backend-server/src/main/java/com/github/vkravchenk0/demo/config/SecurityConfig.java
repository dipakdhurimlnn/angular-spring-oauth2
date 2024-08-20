package com.github.vkravchenk0.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	@Order(1)
	SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());

		http.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowCredentials(true);
				config.addAllowedOriginPattern("*");
				config.addAllowedHeader("*");
				config.addAllowedMethod("*");
				config.addExposedHeader("Authorization");
				config.setMaxAge(366000L);
				return config;

			}
		})).exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
				new LoginUrlAuthenticationEntryPoint("/login"), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
				.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
		// chain would be invoked only for paths that start with /api/
		http.securityMatcher("/api/**")
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/api/test/unprotected", "/api/login", "/api/notices", "/api/contact")
						.permitAll().anyRequest().authenticated())
				// Ignoring session cookie
				.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()))
				// disabling csrf tokens for the sake of the example
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	@Order(3)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/test/unprotected",
				// swagger ui paths
				"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll().anyRequest().authenticated())
				// Form login handles the redirect to the login page from the
				// authorization server filter chain
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
				// disabling csrf tokens for the sake of the example
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}
}