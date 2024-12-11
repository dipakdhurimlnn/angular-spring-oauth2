package com.server.demo.repositoryImpl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.demo.entity.Customer;
import com.server.demo.repository.CustomerRepository;

@Service("DatabaseUserDetailsServiceImpl")
class DatabaseUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		System.err.println(user.get(0).getName());
		return org.springframework.security.core.userdetails.User.withUsername(user.get(0).getEmail())
				.password(user.get(0).getPwd()).authorities(user.get(0).getAuthorities()).build();
	}

	public void addUser(String username, String password, String... roles) {
		Customer user = new Customer();
		user.setName(username);
		user.setPwd(passwordEncoder.encode(password));
		user.setAuthorities(new HashSet(List.of(roles)));
		userRepository.save(user);
	}
}