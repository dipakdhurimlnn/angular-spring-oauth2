package com.spring.auth.server.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.spring.auth.server.demo.filter.SessionInvalidationFilter;
import com.spring.auth.server.demo.repositoryImpl.CustomClientRegistrationRepositoryImpl;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

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
		})).addFilterAfter(new SessionInvalidationFilter(), CsrfFilter.class)
				.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
						new LoginUrlAuthenticationEntryPoint("/login"),
						new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
				.oauth2ResourceServer((resourceServer) -> resourceServer.opaqueToken(Customizer.withDefaults()));

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/api/**")
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/api/test/unprotected", "/api/login", "/api/notices", "/api/contact")
						.permitAll().anyRequest().authenticated())
				.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer((resourceServer) -> resourceServer.opaqueToken(Customizer.withDefaults()))
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	@Order(3)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/test/unprotected", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
				.permitAll().anyRequest().authenticated()).formLogin(Customizer.withDefaults())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(customLogoutSuccessHandler()))
				.httpBasic(Customizer.withDefaults()).oauth2Client(withDefaults())
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new CustomClientRegistrationRepositoryImpl();
	}

	@Bean
	public LogoutSuccessHandler customLogoutSuccessHandler() {
		return (request, response, authentication) -> {
			response.sendRedirect("http://localhost:4200");
		};
	}

}