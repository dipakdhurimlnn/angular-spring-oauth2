package com.github.vkravchenk0.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.vkravchenk0.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByEmail(String username);
}