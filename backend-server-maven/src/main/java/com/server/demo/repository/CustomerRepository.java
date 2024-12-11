package com.server.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	List<Customer> findByEmail(String username);
}