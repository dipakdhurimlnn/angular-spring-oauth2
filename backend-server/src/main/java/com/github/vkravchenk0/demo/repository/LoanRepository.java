package com.github.vkravchenk0.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.vkravchenk0.demo.entity.Loans;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Integer> {

	// @PreAuthorize("hasRole('USER')")
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
