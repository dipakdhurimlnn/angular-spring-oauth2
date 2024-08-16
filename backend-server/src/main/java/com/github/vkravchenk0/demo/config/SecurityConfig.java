package com.github.vkravchenk0.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.github.vkravchenk0.demo.filter.CsrfCookieFilter;
import com.github.vkravchenk0.demo.filter.CustomLoginFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	@Qualifier("DatabaseUserDetailsServiceImpl")
	private UserDetailsService userDetailsService;

	@Bean
	@Order(1)
	SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());

		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");
		http.securityContext((securityContext) -> securityContext.requireExplicitSave(true))
				.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
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
				}))
				.csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
						.ignoringRequestMatchers("/oauth2/**", "/contact", "/register")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new CustomLoginFilter(userDetailsService), CsrfFilter.class)
				.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
						new LoginUrlAuthenticationEntryPoint("/login"),
						new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))

				.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()))
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
		// chain would be invoked only for paths that start with /api/
		http.securityMatcher("/api/**")
				.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/test/unprotected").permitAll()
						.anyRequest().authenticated())
				// Ignoring session cookie
				.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()))
				// disabling csrf tokens for the sake of the example
				.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

//	@Bean
//	@Order(3)
//	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/test/unprotected",
//				// swagger ui paths
//				"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll().anyRequest().authenticated())
//				// Form login handles the redirect to the login page from the
//				// authorization server filter chain
//				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
//				// disabling csrf tokens for the sake of the example
//				.csrf(AbstractHttpConfigurer::disable).addFilterBefore(new JWTTokenValidatorFilter(userDetailsService),
//						UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails userDetails = User.withDefaultPasswordEncoder().username("user@example.com").password("password")
//				.roles("USER").build();
//
//		return new InMemoryUserDetailsManager(userDetails);
//	}

//	@Bean
//	public RegisteredClientRepository registeredClientRepository() {
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId("oidc-client")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.redirectUri("http://localhost:4200/auth/callback")
//				.clientSettings(
//						ClientSettings.builder().requireAuthorizationConsent(false).requireProofKey(true).build())
//				.build();
//
//		return new InMemoryRegisteredClientRepository(oidcClient);
//	}
//	@Bean
//	public RegisteredClientRepository registeredClientRepository() {
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId("oidc-client")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.redirectUri("http://localhost:4200/auth/callback")
//				.clientSettings(
//						ClientSettings.builder().requireAuthorizationConsent(false).requireProofKey(true).build())
//				.build();
//
//		jpaRegisteredClientRepository.save(oidcClient);
//
//		return jpaRegisteredClientRepository;
//	}

}