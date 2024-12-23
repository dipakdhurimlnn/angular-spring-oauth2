package com.server.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.demo.entity.Customer;
import com.server.demo.repository.CustomerRepository;
import com.server.demo.rest.model.RestCustomer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
			.getContextHolderStrategy();

	public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";

	@Autowired
	@Qualifier("DatabaseUserDetailsServiceImpl")
	private UserDetailsService userDetailsService;

	@PostMapping("/api/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
		Customer savedCustomer = null;
		ResponseEntity<String> response = null;
		try {
			String hashPwd = passwordEncoder.encode(customer.getPwd());
			customer.setPwd(hashPwd);
			customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
			savedCustomer = customerRepository.save(customer);
			if (savedCustomer.getId() > 0) {
				response = ResponseEntity.status(HttpStatus.CREATED)
						.body("Given user details are successfully registered");
			}
		} catch (Exception ex) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured due to " + ex.getMessage());
		}
		return response;
	}

	@PostMapping("/api/login")
	public ResponseEntity<RestCustomer> loginUser(@RequestBody Customer customer, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		ResponseEntity response = null;
		if (customer.getEmail().toLowerCase().contains("test")) {
			return response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login-failed");
		}
		List<Customer> customers = customerRepository.findByEmail(customer.getEmail());
		if (customers.size() > 0) {
			if (!passwordEncoder.matches(customer.getPwd(), customers.get(0).getPwd())) {
				return response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login-failed");
			}
		} else {
			return response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login-failed");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(customer.getEmail());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContext context = securityContextHolderStrategy.createEmptyContext();
		context.setAuthentication(authentication);
		securityContextRepository.saveContext(context, httpRequest, httpResponse);
		securityContextHolderStrategy.setContext(context);
		return response = ResponseEntity.status(HttpStatus.OK).body(new RestCustomer(customers.get(0)));
	}

	@GetMapping("/api/currentUser")
	public RestCustomer getUserDetailsAfterLogin(Authentication authentication) {
		List<Customer> customers = customerRepository.findByEmail(authentication.getName());
		if (customers.size() > 0) {
			return new RestCustomer(customers.get(0));
		} else {
			return null;
		}

	}

//	@PostMapping("/api/logout")
//	public ResponseEntity<String> logoutUser(@RequestBody Customer customer) {
//		ResponseEntity response = null;
//		SecurityContextHolder.getContext().setAuthentication(null);
//		SecurityContextHolder.clearContext();
//		return response = ResponseEntity.status(HttpStatus.OK).body("Success");
//	}

}
