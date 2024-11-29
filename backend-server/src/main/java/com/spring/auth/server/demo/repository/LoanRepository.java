package com.spring.auth.server.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.auth.server.demo.entity.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Integer> {

	// @PreAuthorize("hasRole('USER')")
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
